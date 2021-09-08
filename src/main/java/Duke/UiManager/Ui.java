package duke.uimanager;

import duke.command.Parser;
import duke.exceptions.DukeException;

import java.util.ArrayList;

/**
 * @@author Hang Zelin
 *
 * Ui Part of Duke Programme. This programme mainly deals with all the user interactions.
 * It will print out the information for each type of operation execution. And it will also accept users'
 * input for each round of task execution.
 */
public class Ui {

    /**
     * Returns Hello Message to users.
     *
     * @return Default helloMessage.
     */
    public String helloMessage() {
        String text = """
                Hello! I'm Duke
                What can I do for you?
                """;
        return text;
    }

    /**
     * Returns Goodbye Message to users.
     *
     * @return Default goodbyeMessage.
     */
    public String goodbyeMessage() {
        String text = "Bye. Hope to see you again soon!";
        return text;
    }

    /**
     * Returns the Ui info for the MarkDone method in TaskList.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format.
     * @return Ui message when markDone is invoked.
     */
    public String markDoneUi(String parsedTask) {
        String text = "";
        text += "Nice! I've marked this task as done:\n"
                + " " + parsedTask +"\n";

        return text;
    }

    /**
     * Returns the Ui info for the Delete method in TaskList.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     * @return Ui message when delete is invoked.
     */
    public String deleteUi(String parsedTask, int size) {
        String text = "";
        text += "Noted. I've removed this task:\n" + " " + parsedTask
                + "\nNow you have " + size + " tasks in the list.\n";

        return text;
    }

    /**
     * Returns the Ui info for the add method in Tasklists.
     *
     * @param parsedTask Detailed info for a task that is parsed into a specific format
     * @param size Size of the TaskList.
     * @return Ui message when add is invoked.
     */
    public String addUi(String parsedTask, int size) {
        String text = "";
        text += "Got it. I've added this task: \n" + "\n " + parsedTask
                + "\nNow you have " + size + ""
                + " tasks in the list.\n";

        return text;
    }

    /**
     * Returns the Ui info for the getSpecificDateEvent method in TaskLists.
     *
     * @return Ui message when getSpecificDateEvent is invoked.
     */
    public String getSpecificDateEventUi() {
        String text = "Here are all the tasks taking place on the date you give me: \n";
        return text;
    }

    /**
     * Returns the Ui info for the FindTask method in Tasklists.
     *
     * @return Ui message when findTask is invoked.
     */
    public String findTasksUi() {
        String text = "Here are the matching tasks in your list:\n";
        return text;
    }

    public String undoUi() {
        String text = "Okay, I have helped undo your most recent command!\n";
        return text;
    }

    /**
     * Returns the key 4 information from users' input encapsulated in a ArrayList of String.
     * They are: operationType, task, time, index. They will be useful when executing in Duke programme.
     *
     * @return Size of 4 ArrayList contains Message of operationType, task, time and index.
     * @throws DukeException Throws when the input cannot be parsed.
     */
    public ArrayList<String> returnSplitComponent(String input) throws DukeException {
        Parser parser;
        ArrayList<String> parsedMessageList = new ArrayList<>();

        parser = new Parser(input);

        parsedMessageList.add(parser.getOperationType());
        parsedMessageList.add(parser.getTask());
        parsedMessageList.add(parser.getTime());
        parsedMessageList.add(parser.getIndex().toString());

        return parsedMessageList;
    }

    /**
     * Returns the Loading Error Text.
     *
     * @return Error Message in loading.
     */
    public String showLoadingError() {
        String text = "Cannot Load From Data.\n";
        return text;
    }

    /**
     * Returns the Saving Error Text.
     *
     * @return Error Message in saving.
     */
    public String showSavingError() {
        String text = "Cannot Save the Data.\n";
        return text;
    }
}
