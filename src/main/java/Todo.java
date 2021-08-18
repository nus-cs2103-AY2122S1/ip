// tasks without any date/time attached to it
public class Todo extends Task {

    public Todo(String todo, String time) {
        super("[T]", todo, time);
    }
}
