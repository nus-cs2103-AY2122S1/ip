import task.Deadline;
import task.Event;
import task.Task;

import java.time.LocalDate;

public class TasksOnCommand extends Command{
    LocalDate date;

    public TasksOnCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) {
        TaskList list = new TaskList();
        for (int i = 0; i < taskList.size(); i++) {
            Task task = taskList.get(i);
            if (task instanceof Deadline) {
                if (((Deadline) task).getDate().equals(date)) {
                    list.add(task);
                }
            }
            if (task instanceof Event) {
                if (((Event) task).getDate().equals(date)) {
                    list.add(task);
                }
            }
        }
        ui.displayTasksOn(date, list);
    }
}
