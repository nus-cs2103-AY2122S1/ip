import java.time.LocalDate;
import java.util.ArrayList;

public class Ui {
    
    public void showWelcome() {
        System.out.println("  ____________________________________________________________");
        System.out.print("  Hello! I'm Duck.\n  What's up?\n");
        System.out.println("  ____________________________________________________________\n");
    }
    
    public void showOpenLine() {
        System.out.println("  ____________________________________________________________");
    }

    public void showCloseLine() {
        System.out.println("  ____________________________________________________________\n");
    }
    
    public void showInput() {
        System.out.print("> ");
    }
    
    public void showBye() {
        System.out.println("  See you next time!");
    }
    
    public void showList(ArrayList<Task> taskList) {
        System.out.println("  Here are the tasks in your list:");
        for (int i = 0; i < taskList.size(); ++i) {
            System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
        }
    }
    
    public void showDone(Task toSetDone) {
        System.out.print("  Nice! I've marked this task as done:\n    "
                + toSetDone.listEntry() + "\n");
    }
    
    public void showDelete(Task deleted, int listLength) {
        System.out.print("  Noted. I've removed this task:\n    "
                + deleted.listEntry()
                + "\n  Now you have " + listLength + " tasks in the list.\n");
    }
    
    public void showFind(ArrayList<Task> taskList, int listLength, LocalDate desiredDate) {
        System.out.println("  Here are the tasks for the given day:");
        for (int i = 0; i < listLength; ++i) {
            Task currTask = taskList.get(i);
            if (currTask.isTodayTask(desiredDate)) {
                System.out.println("  " + (i + 1) + "." + taskList.get(i).listEntry());
            }
        }
    }
    
    public void showAdd(Task newTask, int listLength) {
        System.out.print("  Got it. I've added this task:\n    "
                + newTask.listEntry()
                + "\n  Now you have " + listLength + " tasks in the list.\n");
    }
    
    public void showException(DukeException e) {
        System.out.println(e.getMessage());
    }
    
    public void showInitialise() {
        System.out.println("  Loading Duke...");
    }
    
    public void showNewDataDirectory() {
        System.out.println("  Data directory does not exist, it has been created!");
    }
    
    public void showNewHardDisk() {
        System.out.println("  Hard disk does not exist, a new one has been created!");
    }
}
