public class Event extends Task{
    private String at;

    public Event (String description, String at){
        super(description);
        this.at = at;
    }

    public String getAt(){
        return at;
    }

    @Override
    public String showTask() {
        return "[E][" + (isDone ? "✗" : " ") + "] " + description + "(at: " + at + ")";
    }
}