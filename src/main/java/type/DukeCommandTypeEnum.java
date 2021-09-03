package type;

public enum DukeCommandTypeEnum {
    BYE("bye"),
    DEADLINE("deadline"),
    DELETE("delete"),
    DONE("done"),
    EVENT("event"),
    FIND("find"),
    LIST("list"),
    TODO("todo");

    private final String value;

    DukeCommandTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
