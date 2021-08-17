import java.util.Optional;

public class Deadline extends Task{

    private Deadline(String description, String deadline) {
        super(description, "D", deadline);
    }

    public static Deadline create(Optional<String> description, Optional<String> time) throws DukeExceptions{
        String desc = description.orElseThrow(() -> new DukeExceptions(
                "Oops, deadline command requires a description \n"
        ));
        String timeMod = time.orElseThrow(()-> new DukeExceptions(
                "Oops, deadline command requires a set time \n"
        ));
        return new Deadline(desc, timeMod);
    }

    @Override
    public String toString() {
        return '[' + this.taskType + ']' + '[' + getStatusIcon() + ']' + ' ' + this.description
                + String.format("(by: %s)", this.time);
    }
}
