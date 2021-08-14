public enum Command {
    ADD,
    LIST,
    DONE,
    EXIT;

    /**
     * @param input Command issued by user in String format.
     * @return Corresponding command.
     */
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
