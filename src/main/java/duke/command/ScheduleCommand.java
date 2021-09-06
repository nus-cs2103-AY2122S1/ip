package duke.command;

import duke.task.Task;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.time.LocalDate;
import java.util.ArrayList;

public class ScheduleCommand implements Command {
    private String[] inputs;
    public ScheduleCommand(String[] inputs) {
        this.inputs = inputs;
    }

    public String execute(TaskList taskList, Ui ui, Storage storage) {

    }

    /**
     * Returns the information of all tasks in a certain date.
     * @param info specify the date of tasks to be returned
     * @param taskList the taskList that stores all the tasks
     * @return a string containing information of all tasks in the date.
     */
    String getSchedule(String info, TaskList taskList) {
        ArrayList<Task> tasks = taskList.getTasks();
        String output = "";
        LocalDate date;
        try {
            date = LocalDate.parse(info.split(" ")[1]);
        } catch (Exception e) {
            return "Wrong format of date";
        }

        for (Task t : tasks) {
            if (date.equals(t.getDate())) {
                //System.out.println(t.toString());
                output += (t.toString() + "\n");
            }
        }
        return output;
    }

}
