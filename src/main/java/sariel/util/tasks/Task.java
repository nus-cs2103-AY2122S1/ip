/**
 * Class of a task to be done.
 *
 */
package sariel.util.tasks;

import java.time.LocalDate;

public abstract class Task {

    protected static final String DELIMITER = "/ghx-124";
    protected static final String DONE = "DONETASKe123111";
    protected static final String NOTDONE = "NOTDONETASK454e-e";
    private static final String NOT_FINISHED = "[ ]";
    private static final String FINISHED = "[X]";
    protected String name;
    private boolean isDone;

    protected enum Label {
        T, E, D;
    }

    /**
     * Constructor of task.
     *
     * @param s Name of the task.
     */
    protected Task(String s) {
        assert s != null : "Nullpointer when creating task description";
        this.name = s;
        this.isDone = false;
    }

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
        Task t = getTask(ssplit, currentType, done);
        return t;
    }

    private static Task getTask(String[] ssplit, Label currentType, boolean done) throws DukeException {
        Task t;
        switch (currentType) {
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

    /**
     * Checks if the name contains the input string as a
     * pattern, not case sensitive.
     *
     * @param s Input word.
     * @return True when the input string pattern is in the name.
     */
    public boolean containsPattern(String s) {
        String test = s.toLowerCase().trim();
        String base = this.name.toLowerCase().trim();
        for (int i = 0; i < base.length() - s.length() + 1; i++) {
            if (test.equals(base.substring(i, i + test.length()))) {
                return true;
            }
        }
        return false;
    }

    /**
     * The date that this task is limited by.
     *
     * @return The local date object representing that date.
     */
    public LocalDate getDate() {
        throw new NullPointerException("Cannot get date from Task");
    }



    @Override
    public String toString() {
        String checkBox = this.isDone
                ? Task.FINISHED
                : Task.NOT_FINISHED;
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
