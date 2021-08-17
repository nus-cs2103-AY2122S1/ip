import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final ArrayList<Task> arrList;

    public Duke() {
        this.arrList = new ArrayList<Task>();
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
        printMessage(STATICS.BYE_MESSAGE);
    }

    private void printList() {
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE);
        for (int i = 0; i < arrList.size(); i++) {
            System.out.println(STATICS.INDENT + (i + 1) + ". " + arrList.get(i).toString());
        }
        System.out.println(STATICS.INDENTED_HORIZONTAL_LINE + "\n");
    }

    private void doneItem(String number) {
        int index = Integer.parseInt(number) - 1;
        Task curr = arrList.get(index);
        this.arrList.set(index, new Task(curr.getDescription(), curr.markAsDone()));
        curr = arrList.get(index);

        printMessage(STATICS.DONE_MESSAGE + "\n" + STATICS.INDENT + "  " + curr.toString());
    }

    public void start() {
        String userInput = "";
        Scanner sc = new Scanner(System.in);

        this.printIntro();

        while (true) {
            userInput = sc.nextLine();

            if (userInput.split(" ")[0].equals("done")) {
                this.doneItem(userInput.split(" ")[1]);
                continue;
            }

            switch (userInput) {
                case "bye":
                    this.printBye();
                    sc.close();
                    return;

                case "list":
                    this.printList();
                    break;

                case "":
                    break;

                default:
                    this.arrList.add(new Task(userInput));
                    this.printMessage("added: " + userInput);
                    break;
            }

        }

    }
}