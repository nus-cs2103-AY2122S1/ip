package bern.functionalities;

import java.util.ArrayList;

import bern.Command;
import bern.exception.BernException;
import bern.exception.DuplicateException;
import bern.exception.EmptyDescriptionException;
import bern.exception.IndexException;
import bern.exception.InvalidCommandException;
import bern.model.Deadline;
import bern.model.Event;
import bern.model.Task;
import bern.model.ToDo;

/**
 * A class to encapsulates all methods UI related.
 */
public class Ui {

    private Parser parser = new Parser();
    private Storage storage = new Storage();

    /**
     * A method to get the reply if input starts with deadline.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifDeadline(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "deadline".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("deadline");
        }
        String task = input.substring(len + 1, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        Deadline addition = new Deadline(task, by);
        if (arListTask.contains(addition)) {
            throw new DuplicateException(input);
        }
        new TaskList().addTask(addition, arListTask);
        assert input.length() > 8 : "deadline input is invalid";
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    /**
     * A method to get the reply if input starts with event.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifEvent(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "event".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("event");
        }
        String task = input.substring(len + 1, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        Event addition = new Event(task, at);
        if (arListTask.contains(addition)) {
            throw new DuplicateException(input);
        }
        new TaskList().addTask(addition, arListTask);
        assert input.length() > 5 : "event input is invalid";
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    /**
     * A method to get the reply if input starts with todo.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifToDo(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "todo".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("todo");
        }
        ToDo addition = new ToDo(input.substring(len + 1));
        if (arListTask.contains(addition)) {
            throw new DuplicateException(input);
        }
        new TaskList().addTask(addition, arListTask);
        assert input.length() > 4 : "todo input is invalid";
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    /**
     * A method to get the reply if input is bye.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifBye(String input, ArrayList<Task> arListTask) throws BernException {
        return "Bye. Hope to see you soon and hope you found my service useful!";
    }

    /**
     * A method to get the reply if input is list.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifList(String input, ArrayList<Task> arListTask) throws BernException {
        String result = "";

        for (int i = 0; i < arListTask.size(); i++) {
            result += String.valueOf(i + 1)
                    + ". "
                    + arListTask.get(i).toString()
                    + (i == arListTask.size() - 1 ? "" : "\n");
        }
        if (arListTask.size() == 0) {
            result = "There is no task.";
        }

        return result;
    }

    /**
     * A method to get the reply if input starts with delete.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifDelete(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "delete".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!new Parser().isANumber(input.substring(7))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(7)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(len + 1)) - 1;
        String temp = arListTask.get(index).toString();
        new TaskList().removeTask(index, arListTask);
        assert input.length() > 6 : "delete input is invalid";
        return "Noted! I've removed this task:\n"
                + temp + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list");
    }

    /**
     * A method to get the reply for any command.
     *
     * @param c The command type as delineated by enum class Command.
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String getReply(Command c, String input, ArrayList<Task> arListTask) throws BernException {
        switch (c) {
        case DONE:
            return new Ui().ifDone(input, arListTask);
        case DEADLINE:
            return ifDeadline(input, arListTask);
        case EVENT:
            return ifEvent(input, arListTask);
        case TODO:
            return ifToDo(input, arListTask);
        case BYE:
            return ifBye(input, arListTask);
        case FIND:
            return ifFind(input, arListTask);
        case LIST:
            return ifList(input, arListTask);
        case DELETE:
            return ifDelete(input, arListTask);
        case INVALID:
            throw new InvalidCommandException(input);
        default:
            return "";
        }
    }

    /***
     * A method to get the reply if input starts with find.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifFind(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "find".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("find");
        }
        String word = input.substring(len + 1);
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : arListTask) {
            if (t.canFindWord(word)) {
                found.add(t);
            }
        }

        String result = "Here are the matching tasks in your list:\n";

        for (int i = 0; i < found.size(); i++) {
            result += String.valueOf(i + 1)
                    + ". "
                    + found.get(i).toString()
                    + (i == found.size() - 1 ? "" : "\n");
        }
        if (found.size() == 0) {
            result = "There is no matching task.";
        }

        assert input.length() > 4 : "find input is invalid";

        return result;
    }

    /**
     * A method to get the reply if input starts with done.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifDone(String input, ArrayList<Task> arListTask) throws BernException {
        int len = "done".length();
        if (input.length() == len || (input.length() == len + 1 && input.substring(len, len + 1).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!new Parser().isANumber(input.substring(5))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(5)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(5)) - 1;
        arListTask.get(index).markAsDone();
        assert input.length() > 4 : "done input is invalid";
        return "Good job! I've marked this task as done:\n"
                + arListTask.get(index).toString();
    }

    /**
     * A method to process input from parsing to replying to writing into file.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     */
    public String getReplyUniversal(String input, ArrayList<Task> arListTask) {
        try {
            if (parser.isDone(input)) {
                storage.writeIntoFile(arListTask);
                return new Ui().getReply(Command.DONE, input, arListTask);
            } else if (parser.isDeadline(input)) {
                storage.writeIntoFile(arListTask);
                return new Ui().getReply(Command.DEADLINE, input, arListTask);
            } else if (parser.isEvent(input)) {
                storage.writeIntoFile(arListTask);
                return new Ui().getReply(Command.EVENT, input, arListTask);
            } else if (parser.isToDo(input)) {
                storage.writeIntoFile(arListTask);
                return new Ui().getReply(Command.TODO, input, arListTask);
            } else if (parser.isBye(input)) {
                return new Ui().getReply(Command.BYE, input, arListTask);
            } else if (parser.isList(input)) {
                storage.writeIntoFile(arListTask);
                return new Ui().getReply(Command.LIST, input, arListTask);
            } else if (parser.isDelete(input)) {
                return new Ui().getReply(Command.DELETE, input, arListTask);
            } else if (parser.isFind(input)) {
                return new Ui().getReply(Command.FIND, input, arListTask);
            } else {
                return new Ui().getReply(Command.INVALID, input, arListTask);
            }
        } catch (Exception e) {
            if (e instanceof BernException) {
                return e.getMessage();
            } else {
                return "I only handle some errors as specified in IP requirements. "
                        + "Sorry this error wasn't handled. :'(";
            }
        }
    }
}
