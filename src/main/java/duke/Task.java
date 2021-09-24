package duke;

import java.time.LocalDateTime;


public class Task {
    private String task;
    private Boolean isDone;
    private String statusIcon = " ";
    private final String taskSymbol;
    private LocalDateTime date;
    private Utility utility;

    /**
     * Constructor for Events and Deadlines which have a date
     * @param task Deadline or duke.Event.
     * @param taskSymbol D or T.
     * @param date Date associated with task.
     */
    public Task(String task, String taskSymbol, LocalDateTime date) {
        assert dateToString(date).length() != 0;

        this.task = task;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
        this.date = date;
        this.utility = new Utility();
    }

    /**
     * Constructor for tasks of type todo which do not have a date.
     *
     * @param task Todo type task.
     * @param taskSymbol T.
     */
    public Task(String task, String taskSymbol) {
        this.task = task;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
    }

    /**
     * Represents task in the from that it is stored in the data file.
     *
     * @return String of the task in the form required by teh data file.
     */
    public String getDataRep() {
        String status;
        String data;
        if (this.isDone) {
            status = "1";
        } else {
            status = "0";
        }

        if (this.taskSymbol.equals("T")) {
            data = String.format("%s,%s,%s", this.getTaskType(), status, this.task);
        } else {
            data = String.format("%s,%s,%s,%s", this.getTaskType(), status,
                    this.task, utility.dateToInputString(this.date));
        }
        return data;
    }

    /**
     * Gets the symbol of the task which is the first letter of the task type.
     *
     * @return Upper-case first letter of task type.
     */
    public String getTaskSymbol() {
        return this.taskSymbol;
    }

    /**
     * Gets the type of task - todo, event and deadline.
     *
     * @return String representation of task type.
     */
    public String getTaskType() {
        if (this.taskSymbol.equals("T")) {
            return "todo";
        } else if (this.taskSymbol.equals("D")) {
            return "deadline";
        } else {
            return "event";
        }
    }

    public String dateToString(LocalDateTime dateTime) {
        return utility.dateToString(dateTime);
    }

    /**
     * Sets task to done.
     */
    public void setDone() {
        this.isDone = true;
        this.statusIcon = "X";
    }

    /**
     * Check if task is done.
     * @return True if task is done and False otherwise.
     */
    public Boolean checkStatus() {
        return isDone;
    }

    /**
     * Checks task status and returns String representation of the task and its status.
     * @return String of the task and its status.
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", this.statusIcon, this.task);
    }
}
