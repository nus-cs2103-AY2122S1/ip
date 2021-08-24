/**
 * Encapsulates the creation of tasks found in the to-do list
 *
 * @author: Wei Yangken
 */
public class Task {
    private String name;
    private boolean isDone;
    private String taskCat;

    /**
     * Construct a generic task yet to be completed
     * @param name The name of the task
     */
    Task(String name, String taskCat, boolean isDone) {
        this.name = name;
        this.isDone = isDone;
        this.taskCat = taskCat;
    }

    /**
     * @return Category of task
     */
    public String getTaskCat() {
        return taskCat;
    }

    /**
     * @return The name of the task
     */
    public String getName() {
        return this.name.trim();
    }

    /**
     * @return Whether the task has been completed
     */
    public boolean isDone() {
        return this.isDone;
    }

    /**
     * Sets task to completed
     */
    public void setToCompleted() {
        this.isDone = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(Duke.breakline);
    }

    /**
     * Reformats the input given by the user to sort into a particular task type to be returned.
     * Checks for incorrectly formatted input from user to be caught as an exception.
     *
     * @param taskName The name of the task
     * @param taskType The type of task
     * @return A task of class taskType based on the command given
     */
    public static Task parseStringIntoTask(String taskName, String taskType, boolean isDone) {
        String split = "";
        try {
            if (taskName.equals("")) {
                String errorMsg = String.format("Oops!!! %s cannot be empty", taskType.toUpperCase());
                throw new DukeException.InsufficientArgumentsException(errorMsg);
            }
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Duke.breakline);
            return null;
        }

        try {

            switch (taskType) {
                case "todo":
                    return new Todo(taskName, isDone);
                case "deadline":
                    split = "/by ";
                    break;
                case "event":
                    split = "/at ";
                    break;
                default:
                    throw new DukeException.TaskTypeNotFoundException("TaskType cannot be found");
            }

            if (!taskName.contains(split)) {
                String errorMsg = String.format("Oops!!! %s cannot be found in %s.", split, taskType);
                throw new DukeException.InsufficientArgumentsException(errorMsg);
            }

            String[] nameNTime = taskName.split(split);
            String name = nameNTime[0];
            String time = nameNTime[1];
            Task task = null;

            if(taskType.equals("deadline")) {
                task = new Deadline(name, time, isDone);
            } else if (taskType.equals("event")) {
                task = new Event(name, time, isDone);
            }
            return task;
        } catch (DukeException e) {
            System.out.println(e.getMessage());
            System.out.println(Duke.breakline);
            return null;
        }

    }

    /**
     * @return Name of task
     */
    @Override
    public String toString() {
        return this.name;
    }

    /**
     * @return Gets addtional details of task
     */
    public String getDetail() {
        return "";
    }

}
