package duke.commands;

import java.time.LocalDate;
import java.time.LocalTime;

import duke.Event;
import duke.Task;
import duke.TaskList;

/**
 * This class handles commands meant for adding tasks with a duration.
 */
public class AddEventCommand implements Command {

    private String description;
    private LocalDate localDate;
    private LocalTime startTime;
    private LocalTime endTime;

    /**
     * Constructor which takes in task description, two LocalTimes as duration
     * and a LocalDate as date of event task.
     *
     * @param description String that is the name of task.
     * @param localDate LocalDate type to represent due date.
     * @param startTime LocalTime to represent starting time of event.
     * @param endTime LocalTime to represent ending time of event.
     */
    public AddEventCommand(String description, LocalDate localDate,
                           LocalTime startTime, LocalTime endTime) {
        this.description = description;
        this.localDate = localDate;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String execute(TaskList taskList) {
        Task event = new Event(description, localDate, startTime, endTime);
        String output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
            taskList.addToList(event), taskList.taskCount());
        return output;
    }
}
