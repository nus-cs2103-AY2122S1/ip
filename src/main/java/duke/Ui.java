package duke;

import duke.exceptions.DukeException;
import duke.exceptions.IncorrectInputException;
import duke.exceptions.InvalidInputException;
import duke.tasks.Task;

/**
 * User interface. Handles text for user interface.
 * @author Ruth Poh
 */
public class Ui {
    private Parser parser;
    private boolean isActivatedClearCommand;

    Ui() {
        parser = new Parser();
        isActivatedClearCommand = false;
    }

    /**
     * Getter method for error message.
     *
     * @param e DukeException thrown
     * @return Error message of DukeException
     */
    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }

    /**
     * Executes command stated in string and saves tasklist changes to storage.
     *
     * @param storage  Storage for storing Tasklist data
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
                                + "- bye\n- help\n- list\n- Todo\n- event\n- deadline\n";
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
                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Uwu! Herw arw yourw taskws:\n");
                        if (tasklist.getTaskCounter() == 0) {
                            stringBuilder.append("Itw seewsm like youw wist is emptwy! Congwats!\n");
                        } else {
                            stringBuilder.append(tasklist.displayList());
                        }

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "Todo":
                    // adds a Todo task to the list.
                    try {
                        tasklist.addTodo(parser.parseTodo(strparse));
                        storage.saveData(tasklist);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                        stringBuilder.append("Youw noww havew " + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "deadline":
                    // adds a deadline task to the list.
                    try {
                        tasklist.addDeadline(parser.parseDeadline(strparse));
                        storage.saveData(tasklist);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                        stringBuilder.append("Youw noww havew " + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "event":
                    // adds an event to the list. pretty much like deadline.
                    try {
                        tasklist.addEvent(parser.parseEvent(strparse));
                        storage.saveData(tasklist);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Uwu! Addewd yourw taskws:\n").append(tasklist.lastAddedTask() + '\n');
                        stringBuilder.append("Youw noww havew " + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "done":
                    // marks a task as done.
                    try {
                        int taskNo = tasklist.markDone(strparse);
                        storage.saveData(tasklist);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n")
                                .append(tasklist.getTaskDescr(taskNo) + '\n');

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "delete":
                    // deletes corresponding task on list.
                    try {
                        Task t = tasklist.delete(strparse);
                        storage.saveData(tasklist);

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Thanwk youw forw youwr serwwice! Thwis taskw hasw beenw deweted:\n")
                                .append(t.toString() + '\n');
                        stringBuilder.append("Youw noww havew "
                                + (tasklist.getTaskCounter())
                                + " taskw(s) inw thew wist! uwu\n");

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "find":
                    try {
                        String tasksFound = tasklist.find(parser.parseFind(strparse));

                        StringBuilder stringBuilder = new StringBuilder();
                        stringBuilder.append("Foundw! Here are the matching tasks:\n")
                                .append(tasksFound + '\n');

                        return stringBuilder.toString();
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "clearall":
                    try {
                        if (strparse.length > 1) {
                            throw new IncorrectInputException("clearall", "'clearall'");
                        }
                        isActivatedClearCommand = true;

                        return ("Pwease confirm clear task wist: y/n");
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "y":
                    try {
                        if (strparse.length > 1) {
                            throw new InvalidInputException();
                        }
                        if (!isActivatedClearCommand) {
                            throw new InvalidInputException();
                        }

                        isActivatedClearCommand = false;
                        tasklist.clearTaskList();
                        storage.saveData(tasklist);

                        return ("Alwight. Cleared!");
                    } catch (DukeException e) {
                        return this.getErrorMessage(e);
                    }
                    //Fallthrough
                case "n":
                    try {
                        if (strparse.length > 1) {
                            throw new InvalidInputException();
                        }
                        if (!isActivatedClearCommand) {
                            throw new InvalidInputException();
                        }

                        isActivatedClearCommand = false;

                        return ("Alwight. Please cawwy on.");
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
     *
     * @return Welcome message for Dukewu.
     */
    public static String getWelcomeMessage() {
        return ("Hewwo and welcomew tow Dukewu!\n" +
                "Dukewu aimws tow helpw youw orgwanise your wist of taskws.\n" +
                "Pwease typwe 'help' for mwore infow! Uwu!");
    }

}
