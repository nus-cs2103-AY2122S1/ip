import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    private final static ArrayList<String> list = new ArrayList<>();
    private final static String bar = "________________________________";

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println(reply("Hello i is Duke\nWhat u want?"));
        add();
    }

    private static String reply(String content) {
        return bar + "\n" + content + "\n" + bar;
    }

    private static void exit() {
        System.out.println(reply("i zao first"));
    }

    private static void echo() {
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("bye")) {
            exit();
        } else {
            System.out.println(reply(input));
            echo();
        }
    }

    private static void add() {
        Scanner obj = new Scanner(System.in);
        String input = obj.nextLine();
        if (input.equals("list")) {
            displayList();
        } else if (input.equals("bye")) {
            exit();
        } else {
            list.add(input);
            System.out.println(reply("added: " + input));
            add();
        }
    }

    private static void displayList() {
        System.out.println(bar);
        for (int i = 0; i < list.size(); i++) {
            System.out.println((i + 1) + "." + list.get(i));
        }
        System.out.println(bar);
        add();
    }
}
