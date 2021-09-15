package duke;

import duke.command.Command;
import duke.exception.DukeException;
import duke.util.*;

import java.util.Scanner;

/**
 *fw duke.Duke class
 */
public class Duke {

    private final TaskList taskList = new TaskList();
    private final Storage storage = new StorageTxt("./data");
    private final Parser parser = new Parser(storage, taskList);
    private boolean isBye;

    /**
     * Basic Constructor
     *
     * @throws DukeException
     */
    public Duke(){
        parser.loadTask();
    }

    /**
     * Saves the tasklist during a closure event
     *
     */
    public void saveOnClosed() {
        try {
            this.storage.saveUpdateTask(taskList);
        } catch (DukeException e) {
            System.out.println(e.toString());
        }
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

    /**
     * duke program that passes output to the GUI
     */
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

    /**
     * checks if the
     *
     * @return
     */
    public boolean checkIsBye() {
        return this.isBye;
    }


    /**
     * Main program Duke in commandline output mode
     *
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.textProcess();
    }

}
