public enum Command {
    ADD,
    LIST,
    DONE,
    EXIT;

    public static Command convertInput(String input) {
        String lowerCaseInput = input.toLowerCase();
        Command result;
        switch (lowerCaseInput) {
            case "list":
                result = Command.LIST;
                break;
            case "done":
                result = Command.DONE;
                break;
            case "exit":
                result = Command.EXIT;
                break;
            default:
                result = Command.ADD;
                break;
        }

        return result;
    }
}
