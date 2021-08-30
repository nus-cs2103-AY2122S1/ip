package bern;

import java.util.ArrayList;
import java.util.Scanner;

import bern.functionalities.Parser;
import bern.functionalities.Storage;
import bern.functionalities.TaskList;
import bern.functionalities.Ui;
import bern.model.Task;

/**
 * This class is where the main method resides.
 */
public class Bern {

    private Storage storage;
    private TaskList taskList;
    private Ui ui;
    private Parser parser;

    /**
     * Constructor for class bern.Bern.
     */
    public Bern() {
        ui = new Ui();
        storage = new Storage();
        taskList = new TaskList();
        parser = new Parser();
    }

    /**
     * Method that runs the programme from an instance of bern.Bern.
     */
    public void run() {
        Scanner myObj = new Scanner(System.in);

        ArrayList<Task> arListTask = new ArrayList<>();

        new Storage().initialiseArListTask(arListTask);

        System.out.println("Hi! I'm Bern, your trustworthy chatbot.\nWhat can I do for you?");

        while (true) {
            String input = myObj.nextLine();
            ui.processInput(input, arListTask);
            if (parser.isBye(input)) {
                break;
            }
        }
    }

    /**
     * Method that runs the programme.
     *
     * @param args Optional arguments that are passed to the main function.
     */
    public static void main(String[] args) {
        new Bern().run();
    }
}
