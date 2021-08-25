package duke;

import java.util.Scanner;
import commands.Command;
import tasks.*;
import exceptions.*;

public class Duke {

    private Ui ui;
    private TaskList taskList;
    private Storage store;
    private boolean isRunning;

    Duke() {
        this.ui = new Ui();
        this.taskList = new TaskList();
        this.store = new Storage("data/tasks.txt");
        this.isRunning = false;
    }

    public void run() {
        this.isRunning = true;
        this.taskList = new TaskList(this.store.readFile());

        Scanner sc = new Scanner(System.in);

        this.ui.greet();

        while (this.isRunning) {
            String input = sc.nextLine();
            commands.Command c = this.ui.parseInput(input);
            this.ui.printSepLine();
            c.execute(this.ui, this.taskList, this.store);
            this.ui.printSepLine();
            this.isRunning = !c.isQuit();
        }

        sc.close();
    }

    public static void main(String[] args) {
        Duke d = new Duke();
        d.run();
    }
}
