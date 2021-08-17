import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final ArrayList<String> arrList;

    public Duke() {
        this.arrList = new ArrayList<>();
    }

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

    private void printList() {
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(STATICS.INDENT + (i + 1) + ". " + arrList.get(i));
        }
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE + "\n");

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
            } else if (userInput.equals("list")) {
                printList();
                continue;
            }
            this.arrList.add(userInput);
            this.printMessage("added: " + userInput);
        }
        sc.close();
    }
}