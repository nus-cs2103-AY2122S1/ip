package winston;

/**
 * A class that manages all lines that handles user experience and lines that are commonly used.
 */
public class Ui {
    public Ui() {
    }
    
    public static String invalidTask(String errorMessage) {
        return "Error: " + errorMessage + "\n";
    }
    
    public static String printTasksLeft(int tasksLeft) {
        return tasksLeft + " Tasks Left\n";
    }
    
    public static String printList(TaskList taskList) {
        return taskList.getList() + "\n";
    }
    
    public static String printDontWorry() {
        return "Don't worry, I've got you. Task Marked!\n";
    }
    
    public static String printMatchingTasks() {
        return "Here are the matching tasks in your list:\n";
    }
    
    public static String welcomeMessage() {
        return "Hi there! Winston reporting.\nWhat can I do for you?\n" 
                + "Available Commands: done, list, todo, deadline, event, bye, delete, findstring, update";
    }
    
    public static String terminationMessage() {
        return "See ya later!";
    }
    
    public static String updateMessage(int messageNumber) {
        return "Task " + messageNumber + "updated";
    }
}
