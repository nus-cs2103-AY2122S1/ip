public class Event extends Task {

    private String time;

    public Event (String todoName) {
        super(todoName.substring(6, todoName.indexOf("/at")));
        int start = todoName.indexOf("/at");
        this.time = todoName.substring(start + 4);
    }

    @Override
    public String toString() {
        return "[E]"
                + super.toString()
                + "(by: "
                + this.time
                + ")";
    }
}
