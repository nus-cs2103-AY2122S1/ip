import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Duke {
    public static List<String> list = new ArrayList<>();
    public static String formatMessage(String s) {
        return "    ____________________________________________________________\n     " +
                s + "\n" +
                "    ____________________________________________________________";
    }
    public static void addToList(String s) {
        list.add(s);
    }
    public static String printList() {
        String res = "";
        for (int counter = 1; counter<=list.size(); counter++) {
            res = res + counter + ". " + list.get(counter-1);
            if (counter != list.size()) {
                res = res + "\n     ";
            }
        }
        return res;
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println(formatMessage("Hello! I'm Duke\n" + "     What can I do for you?"));
        while (true) {
            String input = scanner.nextLine();
            if (input.equals("bye")) {
                break;
            }
            if (input.equals("list")) {
                System.out.println(formatMessage(printList()));
                continue;
            }
            addToList(input);
            System.out.println(formatMessage("added: " + input));
        }
        System.out.println(formatMessage("Bye. Hope to see you again soon!"));
    }
}
