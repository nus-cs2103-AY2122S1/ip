package duke.commands;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;

import duke.Storage;
import duke.Ui;
import duke.tasks.Deadline;
import duke.tasks.Event;
import duke.tasks.Task;
import duke.TaskList;

public class OnDateCommand extends Command {
    private String dateString;

    public OnDateCommand(String dateString) {
        this.dateString = dateString;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        boolean isFormattedDate = true;
        try {
            LocalDate.parse(this.dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        } catch (DateTimeParseException e) {
            isFormattedDate = false;
        }
        if (isFormattedDate) {
            identifyTasksByDate(dateString, tasks);
        }
    };

    public void identifyTasksByDate(String dateString, TaskList tasks) {
        LocalDate date = LocalDate.parse(dateString, DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        ArrayList<Task> tasksOnDate = new ArrayList<>();
        ArrayList<Task> taskList = tasks.getTaskList();
        for (Task task : taskList) {
            if (task instanceof Deadline) {
                Deadline deadline = (Deadline) task;
                if (deadline.getDateFormatted() != null) {
                    if (deadline.getDateFormatted().equals(date)) tasksOnDate.add(deadline); 
                }
            } else if (task instanceof Event) {
                Event event = (Event) task;
                if (event.getTimeFormatted() != null) {
                    LocalDate eventDate = event.getTimeFormatted().toLocalDate();
                    if (eventDate.equals(date)) {
                        tasksOnDate.add(event);
                    }
                }
            }
        }
        System.out.println("On this day you have the following task(s):");
        for (Task task : tasksOnDate) {
            task.showThisTask();
        }
    }

    public boolean isExit() {
        return false;
    }
}
