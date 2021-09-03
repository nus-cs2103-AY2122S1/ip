package duke;

import duke.exception.*;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

/**
 * Deals with making sense of user command
 */
public class Parser {

    TaskList tasks;
    Ui ui;
    Storage storage;

    /**
     * Constructs Parser object
     *
     * @param tasks list of tasks
     * @param ui to deal with interactions with users
     * @param storage to update file
     */

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Deals with interpreting user commands
     */
    public String parse(String input) throws DukeException {
               if (input.equals("bye")) {
                   return ui.bye();
               } else if (input.equals("list")) {
                   return ui.showTaskList(this.tasks, "list");
               } else if (input.contains("done")) {
                   int taskNum = getNum(input);
                   Task task = tasks.getTask(taskNum);
                   task.markAsDone();
                   storage.updateData(tasks);
                   return ui.showTaskDone(task);
               } else if (input.contains("todo")) {
                   String name = getName(input);
                   if (!name.equals("")) {
                       ToDo toDo = new ToDo(name);
                       tasks.addTask(toDo);
                       storage.updateData(tasks);
                       return ui.showTaskAdded(toDo, tasks.getSize());
                   }
               } else if (input.contains("deadline")) {
                   String description = getName(input);
                   if (!description.equals("")) {
                       if (descriptionIsValid(description, "/by", "deadline")) {
                           String[] parts = description.split("/by");
                           String name = parts[0];
                           LocalDateTime by = getDateTime(parts[1]);

                           Deadline deadline = new Deadline(name, by);
                           tasks.addTask(deadline);
                           storage.updateData(tasks);
                           return ui.showTaskAdded(deadline, tasks.getSize());
                       }
                   }
               } else if (input.contains("event")) {
                   String description = getName(input);
                   if (!description.equals("")) {
                       if (descriptionIsValid(description, "/at", "event")) {
                           String[] parts = description.split("/at");
                           String name = parts[0];
                           LocalDateTime at = getDateTime(parts[1]);

                           Event event = new Event(name, at);
                           tasks.addTask(event);
                           storage.updateData(tasks);
                           return ui.showTaskAdded(event, tasks.getSize());
                       }
                   }
               } else if (input.contains("delete")) {
                   int taskNum = getNum(input);
                   Task task = tasks.getTask(taskNum);
                   tasks.deleteTask(task);
                   storage.updateData(tasks);
                   return ui.showTaskDeleted(task, tasks.getSize());
               } else if (input.contains("find")) {
                   String keyword = getName(input);
                   if (!keyword.equals("")) {
                       TaskList t = tasks.matchTasks(keyword);
                       return ui.showTaskList(t, "find");
                   }
               }
               return invalidEntry();
    }

    private String invalidEntry() throws InvalidEntryException {
//        try {
//            throw new InvalidEntryException("error");
//        } catch (DukeException e) {
//            e.printError();
//            return "( ⚆ _ ⚆ ) OOPS!!! I'm sorry, but I don't know what that means :-(";
//        }
        throw new InvalidEntryException("error");
    }

    private int getNum(String s) throws NoNumberException, TaskNotFoundException {
        try {
            if (!s.equals("done") && !s.equals("delete")) {
                String[] parts = s.split(" ", 2);
                int num = Integer.parseInt(parts[1]);
                if (num <= 0 || num > this.tasks.getSize()) {
                    throw new TaskNotFoundException("error");
                }
                return num - 1;
            } else {
                throw new NoNumberException("error");
            }
        } catch (NumberFormatException exception) {
            throw new NoNumberException("error");
        }
    }

    private String getName(String s) throws EmptyDescriptionException  {
        if (s.contains(" ")) {
            String[] parts = s.split(" ", 2);
            if (parts[0].equals("todo") || parts[0].equals("deadline")
                    || parts[0].equals("event") || parts[0].equals("find")) {
                if (parts[1].equals("")) {
                    throw new EmptyDescriptionException("error", parts[0]);
                }
                return parts[1];
            }
        } else {
            throw new EmptyDescriptionException("error", s);
        }
        return "";
    }

    private LocalDateTime getDateTime(String s) throws InvalidDateTimeException {
        try{
            String dt = s.replaceFirst(" ", "");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("error");
        }
    }

    private boolean descriptionIsValid(String description, String pre, String task)
            throws TaskNoDateTimeException, TaskNoNameException, EmptyDescriptionException, MissingPreException {
        if (description.contains(pre)) {
            if (!description.equals(pre)
                    && !(description.startsWith(pre) && description.endsWith(" "))
                    && !(description.startsWith(" ") && description.endsWith(pre))) {
                if(description.startsWith(pre)) {
                    throw new TaskNoNameException("error", task);
                } else if (description.endsWith(pre)) {
                    throw new TaskNoDateTimeException("error", task);
                }
                return true;
            } else {
                throw new EmptyDescriptionException("error", task);
            }
        } else {
            throw new MissingPreException("error", pre);
        }
    }
}
