package duke;
import java.util.Scanner;


public class Ui {
    private static final Scanner USER_INPUT = new Scanner(System.in);
    private static final String LINE_BREAKER = "____________________________________________________________";
    private static final  String GREETING = "Hello! I'm Duke\n" + "What can I do for you?";
    private static final String FAREWELL = "Bye. Hope to see you again soon!";
    private static final String LIST_MESSAGE = "Here are the tasks in your list:";
    private static final String DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static  final String ADD_TASK_MESSAGE = "Got it. I've added this task:";
    private static final String DELETE_TASK_MESSAGE = "Noted. I've removed this task:";

    public void showLoadingError() {
        System.out.println("Can't load saved file");
    }

    public String getInstruction() {
        return USER_INPUT.nextLine();
    }

    public  void printLineBreak () {
        System.out.println(LINE_BREAKER);
    }

    public void printArrayList (TaskList taskList) {
        System.out.println(LIST_MESSAGE);
        for(int i = 0; i < taskList.getSize(); i++) {
            System.out.println(String.valueOf(i+1) + "." + taskList.get(i).toString());
        }
        printLineBreak();
    }

    public void greet(){
        System.out.println(GREETING);
        printLineBreak();
    }

    public void sayFarewell() {
        printLineBreak();
        System.out.println(FAREWELL);
        printLineBreak();
    }

    public void completeTaskMessage (Task task) {
        System.out.println(DONE_MESSAGE + "\n" + task.toString());
        printLineBreak();
    }

    public void deleteTaskMessage (Task task, int size) {
        System.out.println(DELETE_TASK_MESSAGE);
        System.out.println("  " + task.toString());
        taskCounterMessage(size);
        printLineBreak();
    }

    public void addedTaskMessage (Task task, int size) {
        System.out.println(ADD_TASK_MESSAGE);
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
