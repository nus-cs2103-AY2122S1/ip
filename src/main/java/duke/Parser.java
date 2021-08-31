package duke;

import duke.exception.DukeException;

import java.io.IOException;
import java.util.Scanner;

/**
 * A class that reads user input and gives commands accordingly.
 *
 */
public class Parser {
    Scanner sc;
    TaskList list;
    UI ui;
    Storage storage;

    /**
     * Constructor of a parser.
     *
     * @param list A TaskList which contains all the current tasks.
     * @param ui An Ui object which prints out messages to users.
     * @param storage A Storage object which deals with opening and loading file to the program.
     */
    public Parser(TaskList list, UI ui, Storage storage) {
        sc = new Scanner(System.in);
        this.list = list;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * A method to read task from user input and call commands accordingly.
     *
     * @throws IOException Thrown when file cannot be found.
     */
    public void readTask() throws IOException {
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                list.printList();
            } else if (input.startsWith("done")) {
                try {
                    list.markAsDone(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                } catch (NumberFormatException e) {
                    ui.printErrorMessage("Task number is invalid!");
                }
            } else if (input.startsWith("delete")) {
                try {
                    list.deleteTask(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            } else if (input.startsWith("find")) {
                try {
                    list.findTask(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            } else {
                try {
                    list.addTask(input);
                } catch (DukeException e) {
                    ui.printErrorMessage(e.getMessage());
                }
            }
            storage.updateFile(list);
            input = sc.nextLine();
        }

        ui.printByeMessage();
        sc.close();
    }
}
