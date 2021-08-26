import tasks.Task;

import java.util.ArrayList;
import java.util.Scanner;

public class Ui {

    private static final String H_LINE = "------------------------------\n";
    private Scanner commandInput;

    public Ui() {
        
    }

    public void showWelcome() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String helloMsg = H_LINE
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + H_LINE;
        System.out.print(logo + helloMsg);
    }
    
    public void beginListen() {
        commandInput = new Scanner(System.in);
    }
    
    public String readCommand() {
        return commandInput.nextLine();
    }
    
    public void showList(ArrayList<Task> tasks) {
        System.out.print(H_LINE);
        if (tasks.size() > 0) {
            System.out.println("Here are the tasks in your list:");
            for (int i = 0; i < tasks.size(); i++) {
                System.out.println("  " + (i + 1) + ". " + tasks.get(i));
            }
        } else {
            System.out.println("There are no tasks in your list.");
        }
        System.out.print(H_LINE);
    }
    
    public void showCommandFail() {
        System.out.print(H_LINE + "I didn't get that. Please try again.\n" + H_LINE);
    }
    
    public void showError(String message) {
        System.out.print(H_LINE + "Oops... Something's wrong.\n" + message + "\n" + H_LINE);
    }
    
    public void showMessage(String message) {
        System.out.print(H_LINE + message + "\n" + H_LINE);
    }
    
    public void showStorageError() {
        showError("File");
    }
    
    public void stopListen() {
        commandInput.close();
    }
    
    public void showGoodbye() {
        System.out.print(H_LINE + "Bye. Hope to see you again soon!\n" + H_LINE);
    }
}
