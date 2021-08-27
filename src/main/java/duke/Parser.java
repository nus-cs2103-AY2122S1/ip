package duke;

import duke.task.Task;
import duke.task.ToDo;
import duke.task.Event;
import duke.task.Deadline;
import duke.exception.DukeException;
import duke.exception.EmptyDescriptionException;
import duke.exception.InvalidEntryException;
import duke.exception.MissingPreException;
import duke.exception.NoNumberException;
import duke.exception.TaskNoDateTimeException;
import duke.exception.TaskNoNameException;
import duke.exception.TaskNotFoundException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    TaskList tasks;
    Ui ui;
    Storage storage;

    public Parser(TaskList tasks, Ui ui, Storage storage) {
        this.tasks = tasks;
        this.ui = ui;
        this.storage = storage;
    }

    // parse: read commands
    public void parse() {
           Scanner sc = new Scanner(System.in);
           while (sc.hasNextLine()) {
               String s = sc.nextLine();
               if (s.equals("bye")) {
                   ui.bye();
                   break;
               } else if (s.equals("list")) {
                   ui.showTaskList(this.tasks);
               } else if (s.contains("done")) {
                   int taskNum = getNum(s);
                   if (taskNum != -1) {
                       Task task = tasks.getTask(taskNum);
                       task.markAsDone();
                       ui.showTaskDone(task);
                       storage.updateData(tasks);
                   }
               } else if (s.contains("todo")) {
                   String name = getName(s);
                   if (!name.equals("")) {
                       ToDo toDo = new ToDo(name);
                       tasks.addTask(toDo);
                       ui.showTaskAdded(toDo, tasks.getSize());
                       storage.updateData(tasks);
                   }
               } else if (s.contains("deadline")) {
                   String description = getName(s);
                   if (!description.equals("")) {
                       if (descriptionIsValid(description, "/by", "deadline")) {
                           String[] parts = description.split("/by");
                           String name = parts[0];
                           LocalDateTime by = getDateTime(parts[1]);
                           if (by != null) {
                               Deadline deadline = new Deadline(name, by);
                               ui.showTaskAdded(deadline, tasks.getSize());
                               tasks.addTask(deadline);
                               storage.updateData(tasks);
                           }
                       }
                   }
               } else if (s.contains("event")) {
                   String description = getName(s);
                   if (!description.equals("")) {
                       if (descriptionIsValid(description, "/at", "event")) {
                           String[] parts = description.split("/at");
                           String name = parts[0];
                           LocalDateTime at = getDateTime(parts[1]);
                           if (at != null) {
                               Event event = new Event(name, at);
                               ui.showTaskAdded(event, tasks.getSize());
                               tasks.addTask(event);
                               storage.updateData(tasks);
                           }
                       }
                   }
               } else if (s.contains("delete")) {
                   int taskNum = getNum(s);
                   if (taskNum != -1) {
                       Task task = tasks.getTask(taskNum);
                       tasks.deleteTask(task);
                       ui.showTaskDeleted(task, tasks.getSize());
                       storage.updateData(tasks);
                   }
               } else {
                   invalidEntry();
               }
           }
    }

    private void invalidEntry() {
        try {
            throw new InvalidEntryException("error");
        } catch (DukeException e) {
            e.printError();
        }
    }

    private int getNum(String s) {
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
        } catch (NumberFormatException e) {
            System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter a number!");
        } catch (DukeException e) {
            e.printError();
        }
        return -1;
    }

    private String getName(String s) {
        try {
            if (s.contains(" ")) {
                String[] parts = s.split(" ", 2);
                if (parts[0].equals("todo") || parts[0].equals("deadline") || parts[0].equals("event")) {
                    if (parts[1].equals("")) {
                        throw new EmptyDescriptionException("error", parts[0]);
                    }
                    return parts[1];
                }
            } else {
                throw new EmptyDescriptionException("error", s);
            }
        } catch (DukeException e) {
            e.printError();
        }
        return "";
    }

    private LocalDateTime getDateTime(String s) {
        try{
            String dt = s.replaceFirst(" ", "");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime dateTime = LocalDateTime.parse(dt, formatter);
            return dateTime;
        } catch (DateTimeParseException e) {
            System.out.println("( ⚆ _ ⚆ ) OOPS!!! Please enter date & time in this format (yyyy-MM-dd HH:mm)");
        }
        return null;
    }

    private boolean descriptionIsValid(String description, String pre, String task) {
       try {
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
       } catch (DukeException e) {
           e.printError();
       }
       return false;
    }
}
