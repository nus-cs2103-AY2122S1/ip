package duke;

public enum InputType {

    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    FIND("find"),
    HIGH("high"),
    DONE("done");

    public final String str;

    InputType(String str) {
        this.str = str;
    }

    public String getInputType() {
        return str;
    }


}
