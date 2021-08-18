import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner input = new Scanner(System.in);
    private ArrayList<Task> list = new ArrayList<>();

    private void printLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }


    private void checkInput() {
        while (true) {
            String userInput = this.input.nextLine();
            if (userInput.equals("bye")) {
                this.exit();
                break;
            }
            if (userInput.equals("list")) {
                this.showList();
                continue;
            }
            if (userInput.length() >= 6 && userInput.substring(0, 5).equals("done ")) {
                try {
                    int taskNum = Integer.parseInt(userInput.substring(5));
                    this.completeTask(taskNum);
                    continue;
                } catch (NumberFormatException e) {
                    System.out.println("NOT AN INTEGER!");
                }
            }
            this.add(userInput);
        }
    }


    private void greet() {
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }


    private void echo(String userInput) {
        System.out.println("\t" + userInput + "\n");
    }


    private void add(String userInput) {
        this.list.add(new Task(userInput));
        System.out.println("\tadded: " + userInput + "\n");
    }


    private void exit() {
        System.out.println("\tBye, hope to see you again!");
    }


    private void completeTask(int taskNum) {
        this.list.get(taskNum - 1).markAsDone();
        System.out.println("\tI've marked this task as done!");
        System.out.println("\t" + this.list.get(taskNum - 1) + "\n");
    }


    private void showList() {
        System.out.println("\tHere are the tasks in your list:");
        for (int i = 1; i <= this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i - 1));
        }
        System.out.println();
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLogo();
        duke.greet();
        duke.checkInput();
    }
}
