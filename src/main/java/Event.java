// tasks that start at a specific time and ends at a specific time
public class Event extends Task {

    public Event(String todo, String time) {
        super("[E]", todo, time);
    }
}
