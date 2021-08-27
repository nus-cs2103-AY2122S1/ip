package duke;

import duke.parser.Parser;

public class Duke {

    public static void main(String[] args) {
        Parser parser = new Parser();
        parser.initialiseDuke();
        parser.runDuke();
    }


}