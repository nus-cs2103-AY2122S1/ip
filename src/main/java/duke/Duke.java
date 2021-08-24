package duke;

import duke.commands.Command;
import java.util.Scanner;

public class Duke {
    private static final String LOGO = "Hello from\n____        _        \n"
        + "|  _ \\ _   _| | _____ \n"
        + "| | | | | | | |/ / _ \\\n"
        + "| |_| | |_| |   <  __/\n"
        + "|____/ \\__,_|_|\\_\\___|\n";
    private ItemList itemList;
    private Storage storage;
    private Ui ui;

    public static void main(String[] args) {
        new Duke();
    }

    public Duke() {
        this.storage = null;
        this.ui = new Ui();

        try {
            this.storage = new Storage("duke/data/duke.txt");
            this.itemList = this.storage.loadState();
        } catch (DukeException e) {
            System.out.println(e);
        }

        System.out.println(LOGO);
        this.run();
    }

    public void run() {
        Scanner sc = new Scanner(System.in);
        boolean isExit = false;
        String currLine;
        
        while (!isExit && sc.hasNextLine()) {
            try {
                currLine = sc.nextLine();
                Command currCommand = Parser.parse(currLine);
                currCommand.execute(this.itemList, this.ui);
                this.storage.saveState(this.itemList);
                isExit = currCommand.isExit();
            } catch (DukeException e) {
                this.ui.println(e.toString());
            }
        }
        sc.close();
    }
}
