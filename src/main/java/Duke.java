import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<Task> dukeList = new ArrayList<Task>();

    public static void main(String[] args)  {
        Duke duke = new Duke();
        duke.runDuke();
    }

    public void runDuke() {
        displayWelcomeMessage();
        String command = sc.nextLine();
        while(!command.equals("bye")) {
            executeCommand(command);
            command = sc.nextLine();
        }
        displayByeMessage();
    }

    void printLines() {
        System.out.println("------------------------------------------------------------------");
    }

    void displayWelcomeMessage() {
        printLines();
        System.out.println("Hello! I'm Duke\nWhat can I do for you?");
        printLines();
    }

    void displayByeMessage() {
        printLines();
        System.out.println("Bye. Hope to see you again soon!");
        printLines();
    }

     void displayCommand(String command) {
        printLines();
        System.out.println(command);
        printLines();
    }

    void executeCommand(String command) {
        if (command.equals("list")) {
            displayDukeList();
        } else if (command.length() > 4 && command.substring(0,5).equals("done ")) {
            int taskIndex = Integer.parseInt(command.substring(5)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.completeTask();
            displayTaskCompletion(toBeCompleted);
        } else {
            addTaskToDukeList(command);
        }

    }

    void displayTaskCompletion(Task toBeCompleted) {
        printLines();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(toBeCompleted);
        printLines();
    }

    void addTaskToDukeList(String command) {
        Task newTask = new Task(command);
        dukeList.add(newTask);
        displayCommand("added: " + command);
    }

    void displayDukeList() {
        printLines();
        for (int i = 0;i < dukeList.size(); i++) {
            System.out.println((i+1) + ". " + dukeList.get(i));
        }
        printLines();
    }
}
