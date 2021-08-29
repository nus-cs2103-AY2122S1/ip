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
            String command = Ui.nextLine();
            if (duke.isExit(command)) {
                Ui.show("Bye. Hope to see you again soon!");
                duke.exit();
                return;
            } else {
                Ui.show(duke.handleInput(command));
            }
        }
    }


    public String[] handleInput(String command) {
        try {
            String[] out = parser.parse(command);
            storage.write(command);
            return out;
        } catch (Exception e) {
            return new String[]{e.getMessage()};
        }
    }

    public boolean isExit(String command) {
        return parser.isExit(command);
    }

    public void exit() {
        try {
            storage.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
