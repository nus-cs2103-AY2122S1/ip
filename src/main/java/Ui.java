import java.util.Scanner;

public class Ui {
    static Scanner userInput = new Scanner(System.in);
    static String lineBreaker = "____________________________________________________________";
    static String greeting = "Hello! I'm Duke\n" + "What can I do for you?";
    static String farewell = "Bye. Hope to see you again soon!";
    static String listMessage = "Here are the tasks in your list:";
    static String doneMessage = "Nice! I've marked this task as done:";
    static String addTaskMessage = "Got it. I've added this task:";
    static String deleteTaskMessage = "Noted. I've removed this task:";

    void showLoadingError() {
        System.out.println("Can't load saved file");
    }

    String getInstruction() {
        return userInput.nextLine();
    }

    void printLineBreak () {
        System.out.println(lineBreaker);
    }

    void printArrayList (TaskList taskList) {
        System.out.println(listMessage);
        for(int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
        }
        printLineBreak();
    }

    void greet(){
        System.out.println(greeting);
        printLineBreak();
    }

    void sayFarewell() {
        printLineBreak();
        System.out.println(farewell);
        printLineBreak();
    }

    void completeTaskMessage (Task task) {
        System.out.println(doneMessage + "\n" + task.toString());
        printLineBreak();
    }

    void deleteTaskMessage (Task task, int size) {
        System.out.println(deleteTaskMessage);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }

    void addedTaskMessage (Task task, int size) {
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
