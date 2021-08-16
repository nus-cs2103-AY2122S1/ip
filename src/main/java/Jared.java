import java.util.Scanner;

public class Jared {
    private static String[] history = new String[100];
    private static int historyCount = 0;

    private static void add(String input) {
        System.out.println("added: " + input);
        Jared.history[historyCount] = input;
        historyCount ++;
    }

    public static void list() {
        String res = "";
        for (int i = 0; i < historyCount; i++) {
            res += String.format("%d. %s\n", i, Jared.history[i]);
        }
        System.out.println(res);
    }

    public static void main(String[] args) {
        String intro = "____________________________________________________________\n" +
                "Hello! I'm Jared\n" +
                "What can I do for you?\n" +
                "____________________________________________________________";
        Scanner scan = new Scanner(System.in);

        System.out.println(intro);
        while (true) {
            String next = scan.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
            } else if (next.equals("list")) {
                list();
            } else {
                add(next);
            }
        }

    }
}

