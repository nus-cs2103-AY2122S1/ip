package duke.testinginterface;

import java.time.LocalDate;
import java.util.ArrayList;

import duke.task.Task;

/**
 * TaskList Interface to create the stub.
 */
public interface TaskListInterface {
    public ArrayList<Task> getTasks();
    public void addTask(Task task);
    public void deleteTask(int pos);
    public ArrayList<Task> checkDate(LocalDate date);
}
