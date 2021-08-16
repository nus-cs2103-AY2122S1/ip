public class Event extends Task{
    String at;
    Event(String name, String at){
        super(name);
        this.at = at;
    }

    @Override
    public String toString() {
        return "[E]" + (this.done? "[X] " : "[ ] ")
                + this.name
                +String.format("(at:%s)",this.at);
    }
}
