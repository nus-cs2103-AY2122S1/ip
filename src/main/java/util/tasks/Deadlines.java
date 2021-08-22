package util.tasks;

import java.time.LocalDate;

public class Deadlines extends DatedTask {
    private static String label = "[D]";
    private static final String DELIMITER = "/by";
    private static final String NOINPUTERRORMESSAGE = "☹ OOPS!!! The deadline must be filled in prefixed by /by";


    private Deadlines(String s, String dl) {
        super(s.trim(), dl.trim());

    }

    public static Deadlines of(String s) throws DukeException {
        String[] ss = s.split(Deadlines.DELIMITER, 2);
        if (ss.length == 0) throw new DukeException(NOINPUTERRORMESSAGE);
        return new Deadlines(ss[0], ss[1]);
    }


    public static Deadlines of(String name, String date) throws DukeException {
        return new Deadlines(name, date);
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
