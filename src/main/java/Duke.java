import java.util.Scanner;

public class Duke {
    private Scanner input = new Scanner(System.in);

    private void printLogo() {
        String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
    }

    private void checkInput() {
        String userInput = this.input.nextLine();
        if (userInput.equals("bye")) {
            this.exit();
            return;
        }
        this.echo(userInput);
        this.checkInput();
    }


    private void greet() {
        System.out.println("Welcome! I'm Duke.");
        System.out.println("What can I do for you?\n");
    }

    private void echo(String userInput) {
        System.out.println("\t" + userInput + "\n");
    }


    private void exit() {
        System.out.println("\t" + "Bye, hope to see you again!");
    }


    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.printLogo();
        duke.greet();
        duke.checkInput();
    }
}
