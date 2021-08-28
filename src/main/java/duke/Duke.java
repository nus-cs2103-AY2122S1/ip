package duke;

import java.io.IOException;

/**
 * This is Duke, my java bot!
 */
class Duke {
    private static final TaskList tasks = new TaskList();
    private static final Parser parser = new Parser(tasks);
    private static Storage storage;

    /**
     * Runs Duke, my java bot!
     *
     * @param args Input arguments, will be ignored
     */
    public static void main(String[] args) {
        Ui.show("Hello! I'm Duke", "What can I do for you?");
        loadStorage();
        while (true) {
            try {
                String command = Ui.nextLine();
                if (parser.isExit(command)) {
                    Ui.show("Bye. Hope to see you again soon!");
                    storage.close();
                    return;
                } else {
                    Ui.show(parser.parse(command));
                }
                storage.write(command);
            } catch (Exception e) {
                Ui.show(e.getMessage());
            }
        }
    }

    private static void loadStorage() {
        try {
            storage = new Storage("duke.txt");
            for (String command : storage.readAllLines()) {
                parser.parse(command);
            }
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
