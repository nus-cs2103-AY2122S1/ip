package lebron;

import lebron.exception.LebronException;
import lebron.task.*;

import java.util.ArrayList;

/**
 * This class represents the chat bot.
 *
 * @author Nigel Tan
 */

public class Lebron {
    public static final String FILE_PATH = "./build/libs/data/lebron.txt";
    private static final String HORIZONTAL_LINE = "    ____________________________" +
            "________________________________\n";
    private final Storage storage;
    private TaskList taskList;
    private final Ui ui;


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
        this.ui = new Ui(this);
        this.storage = new Storage(FILE_PATH);
        ArrayList<Task> loadList = storage.loadFileContents(FILE_PATH);
        taskList = new TaskList(loadList, this);
    }

    /**
     * Runs the bot.
     *
     * @param text the input text
     * @return the reply received from the bot given an input
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
                reply = ui.replyExit();
                break;
            case LIST:
                reply = ui.replyDisplay(taskList);
                break;
            case DONE:
                if (splitWords.length < 2 || splitWords[1].equals("")) {
                    throw new LebronException("    :( OOPS! Please specify which task you wish "
                            + "to complete.");
                }
                assert splitWords.length == 2 : "There should be 2 items to specify which task is done.";
                int pos = Integer.parseInt(splitWords[1]);
                reply = taskList.markDone(pos - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case TODO:
                if (splitWords.length < 2 || splitWords[1].equals("")) {
                    throw new LebronException("    :( OOPS! The description of a ToDo "
                            + "cannot be empty.");
                }
                assert splitWords.length == 2 : "There should be 2 items to specify task and description.";
                reply = taskList.add(new ToDo(splitWords[1]));
                storage.saveToFile(taskList.getLst());
                break;
            case DEADLINE:
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
                String[] dateTimeArrDeadline = splitBy[1].split(" ", 2);
                if (dateTimeArrDeadline.length < 2 || dateTimeArrDeadline[1].equals("")) {
                    throw new LebronException("    :( OOPS! Please check that your date and time is " +
                            "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
                }
                assert dateTimeArrDeadline.length == 2 : "There should be a date and time.";
                reply = taskList.add(new Deadline(splitBy[0], dateTimeArrDeadline[0]
                        , dateTimeArrDeadline[1]));
                storage.saveToFile(taskList.getLst());
                break;
            case EVENT:
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
                String[] dateTimeArrEvent = splitAt[1].split(" ", 2);
                if (dateTimeArrEvent.length < 2 || dateTimeArrEvent[1].equals("")) {
                    throw new LebronException("    :( OOPS! Please check that your date and time is " +
                            "valid and formatted as 'yyyy-MM-dd' 'HHmm'.");
                }
                assert dateTimeArrEvent.length == 2 : "There should be a date and time.";
                reply = taskList.add(new Events(splitAt[0], dateTimeArrEvent[0], dateTimeArrEvent[1]));
                storage.saveToFile(taskList.getLst());

                break;
            case DELETE:
                if (splitWords.length < 2 || splitWords[1].equals("")) {
                    throw new LebronException("    :( OOPS! Please specify which task you wish "
                            + "to delete.");
                }
                assert splitWords.length == 2 : "There should be 2 items to specify which task to delete.";
                int pos2 = Integer.parseInt(splitWords[1]);
                reply = taskList.delete(pos2 - 1);
                storage.saveToFile(taskList.getLst());
                break;
            case FIND:
                if (splitWords.length < 2 || splitWords[1].equals("")) {
                    throw new LebronException("    :( OOPS! Please specify some words to search for.");
                }
                assert splitWords.length == 2 : "There should be 2 items to specify a string to search for.";
                String keyword = splitWords[1];
                reply = ui.replyFind(taskList, keyword);
                break;
            case UNDO:
                taskList.undo();
                reply = ui.replyUndo();
                storage.saveToFile(taskList.getLst());
                break;
            case OTHER:
                throw new LebronException(" OOPS! I'm sorry, but I don't know what that means.\n");
            default:
                break;
            }
            return reply;

        } catch (LebronException e) {
            return ui.printException(e.getMessage());
        }
    }
}
