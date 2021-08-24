public class Event extends Task{
    private String when;

    public Event(String description, boolean isDone, String when) {
        super(description, isDone, 'E');
        this.when = when;
    }

    @Override
    public String checkStatus() {
        return super.checkStatus() + " " + this.showWhen();
    }

    public String showWhen(){
        return String.format("(at: %s)", this.when);
    }

    @Override
    public String toString(){
        return super.toString() + String.format("|%s", when);
    }
}
