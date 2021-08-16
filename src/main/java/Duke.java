import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;


public class Duke {
    private final List<String> list = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Duke duke = new Duke();

        duke.printInitialGreeting();
        String input = sc.nextLine();
        while(!input.equals("bye")) {
            duke.response(input);
            input = sc.nextLine();
        }
        duke.close();
    }

    public void response(String input) {
        if (input.equals("list")) {
            printList();
        } else {
            addToList(input);
        }
    }

    public void printInitialGreeting() {
        System.out.println("Hello I'm Duke\n" +
                "What can I do for you?");
    }

    public void close() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    private void printList() {
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + ". " + list.get(i));
        }
    }

    private void addToList(String input) {
        this.list.add(input);
        System.out.println("added: " + input);
    }

    public static void printLogo() {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
    }
}
