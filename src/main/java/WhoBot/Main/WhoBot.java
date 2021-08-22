package WhoBot.Main;

import java.io.File;
import java.util.*;
import WhoBot.Utils.Storage;
import WhoBot.Utils.Parser;
import WhoBot.Utils.TaskList;

public class WhoBot {

    // The Global Variables used by the ChatBot
    private static final Scanner cmdReader = new Scanner(System.in);
    public final UI ui;
    private Storage storage;
    private final Parser parser;
    private TaskList taskList;

    public WhoBot() {
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.storage = new Storage("." + File.separator + "data" + File.separator + "WhoBotData.txt");
            this.taskList = new TaskList(storage);
        } catch (WhoBotException ex) {
            ui.echo(ex.getMessage(), UI.TYPE.ERROR);
            System.exit(0);
        }
    }


    public void run() {
        ui.greeting();
        while (true) {
            try {
                String command;
                System.out.print(UI.COLOR_PURPLE + "> " + UI.COLOR_RESET);
                command = cmdReader.nextLine().trim();
                if (parser.parse(command, ui, storage, taskList) == -1) {
                    break;
                };
            } catch (WhoBotException ex) {
                ui.echo(ex.getMessage(), UI.TYPE.ERROR);
            }
        }
    }

    //Main Method
    public static void main(String[] args) {
        WhoBot whoBot = new WhoBot();
        whoBot.run();
    }
}
