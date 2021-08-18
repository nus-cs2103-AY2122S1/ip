public class Event extends Task{
    private String when;

    public Event(String description, String when) {
        super(description);
        this.when = when;
    }

    @Override
    public String checkStatus() {
        return "[E]" + (isDone ? "[X]" : "[ ]") + " " + this.showDescription() + " " + this.showWhen();
    }

    public String showWhen(){
        return String.format("(at: %s)", this.when);
    }
}
