import java.util.ArrayList;
import java.util.Scanner;


public class Duke {
    private Scanner sc = new Scanner(System.in);
    private ArrayList<String> dukeList = new ArrayList<String>();

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
        if(!command.equals("list") && !command.equals("bye")) {
            addToDukeList(command);
        } else if (command.equals("list")) {
            displayDukeList();
        }
    }

    void addToDukeList(String command) {
        dukeList.add(command);
        displayCommand("added: " + command);
    }

    void displayDukeList() {
        printLines();
        for (int i = 0;i < dukeList.size(); i++) {
            System.out.println( (i+1) + ". " + dukeList.get(i));
        }
        printLines();
    }
}
