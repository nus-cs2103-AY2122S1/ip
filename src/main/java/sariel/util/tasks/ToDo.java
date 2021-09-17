package sariel.util.tasks;

public class ToDo extends Task {


    private static String label = "[T]";

    private ToDo(String s) {
        super(s.trim());

    }

    /**
     * The factory method of the todo class
     *
     * @param s The name of the todo task.
     * @return The Task with the corresponding name.
     */
    public static Task of(String s) {
        assert s != null : "String for Todo creation is null";
        return new ToDo(s);
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

    @Override
    public boolean equals(Object obj) {

        if (obj instanceof ToDo) {
            ToDo t = (ToDo) obj;
            return t.name.equals(this.name);
        }
        return false;

    }
}
