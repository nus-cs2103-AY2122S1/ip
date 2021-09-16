package duke;

import java.util.Calendar;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import duke.controller.MainWindow;
import duke.task.Task;
import javafx.application.Platform;

/**
 * Class that is in charge of interacting with user.
 */
public class Ui {
    private MainWindow mainWindow;

    /**
     * Says welcome to the user when they open duke bot.
     */
    public String greet() {
        return "Hello! I'm duke! What can I do for you?";
    }

    /**
     * Says GoodBye to the user when they leave.
     */
    public String bye() {
        return "Click close button on the top right corner to exit the bot.";
    }

    /**
     * Prints out the error.
     *
     * @param errorMessage ErrorMessage throws by Duke.
     */
    public String showError(String errorMessage) {
        return errorMessage;
    }

    /**
     * Prints all tasks in TaskList.
     *
     * @param tasks TaskList that is going to be printed.
     */
    public String listAllTasks(TaskList tasks) {
        String result = new String();
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + "." + tasks.get(i).toString() + "\n";
        }
        return result;
    }

    /**
     * Notifies users that certain task has been marked as done.
     *
     * @param task The task that's going to be marked as done.
     */
    public String markAsDone(Task task) {
        return "Nice! I've marked this task as done:\n"
                + task.toString();
    }

    /**
     * Notifies users that certain tasks has been added.
     *
     * @param task         Task that has been added.
     * @param taskListSize Size of the updated taskList.
     */
    public String addTask(Task task, int taskListSize) {
        return "Got it. I've added this task:\n"
                + task.toString()
                + "\nNow you have "
                + taskListSize + " tasks in the list.";
    }

    /**
     * Notifies the user that certain task has been deleted.
     *
     * @param task         Task that has been deleted.
     * @param taskListSize Size of the updated taskList.
     */
    public String deleteTask(Task task, int taskListSize) {
        return "Successfully deleted task"
                + task.toString()
                + "\nNow you have" + taskListSize + " tasks in the list.";
    }

    /**
     * Connects mainWindow to this duke.
     *
     * @param mainWindow The GUI main window.
     */
    public void setMainWindow(MainWindow mainWindow) {
        this.mainWindow = mainWindow;
    }

    /**
     * Adds tasks to the reminder.
     *
     * @param tasks TaskList of tasks that need to be reminded.
     */
    public void addReminder(TaskList tasks) {
        assert (mainWindow != null);
        List<Task> reminderList = tasks.getReminderList();
        for (Task task : reminderList) {
            TimerTask timeTask = new TimerTask() {
                @Override
                public void run() {
                    Platform.runLater(() -> {
                        mainWindow.displayReminder("reminder!" + task.toString());
                    });
                }
            };

            Calendar date = Calendar.getInstance();
            date.set(Calendar.YEAR, task.getReminderTime().getYear());
            date.set(Calendar.MONTH, task.getReminderTime().getMonthValue() - 1);
            date.set(Calendar.DAY_OF_MONTH, task.getReminderTime().getDayOfMonth());
            date.set(Calendar.HOUR_OF_DAY, task.getReminderTime().getHour());
            date.set(Calendar.MINUTE, task.getReminderTime().getMinute());
            date.set(Calendar.SECOND, 0);
            date.set(Calendar.MILLISECOND, 0);

            Timer timer = new Timer();
            timer.schedule(timeTask, date.getTime());
        }
    }
}
