package duke;

import java.io.IOException;

public class Duke {
    static TaskList tasks = new TaskList();
    static Parser parser = new Parser(tasks);
    static Storage storage;

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

    static void loadStorage() {
        try {
            storage = new Storage("duke.txt");
            for (String command : storage.readAllLines())
                parser.parse(command);
        } catch (IOException | DukeException e) {
            e.printStackTrace();
        }
    }
}
