/**
 * Encapsulates the creation of tasks found in the to-do list
 *
 * @author: Wei Yangken
 */
public class Task {
    private String name;
    private boolean done;

    /**
     * Construct a generic task yet to be completed
     * @param name The name of the task
     */
    Task(String name) {
        this.name = name;
        this.done = false;
    }

    /**
     * @return The name of the task
     */
    public String getName() {
        return this.name;
    }

    /**
     * @return Whether the task has been completed
     */
    public boolean isDone() {
        return this.done;
    }

    /**
     * Sets task to completed
     */
    public void setToCompleted() {
        this.done = true;
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(this);
        System.out.println(Duke.breakline);
    }

    /**
     * Reformats the input given by the user to sort into a particular task type to be returned.
     * Checks for incorrectly formatted input from user to be caught as an exception.
     *
     * @param taskName The name of the task
     * @param split The string that it is split by e.g "/by" or "/at"
     * @param taskType The type of task
     * @return A task of class taskType based on the command given
     */
    public static Task parseStringIntoTask(String taskName, String split, String taskType) {
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
            if (split.equals("") && taskType.equals("todo")) {
                return new Todo(taskName);
            } else {
                if (!taskName.contains(split)) {
                    String errorMsg = String.format("Oops!!! %s cannot be found in %s.", split, taskType);
                    throw new DukeException.InsufficientArgumentsException(errorMsg);
                }

                String[] nameNTime = taskName.split(split);
                String name = nameNTime[0];
                String time = nameNTime[1];
                Task task;

                if(taskType.equals("deadline")) {
                    task = new Deadline(name, time);
                } else if (taskType.equals("event")) {
                    task = new Event(name, time);
                } else {
                    throw new DukeException.TaskTypeNotFoundException("TaskType cannot be found");
                }
                return task;
            }
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


}
