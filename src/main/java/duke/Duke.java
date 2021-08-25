package duke;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;

public class Duke {
    private Tasklist listOfItems;
    private final Storage storage;
    private final Ui ui;
    private final InputParser parser;

    public Duke(String filepath) {
        ui = new Ui();
        listOfItems = new Tasklist();
        parser = new InputParser();

        Path FILE_PATH = Paths.get(System.getProperty("user.dir"), filepath);
        try {
            if (Files.notExists(FILE_PATH)) {
                File f = new File(FILE_PATH.toString());
                f.getParentFile().mkdirs();
                f.createNewFile();
            }
        } catch (IOException e) {
            ui.outputWrapper("An error occurred when opening the file. Try rerunning Duke again.");
        }
        storage = new Storage(FILE_PATH);
        listOfItems = storage.load();
    }

    /**
     * Marks an item on the list as completed.
     *
     * @param input Command line input from the user.
     */
    private void markAsDone(String input) {
        String[] keywords = input.split(" ");
        String command = keywords[0];
        Integer idx = keywords.length > 1 ? Integer.parseInt(keywords[1]) : -1;
        Task task = listOfItems.getTask(idx);
        task.setDone();
        String completionMessage = String.format("You have successfully completed task %s:\n", keywords[1]);
        ui.outputWrapper(completionMessage + task);
    }


    /**
     * Adds an item to the list as todo.
     *
     * @param input Command line input from the user.
     */
    private void addAsTodo(String input) throws DukeException {
        // split input into command and text
        String[] elements = input.split(" ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to describe the todo in format: todo (description)!");
        }
        String textDescription = elements[1];
        Task todo = new Todo(textDescription);
        listOfItems.addTask(todo);
        ui.printAdditionConfirmation(todo, listOfItems);
    }

    /**
     * Adds an item to the list as an event.
     *
     * @param input Command line input from the user.
     */
    private void addAsEvent(String input) throws DukeException {
        String[] keywords = input.split(" ", 2);
        if (keywords.length == 1) {
            throw new DukeException("you need to describe your event to me in format: event (description)!");
        }
        // split input into command and text
        String[] elements = keywords[1].split(" /at ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to tell me the time in format: (task) /at (time)!");
        }
        String time = elements[1];
        String textDescription = elements[0];
        Task event = new Event(textDescription, time);
        listOfItems.addTask(event);
        ui.printAdditionConfirmation(event, listOfItems);
    }
    /**
     * Adds an item to the list as deadline.
     *
     * @param input Command line input from the user.
     */
    private void addAsDeadline(String input) throws DukeException {
        String[] keywords = input.split(" ", 2);
        if (keywords.length == 1) {
            throw new DukeException("you need to describe your deadline to me in format: deadline (description)!");
        }
        String[] elements = keywords[1].split(" /by ", 2);
        if (elements.length == 1) {
            throw new DukeException("you need to tell me the time in format: (task) /by (time)!");
        }
        String time = elements[1];
        String textDescription = elements[0];
        Task deadline = new Deadline(textDescription, time);
        listOfItems.addTask(deadline);
        ui.printAdditionConfirmation(deadline, listOfItems);
    }

    /**
     * Deletes an item from the list by passing the index of the task to be deleted.
     *
     * @param input Command line input from the user to be parsed.
     * @throws DukeException Throws an exception if index is not on the list or with wrong input.
     */
    private void deleteItem(String input) throws DukeException {
        try {
            Integer idx = Integer.parseInt(parser.getDescription(input));
            Task item = listOfItems.getTask(idx);
            listOfItems.removeTask(idx);
            ui.printDeletionConfirmation(item, listOfItems);
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
     * @throws DukeException Throws an exception if no keyword was entered or if no tasks match the keyword given.
     */
    private void findItems(String input) throws DukeException {
        try {
            String keyword = parser.getDescription(input);
            Tasklist validItems = listOfItems.findAllBy(keyword);
            ui.outputWrapper(validItems);
        }catch (ArrayIndexOutOfBoundsException e){
            throw new DukeException("please input a keyword in the format: [find] (keyword)");
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
     * @param input Command line input from the user.
     */
    private void handleInput(String input) {
        String command = parser.getCommand(input);
        try {
            switch(command) {
                case "items":
                    ui.outputWrapper(listOfItems);
                    break;
                case "completed":
                    markAsDone(input);
                    break;
                case "todo":
                    addAsTodo(input);
                    break;
                case "event":
                    addAsEvent(input);
                    break;
                case "deadline":
                    addAsDeadline(input);
                    break;
                case "delete":
                    deleteItem(input);
                    break;
                case "find":
                    findItems(input);
                    break;
                default:
                    markAsInvalid(input);
                    break;
            }
        } catch (DukeException e) {
            ui.outputWrapper(e.getMessage());
        }

    }

    /**
     * Starts the current session for the bot.
     */
    public void startBot()  {

        Scanner i = new Scanner(System.in);
        String input = i.nextLine();

        while (!input.equals("bye")) {
            handleInput(input);
            input = i.nextLine();
        }

        try {
            storage.save(listOfItems);
        } catch (IOException e) {
            ui.outputWrapper("Sorry, data could not be saved.");
        }

        ui.outputWrapper("Thanks for using me. See you again soon!");
        i.close();
    }

    public static void main(String[] args) {
        System.out.println("Hello this is Jeeves, your personal chatbot. What can i do you for today?");
        Duke bot = new Duke("data/Duke.txt");
        bot.startBot();
    }
}
