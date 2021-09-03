package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.ToDo;

/**
 * This class handles commands meant for adding tasks.
 */
public class AddCommand implements Command {

    private String description;
    private LocalTime startTime;
    private LocalTime endTime;
    private Object localDateTime;
    private LocalDate localDate;
    private String taskType;

    /**
     * Constructor which takes in task description and a letter as task type.
     *
     * @param description String that is the name of task.
     * @param taskType String that is a letter to describe task type.
     */
    public AddCommand(String description, String taskType) {
        this.description = description;
        this.taskType = taskType;
    }

    /**
     * Constructor which takes in task description, a letter as task type
     * and Object which is either localDate or localDateTime
     *
     * @param description String that is the name of task.
     * @param dateObj Object that is either localDate or localDateTime.
     * @param taskType String that is a letter to describe task type.
     */
    public AddCommand(String description, Object dateObj, String taskType) {
        this.description = description;
        this.localDateTime = dateObj;
        this.taskType = taskType;

    }

    /**
     * Constructor which takes in task description, a letter for task type,
     * two LocalTimes and a LocalDate.
     *
     * @param description String that is the name of task.
     * @param localDate LocalDate type to represent due date.
     * @param startTime LocalTime to represent starting time of event.
     * @param endTime LocalTime to represent ending time of event.
     * @param taskType String that is a letter to describe task type.
     */
    public AddCommand(String description, LocalDate localDate,
                      LocalTime startTime, LocalTime endTime, String taskType) {
        this.description = description;
        this.localDate = localDate;
        this.startTime = startTime;
        this.endTime = endTime;
        this.taskType = taskType;
    }

    @Override
    public String execute(TaskList taskList) {
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
            assert false : taskType;
        }

        return output;
    }
}
