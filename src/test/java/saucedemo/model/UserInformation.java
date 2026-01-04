package saucedemo.model;

public record UserInformation(String firstName, String lastName, String postalCode) {

    public static Builder builder() {
        return new Builder();
    }

    public static final class Builder {
        private String firstName;
        private String lastName;
        private String postalCode;

        public Builder firstName(String firstName) {
            this.firstName = firstName;

            return this;
        }

        public Builder lastName(String lastName) {
            this.lastName = lastName;

            return this;
        }

        public Builder postalCode(String postalCode) {
            this.postalCode = postalCode;

            return this;
        }

        public UserInformation build() {
            return new UserInformation(firstName, lastName, postalCode);
        }
    }
}
