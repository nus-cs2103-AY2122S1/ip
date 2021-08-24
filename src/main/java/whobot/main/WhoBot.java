package whobot.main;

import java.io.File;
import java.util.*;
import whobot.utils.Storage;
import whobot.utils.Parser;
import whobot.utils.TaskList;

public class WhoBot {

    // The Global Variables used by the ChatBot
    private static final Scanner CMD_READER = new Scanner(System.in);
    public final UI UI;
    private Storage STORAGE;
    private final Parser PARSER;
    private TaskList TASK_LIST;

    public WhoBot() {
        this.PARSER = new Parser();
        this.UI = new UI();
        try {
            this.STORAGE = new Storage("." + File.separator + "data" + File.separator + "WhoBotData.txt");
            this.TASK_LIST = new TaskList(STORAGE);
        } catch (WhoBotException ex) {
            UI.echo(ex.getMessage(), whobot.main.UI.TYPE.ERROR);
            System.exit(0);
        }
    }


    public void run() {
        UI.greeting();
        while (true) {
            try {
                String command;
                System.out.print(whobot.main.UI.COLOR_PURPLE + "> " + whobot.main.UI.COLOR_RESET);
                command = CMD_READER.nextLine().trim();
                if (PARSER.parse(command, UI, STORAGE, TASK_LIST) == -1) {
                    break;
                };
            } catch (WhoBotException ex) {
                UI.echo(ex.getMessage(), whobot.main.UI.TYPE.ERROR);
            }
        }
    }

    //Main Method
    public static void main(String[] args) {
        WhoBot whoBot = new WhoBot();
        whoBot.run();
    }
}
