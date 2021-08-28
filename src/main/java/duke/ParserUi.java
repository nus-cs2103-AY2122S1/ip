package duke;

import java.util.ArrayList;

/**
 * The ui for Parser that is in charge of displaying relevant messages to the user.
 */
public class ParserUi extends Ui {

    /**
     * Reminds the user that the parser cannot interpret the input command.
     */
    public void printCannotInterpretMessage() {
        System.out.println(formatMessage("OOPS!!! I'm sorry, but I don't know what that means :-(\n"));
    }

    /**
     * Shows the user guideline.
     */
    public void printHelpMessage() {
        System.out.println(formatMessage("todo <description>: create and save a todo task;\n"
                + Ui.INDENTATION + "deadline <description> /by <time in format yyyy-mm-dd>: "
                + "create and save a deadline task;\n"
                + Ui.INDENTATION + "event <description> /at <time in format yyyy-mm-dd>: "
                + "create and save an event task;\n"
                + Ui.INDENTATION + "save <directory>: save the current task list to the specified directory;\n"
                + Ui.INDENTATION + "load <directory>: load the task list from the specified directory;\n"
                + Ui.INDENTATION + "done <number>: mark the task with number index as done;\n"
                + Ui.INDENTATION + "delete <number>: delete the task with number index;\n"
                + Ui.INDENTATION + "deleteAll: delete all saved tasks in the task list;\n"
                + Ui.INDENTATION + "find <keyword>: find all tasks with description containing the keyword.\n"));
    }

    /**
     * Shows the list of events saved at .../data/record.
     */
    public void printUserInputRecord(ArrayList<Task> userInputRecords) {
        if (userInputRecords.isEmpty()) {
            System.out.println(formatMessage("Ah oh, seems like nothing is added yet :( \n"
                    + getIndentation() + "Try to input something first! \n"));
        } else {
            System.out.println("    ____________________________________________________________");
            System.out.println("     Here are the tasks in your list:");
            for (int i = 0; i < userInputRecords.size(); i++) {
                System.out.println("     " + (i + 1) + "." + userInputRecords.get(i));
            }
            System.out.println("    ____________________________________________________________");
        }
    }
}
