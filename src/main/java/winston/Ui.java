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
    
    public static String deleteItem() {
        return "IT GOT DELETED SIA!\n";
    }
    
    public static String printDontWorry() {
        return "Time to raise my APM!\nTASK IS MARKED!\n";
    }
    
    public static String printMatchingTasks() {
        return "Here are the matching tasks in your list:\n";
    }
    
    public static String welcomeMessage() {
        return "Winston reporting!\nWhat can I do for you? \n" 
                + "Available Commands: done, list, todo, deadline, event, bye, delete, findstring, update";
    }
    
    public static String terminationMessage() {
        return "See ya later!";
    }
    
    public static String updateMessage(int messageNumber) {
        return "Task " + messageNumber + "updated";
    }
    
    public static String emptyListMessage() {
        return "The list is empty!";
    }
    
    public static String hiYaMessage() {
        return "Hiya! These are your tasks!\n";
    }
}
