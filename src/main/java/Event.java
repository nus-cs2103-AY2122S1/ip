public class Event extends Task{
    private String when;

    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String checkStatus() {
        return (isDone ? "[E]" : "[]" + " " + this.showDescription());
    }

    public String showWhen(){
        return String.format("(%s)", this.when);
    }
}
