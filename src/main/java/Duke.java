import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private static ArrayList<String> todoList;

    public static void main(String[] args) {
        String separator = "------------------------------------------------------------------";

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(logo);
        System.out.println("Hello I'm Duke :)");
        System.out.println("What can I do for you?");
        System.out.println(separator);

        todoList = new ArrayList<>();

        boolean end = false;
        String endCmd = "bye";
        String listCmd = "list";
        Scanner sc = new Scanner(System.in);

        while (!end) {
            String cmd = sc.nextLine();
            System.out.println(separator);

            if (cmd.equals(endCmd)) {
                System.out.println("Bye bye! See you again soon!");
                end = true;
            } else if (cmd.equals(listCmd)) {
                displayList();
            } else {
                addItem(cmd);
            }
            System.out.println(separator);
        }
    }

    private static void displayList() {
        for (int i = 0; i < todoList.size(); i ++) {
            int num = i+1;
            System.out.println(num + ". " + todoList.get(i));
        }
    }

    private static void addItem(String item) {
        todoList.add(item);
        System.out.println("added: " + item);
    }
}
