import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import addon.*;


/**
 * A simple task tracking interface.
 */

public class Duke {
    Tasklist tasks;
    Parser parse;

    public Duke() {
        try {
            File saveFile = new File("./save.txt");
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
        System.out.println(Ui.bar + "\n    Hello! I'm SaDOS\n" +
                "    What can I do for you?\n" +
                "    Send \"bye\" to exit,\n" +
                "    I won't hold it against you\n" +
                "    Send \"help\" for help!\n" +
                Ui.bar);
        Scanner sc = new Scanner(System.in);
        do {
            String str = sc.nextLine();
            if (str.equalsIgnoreCase("bye")) {
                System.out.println(Ui.bar + "\n    Oh you've got to go?\n" +
                        "    Alright, I'll just be here...\n" +
                        "    Waiting....\n" +
                        "    You'll be back right?\n" +
                        Ui.bar);
                away = true;
            } else {
                try {
                    parse.updateList(str);
                } catch (Ui.IncorrectFormatException e){
                    e.printStackTrace();
                }
            }
        } while (!away);
    }


    public static void main(String[] args) {
        Duke currentlist = new Duke();
        currentlist.run();
    }
}