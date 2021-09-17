package Wonderland;

import Wonderland.task.Task;
import Wonderland.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Wonderland.Wonderland.Command class encapsulates all commands behaviour for a bot.
 */
public class Command {
    /**
     * Returns added message for command to-do.
     *
     * @param description description of to-do Wonderland.Wonderland.task.
     * @param tasks Wonderland.Wonderland.task.TaskList of existing tasks.
     * @return added message for command to-do.
     * @throws DukeException if description is empty.
     */
    public String todo(String description, TaskList tasks) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a todo cannot be empty." +
                    "\nIf you don't think, you shouldn't talk!");
        }
        return tasks.addTodo(description);

    }

    /**
     * Returns added message for command event.
     *
     * @param description description of event Wonderland.Wonderland.task.
     * @param at string of event date/time.
     * @param tasks Wonderland.Wonderland.task.TaskList of list of existing tasks.
     * @return added message for command event.
     * @throws DukeException if description or date/time is empty or  not YYYY-MM-DD.
     */
    public String event(String description, String at, TaskList tasks) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of an event cannot be empty." +
                    "\nIf you don't think, you shouldn't talk!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(at.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Date and Time must be specified by YYYY-MM-DD");
        }

        return tasks.addEvent(description, date);
    }

    /**
     * Returns added message for command deadline.
     *
     * @param description description of deadline Wonderland.Wonderland.task.
     * @param by string of deadline date/time.
     * @param tasks Wonderland.Wonderland.task.TaskList of list of existing tasks.
     * @return added message for command deadline.
     * @throws DukeException if description or date/time is empty or not YYYY-MM-DD.
     */
    public String deadline(String description, String by, TaskList tasks) throws DukeException {
        if (description.isEmpty()) {
            throw new DukeException("The description of a deadline cannot be empty." +
                    "\nIf you don't think, you shouldn't talk!");
        }

        LocalDate date;
        try {
            date = LocalDate.parse(by.trim());
        } catch (DateTimeParseException e) {
            throw new DukeException("OOPS!! Date and Time must be specified by YYYY-MM-DD");
        }

        return tasks.addDeadline(description, date);
    }

    /**
     * Returns message representing list of all items user added.
     *
     * @param tasks Wonderland.Wonderland.task.TaskList of existing tasks.
     * @return formatted string representing elements in records array.
     */
    public String list(TaskList tasks) {
        return tasks.list();
    }

    /**
     * Returns delete message for bot.
     *
     * @param index index of Wonderland.Wonderland.task to be deleted.
     * @param tasks Wonderland.Wonderland.task.TaskList of list of existing tasks.
     * @return delete message for bot.
     */
    public String delete(int index, TaskList tasks) throws DukeException {
        if (index <= 0) {
            throw new DukeException("Index must be greater than 0!! " +
                    "\nJust get it right and stop wasting time! I'm late!");
        } else if (index > tasks.length()) {
            throw new DukeException(String.format("OOPS!!! You only have %1$d tasks",tasks.length()));
        }
        return tasks.delete(index - 1);
    }

    /**
     * Return Wonderland.Wonderland.task mark as done message.
     *
     * @param index index of task that is done.
     * @param tasks TaskList of list of existing tasks.
     * @return Task mark as done message.
     */
    public String done(int index, TaskList tasks) throws DukeException {
        if (index <= 0) {
            throw new DukeException("Index must be greater than 0!! " +
                    "\nJust get it right and stop wasting time! I'm late!");
        } else if (index > tasks.length()) {
            throw new DukeException(String.format("OOPS!!! You only have %1$d tasks",tasks.length()));
        }
        Task done = tasks.getTask(index - 1);
        done.markAsDone();
        return String.format("Nice! I've marked this task as done:\n\t %1$s \n\t", done.toString());
    }


    /**
     * Return message for tasks matched with keyword.
     *
     * @param keyword String of keyword to be matched.
     * @param tasks Wonderland.Wonderland.task.TaskList of list of existing tasks.
     * @return message for all tasks matched with keyword.
     * @throws DukeException if keyword is empty.
     */
    public String find(String keyword, TaskList tasks) throws DukeException {
        if (keyword.isEmpty()) {
            throw new DukeException("OOPS!!! No keyword provided.");
        }
        return tasks.find(keyword);
    }
}
