package duke;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

/**
 * The main class for the bot
 * Contains the methods 'to do', 'deadline', 'event', 'list',
 * 'delete' and 'bye'
 */
public class Duke {
    private static String format;
    private MyList list;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().start();
    }

    /**
     * Method to get the current format setting.
     * @return the DateTimeFormatter
     */
    public static String getFormat() {
        return format;
    }

    public static void setFormat(String newFormat) throws IllegalArgumentException {
        DateTimeFormatter.ofPattern(newFormat);
        format = newFormat;
    }

    public Duke() {
        this.list = new MyList();
        this.storage = new Storage(this.list);
    }

    /**
     * Method to start the bot and waits for the user's input.
     */
    public void start() {

        Ui.welcomeMessage();

        this.storage.load();

        Parser p = new Parser(this.list, this.storage);

        while (p.isRunning()) {
            Scanner s = new Scanner(System.in);
            String command = s.nextLine();
            p.readInput(command);
        }
    }
}
