package parser;

import java.time.format.DateTimeParseException;

import duke.DukeException;
import storage.Storage;
import task.Deadline;
import task.Event;
import task.Task;
import task.TaskList;
import task.ToDo;
import ui.Ui;
import commands.AddCommand;
import commands.Command;
import commands.DeleteCommand;
import commands.ExitCommand;
import commands.FindCommand;
import commands.ListCommand;
import commands.NonsenseCommand;
import commands.TaskType;
import commands.UpdateCommand;

public class Parser {

    private Parser() {

    }

    public static Command parseCommand(String fullCommand) {
        String[] commands = fullCommand.split(" ");
        String first = commands[0];
        String rest = "";
        if (commands.length > 1) {
            rest = fullCommand.substring(first.length() + 1);
        }
        
        switch (first) {
        case "list": {
            return new ListCommand();
        }
        case "done": {
            return new UpdateCommand(rest);
        }
        case "deadline": {
            return new AddCommand(TaskType.DEADLINE, rest);
        }
        case "todo": {
            return new AddCommand(TaskType.TO_DO, rest);
        }
        case "event": {
            return new AddCommand(TaskType.EVENT, rest);
        }
        case "date" : {
            return new FindCommand(TaskType.FIND_BY_DATE, rest);
        }
        case "delete": {
            return new DeleteCommand(rest);
        }
        case "bye": {
            return new ExitCommand();
        }
        default: {
            return new NonsenseCommand();
        }
        }
    }

    // public static void addToDo(String rest, TaskList l, Ui ui) throws DukeException {
    //     if (rest.length() > 0){
    //         ToDo td = new ToDo(rest);
    //         l.addToList(td);
    //         printOutput("Got it. I've added this task:\n" + td + "\nNow you have " + l.getLength() + " tasks in the list.");
    //         // updateFile(l);
    //     } else {
    //         throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
    //     }
    // }

    // public static void delete(String rest, TaskList l) throws DukeException {
    //     if (rest.matches("\\d+")) {
    //         int index = Integer.parseInt(rest) - 1;
    //         if (index >= 0 && index < l.getLength()) {
    //             Task task = l.deleteTask(0);
    //             printOutput("Noted. I've removed this task:\n" + task + "\nNow you have " + l.getLength() + " tasks in the list.");
    //             // updateFile(l);
    //         } else {
    //             throw new DukeException("☹ OOPS!!! Input a valid index"); 
    //         }
    //     } else {
    //         throw new DukeException("☹ OOPS!!! Input a valid index");
    //     }
    // }

    // public static void blah() throws DukeException {
    //     throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
    // }

    // public static void addDeadline(String rest, TaskList l, int complete) throws DukeException {
        
    //     if (rest.length() > 0) {
    //         try{
    //             String[] details = rest.split("/by ");
    //             if (details.length == 1) {
    //                 throw new DukeException("☹ OOPS!!! Add a '/by deadline'");
    //             } else {
    //                 Deadline deadline = new Deadline(details[0], details[1]);
    //                 if (complete == 1) {
    //                     deadline.completeItem();
    //                 }
    //                 l.addToList(deadline);
    //                 printOutput("Got it. I've added this task:\n" + deadline + "\nNow you have " + l.getLength() + " tasks in the list.");
    //                 // updateFile(l);
    //             }
    //         } catch (DateTimeParseException e) {
    //             throw new DukeException("☹ OOPS!!! The date of the deadline is poorly formatted (d/MM/yyyy or d/MM/yyyy HHmm)"); 
    //         }
            
    //     } else {
    //         throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
    //     }
    // }

    // public static void addEvent(String rest, TaskList l, int complete) throws DukeException {
    //     System.out.println(rest);
    //     if (rest.length() > 0) {
    //         try {
    //             String[] details = rest.split("/at ");
    //             if (details.length == 1) {
    //                 throw new DukeException("☹ OOPS!!! Add a '/at time of event'");
    //             } else {
    //                 Event event = new Event(details[0], details[1]);
    //                 if (complete == 1) {
    //                     event.completeItem();
    //                 }
    //                 l.addToList(event);
    //                 printOutput("Got it. I've added this task:\n" + event + "\nNow you have " + l.getLength() + " tasks in the list.");
    //                 // updateFile(l); 
    //             }
    //         } catch (DateTimeParseException e) {
    //             throw new DukeException("☹ OOPS!!! The date of the event is poorly formatted (d/MM/yyyy)");
    //         }
            
    //     } else {
    //         throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
    //     }
    // }

    // public static void getTasksOnDate(String date, TaskList l) {
    //     Task[] tasks = l.tasksOnDate(date);
    //     System.out.println(tasks.length);
    //     for (Task task : tasks) {
    //         System.out.println(task);
    //     }
    // }


    public static void printOutput(String input) {
        String line = "-------------------------------------------------------------------------------";
        System.out.println(line + "\n" + input + "\n" + line + "\n");
    }
}
