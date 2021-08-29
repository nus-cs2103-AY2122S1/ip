package duke;

import duke.utils.DukeException;
import duke.utils.Record;

import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private Parser inputH;

    public Duke() {
        try {
            sc = new Scanner(System.in);
            inputH = new Parser(true);
            Ui.reply(inputH.query("greet").msg());
        } catch (DukeException e) {
            Ui.reply(e.getMessage());
        }
    }
    
    public String getQuery() {
        if (sc.hasNext()) {
            return sc.nextLine();
        } else {
            return new String();
        }
    }
    
    public Record getResponse(String input) {
        try {
            Record r = inputH.query(input);
            return r;
        } catch (DukeException e) {
            return new Record(e.getMessage());
        }
    }
    
    public void mainLoop() {
        while (true) {
            String input = getQuery();
            Ui.reply(getResponse(input).msg());
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n" + "|  _ \\ _   _| | _____ \n" + "| | | | | | | |/ / _ \\\n" + "| " +
                "|_|" + " | |_| |   <  __/\n" + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Duke duke = new Duke();
    }
}
