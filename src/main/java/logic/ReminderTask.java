package logic;

import dao.TaskDao;
import model.Task;
import ui.IUi;

import java.time.LocalDate;
import java.util.List;

public class ReminderTask implements Runnable {
    private final TaskDao taskDao;
    private final IUi ui;
    
    private LocalDate currentDate = LocalDate.now();
    ;
    
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
        }
    }
    
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
