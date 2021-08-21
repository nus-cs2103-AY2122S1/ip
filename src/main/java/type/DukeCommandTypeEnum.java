package type;

public enum DukeCommandTypeEnum {
    LIST("list"),
    FIND("find"),
    DONE("done"),
    DELETE("delete"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event");

    private final String value;

    DukeCommandTypeEnum(String value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }
}
