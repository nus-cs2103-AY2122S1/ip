package titi.util;

import titi.task.Deadline;
import titi.task.Event;
import titi.task.Task;
import titi.task.ToDo;

/**
 * Represents an interpreter to make sense of user command,
 * and return Response object to tell Ui how to interact with user.
 */
public class Parser {
    private SavedHistory savedHistory;
    private TaskList taskList;

    /**
     * Initialises a Parser instance.
     *
     * @param savedHistory loads and writes to the saved data
     * @param taskList list of current tasks
     */
    public Parser(SavedHistory savedHistory, TaskList taskList) {
        this.savedHistory = savedHistory;
        this.taskList = taskList;
    }

    /**
     * Interprets user command, and update task list as required.
     *
     * @param input user input to be interpreted
     * @return Response object to tell Ui how to respond to user command
     */
    public Response parse(String input) {

        String cue = input.split(" ", 2)[0];

        // check if description is needed
        boolean isNeedDescription = (cue.equals("todo") || cue.equals("deadline")
                || cue.equals("event") || cue.equals("find"));

        // handle missing description
        if (isNeedDescription && input.split(" ", 2).length == 1) {
            return new Response(Response.Cue.MISSING_DESCRIPTION);
        }

        // parse each type of user input
        switch (cue) {
        case "bye":
            return parseBye();

        case "list":
            return parseList();

        case "done":
            return parseDone(input);

        case "delete":
            return parseDelete(input);

        case "todo":
            return parseTodo(input);

        case "deadline":
            return parseDeadline(input);

        case "event":
            return parseEvent(input);

        case "find":
            return parseFind(input);

        default:
            return new Response(Response.Cue.UNRECOGNISED);
        }
    }


    private Response parseBye() {
        savedHistory.saveHistory(taskList);
        return new Response(Response.Cue.EXIT);
    }

    private Response parseList() {
        return new Response(Response.Cue.LIST);
    }

    private Response parseDone(String input) {
        int numberOfTasks = taskList.size();
        String[] taskNumberStrings = input.substring(5).split(" ");
        TaskList tempList = new TaskList();

        for (String taskNumberString: taskNumberStrings) {
            int taskNumber = Integer.parseInt("" + taskNumberString);
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASK_ERROR);
            }
            Task task = taskList.get(taskNumber - 1);
            task.complete();
            tempList.add(task);
        }
        return new Response(Response.Cue.DONE, tempList);
    }

    private Response parseDelete(String input) {
        int numberOfTasks = taskList.size();
        String[] taskNumberStrings = input.substring(7).split(" ");
        TaskList tempList = new TaskList();

        for (String taskNumberString: taskNumberStrings) {
            int taskNumber = Integer.parseInt("" + taskNumberString);
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASK_ERROR);
            }
            Task task = taskList.get(taskNumber - 1);
            tempList.add(task);
        }

        for (int i = 0; i < tempList.size(); i++) {
            taskList.remove(tempList.get(i));
        }

        return new Response(Response.Cue.DELETE, tempList);
    }

    private Response parseTodo(String input) {
        String description = input.split(" ", 2)[1];
        ToDo todo = new ToDo(description);
        taskList.add(todo);
        return new Response(Response.Cue.TODO, todo);
    }

    private Response parseDeadline(String input) {
        String[] temp = input.split(" ", 2)[1].split(" /by ", 2);
        String description = temp[0];
        String time = temp[1];
        Deadline deadline = new Deadline(description, time);
        taskList.add(deadline);
        return new Response(Response.Cue.DEADLINE, deadline);
    }

    private Response parseEvent(String input) {
        String[] temp = input.split(" ", 2)[1].split(" /at ", 2);
        String description = temp[0];
        String time = temp[1];
        Event event = new Event(description, time);
        taskList.add(event);
        return new Response(Response.Cue.EVENT, event);
    }

    private Response parseFind(String input) {
        String description = input.split(" ", 2)[1];
        TaskList tempList = new TaskList();

        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task.isContain(description)) {
                tempList.add(task);
            }
        }
        if (tempList.size() == 0) {
            return new Response(Response.Cue.UNRECOGNISED);
        } else {
            return new Response(Response.Cue.FIND, tempList);
        }
    }
}
