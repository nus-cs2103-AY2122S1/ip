import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean check = true;
        Task[] tasks = new Task[100];
        int counter = 0;

        System.out.println("Hello! I'm Duke\n" + "What can I do for you?");
        while (check) {
            String s = sc.nextLine();
            String numberOnly = s.replaceAll("[^0-9]", "");
            String letterOnly = s.replaceAll("[0-9]", "");

            if (s.equals("bye")) {
                check = false;
                System.out.println("Bye. Hope to see you again soon!");
            } else if (s.equals("list")) {
                for (int i = 0; i < counter; i++) {
                    System.out.println((i + 1) + ".[" + tasks[i].getStatusIcon() + "] " + tasks[i].toString());
                }
            } else if (letterOnly.equals("done ") && !(numberOnly.equals(""))) {
                int idx = Integer.parseInt(numberOnly) - 1;
                tasks[idx].setDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println("[" + tasks[idx].getStatusIcon() + "] " + tasks[idx].toString());
            } else {
                tasks[counter] = new Task(s);
                counter++;
                System.out.println("added: " + s);
            }
        }
    }
}
