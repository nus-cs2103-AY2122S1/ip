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

public class Duke {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    public Duke(String filePath) {
        storage = new Storage("data/Duke.txt");
        tasks = new TaskList(storage.loadFromFile());
        ui = new Ui();

    }

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
                    Task current = tasks.getTask(Integer.parseInt(parsedInputString[1]));
                    current.markAsDone();
                    System.out.println(ui.doneTaskMessage(current.toString()));
                    taskListIsUpdated = true;
                    continue;

                    // case where user wants to delete a task item, similar to done
                } else if (command.equals("delete")) {
                    Task current = tasks.getTask(Integer.parseInt(parsedInputString[1]));
                    System.out.println(ui.deletedTaskMessage(current.toString(), tasks.getLength()));
                    tasks.removeTask(current);
                    taskListIsUpdated = true;
                    continue;

                    // Case where user wants to see the entire task list
                } else if (command.equals("list")) {
                    System.out.println(ui.listMessage(tasks));
                    continue;

                    // Case where user exits the program
                } else if (command.equals("bye")) {
                    System.out.println(ui.byeMessage());
                    break;


                    // Case where user wants to add a new to do task
                } else if (command.equals("todo")) {
                    tasks.addTask(new Todo(parsedInputString[1]));
                    taskListIsAddedTo = true;

                    // Case where user wants to add a new event task
                } else if (nextTask.startsWith("event")) {
//                        for (String i : parsedInputString) {
//                        System.out.println(i);}
                    tasks.addTask(new Event(parsedInputString[1], parsedInputString[2]));
                    taskListIsAddedTo = true;

                    // Case where user wants to add a new deadline task
                } else if (nextTask.startsWith("deadline")) {
                    LocalDate date = LocalDate.parse(parsedInputString[2]);
                    LocalTime time = LocalTime.parse(parsedInputString[3]);
                    String deadlineDesc = parsedInputString[1]; //skip the "deadline "
                    tasks.addTask(new Deadline(deadlineDesc, date, time));
                    taskListIsAddedTo = true;

                    // Else case for all non-recognised user inputs
                } else {
                    throw new DukeException("Please enter a valid command");
                }

                if (taskListIsUpdated || taskListIsAddedTo) {

                    storage.saveToFile();

                    if (taskListIsAddedTo) {
                        // When adding a new task, this message be printed
                        System.out.println(ui.addTaskMessage(tasks));
                    }
                }


                // catch all the custom exceptions and displays the message
            } catch (DukeException e) {
                System.out.println(ui.dukeExceptionMessage(e));

                // catch the remaining exceptions
            } catch (Exception e) {
                System.out.println(ui.exceptionMessage(e));
            }
        }
    }

    public static void main(String[] args) {
        new Duke("data/Duke.txt").run();
    }
}
