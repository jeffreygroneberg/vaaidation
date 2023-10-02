package com.microsoft.germany.csu.appinno.vaiidation.vaiidation.json;

//** // {"results":[ { "field":"address", "value":"1234", "validation":"Must be an address", "result":"failed", "reason":"Invalid address format" }, { "field":"city", "value":"London", "validation":"Must be a city in Germany", "result":"failed", "reason":"City is not in Germany" } ]} */
public class GPTOutput {

    public GPTOutput() {
        this.results = new Result[0];
    }

    public GPTOutput(Result[] results) {
        this.results = results;
    }

    public Result[] results;

    public static class Result {

        public Result() {
        }

        public Result(String field, String value, String validation, boolean valid, String reason) {

            this.field = field;
            this.value = value;
            this.validation = validation;
            this.valid = valid;
            this.reason = reason;

        }

        private String field;
        private String value;
        private String validation;
        private boolean valid;
        private String reason;

        public String getField() {
            return field;
        }

        public void setField(String field) {
            this.field = field;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public String getValidation() {
            return validation;
        }

        public void setValidation(String validation) {
            this.validation = validation;
        }

        public boolean getValid() {
            return valid;
        }

        public void setValid(boolean valid) {
            this.valid = valid;
        }

        public String getReason() {
            return reason;
        }

        public void setReason(String reason) {
            this.reason = reason;
        }

    }

}
