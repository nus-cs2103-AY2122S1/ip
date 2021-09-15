package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;

/**
 * User interface. Handles text for user interface.
 *
 * @author Ruth Poh
 */
public class Ui {
    private Parser parser;

    Ui() {
        parser = new Parser();
    }

    /**
     * Getter method for error message.
     * @param e DukeException thrown
     * @return Error message of DukeException
     */
    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Executes command stated in string and saves tasklist changes to storage.
     * @param storage Storage for storing Tasklist data
     * @param tasklist TaskList of tasks
     * @param str Command string
     * @return Message after executing command stated in string
     */
    public String getMessage(Storage storage, TaskList tasklist, String str) {
        try {
            // splits input to parse for keywords.
            String[] strparse = str.split(" ");

            switch (this.parser.parseCommand(strparse[0])) {
            case "bye":
                // breaks loop, closes chatbot.
                try {
                    if (strparse.length > 1) {
                        throw new IncorrectInputException("bye", "'bye'");
                    }

                    return "Goodbywe, Mastwer! Seew youw soown!\n\n";
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "help":
                // lists command list
                try {
                    if (strparse.length > 1) {
                        throw new IncorrectInputException("help", "'help'");
                    }

                    return "Commandws supported:\n\n"
                            + "- bye\n- help\n- list\n- todo\n- event\n- deadline\n";
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "list":
                // lists history of current tasks.
                try {
                    if (strparse.length > 1) {
                        throw new IncorrectInputException("list", "'list'");
                    }
                    StringBuilder strb = new StringBuilder();
                    strb.append("Uwu! Herw arw yourw taskws:\n");
                    if (tasklist.getTaskCounter() == 0) {
                        strb.append("Itw seewsm like youw wist is emptwy! Congwats!\n");
                    } else {
                        strb.append(tasklist.displayList());
                    }

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "todo":
                // adds a todo task to the list.
                try {
                    tasklist.addTodo(parser.parseTodo(strparse));
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                    strb.append("Youw noww havew " + (tasklist.getTaskCounter())
                            + " taskw(s) inw thew wist! uwu\n");

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "deadline":
                // adds a deadline task to the list.
                try {
                    tasklist.addDeadline(parser.parseDeadline(strparse));
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                    strb.append("Youw noww havew " + (tasklist.getTaskCounter())
                            + " taskw(s) inw thew wist! uwu\n");

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "event":
                // adds an event to the list. pretty much like deadline.
                try {
                    tasklist.addEvent(parser.parseEvent(strparse));
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                    strb.append("Youw noww havew " + (tasklist.getTaskCounter())
                            + " taskw(s) inw thew wist! uwu\n");

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "done":
                // marks a task as done.
                try {
                    int taskNo = tasklist.markDone(strparse);
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n")
                            .append(tasklist.getTaskDescr(taskNo) + '\n');

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "delete":
                // deletes corresponding task on list.
                try {
                    Task t = tasklist.delete(strparse);
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Thanwk youw forw youwr serwwice! Thwis taskw hasw beenw deweted:\n")
                            .append(t.toString() + '\n');
                    strb.append("Youw noww havew "
                            + (tasklist.getTaskCounter())
                            + " taskw(s) inw thew wist! uwu\n");

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            case "find":
                try {
                    String tasksFound = tasklist.find(parser.parseFind(strparse));

                    StringBuilder strb = new StringBuilder();
                    strb.append("Foundw! Here are the matching tasks:\n")
                            .append(tasksFound + '\n');

                    return strb.toString();
                } catch (DukeException e) {
                    return this.getErrorMessage(e);
                }
                //Fallthrough
            default:
                throw new InvalidInputException();
            }
        } catch (DukeException e) {
            return this.getErrorMessage(e);
        }
    }

    /**
     * Getter method for welcome message for Dukewu.
     * @return Welcome message for Dukewu.
     */
    public static String getWelcomeMessage() {
        return ("Hewwo and welcomew tow Dukewu!\n" +
                "Dukewu aimws tow helpw youw orgwanise your wist of taskws.\n" +
                "Pwease typwe 'help' for mwore infow! Uwu!");
    }

}
