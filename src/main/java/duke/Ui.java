package duke;
import java.util.Scanner;


public class Ui {
    private static final Scanner userInput = new Scanner(System.in);
    private static String lineBreaker = "____________________________________________________________";
    private static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    private static String farewell = "Bye. Hope to see you again soon!";
    private static String listMessage = "Here are the tasks in your list:";
    private static String doneMessage = "Nice! I've marked this task as done:";
    private static String addTaskMessage = "Got it. I've added this task:";
    private static String deleteTaskMessage = "Noted. I've removed this task:";

    public void showLoadingError() {
        System.out.println("Can't load saved file");
    }

    public String getInstruction() {
        return userInput.nextLine();
    }

    public  void printLineBreak () {
        System.out.println(lineBreaker);
    }

    public void printArrayList (TaskList taskList) {
        System.out.println(listMessage);
        for(int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
        }
        printLineBreak();
    }

    public void greet(){
        System.out.println(greeting);
        printLineBreak();
    }

    public void sayFarewell() {
        printLineBreak();
        System.out.println(farewell);
        printLineBreak();
    }

    public void completeTaskMessage (Task task) {
        System.out.println(doneMessage + "\n" + task.toString());
        printLineBreak();
    }

    public void deleteTaskMessage (Task task, int size) {
        System.out.println(deleteTaskMessage);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }

    public void addedTaskMessage (Task task, int size) {
        System.out.println(addTaskMessage);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }


    void taskCounterMessage (int size) {
        System.out.println("Now you have " + size + " tasks in the list.");
    }

    void printErrorMessage(DukeException e) {
        System.out.println(e.toString());
        printLineBreak();
    }
}
