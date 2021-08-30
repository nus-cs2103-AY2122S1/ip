import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import addon.Parser;
import addon.Storage;
import addon.Tasklist;
import addon.Ui;

/**
 * A simple task tracking interface.
 */

public class Sados {
    private Parser parse;

    /**
     * Sados constructor.
     */
    public Sados() {
        try {
            File saveFile = new File("./save.txt");
            Tasklist tasks;
            if (!saveFile.createNewFile()) {
                tasks = new Tasklist(Storage.loadFile(saveFile));
            } else {
                tasks = new Tasklist(new ArrayList<>());
            }
            parse = new Parser(tasks);
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * Starts off the interface.
     */
    public void run() {
        boolean away = false;
        System.out.println(Ui.BAR + "\n    Hello! I'm SaDOS\n" + "    What can I do for you?\n"
                + "    Send \"bye\" to exit,\n" + "    I won't hold it against you\n" + "    Send \"help\" for help!\n"
                + Ui.BAR);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println(Ui.BAR + "\n    Oh you've got to go?\n" + "    Alright, I'll just be here...\n"
                        + "    Waiting....\n" + "    You'll be back right?\n" + Ui.BAR);
                away = true;
            } else {
                try {
                    parse.updateList(str);
                } catch (Ui.IncorrectFormatException e) {
                    e.printStackTrace();
                }
            }
        } while (!away);
    }

    /**
     * Initialises a new Sados, and starts the program by calling run().
     *
     * @param args arguments to be passed in (none).
     */
    public static void main(String[] args) {
        Sados currentlist = new Sados();
        currentlist.run();
    }
}
