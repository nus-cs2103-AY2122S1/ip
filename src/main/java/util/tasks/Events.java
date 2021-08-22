package util.tasks;

import java.time.LocalDate;

public class Events extends DatedTask {
    private static String label = "[E]";
    private static final String NOINPUTERRORMESSAGE = "☹ OOPS!!! The event deadline must be filled in prefixed by /at";
    private Events(String name, LocalDate date) {
        super(name.trim(), date);

    }



    private Events(String name, String date) {
        super(name.trim(), LocalDate.parse(date.trim()));

    }

    public static Events of(String total) throws DukeException {
        String[] ss = total.split("/at", 2);
        if (ss.length == 0) throw new DukeException();
        return new Events(ss[0], ss[1]);
    }

    public static Events of(String name, String date) throws DukeException {
        return new Events(name, date);
    }



    @Override
    public String toString() {
        return this.label + super.toString() + " (at: " + this.localDate() + ")";
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Events) {
            Events e = (Events) obj;
            return this.name.equals(e.name) && e.lDate.equals(this.lDate);
        }
        return false;
    }

    @Override
    public String encode() {
        //String indicating whether this task is done or not.
        String d = this.isDone()
                ? Task.DONE
                : Task.NOTDONE;

        return Task.Label.E + Task.DELIMITER + d + Task.DELIMITER + this.name + Task.DELIMITER + this.lDate;
    }
}
