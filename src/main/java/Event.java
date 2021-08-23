public class Event extends Task{
    public static final String USAGE_TEXT = "Usage: event <task name> /at <deadline> ";
    String at;



    /**
     * default constructor for a new task
     * @param name task name
     * @param at time of event
     */
    Event(String name, String at){
        this(name, false, at);
    }

    /**
     * default constructor for a new task
     * @param name task name
     * @param done boolean state of task done
     * @param at time of event
     */
    Event(String name, boolean done, String at) {
        super(name);
        this.at = at;
    }

    public String serialize() {
        return "Task:event\n" +
                String.format("\tName:%s\n", this.name) +
                String.format("\tDone:%s\n", this.done) +
                String.format("\tAt:%s\n", this.at);
    }

    @Override
    public String toString() {
        return "[E]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(at:%s)",this.at);
    }
}