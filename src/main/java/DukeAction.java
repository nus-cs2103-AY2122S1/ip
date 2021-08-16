public abstract class DukeAction {
    public DukeAction() {}

    public static DukeActionTypeEnum getActionType(String message) throws NonExistentActionTypeException {
        String trimmedMessage = message.trim();
        String[] messagesSplitUsingSpace = trimmedMessage.split(" ");
        String commandWord = messagesSplitUsingSpace[0];

        for (DukeActionTypeEnum actionType : DukeActionTypeEnum.values()) {
            int actionTypeLength = actionType.toString().length();
            if (commandWord.length() >= actionTypeLength && commandWord.equals(actionType.toString())) {
                return actionType;
            }
        }

        throw new NonExistentActionTypeException(message);
    }

    public static String removeCommandPrefix(String message, String command) {
        return message.trim().substring(command.length());
    }

    public static int getTaskNumberFromMessage(String message) throws InvalidTaskNumberException {
        try {
            String trimmedMessage = message.trim();
            return Integer.parseInt(trimmedMessage);
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException(message);
        }
    }

    public static DukeAction makeAction(String inputMessage)
            throws InvalidTaskNumberException, NonExistentActionTypeException {
        DukeActionTypeEnum actionType = getActionType(inputMessage);
        String messageWithoutCommand = removeCommandPrefix(inputMessage, actionType.toString());
        String trimmedMessage = messageWithoutCommand.trim();
        switch (actionType) {
            case LIST:
                return DukeActionList.createAction();
            case DONE:
                return DukeActionDone.createAction(trimmedMessage);
            case DELETE:
                return DukeActionDelete.createAction(trimmedMessage);
            default:
                return DukeActionAdd.createAction(trimmedMessage, actionType);
        }
    }

    public abstract void executeAction(DukeTaskList list) throws DukeException;

    public abstract DukeOutputMessage getOutputMessage();
}
