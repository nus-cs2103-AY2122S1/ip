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
        Task task;

        while (!input.equals("bye")) {
            String[] words = input.split(" ", 2);
            String keyword = words[0];

            if (keyword.equals("list")) {
                Message.list();
            } else if (keyword.equals("done")) {
                // assumption that task id is the second word
                try {
                    task = tasks.get(Integer.parseInt(words[1])-1);
                    task.isDone = true;
                    Message.done(task);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println("No such task added!");
                }
            } else if (keyword.equals("todo")) {
                task = new Todo(words[1]);
                Message.addTask(task);
                tasks.add(task);
            } else if (keyword.equals("deadline")) {
                String description = words[1].split("/")[0];
                String date = words[1].split("/by")[1];
                task = new Deadline(description, date);
                Message.addTask(task);
                tasks.add(task);
            } else if (keyword.equals("event")) {
                String description = words[1].split("/")[0];
                String date = words[1].split("/at")[1];
                task = new Event(description, date);
                Message.addTask(task);
                tasks.add(task);
            }
            input = sc.nextLine();
        }
        Message.exit();
    }
}
