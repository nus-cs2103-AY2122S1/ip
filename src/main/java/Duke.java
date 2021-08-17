import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    List<String> itemList;

    public Duke() {
        itemList = new ArrayList<>(100);
    }

    void startConversation() {
        Scanner sc = new Scanner(System.in);

        greeting("Alex");
        while (true) {
            // conversation loop
            String input = sc.nextLine();
            if (input.equals("bye")) {
                break;
            }

            switch (input) {
            case "":
                break;
            case "list":
                listItem();
                break;
            default:
                addItem(input);
            }
        }
        sayBye("Alex");
    }

    private void addItem(String item) {
        this.itemList.add(item);
        printHorizLine();
        System.out.println("\tadded " + item);
        printHorizLine();
    }

    private void listItem() {
        printHorizLine();
        for (int i = 1; i <= this.itemList.size(); i++) {
            System.out.format("\t%d. %s\n", i, itemList.get(i-1));
        }
        printHorizLine();
    }

    private void greeting(String name) {
        printHorizLine();
        System.out.println("\tHello " + name + "!");
        System.out.println("\tI'm Duke");
        printHorizLine();
    }

    private void sayBye(String name) {
        printHorizLine();
        System.out.println("\tBye " + name + ", hope to see you soon!");
        printHorizLine();
    }

    private void printHorizLine() {
        System.out.println("\t————————————————————————————————————————");
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        Duke duke = new Duke();
        duke.startConversation();
    }
}
