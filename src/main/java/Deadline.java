// tasks that need to be done before a specific date/time
public class Deadline extends Task {

    public Deadline(String todo, String time) {
        super("[D]", todo, time);
    }
}
