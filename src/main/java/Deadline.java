public class Deadline extends Task {
    private String time = "unknown";

    public Deadline(String name, String time) {
        super(name);
        this.time = time;
    }

    public static Deadline load(String[] loadData) {
        boolean done = loadData[1].equals("o");
        String name = loadData[2];
        String time = loadData[3];

        Deadline deadline = new Deadline(name, time);
        if (done) {
            deadline.doTask();
        }

        return deadline;
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + time + ")";
    }

    @Override
    public String getSaveString() {
        return "d," + super.getSaveString() + "," + time;
    }
}
