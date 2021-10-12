package Du;

/**
 * Class for each individual task and a list of the tasks
 */
public class Task {
    protected String name;
    protected boolean isDone;
    protected TaskList taskList;

    /**
     * Public constructor to create a new task
     * @param name name of the task
     */
    public Task(String name, boolean done, TaskList tasklist) {
        this.name = name;
        this.isDone = done;
        this.taskList = tasklist;
        tasklist.addTask(this);
    }

    public String getName() {
        return this.name;
    }

    /**
     * Prints log for adding a task
     */
    public String log_add_task() {
        return "Got it. I've added this task:\n"
                + this + "\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";
    }

    public String log_add_recurring_task(int number_of_times, String frequency) {
        return "Got it. I've added this task:\n"
                + this + "\n" + frequency + " for " + number_of_times + " times\n"
                + "Now you have " + taskList.size() + " task(s) in the list.\n";
    }

    /**
     * Changes task's done state to be true
     */
    public String finish_task() {
        this.isDone = true;
        return "Nice! I've marked this task as done:\n"
                + this + "\n";
    }

    /**
     * @return state of task
     */
    public String getStatus() {
        return (isDone ? "X" : " ");
    }


    /**
     * records the Task in a certain format to save to the file
     * @return String which the Task is formatted in
     */
    public String log_record() {
        int state;
        if (this.isDone) {
            state = 1;
        } else {
            state = 0;
        }
        return "D , " + state + " , " + name;
    }

}
