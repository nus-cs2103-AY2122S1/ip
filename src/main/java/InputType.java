public enum InputType {

    BYE("bye"),
    LIST("list"),
    TODO("todo"),
    DEADLINE("deadline"),
    EVENT("event"),
    DONE("done");

    public final String str;

    private InputType(String str) {
        this.str = str;
    }

    public String getInputType() {
        return str;
    }


}
