public enum DukeCommandArgumentType {
    REQUIRED, OPTIONAL, NOT_ALLOWED;

    @Override
    public String toString() {
        switch (this) {
            case REQUIRED -> {
                return "required";
            }
            case OPTIONAL -> {
                return "optional";
            }
            case NOT_ALLOWED -> {
                return "not allowed";
            }
            default -> {
                return null;
            }
        }
    }
}
