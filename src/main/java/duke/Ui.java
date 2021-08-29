package duke;

import duke.task.Task;
import duke.task.TaskList;
import java.util.Scanner;

public class Ui {
    Scanner sc;
    private static TaskList taskList;
    private static Storage storage;
    
    public Ui(TaskList taskList, Storage storage) {
       sc = new Scanner(System.in);
       this.taskList = taskList;
       this.storage = storage;
    }

    public String command() {
        if (sc.hasNextLine()) {
            String str = sc.nextLine();
            return str;
        }
        return "";
    }
    
    public void showWelcome() {
        System.out.println("Hello from Duke!");
        System.out.println("");
        storage.loadTaskListData(taskList);
        System.out.println("");
        System.out.println("Hope you are doing well. How can I help you?");
    }
    
    public static void bye() {
        System.out.println("Bye. Have a great day!");
    }
    
    public static void showLine() {
        System.out.println("-----------------------------------------------");
    }
    
    public static void printMessage(String msg) {
        System.out.println(msg);
    }
    
    public static void taskResponse(Task task) {
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
    
    public static void doneResponse(Task task) {
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(task);
        storage.writeToFile("./duke.txt", taskList);
    }
    
    public static void deleteResponse(Task task) {
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println("Now you have " + taskList.size() + " tasks in the list.");
    }
}











