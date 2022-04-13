package pilcrow;

/**
 * Abstraction to handle interactions with the user. Usually just prints text out.
 */
public class Ui {
    private static final String PILCROW_LOGO_FIRST = "              _____\n             /   | |\n            |    | |\n";
    private static final String PILCROW_LOGO_SECOND = "            |    | |\n             \\___| |\n                 | |\n";
    private static final String PILCROW_LOGO_THIRD = "                 | |\n                _| |_\n               |_|_|_|\n";
    private static final String PILCROW_LOGO = PILCROW_LOGO_FIRST + PILCROW_LOGO_SECOND + PILCROW_LOGO_THIRD;

    /**
     * Prints out a "Task Added" message given a Task and TaskList.
     * @param task Recently added Task.
     * @param taskList Existing TaskList, containing the recently added Task.
     */
    public String printTaskAddedMessage(Task task, TaskList taskList) {
        String text = "Added \"" + task + "\" to list of tasks(" + taskList.getNumberOfTasks() + ")";
        System.out.println(text);
        return text;
    }

    /**
     * Prints out a TaskList, given that TaskList.
     * @param taskList TaskList to be printed out.
     */
    public String printTaskList(TaskList taskList) {
        String text = taskList.toString();
        System.out.println(text);
        return text;
    }

    /**
     * Prints out a "Task Set as Done" message, given that Task and the TaskList.
     * @param task Task recently set as done.
     * @param taskList TaskList containing the Task recently set as done.
     */
    public String printSetTaskIsDoneMessage(Task task, TaskList taskList) {
        String text = "\"" + task + "\" set as " + (task.getIsDone() ? "done." : "not done");
        System.out.println(text);
        return text;
    }

    /**
     * Prints out a "Deleted Task" message, given the index of the deleted task.
     * @param index Integer index of the deleted task, whose count starts at 1.
     */
    public String printDeleteTaskMessage(int index) {
        String text = "Task " + index + " has been deleted.";
        System.out.println(text);
        return text;
    }

    public String printTaskListSortedMessage() {
        String text = "List of tasks is sorted.";
        System.out.println(text);
        return text;
    }

    /**
     * Prints out an "Unaccepted Command" message.
     * Used when the user enters a command that is not recognized.
     */
    public String printUnacceptedCommandMessage() {
        InvalidInputException exception = new InvalidInputException("Must enter accepted command.");
        this.printException(exception);
        return exception.toString();
    }

    /**
     * Prints out an exception message.
     * @param exception Exception to be printed out.
     */
    public String printException(Exception exception) {
        System.out.println(exception);
        return exception.toString();
    }

    /**
     * Prints out a goodbye message.
     * Used when the user exits the program.
     */
    public String printGoodbyeMessage() {
        String text = "C'est fini.\n" + Ui.PILCROW_LOGO;
        System.out.println(text);
        return text;
    }
}