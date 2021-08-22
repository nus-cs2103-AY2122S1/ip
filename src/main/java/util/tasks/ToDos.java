package util.tasks;

public class ToDos extends Task {
    private static String label = "[T]";
    public ToDos(String s) {
        super(s.trim());

    }

    public static Task of(String s) {
        return new ToDos(s);
    }

    @Override
    public String toString() {
        return this.label + super.toString();
    }

    @Override
    public String encode() {
        //String indicating whether this task is done or not.
        String d = this.isDone()
                ? Task.DONE
                : Task.NOTDONE;

        return Task.Label.T + Task.DELIMITER + d + Task.DELIMITER + this.name;
    }
}
