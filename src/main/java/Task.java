public class Task {
    private String task;
    private Boolean isDone;
    private String statusIcon = " ";
    private String taskSymbol;
    private String date;

    /**
     * Constructor for Events and Deadlines which have a date
     * @param task Deadline or Event.
     * @param taskSymbol D or T.
     * @param date Date associated with task.
     */
    public Task(String task, String taskSymbol, String date) {
        this.task = task;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
        this.date = date;
    }

    /**
     * Constructor for tasks of type todo which do not have a date.
     * @param task Todo type task.
     * @param taskSymbol T.
     */
    public Task(String task, String taskSymbol) {
        this.task = task;
        this.isDone = false;
        this.taskSymbol = taskSymbol;
        this.date = "";
    }

    /**
     * Representation of task in the from that it is stored in the data file.
     * @return String of the task in the form required by teh data file.
     */
    public String getDataRep() {
        String status;
        String dataToDel;
        if(this.isDone) {
            status = "1";
        } else {
            status = "0";
        }

        if(this.taskSymbol.equals("T")) {
            dataToDel = String.format("%s,%s,%s", this.getTaskType(), status, this.task);

        } else {
            dataToDel = String.format("%s,%s,%s,%s", this.getTaskType(), status, this.task, this.date);
        }
        return dataToDel;
    }

    /**
     * Get the symbol of the task which is the first letter of the task type.
     * @return Upper-case first letter of task type.
     */
    public String getTaskSymbol() {
        return this.taskSymbol;
    }

    /**
     * Getter for the type of task - todo, event and deadline.
     * @return Task type.
     */
    public String getTaskType() {
        if(this.taskSymbol.equals("T")) {
            return "todo";
        } else if (this.taskSymbol.equals("D")) {
            return "deadline";
        } else {
            return "event";
        }
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
    public String toString(){
        return String.format("[%s] %s", this.statusIcon, this.task);
    }
}
