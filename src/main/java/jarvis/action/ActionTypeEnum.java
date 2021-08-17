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
}
