package TiTi.util;

import TiTi.task.Task;
import TiTi.task.Event;
import TiTi.task.Deadline;
import TiTi.task.ToDo;

import java.util.Scanner;


public class Parser {
    private SavedHistory savedHistory;
    private TaskList taskList;
    private Scanner sc;

    public Parser(SavedHistory savedHistory, TaskList taskList) {
        this.savedHistory = savedHistory;
        this.taskList = taskList;
        sc = new Scanner(System.in);
    }

    public Response cue() {
        String input = sc.nextLine();
        String cue = input.split(" ", 2)[0];

        // handle missing description
        if ((cue.equals("todo") || cue.equals("deadline") || cue.equals("event"))
                && input.split(" ", 2).length == 1) {
            return new Response(Response.Cue.MISSINGDESCRIPTION, null);
        }

        int taskNumber;
        int numberOfTasks = taskList.size();
        String[] temp;
        String description;
        String time;

        switch (cue) {
        case "bye":
            savedHistory.saveHistory(taskList);
            return new Response(Response.Cue.EXIT, null);

        case "list":
            return new Response(Response.Cue.LIST, null);

        case "done":
            taskNumber = Integer.parseInt("" + input.charAt(5));
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASKERROR, null);
            } else {
                Task task = taskList.get(taskNumber - 1);
                task.complete();
                return new Response(Response.Cue.DONE, task);
            }

        case "delete":
            taskNumber = Integer.parseInt("" + input.charAt(7));
            if (taskNumber > numberOfTasks) {
                return new Response(Response.Cue.TASKERROR, null);
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

        default:
            return new Response(Response.Cue.UNRECOGNISED, null);
        }
    }
    
}
