//Level-4 -> A-Inheritance: Event Task Class
public class Event extends Task {

    public Event(String description, String at, TASK_TYPE type) {
        super(description,at,type);
    }

    @Override
    public String toString() {
        return super.toString().concat(" (at: ".concat(this.at).concat(")"));
    }
}