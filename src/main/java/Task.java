public abstract class Task {
    protected String description;
    protected boolean done;

    public Task(String input, boolean done) {
        description = input;
        this.done = done;
    }

    public boolean toggleDone() {
        done = !done;
        return done;
    }

    /**
     * String representation of Task
     *
     * @return task display
     */
    @Override
    public String toString() {
        String checkBox = done
                ? "[X] "
                : "[ ] ";
        return checkBox + description;
    }

    abstract String saveString();

    public static Task StringToTask(String task) {
        String[] args = task.split("\\t");
        String taskType = args[0];
        switch(taskType) {
            case "T":
                //To-do
                return new TaskTodo(args[2], args[1].equals("1"));
            case "D":
                return new TaskDeadline(args[2], args[3], args[1].equals("1"));
            case "E":
                return new TaskEvent(args[2], args[3], args[1].equals("1"));
            default:
                //throw exception
                return null;
        }
    }
}
