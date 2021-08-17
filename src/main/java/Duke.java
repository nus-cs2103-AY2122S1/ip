import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<String> list = new ArrayList<>();
        greeting();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            if (cmd.equals("list")) {
                iterList(list);
            } else {
                addToList(list, cmd);
            }
            cmd = sc.nextLine();
        }
        sc.close();
        exit();
    }

    private static void greeting() {
        System.out.println("Aloha! I'm Sophia\nWhat can I do for you?\n");
    }

    private static void iterList(ArrayList<String> ls) {
        int i = 1;
        for (String s : ls) {
            System.out.println(i + ". " + s);
            i++;
        }
    }

    private static void addToList(ArrayList<String> ls, String text) {
        ls.add(text);
        System.out.println("added: " + text);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
