package pilcrow;

// Abstraction to handle interactions with the user. Usually just prints text out.
public class Ui {
    private final static String PILCROW_LOGO = """
              _____
             /   | |
            |    | |
            |    | |
             \\___| |
                 | |
                 | |
                _| |_
               |_|_|_|
            """;

    /**
     * Prints out a "Task Added" message given a Task and TaskList.
     * @param task Recently added Task.
     * @param taskList Existing TaskList, containing the recently added Task.
     */
    public void printTaskAddedMessage(Task task, TaskList taskList) {
        System.out.println("Added \"" + task + "\" to list of tasks(" + taskList.getNumberOfTasks() + ")");
    }

    /**
     * Prints out a TaskList, given a TaskList.
     * @param taskList TaskList to be printed out.
     */
    public void printTaskList(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    /**
     * Prints out a "Task Set as Done" message, given that Task and the TaskList.
     * @param task Task recently set as done.
     * @param taskList TaskList containing the Task recently set as done.
     */
    public void printSetTaskIsDoneMessage(Task task, TaskList taskList) {
        System.out.println("\"" + task + "\" set as " + (task.getIsDone() ? "done." : "not done"));
    }

    /**
     * Prints out a "Deleted Task" message, given the index of the deleted task.
     * @param index Integer index of the deleted task, whose count starts at 1.
     */
    public void printDeleteTaskMessage(int index) {
        System.out.println("Task " + index + " has been deleted.");
    }

    /**
     * Prints out an "Unaccepted Command" message.
     * Used when the user enters a command that is not recognized.
     */
    public void printUnacceptedCommandMessage() {
        InvalidInputException exception = new InvalidInputException("Must enter accepted command.");
        this.printException(exception);
    }

    /**
     * Prints out an exception message.
     * @param exception Exception to be printed out.
     */
    public void printException(Exception exception) {
        System.out.println(exception);
    }

    /**
     * Prints out a goodbye message.
     * Used when the user exits the program.
     */
    public void printGoodbyeMessage() {
        System.out.println("C'est fini.");
        System.out.println(Ui.PILCROW_LOGO);
    }

}