package duke;

import duke.command.Command;

import java.util.Scanner;

/**
 * Duke is a personal assistant that keep a list of your tasks. Users
 * are able to add different types of tasks to their own list as well as
 * mark tasks as completed or delete tasks from their task list.
 *
 * @author leezhixuan
 */

public class Duke {

    private static String name = "Duke";
    private boolean isRunning = false;
    private ToDoList tdl;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    private Duke() {
        this.ui = new Ui(Duke.name);
        this.ui.greet();
        this.tdl = new ToDoList(Duke.name);
        this.storage = new Storage(this.tdl);
        this.storage.reloadTask();
        this.parser = new Parser();
    }

    private void startBot() {
        this.isRunning = true;
        Scanner input = new Scanner(System.in);
        while (this.isRunning) {
            String command = input.nextLine();
            Command c = this.parser.parse(command, this.tdl, this.ui, this, this.storage);
            c.execute();
        }
    }

    public static String getName() {
        return name;
    }

    /**
     * Stops the chat bot.
     */
    public void stopRunning() {
        this.isRunning = false;
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.startBot();
    }
}