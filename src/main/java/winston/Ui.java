package winston;

/**
 * A class that manages all lines that handles user experience and lines that are commonly used.
 */
public class Ui {
    public Ui() {
    }
    
    public static void invalidTask() {
        System.out.print("Invalid command. Returning to Main Menu\n");
    }
    
    public static void printTasksLeft(int tasksLeft) {
        System.out.println(tasksLeft + " Tasks Left");
    }
    
    public static void printList(TaskList taskList) {
        System.out.println(taskList.getList());
    }
    
    public static void printDontWorry() {
        System.out.println("Don't worry, I've got you. Task Marked!");
    }
    
    public static void printMatchingTasks() {
        System.out.println("Here are the matching tasks in your list:\n");
    }
    public static void invalidDateFormat() {
        System.out.println("Invalid date format. Please give a valid date format. E.g 2021-12-12");
    }
    
    public static void welcomeMessage() {
        System.out.println("Hi there! Winston reporting.\nWhat can I do for you?\n" +
                "Available Commands: done, list, todo, deadline, event, bye, delete, find");
    }
    
    public static void terminationMessage() {
        System.out.println("See ya later!");
    }
}
