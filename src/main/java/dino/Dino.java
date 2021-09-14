package dino;

import java.time.LocalDate;
import java.time.LocalTime;

import dino.data.Storage;
import dino.data.TaskList;
import dino.task.Deadline;
import dino.task.Event;
import dino.task.Task;
import dino.task.Todo;
import dino.user.DinoException;
import dino.user.Parser;
import dino.user.Ui;


/**
 * Main class for the Dino program.
 */
public class Dino {

    private Storage storage;
    private TaskList tasks;
    private Ui ui;


    /**
     * Constructor for the Dino instance.
     *
     * @param filePath the String representing the path of the file where the data is saved
     */
    public Dino(String filePath) {
        storage = new Storage(filePath);
        tasks = new TaskList(storage.loadFromFile());
        ui = new Ui();
        assert storage != null;
    }

    /**
     * Default constructor for Dino.
     * Required for JavaFX GUI
     */
    public Dino() {
        this("data/Dino.txt");
    }

    /**
     * Handles the user input and sends it to the parser to perform the commands, if valid.
     */
    public String getResponse(String input) {

        Parser parser = new Parser(tasks);
        String output = "";

        try {
            String[] parsedInputString = parser.checkInput(input);
            String command = parsedInputString[0];

            if (command.equals("done")) {
                output = executeDone(output, parsedInputString[1]);
            } else if (command.equals("delete")) {
                output = executeDelete(output, parsedInputString[1]);
            } else if (command.equals("list")) {
                output += ui.displayListMessage(tasks);
            } else if (command.equals("bye")) {
                output += ui.displayByeMessage();
            } else if (command.equals("todo")) {
                output += executeTodo(parsedInputString[1]);
            } else if (command.equals("event")) {
                output += executeEvent(parsedInputString);
            } else if (command.equals("deadline")) {
                output += executeDeadline(parsedInputString);
            } else if (command.equals("find")) {
                output = executeFind(output, parsedInputString[1]);
            } else {
                throw new DinoException("Please enter a valid command");
            }

            storage.saveToFile();

        } catch (DinoException e) {
            return output + ui.displayDinoExceptionMessage(e);
        } catch (Exception e) {
            return output + ui.displayExceptionMessage(e) ;
        }
        return output;
    }

    private String executeFind(String output, String keyword1) {
        String keyword = keyword1;
        output += ui.findMessage(tasks.findTask(keyword));
        return output;
    }

    private String executeEvent(String[] parsedInputString) {
        tasks.addTask(new Event(parsedInputString[1], parsedInputString[2]));
        return ui.displayAddTaskMessage(tasks);
    }

    private String executeDeadline(String[] parsedInputString) {
        LocalDate date = LocalDate.parse(parsedInputString[2]);
        LocalTime time = LocalTime.parse(parsedInputString[3]);
        String deadlineDesc = parsedInputString[1];

        tasks.addTask(new Deadline(deadlineDesc, date, time));
        return ui.displayAddTaskMessage(tasks);
    }

    private String executeTodo(String desc) {
        tasks.addTask(new Todo(desc));
        return ui.displayAddTaskMessage(tasks);
    }

    private String executeDelete(String output, String s) {
        Task current = tasks.getTask(Integer.parseInt(s) - 1);
        tasks.removeTask(current);
        output += ui.displayDeletedTaskMessage(current.toString(), tasks.getLength());
        return output;
    }

    private String executeDone(String output, String s) {
        Task current = tasks.getTask(Integer.parseInt(s) - 1);
        current.setIsDone();
        output += ui.displayDoneTaskMessage(current.toString());
        return output;
    }


    /**
     * Starts the whole program.
     *
     * @param args NIL
     */
    public static void main(String[] args) {
        new Dino("data/Dino.txt");
    }
}
