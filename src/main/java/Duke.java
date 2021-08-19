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
        String command = sc.next();
        String description = sc.nextLine();
        while(!command.equals("bye")) {
            executeCommand(command, description);
            command = sc.next();
            description = sc.nextLine();
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

     void displayCommand(String description) {
        printLines();
        System.out.println(description);
        printLines();
    }

    void executeCommand(String command, String description) {
        if (command.equals("list")) {
            displayDukeList();
        } else if (command.equals("done")) {
            int taskIndex = Integer.parseInt(description.substring(1)) - 1;
            Task toBeCompleted = dukeList.get(taskIndex);
            toBeCompleted.completeTask();
            displayTaskCompletion(toBeCompleted);
        } else {
            printLines();
            System.out.println("Got it. I've added this task: ");
            if (command.equals("deadline")) {
                String[] description_and_time = description.split("/");
                String deadlineDescription = (description_and_time[0].split(" ", 2))[1];
                String time = (description_and_time[1].split(" ", 2))[1];
                Deadline newDeadline = new Deadline(deadlineDescription, time);
                dukeList.add(newDeadline);
                System.out.println(newDeadline);
            } else if (command.equals("event")) {
                String[] event_and_time = description.split("/");
                String eventDescription = (event_and_time[0].split(" ", 2))[1];
                String time = (event_and_time[1].split(" ", 2))[1];
                Event newEvent = new Event(eventDescription, time);
                dukeList.add(newEvent);
                System.out.println(newEvent);
            } else {
                ToDo newTodo = new ToDo(description.substring(1));
                dukeList.add(newTodo);
                System.out.println(newTodo);
            }
            System.out.println("Now you have " + dukeList.size() + " tasks in the list.");
            printLines();
        }

    }

    void displayTaskCompletion(Task toBeCompleted) {
        printLines();
        System.out.println("Nice! I've marked this task as done: ");
        System.out.println(toBeCompleted);
        printLines();
    }


    void displayDukeList() {
        printLines();
        for (int i = 0;i < dukeList.size(); i++) {
            System.out.println((i+1) + ". " + dukeList.get(i));
        }
        printLines();
    }
}
