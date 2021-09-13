package logic;

import dao.TaskDao;
import model.Task;
import ui.IUi;

import java.time.LocalDate;
import java.util.List;

/**
 * A Task than runs infinitely to send reminder about task every new day.
 */
public class ReminderTask implements Runnable {
    private final TaskDao taskDao;
    private final IUi ui;
    
    private LocalDate currentDate = LocalDate.now();
    
    /**
     * Creates A Task that display reminders.
     *
     * @param taskDao TaskDao.
     * @param ui IUi.
     */
    public ReminderTask(TaskDao taskDao, IUi ui) {
        this.taskDao = taskDao;
        this.ui = ui;
        
        displayReminder();
    }
    
    private void displayReminder() {
        List<Task> tasks = taskDao.getByTiming(currentDate.plusDays(1));
        
        String reminderMessage = "Sup Dude, here are the events and deadlines for today!";
        
        if (!tasks.isEmpty()) {
            ui.printSentence(reminderMessage);
            ui.printIndexedList(tasks);
        } else {
            ui.printSentence("There is no events for today! Good job lad");
        }
    }
    
    /**
     * {@inheritDoc}
     * <p> Runs the task as a runnable. </p>
     */
    @Override
    @SuppressWarnings("InfiniteLoopStatement")
    public void run() {
        while (true) {
            LocalDate checkDate = LocalDate.now();
            
            if (checkDate.isAfter(currentDate)) {
                System.out.println(checkDate);
                System.out.println(currentDate);
                currentDate = checkDate;
                displayReminder();
            }
        }
    }
}
