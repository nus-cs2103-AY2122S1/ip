package whobot.main;

import java.io.File;
import java.util.Locale;
import java.util.Scanner;

import javafx.application.Application;
import whobot.utils.Parser;
import whobot.utils.Storage;
import whobot.utils.TaskList;


/**
 * Main Driver Class
 */
public class WhoBot {

    /** To Enable/Disable GUI */
    private static boolean isGui = true;

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
            String filename = "." + File.separator + "data" + File.separator + "WhoBotData.txt";
            this.storage = new Storage(filename);
            this.taskList = new TaskList(storage);
        } catch (WhoBotException ex) {
            ui.echo(ex.getMessage(), UI.Type.ERROR);
            System.exit(0);
        }
    }

    /***
     * Gets whether this app is running in GUI mode
     * @return isGUI status
     */
    public static boolean isGui() {
        return isGui;
    }

    /***
     * Runs the WhoBot and Accepts Input from the User
     */
    public void run() {
        if (isGui) {
            Application.launch(Gui.class);
        } else {
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
    }

    /***
     * Main Method
     *
     * @param args Commandline arguments
     */
    public static void main(String[] args) {
        if (args.length > 0 && args[0].toLowerCase(Locale.ROOT).equals("--cli")) {
            WhoBot.isGui = false;
        }
        WhoBot whoBot = new WhoBot();
        whoBot.run();
    }
}
