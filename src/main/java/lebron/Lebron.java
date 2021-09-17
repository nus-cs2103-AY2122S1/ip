package lebron;

import lebron.exception.LebronException;
import lebron.task.Deadline;
import lebron.task.Event;
import lebron.task.Task;
import lebron.task.TaskList;
import lebron.task.ToDo;


import java.util.ArrayList;
import java.util.EmptyStackException;
import java.util.Stack;

/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Lebron {
    public static final String FILE_PATH = "./build/libs/data/lebron.txt";
    private final Storage storage;
    private final Ui ui;
    private Stack<TaskList> previousStates = new Stack<>();

    /**
     * Available commands that the bot can understand.
     */
    public enum Command {
        LIST("list"),
        DONE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event"),
        DELETE("delete"),
        FIND("find"),
        UNDO("undo"),
        BYE("bye"),
        OTHER(" ");

        private final String text;

        Command(String text) {
            this.text = text;
        }

        /**
         * Creates a Command enum value from a string.
         *
         * @param text the text.
         * @return the desired Command enum.
         */
        public static Command fromString(String text) {
            for (Command c : Command.values()) {
                if (c.text.equalsIgnoreCase(text)) {
                    return c;
                }
            }
            return OTHER;
        }
    }

    /**
     * Constructor for the chatbot.
     *
     */
    public Lebron() {
        this.ui = new Ui();
        this.storage = new Storage(FILE_PATH);
        ArrayList<Task> loadList = storage.loadFileContents(FILE_PATH);
        this.previousStates.push(new TaskList(loadList));
    }

    /**
     * Runs the bot.
     *
     * @param text the input text.
     * @return the reply received from the bot given an input.
     */
    public String run(String text) {
        Parser parser = new Parser();
        String reply = "";
        try {
            String commandWord = parser.parseText(text);
            String[] splitWords = parser.split(text);
            Command command = Command.fromString(commandWord);
            switch (command) {
            case BYE:
                reply = this.commandBye();
                break;
            case LIST:
                reply = this.commandList();
                break;
            case DONE:
                reply = this.commandDone(splitWords);
                break;
            case TODO:
                reply = this.commandToDo(splitWords);
                break;
            case DEADLINE:
                reply = this.commandDeadline(splitWords);
                break;
            case EVENT:
                reply = this.commandEvent(splitWords);
                break;
            case DELETE:
                reply = this.commandDelete(splitWords);
                break;
            case FIND:
                reply = this.commandFind(splitWords);
                break;
            case UNDO:
                this.commandUndo();
                reply = ui.replyUndo();
                break;
            case OTHER:
                throw new LebronException("    OOPS! I'm sorry, but I don't know what that means.\n");
            default:
                break;
            }
            return reply;

        } catch (LebronException e) {
            return ui.printException(e.getMessage());
        }
    }

    /**
     * Retrieves the TaskList at the top of the stack, which is the current state.
     *
     * @return the TaskList at the top of the stack.
     */
    private TaskList getLatestState() {
        return previousStates.peek();
    }

    /**
     * Handles the case where commandWord is bye.
     *
     * @return the response from the bot
     */
    private String commandBye() {
        return ui.replyExit();
    }

    /**
     * Handles the case where commandWord is list.
     *
     * @return the response from the bot
     */
    private String commandList() {
        return ui.replyDisplay(getLatestState());
    }

    /**
     * Handles the case where commandWord is undo. Will remove the current state of the TaskList from the stack.
     */
    private void commandUndo() throws LebronException {
        if (previousStates.size() == 1) {
            throw new LebronException("    I can't undo any further!");
        }
        try {
            this.previousStates.pop();
            storage.saveToFile(getLatestState());
        } catch (EmptyStackException e) {
            throw new LebronException("    OOPS! I have no memory of your last action! Try doing something first!");
        }
    }


    /**
     * Handles the case where commandWord is done.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandDone(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! Please specify which task you wish "
                    + "to complete.");
        }
        if (Integer.parseInt(splitWords[1]) > getLatestState().getSize()) {
            throw new LebronException("    :( OOPS! The task is not yet created!");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify which task is done.";
        try {
            int pos = Integer.parseInt(splitWords[1]);
            TaskList newTaskList = getLatestState().getCopy(getLatestState());
            Task task = getLatestState().markDone(pos - 1);
            previousStates.push(newTaskList);
            storage.saveToFile(getLatestState());
            return ui.replyMarkDone(task);
        }
        catch (NumberFormatException e) {
            throw new LebronException("    :( OOPS! Make sure you input a valid number!");
        }
    }

    /**
     * Handles the case where commandWord is todo.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandToDo(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! The description of a ToDo "
                    + "cannot be empty.");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify task and description.";
        Task task = new ToDo(splitWords[1]);
        TaskList newTaskList = getLatestState().getCopy(getLatestState());
        newTaskList.add(task);
        previousStates.push(newTaskList);
        storage.saveToFile(newTaskList);
        return ui.replyAdd(newTaskList.getLst(), task);
    }

    /**
     * Handles the case where commandWord is deadline.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandDeadline(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! The description of a Deadline " +
                    "cannot be empty.");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify task and description.";
        String[] splitBy = splitWords[1].split("/by ", 2);
        if (splitBy.length < 2 || splitBy[1].equals("")) {
            throw new LebronException("    :( OOPS! Please check that the '/by' keyword " +
                    "is used and that a due date and time is given.");
        }
        assert splitBy.length == 2 : "There should be a description and a datetime.";
        String[] dateTimeArr = splitBy[1].split(" ", 2);
        if (dateTimeArr.length < 2 || dateTimeArr[1].equals("")) {
            throw new LebronException("    :( OOPS! Please check that your date and time is " +
                    "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
        }
        assert dateTimeArr.length == 2 : "There should be a date and time.";
        Task task = new Deadline(splitBy[0], dateTimeArr[0],
                dateTimeArr[1]);
        TaskList newTaskList = getLatestState().getCopy(getLatestState());
        newTaskList.add(task);
        previousStates.push(newTaskList);
        storage.saveToFile(newTaskList);
        return ui.replyAdd(newTaskList.getLst(), task);
    }

    /**
     * Handles the case where commandWord is event.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandEvent(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! The description of an Event " +
                    "cannot be empty.");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify task and description.";
        String[] splitAt = splitWords[1].split("/at ", 2);
        if (splitAt.length < 2 || splitAt[1].equals("")) {
            throw new LebronException("    :( OOPS! Please check that the '/at' keyword " +
                    "is used and that a due date is given.");
        }
        assert splitAt.length == 2 : "There should be a description and a datetime.";
        String[] dateTimeArr = splitAt[1].split(" ", 2);
        if (dateTimeArr.length < 2 || dateTimeArr[1].equals("")) {
            throw new LebronException("    :( OOPS! Please check that your date and time is " +
                    "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
        }
        assert dateTimeArr.length == 2 : "There should be a date and time.";
        Task task = new Event(splitAt[0], dateTimeArr[0], dateTimeArr[1]);
        TaskList newTaskList = getLatestState().getCopy(getLatestState());
        newTaskList.add(task);
        previousStates.push(newTaskList);
        storage.saveToFile(newTaskList);
        return ui.replyAdd(newTaskList.getLst(), task);
    }

    /**
     * Handles the case where commandWord is delete.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandDelete(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! Please specify which task you wish "
                    + "to delete.");
        }
        if (Integer.parseInt(splitWords[1]) - 1 > getLatestState().getSize()) {
            throw new LebronException("    :( OOPS! The task is not yet created!");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify which task to delete.";
        int pos = Integer.parseInt(splitWords[1]);
        TaskList newTaskList = getLatestState().getCopy(getLatestState());
        Task task = newTaskList.delete(pos - 1);
        previousStates.push(newTaskList);
        storage.saveToFile(newTaskList);
        return ui.replyDelete(task, newTaskList.getSize());
    }

    /**
     * Handles the case where commandWord is find.
     *
     * @param splitWords the parsed user input.
     * @return the response from the bot.
     * @throws LebronException if the input is invalid.
     */
    private String commandFind(String[] splitWords) throws LebronException {
        if (splitWords.length < 2 || splitWords[1].equals("")) {
            throw new LebronException("    :( OOPS! Please specify some words to search for.");
        }
        assert splitWords.length == 2 : "There should be 2 items to specify a string to search for.";
        String keyword = splitWords[1];
        return ui.replyFind(getLatestState(), keyword);
    }
}
