package com.microsoft.germany.csu.appinno.vaiidation.vaiidation;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import com.azure.ai.openai.OpenAIClient;
import com.azure.ai.openai.models.ChatCompletions;
import com.azure.ai.openai.models.ChatCompletionsOptions;
import com.azure.ai.openai.models.ChatMessage;
import com.azure.ai.openai.models.ChatRole;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.annotations.Vaiidation;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.annotations.VaiidationField;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.json.GPTInput;
import com.microsoft.germany.csu.appinno.vaiidation.vaiidation.json.GPTOutput;

@Component
public class GPTValidation implements Validator {

    @Autowired
    private OpenAIClient client;

    @Value("${azure.openai.deploymentmodelid}")
    String deploymentModelId;

    @Override
    public boolean supports(Class<?> clazz) {
        return clazz.isAnnotationPresent(Vaiidation.class);
    }

    @Override
    public void validate(Object target, Errors errors) {

        List<ChatMessage> chatMessages = new ArrayList<>();

        GPTInput inputJSON = new GPTInput();
        inputJSON.setInstruction(
                "Process the validation tasks and generate a JSON response containing a list of result objects with properties of field, value, validation, valid. Each result object should indicate whether the validation rule is valid or not using the valid property (true and false), and if not, include a reason property.");

        Class<?> clazz = target.getClass();
        if (clazz.isAnnotationPresent(Vaiidation.class)) {

            for (Field field : clazz.getDeclaredFields()) {
                if (field.isAnnotationPresent(VaiidationField.class)) {

                    VaiidationField vaiidationField = field.getAnnotation(VaiidationField.class);
                    String fieldPrompt = vaiidationField.validationPrompt();

                    field.setAccessible(true);
                    Object value = null;
                    try {
                        value = field.get(target);
                    } catch (IllegalArgumentException | IllegalAccessException e) {
                        e.printStackTrace();
                    }

                    // parse value to string
                    String valueString = value.toString();

                    inputJSON.getTasks().add(new GPTInput.Task(fieldPrompt, valueString, field.getName()));

                }
            }
        }

        // map content to ResultJSON
        ObjectMapper mapper = new ObjectMapper();

        String jsonString = "";
        // map inputJSON to string
        try {
            jsonString = mapper.writeValueAsString(inputJSON);
        } catch (Exception e) {
            e.printStackTrace();
        }

        chatMessages.add(new ChatMessage(ChatRole.SYSTEM, jsonString));

        ChatCompletions chatCompletions = client.getChatCompletions(deploymentModelId,
                new ChatCompletionsOptions(chatMessages));

        String content = chatCompletions.getChoices().get(0).getMessage().getContent();

        GPTOutput resultJSON = null;
        try {
            resultJSON = mapper.readValue(content, GPTOutput.class);
        } catch (Exception e) {
            e.printStackTrace();
        }

        // create errors based on result for validation
        for (GPTOutput.Result result : resultJSON.results) {
            if (!result.getValid()) {
                errors.rejectValue(result.getField(), result.getReason());
            }
        }

    }

}
