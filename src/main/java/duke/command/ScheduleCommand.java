package duke.command;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

public class ScheduleCommand implements Command {
    private String[] inputs;
    public ScheduleCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return getSchedule(taskList);
    }

    /**
     * Returns the information of all tasks in a certain date.
     * @param taskList the taskList that stores all the tasks
     * @return a string containing information of all tasks in the date.
     */
    String getSchedule(TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String output = "";
        LocalDate date;

        assert inputs.length >= 2 : "Wrong length of inputs.";

        if (tasks.size() == 0) {
            return "No matched tasks :/";
        }
        date = LocalDate.parse(inputs[1]);
        for (Task t : tasks) {
            if (date.equals(t.getDate())) {
                output += (t.toString() + "\n");
            }
        }
        return output;
    }

}
