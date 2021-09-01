package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.TaskList;

import java.util.Scanner;

/**
 *fw duke.Duke class
 */
public class Duke {

    private final Parser parser;
    private final TaskList taskList;
    private final Storage storage;
    private boolean isBye;

    /**
     * Basic Constructor
     *
     * @throws DukeException
     */
    public Duke(){
        storage = new Storage("src/main/java/resources");
        taskList = new TaskList();
        parser = new Parser(storage, taskList);
        parser.loadTask();
    }

    /**
     * duke program that prints in commandLine
     */
    private void textProcess() {

        boolean isBye = false;
        Scanner scan = new Scanner(System.in);
        while (!isBye) {
            String input = scan.nextLine();
            try {
                Command c = parser.parse(input);
                System.out.println(c.exec());
                isBye = c.checkIsBye();
            } catch (DukeException e) {
                System.out.println(e.toString());
            }
        }
    }



    public String guiProcess(String input) {
        String output = "";
        try {
            Command c = parser.parse(input);
            output = c.exec();
            isBye = c.checkIsBye();
        } catch (DukeException e) {
            return e.toString();
        }
        return output;
    }

    public boolean checkIsBye() {
        return this.isBye;
    }


    /**
     * Main program
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.textProcess();
    }
}
