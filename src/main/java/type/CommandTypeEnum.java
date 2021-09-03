package type;

public enum CommandTypeEnum {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    LIST("list"),
    TODO("todo");

    private final String value;

    CommandTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
