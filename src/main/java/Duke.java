import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<String> al = new ArrayList<>();
        String str = sc.next();
        while (!str.equals("bye")) {
            if (str.equals("list")) {
                for (int i = 0; i < al.size(); i++) {
                    String s = String.format("%d. %s", i + 1, al.get(i));
                    System.out.println(s);
                }
            } else {
                al.add(str);
                System.out.println("added: " + str);
            }
            str = sc.next();
        }
        System.out.println("Bye. Hope to see you again soon!");
    }
}
