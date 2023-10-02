package com.microsoft.germany.csu.appinno.vaiidation.vaiidation.json;

import java.util.ArrayList;
import java.util.List;

public class GPTInput {

    private List<Task> tasks = new ArrayList<Task>();

    private String instruction;

    // Getters and setters for 'tasks' and 'instruction'
    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    public String getInstruction() {
        return instruction;
    }

    public void setInstruction(String instruction) {
        this.instruction = instruction;
    }

    // Inner class to represent individual tasks
    public static class Task {

        private String validationText;
        private String value;
        private String field;

        public Task(String fieldPrompt, String valueString, String name) {

            this.validationText = fieldPrompt;
            this.value = valueString;
            this.field = name;

        }

        // Getters and setters for 'validationText', 'value', and 'field'
        public String getValidationText() {
            return validationText;
        }

        public void setValidationText(String validationText) {
            this.validationText = validationText;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }
    }
}
