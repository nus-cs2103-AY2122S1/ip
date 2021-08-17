import java.util.Optional;

public class Event extends Task{

    private Event(String description, String time) {
        super(description, "E", time);
    }

    public static Event create(Optional<String> description, Optional<String> time) throws DukeExceptions{
        String desc = description.orElseThrow(() -> new DukeExceptions(
                "Oops, event command requires a description \n"
        ));
        String timemod = time.orElseThrow(()-> new DukeExceptions(
                "Oops, event command requires a set time \n"
        ));
        return new Event(desc, timemod);
    }

    @Override
    public String toString() {
        return "[" + this.taskType + "]" + "[" + getStatusIcon() + "]" + " " + this.description
                + String.format("(at: %s)", this.time);
    }
}
