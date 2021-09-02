package duke;

import java.lang.reflect.Array;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * User interface. Handles text for user interface.
 *
 * @author Ruth Poh
 */
public class Ui {
    private final String LOGO = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";
    private final String LINEBREAK_START = "~~*********___\\(owo)/___\\(owo)/___*********~~\n";
    private final String LINEBREAK_END = "\n\n~~*********___\\(owo)/___\\(owo)/___*********~~";
    private Parser parser;

    Ui() {
        parser = new Parser();
    }

    public String getErrorMessage(DukeException e) {
        return e.getMessage();
    }

    public String getMessage(Storage storage, TaskList tasklist, String str) {
        try {
            String[] strparse = str.split(" ");
            // splits input to parse for keywords.

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
                    return getErrorMessage(e);
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
                    int i = tasklist.markDone(strparse);
                    storage.saveData(tasklist);

                    StringBuilder strb = new StringBuilder();
                    strb.append("Thanwk youw forw youwr serwwice! Thwis taskw isw downe:\n")
                            .append(tasklist.getTaskDescr(i) + '\n');

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
                    String temp = tasklist.find(parser.parseFind(strparse));

                    StringBuilder strb = new StringBuilder();
                    strb.append("Foundw! Here are the matching tasks:\n")
                            .append(temp + '\n');

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
}
