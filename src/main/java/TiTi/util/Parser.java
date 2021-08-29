package TiTi.util;

import TiTi.task.Task;
import TiTi.task.Event;
import TiTi.task.Deadline;
import TiTi.task.ToDo;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Make sense of user command, and return Response
 * to tell Ui how to interact with user.
 */
public class Parser {
    private SavedHistory savedHistory;
    private TaskList taskList;
    private Scanner sc;

    /**
     * Constructor for Parser class.
     *
     * @param savedHistory loads and writes to the saved data
     * @param taskList list of current tasks
     */
    public Parser(SavedHistory savedHistory, TaskList taskList) {
        this.savedHistory = savedHistory;
        this.taskList = taskList;
        sc = new Scanner(System.in);
    }

    /**
     * Ask and interpret user command, and update list as required.
     *
     * @return Response object to tell Ui how to respons to user command
     */
    public Response cue() {
        String input = sc.nextLine();
        String cue = input.split(" ", 2)[0];

        // handle missing description
        if ((cue.equals("todo") || cue.equals("deadline")
                || cue.equals("event") || cue.equals("find"))
                && input.split(" ", 2).length == 1) {
            return new Response(Response.Cue.MISSINGDESCRIPTION);
        }

        int taskNumber;
        int numberOfTasks = taskList.size();
        String[] temp;
        String description;
        String time;

        switch (cue) {
        case "bye":
            savedHistory.saveHistory(taskList);
            return new Response(Response.Cue.EXIT);

        case "list":
            return new Response(Response.Cue.LIST);

        case "done":
            taskNumber = Integer.parseInt("" + input.charAt(5));
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASKERROR);
            } else {
                Task task = taskList.get(taskNumber - 1);
                task.complete();
                return new Response(Response.Cue.DONE, task);
            }

        case "delete":
            taskNumber = Integer.parseInt("" + input.charAt(7));
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASKERROR);
            } else {
                Task task = taskList.get(taskNumber - 1);
                taskList.remove(taskNumber - 1);
                return new Response(Response.Cue.DELETE, task);
            }

        case "todo":
            description = input.split(" ", 2)[1];
            ToDo todo = new ToDo(description);
            taskList.add(todo);
            return new Response(Response.Cue.TODO, todo);

        case "deadline":
            temp = input.split(" ", 2)[1].split(" /by ", 2);
            description = temp[0];
            time = temp[1];
            Deadline deadline = new Deadline(description, time);
            taskList.add(deadline);
            return new Response(Response.Cue.DEADLINE, deadline);

        case "event":
            temp = input.split(" ", 2)[1].split(" /at ", 2);
            description = temp[0];
            time = temp[1];
            Event event = new Event(description, time);
            taskList.add(event);
            return new Response(Response.Cue.EVENT, event);

        case "find":
            description = input.split(" ", 2)[1];
            TaskList tempList = new TaskList();
            for (int i = 0; i < taskList.size(); i++) {
                Task task = taskList.get(i);
                if (task.checkString(description)) {
                    tempList.add(task);
                }
            }
            if (tempList.size() == 0) {
                return new Response(Response.Cue.UNRECOGNISED);
            } else {
                return new Response(Response.Cue.FIND, tempList);
            }

        default:
            return new Response(Response.Cue.UNRECOGNISED);
        }
    }

}
