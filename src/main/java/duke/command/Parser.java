package duke.command;

import java.time.format.DateTimeFormatter;

import duke.exception.DukeException;
import duke.io.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

/**
 * Parser class encapsulates a parser within the Duke bot that processes
 * user inputs. The parser takes in a command from the user, processes it
 * and returns a description of the command.
 */
public class Parser {
    private String input;
    private TaskList taskList;
    private Storage storage;
    private Ui ui;

    /**
     * Constructor for a Parser
     *
     * @param input user input in the form of a command
     * @param taskList list of tasks
     * @param storage text file to read and write from
     * @param ui user interface containing messages to return to the user
     */
    public Parser(String input, TaskList taskList, Storage storage, Ui ui) {
        this.input = input;
        this.taskList = taskList;
        this.storage = storage;
        this.ui = ui;
    }

    /**
     * Initializes a new Event task, writes the task to the text file,
     * and returns a full description of the Event task.
     *
     * @param taskDescription a short description of the Event task
     * @param tList list of tasks
     * @return a full description of the Event task
     * @throws ArrayIndexOutOfBoundsException
     */
    public String newEvent(String taskDescription, TaskList tList) throws ArrayIndexOutOfBoundsException {
        Event newEvent = new Event(taskDescription);
        tList.addTask(newEvent);

        String textToAppend = newEvent.showType() + " | "
                + ((newEvent.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newEvent.showTaskOnly() + " | "
                + newEvent.showWhen() + "\n";

        try {
            this.storage.save(textToAppend, true);
        } catch (DukeException de) {
            return de.toString();
        }

        return this.ui.deadlineOrEventAddedMessage(
                newEvent.showType(), newEvent.checkDone(), newEvent.showTaskOnly(),
                newEvent.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                newEvent.showTime(), taskList);
    }

    /**
     * Initializes a new Deadline task, writes the task to the text file,
     * and returns a full description of the Deadline task.
     *
     * @param taskDescription a short description of the Deadline task
     * @param tList list of tasks
     * @return a full description of the Deadline task
     * @throws ArrayIndexOutOfBoundsException
     */
    public String newDeadline(String taskDescription, TaskList tList) throws ArrayIndexOutOfBoundsException {
        Deadline newDeadline = new Deadline(taskDescription);
        tList.addTask(newDeadline);

        String textToAppend = newDeadline.showType() + " | "
                + ((newDeadline.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newDeadline.showTaskOnly() + " | "
                + newDeadline.showWhen() + "\n";

        try {
            this.storage.save(textToAppend, true);
        } catch (DukeException de) {
            return de.toString();
        }

        return this.ui.deadlineOrEventAddedMessage(
                newDeadline.showType(), newDeadline.checkDone(), newDeadline.showTaskOnly(),
                newDeadline.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")),
                newDeadline.showTime(), taskList);
    }

    /**
     * Initializes a new ToDo task, writes the task to the text file,
     * and returns a full description of the ToDo task.
     *
     * @param taskDescription a short description of the ToDo task
     * @param tList list of tasks
     * @return a full description of the ToDo task
     * @throws ArrayIndexOutOfBoundsException
     */
    public String newToDo(String taskDescription, TaskList tList) throws ArrayIndexOutOfBoundsException {
        ToDo newToDo = new ToDo(taskDescription);
        tList.addTask(newToDo);

        String textToAppend = newToDo.showType() + " | "
                + ((newToDo.checkDone()).equals("[X]") ? "1" : "0") + " | "
                + newToDo.showTask() + "\n";
        try {
            this.storage.save(textToAppend, true);
        } catch (DukeException de) {
            return de.toString();
        }

        return this.ui.todoAddedMessage(
                newToDo.showType(), newToDo.checkDone(), newToDo.showTask(), this.taskList);
    }

    /**
     * Takes the input received by the parser and deciphers the command
     *
     * @return the respective descriptions of the task or command
     * @throws DukeException
     */
    public String compute() throws DukeException {
        String output = "";

        String[] instruction = this.input.split(" ", 2);

        switch (instruction[0]) {
        case "todo":
            // Fallthrough
        case "deadline":
            // Fallthrough
        case "event":
            try {
                if (instruction[0].equals("todo")) {
                    output = newToDo(instruction[1], this.taskList);
                } else if (instruction[0].equals("deadline")) {
                    output = newDeadline(instruction[1], this.taskList);
                } else {
                    output = newEvent(instruction[1], this.taskList);
                }
                break;
            } catch (ArrayIndexOutOfBoundsException a) {
                throw new DukeException("The description of a " + instruction[0] + " cannot be empty.");
            }
        case "done":
            // Fallthrough
        case "delete":
            try {
                int taskIndex = Integer.parseInt(instruction[1]);
                String taskOutput = "";

                if (instruction[0].equals("delete")) {
                    taskOutput = this.taskList.deleteTask(taskIndex);
                } else {
                    taskOutput = this.taskList.markDone(taskIndex);
                }

                try {
                    this.storage.rewrite(this.taskList);
                } catch (DukeException de) {
                    System.out.println(de);
                }

                output = taskOutput;
                break;
            } catch (NumberFormatException | IndexOutOfBoundsException e) {
                throw new DukeException("Please enter a valid task number.");
            }
        case "list":
            output = this.taskList.showList();
            break;
        case "find":
            output = this.taskList.searchList(instruction[1]);
            break;
        case "bye":
            output = this.ui.goodbyeMessage();
            break;
        default:
            try {
                this.storage.rewrite(this.taskList);
            } catch (DukeException de) {
                System.out.println(de);
            }

            output = new DukeException("I'm sorry, but I don't know what that means :-(").toString();
        }

        return output;
    }
}
