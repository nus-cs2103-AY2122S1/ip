import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private Scanner input = new Scanner(System.in);
    private ArrayList<String> list = new ArrayList<>();

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
                return;
            }
            if (userInput.equals("list")) {
                this.showList();
                continue;
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
        this.list.add(userInput);
        System.out.println("\tadded: " + userInput + "\n");
    }


    private void exit() {
        System.out.println("\t" + "Bye, hope to see you again!");
    }


    private void showList() {
        for (int i = 0; i < this.list.size(); i++) {
            System.out.println("\t" + i + ". " + this.list.get(i));
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
