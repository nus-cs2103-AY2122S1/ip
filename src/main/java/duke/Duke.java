package duke;

import java.io.IOException;

/**
 * This is Duke, my java bot!
 */
class Duke {
    private final TaskList tasks = new TaskList();
    private final Parser parser = new Parser(tasks);
    private Storage storage;

    public Duke() {
        try {
            storage = new Storage("duke.txt");
            for (String command : storage.readAllLines()) {
                parser.parse(command);
            }
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }

    /**
     * Runs Duke, my java bot!
     *
     * @param args Input arguments, will be ignored
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        Ui.show("Hello! I'm Duke", "What can I do for you?");
        while (true) {
            String[] result = duke.handleInput(Ui.nextLine());
            if (result != null) {
                Ui.show(result);
            } else {
                Ui.show("Bye. Hope to see you again soon!");
                return;
            }
        }
    }

    /**
     * Runs command and returns output
     *
     * @param command Command from user
     * @return Array of outputs to be shown to user, returns null if command is exit
     */
    public String[] handleInput(String command) {
        try {
            if (parser.isExit(command)) {
                storage.close();
                return null;
            }
            String[] out = parser.parse(command);
            storage.write(command);
            return out;
        } catch (Exception e) {
            return new String[]{e.getMessage()};
        }
    }

}
