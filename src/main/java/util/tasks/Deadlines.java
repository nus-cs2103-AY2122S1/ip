package util.tasks;

public class Deadlines extends Task {
    private static String label = "[D]";
    private String dl;
    public Deadlines(String s, String dl) {
        super(s.trim());
        this.dl = "(by: " + dl.trim() + ")";
    }

    @Override
    public String toString() {
        return this.label + super.toString() + " " + this.dl;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Deadlines) {
            Deadlines dl = (Deadlines) obj;
            return this.name.equals(dl.name) && this.dl.equals(dl.dl);
        }
        return false;
    }

    @Override
    public String encode() {
        //String indicating whether this task is done or not.
        String d = this.isDone()
                ? Task.DONE
                : Task.NOTDONE;

        return Task.Label.T + Task.DELIMITER + d + Task.DELIMITER + this.name + Task.DELIMITER + this.dl;
    }
}
