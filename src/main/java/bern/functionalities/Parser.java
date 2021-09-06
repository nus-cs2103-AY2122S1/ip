package bern.functionalities;

import bern.model.Deadline;
import bern.model.Event;
import bern.model.Task;
import bern.model.ToDo;

/**
 * A class to contain all methods parsing related.
 */
public class Parser {
    /**
     * A method to create a ToDo instance from a string command.
     *
     * @param s The command from which a ToDo instance will be made.
     * @return The ToDo instance.
     */
    public ToDo toDoFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7);
        assert desc.length() > 0 : "description is empty";
        ToDo ans = new ToDo(desc);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    /**
     * A method to create a Deadline instance from a string command.
     *
     * @param s The command from which a Deadline instance will be made.
     * @return The Deadline instance.
     */
    public Deadline deadlineFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(by: ") - 1);
        assert desc.length() > 0 : "description is empty";
        String by = s.substring(s.indexOf("(by: ") + 5, s.length() - 1);
        assert by.length() > 0 : "by is empty";
        Deadline ans = new Deadline(desc, by);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    /**
     * A method to create an Event instance from a string command.
     *
     * @param s The command from which an Event instance will be made.
     * @return The Event instance.
     */
    public Event eventFromString(String s) {
        boolean isDone = s.substring(4, 5).equals("X");
        String desc = s.substring(7, s.indexOf("(at: ") - 1);
        assert desc.length() > 0 : "description is empty";
        String at = s.substring(s.indexOf("(at: ") + 5, s.length() - 1);
        assert at.length() > 0 : "at is empty";
        Event ans = new Event(desc, at);
        if (isDone) {
            ans.markAsDone();
        }
        return ans;
    }

    /**
     * A method to create a Task instance (can be Deadline, Event, or ToDo) from a string command.
     *
     * @param s The command from which a Task instance will be made.
     * @return The Task instance.
     */
    public Task taskFromString(String s) {
        String cat = s.substring(1, 2);
        assert cat.length() > 0 : "category is empty";
        return cat.equals("T")
                ? toDoFromString(s)
                : cat.equals("D")
                ? deadlineFromString(s)
                : eventFromString(s);
    }

    /**
     * A method to check whether a given string can be parsed as a number (integer).
     *
     * @param s The given string.
     * @return A boolean to indicate whether it's a number or not.
     */
    public boolean isANumber(String s) {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * A method to check whether a command is of category "Done".
     *
     * @param input The command given.
     * @return Boolean whether the command is of category "Done".
     */
    public boolean isDone(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("done");
    }

    /**
     * A method to check whether a command is of category "Deadline".
     *
     * @param input The command given.
     * @return Boolean whether the command is of category "Deadline".
     */
    public boolean isDeadline(String input) {
        return input.length() >= 8 && input.substring(0, 8).equals("deadline");
    }

    /**
     * A method to check whether a command is of category "Event".
     *
     * @param input The command given.
     * @return Boolean whether the command is of category "Event".
     */
    public boolean isEvent(String input) {
        return input.length() >= 5 && input.substring(0, 5).equals("event");
    }

    /**
     * A method to check whether a command is of category "ToDO".
     *
     * @param input The command given.
     * @return Boolean whether the command is of category "ToDO".
     */
    public boolean isToDo(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("todo");
    }

    /**
     * A method to check whether a command is of category "Delete".
     *
     * @param input The command given.
     * @return Boolean whether the command is of category "Delete".
     */
    public boolean isDelete(String input) {
        return input.length() >= 6 && input.substring(0, 6).equals("delete");
    }

    /**
     * A method to check whether a command is "Bye".
     *
     * @param input The command given.
     * @return Boolean whether the command is "Bye".
     */
    public boolean isBye(String input) {
        return input.equals("bye");
    }

    /**
     * A method to check whether a command is "List".
     *
     * @param input The command given.
     * @return Boolean whether the command is "List".
     */
    public boolean isList(String input) {
        return input.equals("list");
    }

    public boolean isFind(String input) {
        return input.length() >= 4 && input.substring(0, 4).equals("find");
    }
}
