package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.ToDo;
import duke.Ui;

public class AddCommand implements Command {

    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Object localDateTime;
    private LocalDate localDate;
    private String taskType;

    public AddCommand(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    public AddCommand(String description, Object dateObj, String taskType) {
        this.description = description;
        this.localDateTime = dateObj;
        this.taskType = taskType;

    }

    public AddCommand(String description, LocalDate localDate,
                      LocalTime startTime, LocalTime endTime, String taskType) {
        this.description = description;
        this.localDate = localDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList taskList, Ui ui) {
        String output = "";

        switch (taskType) {
        case "todo":
            Task toDo = new ToDo(description);
            output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                    taskList.addToList(toDo), taskList.taskCount());
            break;
        case "deadline":
            Task deadline = new Deadline(description, localDateTime);
            output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                    taskList.addToList(deadline), taskList.taskCount());
            break;
        case "event":
            Task event = new Event(description, localDate, startTime, endTime);
            output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                    taskList.addToList(event), taskList.taskCount());
            break;
        default:
            break;
        }

        return output;
    }

    @Override
    public boolean isRunning() {
        return true;
    }
}
