package util.tasks;

import java.time.LocalDate;

public class Deadlines extends DatedTask {
    private static String label = "[D]";

    public Deadlines(String s, LocalDate dl) {
        super(s.trim(), dl);

    }
    public Deadlines(String s, String dl) {
        super(s.trim(), LocalDate.parse(dl.trim()));

    }

    @Override
    public String toString() {
        return this.label + super.toString() + " " + "(by: " + this.localDate() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadlines) {
            Deadlines dl = (Deadlines) obj;
            return this.name.equals(dl.name) && this.lDate.equals(dl.lDate);
        }
        return false;
    }

    @Override
    public String encode() {
        //String indicating whether this task is done or not.
        String d = this.isDone()
                ? Task.DONE
                : Task.NOTDONE;

        return Task.Label.D + Task.DELIMITER + d + Task.DELIMITER + this.name + Task.DELIMITER + this.lDate;
    }
}
