package duke.util;

import duke.command.*;
import duke.exception.DukeException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Parser {
    /**
     * Returns a Task recovered from the record.
     * @param record contain the information of a task
     * @return the task recovered from the record.
     */
    static Task convertRecordToTask(String record) {
        String[] strings = record.split("&&");
        assert strings.length > 0 : "Number of words in a record should > 0";
        switch (strings[0]) {
        case "T":
            return new Todo(strings[2], strings[1].equals("Done"));
        case "D":
            return new Deadline(strings[2], strings[3], strings[1].equals("Done"));
        case "E":
            return new Event(strings[2], strings[3], strings[1].equals("Done"));
        default:
            return null;
        }
    }



    /**
     * Parses the input
     * @param input
     * @param taskList
     * @param ui
     * @return
     */
    static Command parseInput(String input, TaskList taskList, Ui ui) throws DukeException {
        String[] infos = input.split(" ", 2);
        assert infos.length > 0 : "Number of words in input should > 0";
        String type = infos[0];
        switch (type) {
        case "bye":
            return new ByeCommand();
        case "list":
            return new ListCommand(infos);
        case "schedule":
            return new ScheduleCommand(infos);
        case "done":
            return new DoneCommand();
        case "find":
        case "delete":
            return new DeleteCommand(infos);
        case "deadline":
            //fall through
        case "todo":
            //fall through
        case "event":
            return new AddCommand(infos);
        default:
            throw new DukeException("The input cannot be recognized.");
        }
    }


    /**
     * Returns error messages or a string showing the added task.
     * The input string must follow input format, otherwise error messages will be return.
     * The new task will be created and added to lst.
     * @param input input message
     * @param taskList the taskList containing all tasks
     * @return a string showing the added task and number of tasks
     */
    static String addList(String input, TaskList taskList) {
        ArrayList<Task> lst = taskList.getTasks();
        ArrayList<String> validType = new ArrayList<>(
                Arrays.asList("deadline", "event", "todo"));
        String type = input.split(" ", 2)[0];
        String content;
        if (!validType.contains(type)) {
            return "I'm sorry, but I don't know what that means :-(";
        }
        try {
            content = input.split(" ", 2)[1];
        } catch (IndexOutOfBoundsException e) {
            return "The description of a todo cannot be empty.";
        }

        if (type.equals("todo")) {
            lst.add(new Todo(content));
        } else if (type.equals("deadline")) {
            String[] strings = content.split(" /by ");
            if (strings.length != 2) {
                return "Please check the format of your deadline.";
            }
            lst.add(new Deadline(content.split(" /by ")[0], content.split(" /by ")[1]));
        } else if (type.equals("event")) {
            String[] strings = content.split(" /at ");
            if (strings.length != 2) {
                return "Please check the format of your event.";
            }
            lst.add(new Event(content.split(" /at ")[0], content.split(" /at ")[1]));
        }

        String output = "    ____________________________________________________________\n"
                + "     Got it. I've added this task: \n"
                + "      " + lst.get(lst.size() - 1).toString() + "\n"
                + "     Now you have " + lst.size() + " tasks in the list. \n"
                + "    ____________________________________________________________\n";
        return output;
    }


    /**
     * Returns the message that shows which task is marked as completed.
     * Mark the task indicated by index as completed.
     * @param index the index of task to be marked
     * @param taskList the taskList containing all tasks
     * @return the messages
     */
    static String doTask(String index, TaskList taskList) {
        int idx;
        ArrayList<Task> tasks = taskList.getTasks();
        try {
            idx = Integer.parseInt(index);
            tasks.get(idx - 1);
        } catch (NumberFormatException nfe) {
            return "Please check the format of the index.";
        } catch (IndexOutOfBoundsException e) {
            return "The task does not exist in task list.";
        }

        StringBuilder s = new StringBuilder();
        s.append("    ____________________________________________________________\n");
        s.append("    Nice! I've marked this task as done: \n");

        tasks.get(idx - 1).setDone();
        s.append("       " + tasks.get(idx - 1).toString() + "\n");

        s.append("    ____________________________________________________________\n");

        return s.toString();
    }


}


