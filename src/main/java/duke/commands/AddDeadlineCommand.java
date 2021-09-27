package duke.commands;

import java.time.LocalDate;
import java.time.LocalDateTime;

import duke.Deadline;
import duke.Task;
import duke.TaskList;

/**
 * This class handles command meant for adding tasks with deadline.
 */
public class AddDeadlineCommand implements Command {

    private String description;
    private Object localDateTime;
    private int recurrenceCount;
    private String recurrenceType;
    private boolean isRecurringTask = false;

    /**
     * Constructor which takes in task description and Object which is either
     * localDate or localDateTime.
     *
     * @param description String that is the name of task.
     * @param dateObj Object that is either localDate or localDateTime.
     */
    public AddDeadlineCommand(String description, Object dateObj) {
        this.description = description;
        localDateTime = dateObj;
    }
    /**
     * Constructor which takes in task description and Object which is either
     * localDate or localDateTime.
     *
     * @param description String that is the name of task.
     * @param dateObj Object that is either localDate or localDateTime.
     * @param recurrenceType String that tells if tasks occur daily or weekly.
     * @param recurrenceCount int that tells how many times the task should be repeated.
     */
    public AddDeadlineCommand(String description, Object dateObj,
                              String recurrenceType, int recurrenceCount) {
        this.description = description;
        localDateTime = dateObj;
        this.recurrenceType = recurrenceType;
        this.recurrenceCount = recurrenceCount;
        isRecurringTask = true;
    }

    private void addRecurringTask(TaskList taskList) {
        int intervalMultiplier = recurrenceType.equals("/weekly") ? 7 : 1;

        for (int i = 0; i < recurrenceCount; i++) {
            if (localDateTime instanceof LocalDateTime) {
                LocalDateTime dateTime = (LocalDateTime) localDateTime;
                Task deadline = new Deadline(description, dateTime.plusDays(i * intervalMultiplier));
                taskList.addToList(deadline);
            } else {
                LocalDate date = (LocalDate) localDateTime;
                Task deadline = new Deadline(description, date.plusDays(i * intervalMultiplier));
                taskList.addToList(deadline);
            }
        }
    }

    @Override
    public String execute(TaskList taskList) {
        String output;

        if (isRecurringTask) {
            addRecurringTask(taskList);
            output = "Got it. I've added the task for " + recurrenceCount + " times.";
        } else {
            Task deadline = new Deadline(description, localDateTime);
            output = String.format("Got it. I've added this task:\n%s\nNow you have %d tasks in the list.",
                taskList.addToList(deadline), taskList.taskCount());
        }
        return output;
    }
}
