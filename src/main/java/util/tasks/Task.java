/**
 * Class of a task to be done.
 *
 */
package util.tasks;

public abstract class Task {

    /**
     * The method to check if the Task is
     * dated or not.
     * @return Whether this task is dated.
     */
    public boolean isDated() {
        return false;
    }

    /**
     * Method to encode the task as a String.
     * Format - {type of task}{DELIMITER}{DONE/NOTDONE}{DELIMITER}{TASKNAME}{Extra information}
     *
     *
     * @return The encoded task;
     */
    public abstract String encode();




    protected static enum Label {
        T, E, D;
    }

    protected String name;
    private boolean isDone;
    //what is a better choice for a delimiter
    protected static String DELIMITER = "/ghx-124";
    protected static String DONE = "DONETASKe123111";
    protected static String NOTDONE = "NOTDONETASK454e-e";
    private static String notDone = "[ ]";
    private static String done = "[X]";


    /**
     * Constructor of task.
     *
     * @param s Name of the task.
     */
    protected Task(String s) {
        this.name = s;
        this.isDone = false;
    }



    /**
     * Marks when the task is done.
     */
    public void done() {
        this.isDone = true;
    }

    /**
     * Obtains the corresponding task from the string input.
     *
     * @param s The input String.
     * @return The task the string represents.
     */
    public static Task decode(String s) throws DukeException {
        String[] ssplit = s.split(DELIMITER, 4);
        Label currentType = Label.valueOf(ssplit[0]);
        boolean done = ssplit[1].equals(Task.DONE);
        Task t;
        switch (currentType) {
            //For todo
            case T:
                t = ToDo.of(ssplit[2]);
                break;
            case E:
                t = Event.of(ssplit[2], ssplit[3]);
                break;
            case D:
                t = Deadline.of(ssplit[2], ssplit[3]);
                break;
            default:
                throw new DukeException("Not a valid text document");
        }
        if (done) {
            t.done();
        }
        return t;
    }

    /**
     * Whether this task is done or not.
     *
     * @return true if the task is done and false otherwise.
     */
    public boolean isDone() {
        return this.isDone;
    }


    /**
     * Checks if the name contains the input string as a word.
     *
     * @param s Input word
     * @return True when the input string is in the name.
     */
    public boolean contains(String s) {
        String[] arr = this.name.split(" ");
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equals(s)) {
                return true;
            }
        }
        return false;

    }

    @Override
    public String toString() {
        String checkBox = this.isDone
                ? Task.done
                : Task.notDone;
        return checkBox + " " + this.name;
    }



    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task) {
            Task t = (Task) obj;
            return t.name.equals(this.name);
        }
        return false;
    }


}