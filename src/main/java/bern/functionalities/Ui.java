package bern.functionalities;

import bern.Command;
import bern.exception.BernException;
import bern.exception.EmptyDescriptionException;
import bern.exception.IndexException;
import bern.exception.InvalidCommandException;
import bern.model.Deadline;
import bern.model.Event;
import bern.model.Task;
import bern.model.ToDo;

import java.util.ArrayList;

/**
 * A class to encapsulates all methods UI related.
 */
public class Ui {

    Parser parser = new Parser();
    Storage storage = new Storage();

    /**
     * A method to get the reply if input starts with deadline.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     * @return The reply by the bot.
     * @throws BernException If input is invalid.
     */
    public String ifDeadline(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 8 || (input.length() == 9 && input.substring(8, 9).equals(" "))) {
            throw new EmptyDescriptionException("deadline");
        }
        // TODO Haven't handled if there is no /by
        String task = input.substring(9, input.indexOf('/') - 1);
        String by = input.substring(input.indexOf('/') + 4);
        new TaskList().addTask(new Deadline(task, by), arListTask);
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
        if (input.length() == 5 || (input.length() == 6 && input.substring(5, 6).equals(" "))) {
            throw new EmptyDescriptionException("event");
        }
        // TODO Haven't handled if there is no /at
        String task = input.substring(6, input.indexOf('/') - 1);
        String at = input.substring(input.indexOf('/') + 4);
        new TaskList().addTask(new Event(task, at), arListTask);
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list")
        ;
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
        if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
            throw new EmptyDescriptionException("todo");
        }
        new TaskList().addTask(new ToDo(input.substring(5)), arListTask);
        return "Got it. I've added this task:\n"
                + arListTask.get(arListTask.size() - 1).toString() + "\n"
                + "Now you have " + String.valueOf(arListTask.size())
                + (arListTask.size() == 1 ? " task in the list" : " tasks in the list")
        ;
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
        if (input.length() == 6 || (input.length() == 7 && input.substring(6, 7).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!new Parser().isANumber(input.substring(7))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(7)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(7)) - 1;
        String temp = arListTask.get(index).toString();
        new TaskList().removeTask(index, arListTask);
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
        case LIST:
            return ifList(input, arListTask);
        case DELETE:
            return ifDelete(input, arListTask);
        case INVALID:
            throw new InvalidCommandException(input);
        }
        return "";
    }

    public String ifFind(String input, ArrayList<Task> arListTask) throws BernException {
        if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
            throw new EmptyDescriptionException("find");
        }
        String word = input.substring(5);
        ArrayList<Task> found = new ArrayList<>();
        for (Task t : arListTask) {
            if (t.findWord(word)) {
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
        if (input.length() == 4 || (input.length() == 5 && input.substring(4, 5).equals(" "))) {
            throw new EmptyDescriptionException("done");
        } else if (!new Parser().isANumber(input.substring(5))) {
            throw new InvalidCommandException(input);
        } else if (Integer.parseInt(input.substring(5)) > arListTask.size()) {
            throw new IndexException(input);
        }
        int index = Integer.parseInt(input.substring(5)) - 1;
        arListTask.get(index).markAsDone();
        return "Good job! I've marked this task as done:\n"
                + arListTask.get(index).toString();
    }

    /**
     * A method to process input from parsing to replying to writing into file.
     *
     * @param input The given command.
     * @param arListTask The initial ArrayList of Tasks.
     */
    public void processInput(String input, ArrayList<Task> arListTask) {
        try {
            if (parser.isDone(input)){
                System.out.println(new Ui().getReply(Command.DONE, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isDeadline(input)) {
                System.out.println(new Ui().getReply(Command.DEADLINE, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isEvent(input)) {
                System.out.println(new Ui().getReply(Command.EVENT, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isToDo(input)) {
                System.out.println(new Ui().getReply(Command.TODO, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isBye(input)){
                System.out.println(new Ui().getReply(Command.BYE, input, arListTask));
            } else if (parser.isList(input)){
                System.out.println(new Ui().getReply(Command.LIST, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isDelete(input)){
                System.out.println(new Ui().getReply(Command.DELETE, input, arListTask));
                storage.writeIntoFile(arListTask);
            } else if (parser.isFind(input)) {
                System.out.println(new Ui().getReply(Command.FIND, input, arListTask));
            } else {
                new Ui().getReply(Command.INVALID, input, arListTask);
            }
        } catch (BernException e) {
            System.out.println(e.getMessage());
        }
    }
}
