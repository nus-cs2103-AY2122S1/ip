package duke;

import java.util.Scanner;

import duke.command.Command;


/**
 *  This is my attempt at Duke!
 * @author A0222594A
 *
 */

public class Duke {

    private UI ui;
    private Parser parser;
    private TaskList list;
    private final Scanner sc = new Scanner(System.in);

    /**
     * Constructor for Duke
     */
    public Duke(String filePath) {
        ui = new UI();
        Data data = new Data(filePath);
        list = new TaskList(data.loadData(), data);
        parser = new Parser(list, data);
    }


    /**
     * You should have your own function to generate a response to user input.
     * Replace this stub with your completed method.
     */
    String getResponse(String input) {
        Command command = parser.inputToCommand(input);
        return command.getResponse(input);
    }

}
