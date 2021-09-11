package duke;

import duke.command.Command;

import duke.exception.DukeException;

import duke.util.Parser;
import duke.util.Storage;
import duke.util.StorageCsv;
import duke.util.TaskList;

import java.util.Scanner;

/**
 *fw duke.Duke class
 */
public class Duke {

    private final TaskList taskList = new TaskList();;
    private final Storage storage = new StorageCsv("src/main/java/savedOutput");;
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
//        Duke duke = new Duke();
//        duke.textProcess();

    }

    //TEsting
    public static String[] convertTaskDetailsToCsvRow(String taskDetails) {
        String[] csvRow = new String[5];
        String[] parsedInput = taskDetails.trim().split(" ", 6);
        return parsedInput;
    }


}
