package duke;

import java.time.LocalTime;
import java.util.Scanner;
import java.time.LocalDate;

import duke.task.Task;
import duke.task.Todo;
import duke.task.Deadline;
import duke.task.Event;
import duke.data.Storage;
import duke.data.TaskList;
import duke.user.Ui;
import duke.user.Parser;
import duke.user.DukeException;

/**
 * Main class for the Duke program
 */
public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Constructor for the Duke instance
     *
     * @param filePath the String representing the path of the file where the data is saved
     */
    public Duke(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFromFile());
        ui = new Ui();
    }

    /**
     * Method which begins the majority of the program execution
     */
    public void run() {
        boolean taskListIsUpdated = false;
        boolean taskListIsAddedTo = false;

        // Init the scanner
        Scanner in = new Scanner(System.in);

        // user inputs will go to parser
        Parser parser = new Parser(tasks);

        while (true) {
            String nextTask = in.nextLine();  // Read user input

            try {
                String[] parsedInputString = parser.checkInput(nextTask);
                String command = parsedInputString[0];

                // Case where user marks a task as done
                if (command.equals("done")) {
                    Task current = tasks.getTask(Integer.parseInt(parsedInputString[1]) - 1);
                    current.setIsDone();
                    System.out.println(ui.displayDoneTaskMessage(current.toString()));
                    taskListIsUpdated = true;

                    // case where user wants to delete a task item, similar to done
                } else if (command.equals("delete")) {
                    Task current = tasks.getTask(Integer.parseInt(parsedInputString[1]) - 1);
                    tasks.removeTask(current);
                    System.out.println(ui.displayDeletedTaskMessage(current.toString(), tasks.getLength()));
                    taskListIsUpdated = true;

                    // Case where user wants to see the entire task list
                } else if (command.equals("list")) {
                    System.out.println(ui.displayListMessage(tasks));
                    continue;

                    // Case where user exits the program
                } else if (command.equals("bye")) {
                    System.out.println(ui.displayByeMessage());
                    break;

                    // Case where user wants to add a new to do task
                } else if (command.equals("todo")) {
                    tasks.addTask(new Todo(parsedInputString[1]));
                    taskListIsAddedTo = true;

                    // Case where user wants to add a new event task
                } else if (command.equals("event")) {
                    tasks.addTask(new Event(parsedInputString[1], parsedInputString[2]));
                    taskListIsAddedTo = true;

                    // Case where user wants to add a new deadline task
                } else if (command.equals("deadline")) {
                    LocalDate date = LocalDate.parse(parsedInputString[2]);
                    LocalTime time = LocalTime.parse(parsedInputString[3]);
                    String deadlineDesc = parsedInputString[1]; //skip the "deadline "
                    tasks.addTask(new Deadline(deadlineDesc, date, time));
                    taskListIsAddedTo = true;

                    // Case where user wants to find a keyword
                } else if (command.equals("find")) {
                    String keyword = parsedInputString[1];
                    System.out.println(ui.findMessage(tasks.findTask(keyword)));
                    continue;

                    // Else case for all non-recognised user inputs
                } else {
                    throw new DukeException("Please enter a valid command");
                }

                // If the program reaches this point, meaning no continue or break was hit, it means
                // there was an update to the file, and we can save the file
                storage.saveToFile();
                if (taskListIsAddedTo) {
                    // When adding a new task, this message be printed
                    System.out.println(ui.displayAddTaskMessage(tasks));
                }

                // catch all the custom exceptions and displays the message
            } catch (DukeException e) {
                System.out.println(ui.displayDukeExceptionMessage(e));

                // catch the remaining exceptions
            } catch (Exception e) {
                System.out.println(ui.displayExceptionMessage(e));
            }
        }
    }

    /**
     * Main method to start the whole program
     *
     * @param args NIL
     */
    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}
