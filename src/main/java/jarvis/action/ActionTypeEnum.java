package jarvis.action;

import jarvis.exception.UnknownActionException;

public enum ActionTypeEnum {
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    EXIT("bye"),
    LIST("list"),
    TODO("todo");

    private String actionTrigger;

    ActionTypeEnum(String actionTrigger) {
        this.actionTrigger = actionTrigger;
    }

    public int getActionTypeStringLength() {
        return actionTrigger.length();
    }

    public static ActionTypeEnum identifyActionType(String userInput) throws UnknownActionException {
        String actionTrigger = userInput.split(" ", 2)[0];

        for (ActionTypeEnum actionTypeEnum: ActionTypeEnum.values()) {
            if (actionTrigger.equals(actionTypeEnum.actionTrigger)) {
                return actionTypeEnum;
            }
        }

        throw new UnknownActionException(actionTrigger);
    }
}
