package util.tasks;

import java.time.LocalDate;

public class Deadlines extends DatedTask {
    private static String label = "[D]";

    public Deadlines(String s, LocalDate dl) {
        super(s.trim(), dl);

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
}
