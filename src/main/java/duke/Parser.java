package duke;

import java.util.Scanner;

import duke.exception.WrongCommandFormatException;
import duke.tasktype.Deadline;
import duke.tasktype.Event;
import duke.tasktype.Todo;


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
     * Method to generate Dukes response based on the user command.
     * @param command The command the user enters.
     * @return The String representing Duke's response.
     */
    public String getDukeResponse(String command) {
        Scanner s = new Scanner(command);
        String input = s.next();
        String response = "";

        switch (input) {
        case "list":
            response = this.list.listAll();
            break;
        case "done":
            response = getDoneResponse(s.nextLine());
            break;
        case "todo":
            response = getTodoResponse(s.nextLine());
            break;
        case "deadline":
            response = getDeadlineResponse(s.nextLine());
            break;
        case "event":
            response = getEventResponse(s.nextLine());
            break;
        case "delete":
            response = getDeleteResponse(s.nextLine());
            break;
        case "setFormat":
            response = getSetFormatResponse(s.nextLine());
            break;
        case "format":
            response = Ui.currentDateFormatMessage();
            break;
        case "find":
            response = getFindResponse(s.nextLine());
            break;
        case "bye":
            response = Ui.botShutdownMessage();
            this.isRunning = false;
            break;
        default:
            response = Ui.noSpecificCmdMessage();
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'done' command.
     * @param command The command
     * @return Duke's response
     */
    private String getDoneResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        int counter = 0;

        while (s.hasNextInt()) {
            int index = s.nextInt();
            response += this.list.markComplete(index);
            this.storage.writeToFile();
            counter++;
        }
        if (counter == 0) {
            response = Ui.invalidIndexMessage();
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'todo' command.
     * @param command The 'todo' command
     * @return Duke's response.
     */
    private String getTodoResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        String description = "";
        while (s.hasNextLine()) {
            description = s.nextLine();
        }
        try {
            Todo newTodo = new Todo(description, false);
            response = this.list.addTask(newTodo);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to return duke's response to a 'deadline' command.
     * @param command The 'deadline' command
     * @return Duke's response.
     */
    private String getDeadlineResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        String description = "";
        while (s.hasNextLine()) {
            description = s.nextLine();
        }
        try {
            Deadline newDeadline = new Deadline(description, false);
            response = this.list.addTask(newDeadline);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to generate Duke's response to an 'event' command.
     * @param command The 'event' command.
     * @return Duke's response.
     */
    private String getEventResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        String description = "";
        while (s.hasNextLine()) {
            description = s.nextLine();
        }
        try {
            Event newEvent = new Event(description, false);
            response = this.list.addTask(newEvent);
            this.storage.writeToFile();
        } catch (WrongCommandFormatException e) {
            response = Ui.formatExceptionMessage(e);
        }
        return response;
    }

    /**
     * Method to generate duke's response to a 'delete' command.
     * @param command The 'delete' command.
     * @return Duke's response.
     */
    private String getDeleteResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        if (s.hasNextInt()) {
            int index = s.nextInt();
            response = this.list.deleteTask(index);
            this.storage.writeToFile();
        } else {
            response = Ui.invalidIndexMessage();
        }
        return response;
    }

    /**
     * Method to generate Duke's reponse to a 'setFormat' command.
     * @param command The 'setFormat' command.
     * @return Duke's response.
     */
    private String getSetFormatResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        if (s.hasNextLine()) {
            try {
                Duke.setFormat(s.nextLine().substring(1));
                response = Ui.formatUpdatedMessage();
                this.storage.writeToFile();
            } catch (IllegalArgumentException e) {
                response = Ui.unacceptableFormatMessage();
            }
        } else {
            response = Ui.noFormatSpecifiedMessage();
        }
        return response;
    }

    /**
     * Method to generate Duke's response to a 'find' command.
     * @param command The 'find' command.
     * @return Duke's response.
     */
    private String getFindResponse(String command) {
        String response = "";
        Scanner s = new Scanner(command);
        if (s.hasNextLine()) {
            response = this.list.find(s.nextLine());
        } else {
            response = Ui.noKeywordSpecifiedMessage();
        }
        return response;
    }
}
