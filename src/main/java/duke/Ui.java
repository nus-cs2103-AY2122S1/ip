package duke;

import java.time.LocalDate;
import java.util.Scanner;

public class Ui {

    private Scanner scanner = new Scanner(System.in);

    public void showLine() {
        System.out.println("____________________________________________________________\n");
    }
    
    public void showWelcome() {
        showLine();
        System.out.println("Heyllo! Jackie here\n" 
                + "What can I do for you?\n");
        showLine();
    }
    
    public void showLoadingError() {
        showLine();
        System.out.println("☹ OH NOOOOO! I cannot locate the file!!\n");
        showLine();
    }
    
    public void showError(String errMess) {
        showLine();
        System.out.println(errMess);
        showLine();
    }
    
    public String readCommand() {
        return scanner.nextLine();
    }
    
    public void showDone(duke.task.Task task) {
        showLine();
        System.out.println("Noice! I've marked this task as done: \n" + task + "\n");
        showLine();
    }
    
    public void showFarewell() {
        showLine();
        System.out.println("Bye bye. Love you\n");
        showLine();
        scanner.close();
    }
    
    public void emptyList() {
        showLine();
        System.out.println("Darling, you have nothing in your list though \n");
        showLine();
    }
    
    public void showFullList(TaskList taskList) {
        showLine();
        System.out.println("Darling, here are the tasks in your list:\n" + taskList);
        showLine();
    }
    
    public void showScheduleList(TaskList taskList, LocalDate dateFilter) {
        showLine();
        System.out.println(String.format("Darling, here are the tasks with a schedule of %s:\n"
                        , dateFilter.toString()));
        System.out.println(taskList.listSchedule(dateFilter));
        showLine();
    }

    public void showDelete(duke.task.Task task, int num) {
        showLine();
        System.out.println("okie! I've removed this annoying task: \n" + task + "\n");
        showNumOfTask(num);
        showLine();
    }
    
    public void showNumOfTask(int num) {
        System.out.println("Now you have " + num + " tasks in the list.\n");
    }

    public void showAdd(duke.task.Task task, int num) {
        showLine();
        System.out.println("Gotcha my dear. I've added this task for you: \n" + task
                + "\nNow you have " + num + " tasks in the list.\n");
        showLine();
    }

}
