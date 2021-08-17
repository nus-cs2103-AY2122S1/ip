import java.util.Scanner;

public class Duke {

    private void printMessage(String message) {
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE);
        System.out.println(STATICS.INDENT + message);
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void printIntro() {
        System.out.println(STATICS.logo);
        printMessage(STATICS.INTRODUCTION);
    }

    private void printBye() {
        printMessage("Bye. Hope to see you again soon!");
    }

    public void start() {
        String userInput = "";
        Scanner sc = new Scanner(System.in);

        this.printIntro();

        while (true) {
            userInput = sc.nextLine();
            if (userInput.equals("bye")) {
                this.printBye();
                break;
            }
            this.printMessage(userInput);
        }
        sc.close();
    }
}