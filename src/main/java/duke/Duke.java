package duke;

import duke.utils.DukeException;
import duke.utils.Record;

import java.util.Scanner;

public class Duke {
    private Scanner sc;
    private Parser inputH;

    public Duke() {
        this("");
    }
    
    public Duke(String dbFileName) {
        try {
            sc = new Scanner(System.in);
            inputH = dbFileName.equals("") ? new Parser(true) : 
                    new Parser(true, dbFileName);
            Ui.reply("Loading Duke...");
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
            Record r = getResponse(input);
            Ui.reply(r.msg());
            if (r.bye()) {
                break;
            }
        }
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        Ui.reply(duke.getResponse("greet").msg());

        duke.mainLoop();
    }
}
