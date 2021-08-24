package duke;

import java.util.Scanner;

import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;
import duke.exception.WrongCommandFormatException;

/**
 * Class to handle all the user inputs.
 *
 * @author Houten Teo
 * @version CS2103T week 3
 */
public class Parser {
    private boolean isRunning;
    private MyList list;
    private Storage storage;

    /**
     * Constructor for the class
     * @param list The list that the parser would be updating.
     * @param storage the storage to read and write the data.
     */
    public Parser(MyList list, Storage storage) {
        this.isRunning = true;
        this.list = list;
        this.storage = storage;
    }

    /**
     * Method to check if the parser is still running.
     * @return true if its running and false otherwise.
     */
    public boolean isRunning() {
        return this.isRunning;
    }

    /**
     * Method to read the inputs of the user.
     */
    public void readInput() {

        Scanner s = new Scanner(System.in);
        String input = s.next();

        if (!input.equals("bye")) {
            if (input.equals("list")) {
                this.list.listAll();
            } else if (input.equals("done")) {
                Scanner s1 = new Scanner(s.nextLine());
                int counter = 0;
                while (s1.hasNextInt()) {
                    int index = s1.nextInt();
                    this.list.markComplete(index);
                    this.storage.writeToFile();
                    counter ++;
                }
                if (counter == 0) {
                    Ui.invalidIndexMessage();
                }
            } else if (input.equals("todo")) {
                Scanner s2 = new Scanner(s.nextLine());
                String description = "";
                while (s2.hasNextLine()) {
                    description = s2.nextLine();
                }
                try {
                    Todo newTodo = new Todo(description, false);
                    this.list.addTask(newTodo);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    Ui.formatExceptionMessage(e);
                }

            } else if (input.equals("deadline")) {
                Scanner s3 = new Scanner(s.nextLine());
                String description = "";
                while (s3.hasNextLine()) {
                    description = s3.nextLine();
                }
                try {
                    Deadline newDeadline = new Deadline(description, false);
                    this.list.addTask(newDeadline);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    Ui.formatExceptionMessage(e);
                }
            } else if (input.equals("event")) {
                Scanner s4 = new Scanner(s.nextLine());
                String description = "";
                while (s4.hasNextLine()) {
                    description = s4.nextLine();
                }
                try {
                    Event newEvent = new Event(description, false);
                    this.list.addTask(newEvent);
                    this.storage.writeToFile();
                } catch (WrongCommandFormatException e) {
                    Ui.formatExceptionMessage(e);
                }
            } else if (input.equals("delete")) {
                Scanner s5 = new Scanner(s.nextLine());
                if (s5.hasNextInt()) {
                    int index = s5.nextInt();
                    this.list.deleteTask(index);
                    this.storage.writeToFile();
                } else {
                    Ui.invalidIndexMessage();
                }
            } else if (input.equals("setFormat")) {
                Scanner s6 = new Scanner(s.nextLine());
                if (s6.hasNextLine()) {
                    try {
                        Duke.setFormat(s6.nextLine().substring(1));
                        Ui.formatUpdatedMessage();
                        this.storage.writeToFile();
                    } catch (IllegalArgumentException e) {
                        Ui.unacceptableFormatMessage();
                    }
                }
            } else if (input.equals("format")) {
                Ui.currentDateFormatMessage();
            } else {
                Ui.noSpecificCmdMessage();
            }
        } else {
            Ui.botShutdownMessage();
            this.isRunning = false;
        }

    }
}
