package duke;

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public static boolean done = false;

    /**
     * initialises Duke
     * @param filePath
     */

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadTasks());
    }

    private static void endBot() {
        done = true;
        System.out.println("Bye for now!");
    }

    /**
     * runs the Duke program
     */

    public void run() {
        while (!done) {
            String input = ui.getInput();
            if (input.equals("bye")) {
                endBot();
            } else {
                tasks.action(input);
                storage.saveTasks(tasks.output());
            }
        }
    }

    public static void main(String[] args) {
        System.out.println("Sup! I'm Luka, your personal assistant.\n");
        new Duke("data/tasks.txt").run();
    }
}
