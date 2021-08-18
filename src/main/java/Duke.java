import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String introMessage = "Hello! I'm Lawbringer!\n" +
                "What can i do for you?";
        System.out.println(introMessage);
        Scanner scanner = new Scanner(System.in);
        List<Task> tasks = new ArrayList<>();
        while (true) {
            String userInput = scanner.nextLine();
            if (userInput.equals("bye")) {
                System.out.println("Bye. Hope to see you again soon!");
                break;
            } else if (userInput.equals("list")) {
                int counter = 1;
                for (Task task : tasks) {
                    System.out.println(counter + "." + task.toString());
                    counter++;
                }
            } else if (userInput.startsWith("done")) {
                int index = Integer.parseInt(userInput.substring(5)) - 1;
                tasks.get(index).maskAsDone();
                Task task = tasks.get(index);
                String doneMessage = "Nice! I've marked this task as done:\n" +
                        "  " + task.toString();
                System.out.println(doneMessage);
            } else if (userInput.startsWith("todo")) {
                ToDo todo = new ToDo(userInput.substring(5));
                tasks.add(todo);
                String message = "Got it. I've added this task:\n" + "  " +
                        todo.toString() + "\nNow you have " + tasks.size() +
                        " task(s) in the list." ;
                System.out.println(message);
            } else if (userInput.startsWith("deadline")) {
                int index = userInput.indexOf('/');
                String by = userInput.substring(index+4);
                Deadline deadline = new Deadline(userInput.substring(9,index), by);
                tasks.add(deadline);
                String message = "Got it. I've added this task:\n" + "  " +
                        deadline.toString() + "\nNow you have " + tasks.size() +
                        " task(s) in the list." ;
                System.out.println(message);
            } else if (userInput.startsWith("event")) {
                int index = userInput.indexOf('/');
                String at = userInput.substring(index+4);
                Deadline deadline = new Deadline(userInput.substring(6,index), at);
                tasks.add(deadline);
                String message = "Got it. I've added this task:\n" + "  " +
                        deadline.toString() + "\nNow you have " + tasks.size() +
                        " task(s) in the list." ;
                System.out.println(message);
            }
        }
    }
}
