package pilcrow;

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

    public void printTaskAddedMessage(Task task, TaskList taskList) {
        System.out.println("Added \"" + task + "\" to list of tasks(" + taskList.getNumberOfTasks() + ")");
    }

    public void printTaskList(TaskList taskList) {
        System.out.println(taskList.toString());
    }

    public void printSetTaskIsDoneMessage(Task task, TaskList taskList) {
        System.out.println("\"" + task + "\" set as " + (task.getIsDone() ? "done." : "not done"));
    }

    public void printDeleteTaskMessage(int index) {
        System.out.println("Task " + index + " has been deleted.");
    }

    public void printUnacceptedCommandMessage() {
        InvalidInputException exception = new InvalidInputException("Must enter accepted command.");
        this.printException(exception);
    }

    public void printException(Exception exception) {
        System.out.println(exception);
    }

    public void printGoodbyeMessage() {
        System.out.println("C'est fini.");
        System.out.println(Ui.PILCROW_LOGO);
    }

}