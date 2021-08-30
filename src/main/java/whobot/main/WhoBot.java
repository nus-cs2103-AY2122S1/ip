package whobot.main;

import java.io.File;
import java.util.Scanner;

import whobot.utils.Parser;
import whobot.utils.Storage;
import whobot.utils.TaskList;


/**
 * Main Driver Class
 */
public class WhoBot {

    /** Scanner for getting input from user */
    private static final Scanner CMD_READER = new Scanner(System.in);

    /** UI to output to */
    public final UI ui;

    /** Storage to store Task list in */
    private Storage storage;

    /** Parser to parse user input */
    private final Parser parser;

    /** Task List to store all tasks */
    private TaskList taskList;

    /***
     * Constructor Class for WhoBot Class
     */
    public WhoBot() {
        this.parser = new Parser();
        this.ui = new UI();
        try {
            this.storage = new Storage("." + File.separator + "data" + File.separator + "WhoBotData.txt");
            this.taskList = new TaskList(storage);
        } catch (WhoBotException ex) {
            ui.echo(ex.getMessage(), UI.Type.ERROR);
            System.exit(0);
        }
    }

    /***
     * Runs the WhoBot and Accepts Input from the User
     */
    public void run() {
        ui.greeting();
        while (true) {
            try {
                String command;
                System.out.print(UI.COLOR_PURPLE + "> " + UI.COLOR_RESET);
                command = CMD_READER.nextLine().trim();
                if (parser.parse(command, ui, storage, taskList) == -1) {
                    break;
                }
            } catch (WhoBotException ex) {
                ui.echo(ex.getMessage(), UI.Type.ERROR);
            }
        }
    }

    /***
     * Main Method
     *
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        WhoBot whoBot = new WhoBot();
        whoBot.run();
    }
}
