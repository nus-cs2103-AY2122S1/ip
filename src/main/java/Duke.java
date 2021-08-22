import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) throws DukeException {

        System.out.println("Hello! I'm Duke \nWhat can I do for you?");
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> tasks = new ArrayList<>();
        DukeHandler handler = new DukeHandler(tasks);
        Task currentTask = null;
        while (true) {
            String input = sc.nextLine();
            if (handler.isExit(input)) {
                System.out.println("\tBye. Hope to see you again soon!");
                break;
            } else {
                try {
                    String[] results = handler.parseInput(input);
                    for (int i = 0; i < results.length; i++) {
                        System.out.println(results[i]);
                    }
                } catch (Exception e) {
                    System.out.println(e);
                }

            }
        }
    }
}
