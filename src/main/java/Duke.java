import java.util.Scanner;

public class Duke {
    static int cnt = 1;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        Task[] list = new Task[100];
        System.out.println("Hello! I'm Duke created by Tianyue.\n" +
                "What can I do for you?");


        String text = scanner.nextLine();


        while(!text.isEmpty()) {
            if (text.contains("done")) {
                System.out.println("Nice! I've marked this task as done: ");
                char last_digit = text.charAt(text.length() - 1);
                int index = Character.getNumericValue(last_digit);

                list[index - 1].maskAsDone();
                System.out.println("[X] " + list[index - 1].description);
                text = scanner.nextLine();
            }

            else if (text.equals("list")) {
                System.out.println("Here are the tasks in your list:");

                for (int i = 0; i < cnt - 1; i++) {
                    System.out.println(i + 1 + "." + list[i].toString());
                }

                text = scanner.nextLine();
            }

            else if (text.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            }

            else {
                System.out.println("added: " + text);
                Task t = new Task(text);
                list[cnt - 1] = t;
                cnt++;
                text = scanner.nextLine();
            }


        }
    }
}
