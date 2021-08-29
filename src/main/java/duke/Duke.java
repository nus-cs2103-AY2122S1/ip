package duke;

import duke.utils.DukeException;
import duke.utils.Record;

import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo =
                " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| " + "|_|" + " | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        /* Initialization */
        try {
            Scanner sc = new Scanner(System.in);
            Parser inputH = new Parser(true);
            Ui.reply(inputH.query("greet").msg());
            String input;
            while (true) {
                try {
                    input = sc.nextLine();
                    Record r = inputH.query(input);
                    Ui.reply(r.msg());
                    if (r.bye()) {
                        break;
                    }
                } catch (DukeException e) {
                    Ui.reply(e.getMessage());
                }
            }
        } catch (DukeException e) {
            Ui.reply(e.getMessage());
        }
    }
}
