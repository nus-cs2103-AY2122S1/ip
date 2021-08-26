package duke;

import duke.Exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;

import java.time.format.DateTimeParseException;

public class Parser {
    private TaskList tasks;

    public Parser(TaskList tasks) {
        this.tasks = tasks;
    }

    public int parse(String command) throws DukeException {
        String[] commandSplit = command.split(" ", 2);
        String commandWord = commandSplit[0].toLowerCase();
        String commandDesc = "";
        if (commandSplit.length == 2) {
            commandDesc = commandSplit[1];
        }

        switch (commandWord) {
        case "done":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! Please specify the task number for the task " +
                        "you want to complete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                this.tasks.markDone(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("\tOOPS!!! Please input a task number instead.");
            }

            break;
        case "list":
            this.tasks.listTasks();

            break;
        case "todo":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! The description of a todo cannot be empty.");
            }
            this.tasks.addTask(new ToDo(commandDesc));

            break;
        case "deadline":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! The description of a deadline cannot be empty.");
            }
            try {
                String[] commandDescSplit = commandDesc.split("/by");
                String dateAndTime = commandDescSplit[1].trim();
                String[] dateAndTimeSplit = dateAndTime.split(" ");
                String date = dateAndTimeSplit[0];
                String time = dateAndTimeSplit[1];
                this.tasks.addTask(new Deadline(commandDescSplit[0].trim(), date, time));
            } catch (DateTimeParseException e) {
                throw new DukeException("You've entered a date or time in an invalid format! " +
                        "\nIt should be in the form: yyyy-mm-dd hrs:mins");
            }


            break;
        case "event":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! The description of a event cannot be empty.");
            }
            String[] commandDescSplit = commandDesc.split("/at");
            this.tasks.addTask(new Event(commandDescSplit[0].trim(), commandDescSplit[1].trim()));

            break;
        case "delete":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! Please specify the task number for the task " +
                        "you want to delete.");
            }
            try {
                int taskNumber = Integer.parseInt(commandDesc);
                this.tasks.deleteTask(taskNumber);
            } catch (NumberFormatException e) {
                System.out.println("\tOOPS!!! Please input a task number instead.");
            }
            break;
        case "find":
            if (commandDesc.equals("")) {
                throw new DukeException("\tOOPS!!! The description of a event cannot be empty.");
            }
            this.tasks.findTask(commandDesc.toLowerCase());
            break;
        case "bye":
            return 1;
        default:
            throw new DukeException("\tOOPS!!! You have entered an invalid command, please try again!");
        }
        return 0;
    }
}
