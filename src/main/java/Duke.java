public class Duke {
    private static final String ENDING_COMMAND = "bye";

    private Storage storage;
    private TaskList taskList = new TaskList();

    public Duke(String filePath) {
        storage = new Storage(filePath);
        storage.readTasks(taskList);
    }

    public void run() {
        Ui.say("Hello! I'm Iris. What can I do for you?");
        String command = Ui.prompt();
        while (!command.equals(ENDING_COMMAND)) {
            try {
                Parser.handleCommand(command, taskList);
            } catch (IrisException exception) {
                Ui.sayError(exception);
            }
            storage.writeTasks(taskList);
            command = Ui.prompt();
        }

        Ui.say("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args) {
        new Duke("src/data.txt").run();
    }
}
