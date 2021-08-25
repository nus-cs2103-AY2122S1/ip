package duke;

import duke.command.Command;

import java.util.Scanner;

public class Duke {

    private static String name = "Duke";
    private boolean isRunning = false;
    private ToDoList tdl;
    private Storage storage;
    private Ui ui;
    private Parser parser;

    public Duke() {
        this.ui = new Ui(Duke.name);
        this.ui.greeting();
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

    public void exit(ToDoList tdl) {
        this.isRunning = false;
        System.out.println("========== " + Duke.name + " ===========");
        System.out.println("Wow! I can get off work now :D");
        this.storage.save();
        System.out.println("Saved your work by the way!");
        System.out.println("========== " + Duke.name + " ===========\n");
    }

    public static String getName() {
        return name;
    }

    public void stopRunning() {
        this.isRunning = false;
    }

    public static void main(String[] args) {
        Duke chatBot = new Duke();
        chatBot.startBot();
    }
}