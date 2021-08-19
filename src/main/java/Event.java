public class Event extends Task{
    private String at;

    public static Task create(String userParams) {
        String[] userParamsSplit = userParams.split(" /", 2);
        String description = userParamsSplit[0];
        String at = "";
        if(userParamsSplit[1].startsWith("at")) {
            at = userParamsSplit[1].replaceFirst("at", "");
        }
        return new Event(description, at);
    }

    private Event(String description, String at) {
        super(description);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (at:" + at + ")";
    }
}
