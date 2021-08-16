import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<String> tasks = new ArrayList<>();


    public static void main(String[] args) {
        System.out.println("Hello, I am Duke. What can I do for you?");
        waitResponse();
    }

    private static void waitResponse() {
        Scanner scanner = new Scanner(System.in);
        if (scanner.hasNext()){
            String next = scanner.nextLine();
            if (next.equals("bye")) {
                System.out.println("Bye. Hope to see you again!");
                return;
            } else if (next.equals("list")){
                System.out.println("List of Tasks:");
                for (int i = 0; i<tasks.size(); i++) {
                    System.out.println(String.format("%d. %s", i + 1, tasks.get(i)));
                }
                waitResponse();
            } else {
                tasks.add(next);
                System.out.println(String.format("Added: %s", next));
                waitResponse();
            }
        }
    }
}
