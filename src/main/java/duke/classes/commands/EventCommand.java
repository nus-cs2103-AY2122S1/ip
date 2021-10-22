package duke.classes.commands;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import duke.classes.TaskList;
import duke.classes.exceptions.DukeException;
import duke.classes.tasks.Event;

public class EventCommand extends Command {
    private String[] words;
    private TaskList taskList;
    private DateTimeFormatter standardDate = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    private DateTimeFormatter standardTime = DateTimeFormatter.ofPattern("HH:mm");

    /**
     * Class Constructor
     *
     * @param taskList The tasklist to be modified
     * @param words String[] of details to process
     */
    public EventCommand(TaskList taskList, String[] words) {
        this.taskList = taskList;
        this.words = words;
    }

    /**
     * Creates new to do Task
     *
     * @return String to be output by Duke
     */
    public String execute() throws DukeException {
        List<String> postFilter = taskList.separateDesc("/at", words);
        String desc = postFilter.get(0);

        if (postFilter.get(1).equals("")) {
            throw new DukeException("!!! The date of a event cannot be empty. Use /at to input date !!!");
        } else if (desc.equals("")) {
            throw new DukeException("!!! Input the description then use /at to input date !!!");
        }

        String[] dateTime = postFilter.get(1).split(" ");

        Event newTask;
        LocalDate date = LocalDate.parse(dateTime[0], standardDate);
        if (dateTime.length > 1) {
            // Time is present
            LocalTime time = LocalTime.parse(dateTime[1], standardTime);
            newTask = new Event(desc, date, time);
        } else {
            // Time not present
            newTask = new Event(desc, date);
        }
        taskList.add(newTask);
        return "Got it. I've added this task:\n\t" + newTask
                + "\nNow you have " + taskList.size() + " tasks in the list.";
    }
}
