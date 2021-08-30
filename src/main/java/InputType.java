public enum InputType {

    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DELETE("delete"),
    DONE("done");

    public final String str;

    InputType(String str) {
        this.str = str;
    }

    public String getInputType() {
        return str;
    }


}
