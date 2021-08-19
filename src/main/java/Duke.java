import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();
    static Message msg = new Message();

    public static void main(String[] args) {
        Message.greet();
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] words = input.split(" ");
            String firstWord = words[0];

            if (firstWord.equals("list")) {
                Message.list();
            } else if (firstWord.equals("done")) {
                // assumption that task id is the second word

                try {
                    Task task = tasks.get(Integer.parseInt(words[1])-1);
                    task.isDone = true;
                    Message.done(task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No such task added!");
                }

            } else {
                Task task = new Task(input);
                Message.addTask(task.description);
                tasks.add(task);
            }
            input = sc.nextLine();
        }
        Message.exit();
    }
}
