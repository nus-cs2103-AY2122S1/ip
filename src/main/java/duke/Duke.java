package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * This class contains inner methods for initializing and running the Duke bot.
 */
public class Duke {
    private Tasklist listOfItems;
    private final Storage storage;
    private final Ui ui;
    private final InputParser parser;

    /**
     * Creates a new Duke object and instantiates Storage, Ui and an input parser.
     */
    public Duke() {
        String filepath = "data/Duke.txt";
        ui = new Ui();
        listOfItems = new Tasklist();
        parser = new InputParser();

        Path path = Paths.get(System.getProperty("user.dir"), filepath);
        try {
            if (Files.notExists(path)) {
                File f = new File(path.toString());
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            getResponse("An error occurred when opening the file. Try rerunning Duke again.");
        }
        storage = new Storage(path);
        listOfItems = storage.load();
    }

    /**
     * Marks an item as done on the task list.
     *
     * @param input Gui input from the user.
     * @return String confirmation of task being successfully completed.
     */
    private String markAsDone(String input) {
        try {
            String[] keywords = input.split(" ");
            Integer idx = keywords.length > 1 ? Integer.parseInt(keywords[1]) : -1;
            Task task = listOfItems.getTask(idx);
            task.setDone();
            String completionMessage = String.format("You have successfully completed task %s:\n", keywords[1]);
            return completionMessage + task;
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("your index cannot be found in the task list!");
        }
    }


    /**
     * Adds a todo item to the list.
     *
     * @param input Gui input from the user.
     * @return String confirmation of task being successfully added.
     * @throws DukeException when input format is wrong.
     */
    private String addAsTodo(String input) throws DukeException {
        // split input into command and text
        String[] elements = input.split(" ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to describe the todo in format:\n todo (description)!");
        }
        String textDescription = elements[1];
        Task todo = new Todo(textDescription);
        listOfItems.addTask(todo);
        return ui.printAdditionConfirmation(todo, listOfItems);
    }

    /**
     * Adds an event item to the list.
     *
     * @param input Gui input from the user.
     * @return String confirmation of task being successfully added.
     * @throws DukeException when input format is wrong.
     */
    private String addAsEvent(String input) throws DukeException {
        String[] keywords = input.split(" ", 2);
        if (keywords.length == 1) {
            throw new DukeException("you need to describe your event to me in format:\n event (description)!");
        }
        // split input into command and text
        String[] elements = keywords[1].split(" /at ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to tell me the time in format:\n (task) /at (time)!");
        }
        String time = elements[1];
        String textDescription = elements[0];
        Task event = new Event(textDescription, time);
        listOfItems.addTask(event);
        return ui.printAdditionConfirmation(event, listOfItems);
    }

    /**
     * Adds a deadline item to the list.
     *
     * @param input Gui input from the user.
     * @return String confirmation of task being successfully added.
     * @throws DukeException when input format is wrong.
     */
    private String addAsDeadline(String input) throws DukeException {
        String[] keywords = input.split(" ", 2);
        if (keywords.length == 1) {
            throw new DukeException("you need to describe your deadline to me in format:\n deadline (description)!");
        }
        String[] elements = keywords[1].split(" /by ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to tell me the time in format:\n (task) /by (time)!");
        }
        String time = elements[1];
        String textDescription = elements[0];
        Task deadline = new Deadline(textDescription, time);
        listOfItems.addTask(deadline);
        return ui.printAdditionConfirmation(deadline, listOfItems);
    }

    /**
     * Deletes an item from the list by passing the index of the task to be deleted.
     *
     * @param input Command line input from the user to be parsed.
     * @return String confirmation of task being successfully deleted.
     * @throws DukeException Throws an exception if index is not on the list or with wrong input.
     */
    private String deleteItem(String input) throws DukeException {
        try {
            Integer idx = Integer.parseInt(parser.getDescription(input));
            Task item = listOfItems.getTask(idx);
            listOfItems.removeTask(idx);
            return ui.printDeletionConfirmation(item, listOfItems);
        } catch (NumberFormatException e) {
            throw new DukeException("you need to choose a number from the list in the form: delete (list index)!");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException("you need to pick an item from within the list!");
        }
    }

    /**
     * Displays a list of items on the command line that contain the keyword entered by the user.
     *
     * @param input Command line input from the user.
     * @return String list of items on the task list.
     * @throws DukeException Throws an exception if no keyword was entered or if no tasks match the keyword given.
     */
    private String findItems(String input) throws DukeException {
        try {
            String keyword = parser.getDescription(input);
            Tasklist validItems = listOfItems.findAllBy(keyword);
            return ui.listToPrintableString(validItems);
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new DukeException("please input a keyword in the format:\n[find] (keyword)");
        } catch (NullPointerException e) {
            throw new DukeException("no tasks match your given keyword!");
        }

    }
    /**
     * Handles invalid commands from the user.
     *
     * @throws DukeException Throws an exception when a wrong input is given on the command line.
     */
    private void markAsInvalid(String input) throws DukeException {
        if (input.equals("")) {
            throw new DukeException("you need to type something!");
        } else {
            throw new DukeException("your command is invalid!");
        }
    }

    /**
     * Handles the user's input and determines which command should be run.
     *
     * @param input Gui input from the user.
     * @return String response from the user's input.
     */
    public String getResponse(String input) {
        String command = parser.getCommand(input);
        try {
            switch(command) {
            case "items":
                return ui.listToPrintableString(listOfItems);
            case "completed":
                return markAsDone(input);
            case "todo":
                return addAsTodo(input);
            case "event":
                return addAsEvent(input);
            case "deadline":
                return addAsDeadline(input);
            case "delete":
                return deleteItem(input);
            case "find":
                return findItems(input);
            case "bye":
                storage.save(listOfItems);
                return "your data has been successfully saved!";
            case "help":
                return new ManPage().toString();
            default:
                markAsInvalid(input);
                break;
            }
        } catch (DukeException e) {
            return e.getMessage();
        }
        return "something";
    }
}
