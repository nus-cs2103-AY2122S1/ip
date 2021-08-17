package jarvis.action;

public enum ActionTypeEnum {
    DEADLINE("deadline"),
    DONE("done"),
    EVENT("event"),
    EXIT("bye"),
    LIST("list"),
    TODO("todo");

    private final String actionTrigger;

    ActionTypeEnum(String actionTrigger) {
        this.actionTrigger = actionTrigger;
    }

    public int getActionTypeStringLength() {
        return actionTrigger.length();
    }

    public static ActionTypeEnum identifyActionType(String userInput) {
        String actionTrigger = userInput.split(" ", 2)[0];

        for (ActionTypeEnum actionTypeEnum: ActionTypeEnum.values()) {
            if (actionTrigger.equals(actionTypeEnum.actionTrigger)) {
                return actionTypeEnum;
            }
        }

        return ActionTypeEnum.EXIT;
    }
}
