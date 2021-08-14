import java.util.ArrayList;
import java.util.Scanner;

public class Duke {

    private static ArrayList<String> list = new ArrayList<>(100);

    private static void chat(String content) {
        System.out.println(
                "____________________________________________________________\n"
                + content
                + "\n____________________________________________________________\n"
        );
    }

    private static void addItem(String item) {
        list.add(item);
    }

    private static void displayList() {
        StringBuilder listString = new StringBuilder();
        for (int i = 0; i < list.size(); i++) {
            listString.append(String.valueOf(i + 1) + ". " + list.get(i));
            if (i != list.size() - 1) {
                listString.append("\n");
            }
        }
        chat(listString.toString());
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        chat("Hello I'm\n" + logo + "What can I do for you?");
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                chat("Bye. Hope to see you again soon!");
                break;
            } else if (input.equals("list")) {
                displayList();
            } else {
                addItem(input);
                chat("added: " + input);
            }
        }
    }


}
