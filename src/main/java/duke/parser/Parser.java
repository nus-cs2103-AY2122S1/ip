package duke.parser;

import duke.storage.Storage;
import duke.data.TaskList;
import duke.exception.*;
import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.ui.Ui;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

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
                   return ui.showBye();
               }

               if (input.equals("list")) {
                   return ui.showTaskList(this.tasks, "list");
               }

               if (input.contains("done")) {
                   int taskNum = getNum(input);
                   Task task = tasks.getTask(taskNum);
                   task.markAsDone();
                   storage.updateData(tasks);
                   return ui.showTaskDone(task);
               }

               if (input.contains("todo")) {
                   String name = getName(input);
                   if (!name.equals("")) {
                       ToDo toDo = new ToDo(name);
                       tasks.addTask(toDo);
                       storage.updateData(tasks);
                       return ui.showTaskAdded(toDo, tasks.getSize());
                   }
               }

               if (input.contains("deadline")) {
                   String description = getName(input);
                   if (checkDescriptionIsValid(description, "/by", "deadline")) {
                       String[] parts = description.split("/by");
                       String name = parts[0];
                       LocalDateTime by = getDateTime(parts[1]);

                       Deadline deadline = new Deadline(name, by);
                       tasks.addTask(deadline);
                       storage.updateData(tasks);
                       return ui.showTaskAdded(deadline, tasks.getSize());
                   }
               }

               if (input.contains("event")) {
                   String description = getName(input);
                   if (checkDescriptionIsValid(description, "/at", "event")) {
                       String[] parts = description.split("/at");
                       String name = parts[0];
                       LocalDateTime at = getDateTime(parts[1]);

                       Event event = new Event(name, at);
                       tasks.addTask(event);
                       storage.updateData(tasks);
                       return ui.showTaskAdded(event, tasks.getSize());
                   }
               }

               if (input.contains("delete")) {
                   int taskNum = getNum(input);
                   Task task = tasks.getTask(taskNum);
                   tasks.deleteTask(task);
                   storage.updateData(tasks);
                   return ui.showTaskDeleted(task, tasks.getSize());
               }

               if (input.contains("find")) {
                   String keyword = getName(input);
                   if (!keyword.equals("")) {
                       TaskList t = tasks.matchTasks(keyword);
                       return ui.showTaskList(t, "find");
                   }
               }
               return invalidEntry();
    }

    private String invalidEntry() throws InvalidEntryException {
        throw new InvalidEntryException("error");
    }

    private int getNum(String s) throws NoNumberException, TaskNotFoundException {
        try {
            if (s.contains(" ")) {
                String[] parts = s.split(" ", 2);

                assert parts[0].equals("done") || parts[0].equals("delete")
                        : "Invalid Keyword";

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

            boolean isKeyword = (parts[0].equals("todo") || parts[0].equals("deadline")
                    || parts[0].equals("event") || parts[0].equals("find"));
            assert isKeyword : "Invalid Keyword!";

            if (!parts[1].equals("")) {
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

    private boolean checkDescriptionIsValid(String description, String pre, String task)
            throws TaskNoDateTimeException, TaskNoNameException, InvalidDescriptionException, MissingPreException {

        boolean hasPreposition = description.contains(pre);
        boolean hasNameOrDateTime = !description.equals("") && !description.equals(pre);
        boolean isMissingName = description.startsWith(pre);
        boolean isMissingDateTime = description.endsWith(pre);

        if (hasPreposition) {
            if (hasNameOrDateTime) {
                if(isMissingName) {
                    throw new TaskNoNameException("error", task);
                } else if (isMissingDateTime) {
                    throw new TaskNoDateTimeException("error", task);
                }
                return true;
            }
            throw new InvalidDescriptionException("error", task);
        }
        throw new MissingPreException("error", pre);
    }
}
