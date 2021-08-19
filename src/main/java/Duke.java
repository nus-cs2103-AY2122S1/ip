import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();

        System.out.println("Hello! This is Duke :)" + "\n" + "What can I do for you?");
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            if (input.equals("list")) {
                System.out.println("Here are the tasks on your list: ");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i-1).printTask());
                }
            } else if (input.length() >= 4 && input.startsWith("done")){
                String i = input.substring(input.length()-1);
                try {
                    int index = Integer.parseInt(i);
                    if (index < 0 || index > list.size()) {
                        System.out.println("Task number " + index + " does not exist. Please send a correct number ><");
                    } else {
                        Task task = list.get(index - 1);
                        task.markAsDone();
                        System.out.println("Nice! I have marked this task as done!");
                        System.out.println(task.printTask());
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Task does not exist. Please send a correct number ><");
                }
            } else {
                System.out.println("Got it. I've added this task.");
                if (input.startsWith("todo")) {
                    list.add(new ToDos(input));
                } else if (input.startsWith("deadline")) {
                    String[] message = input.split("/by");
                    list.add(new Deadline(message[0], message[1]));
                } else if (input.startsWith("event")){
                    String[] message = input.split("/at");
                    list.add(new Events(message[0], message[1]));
                } else {
                    list.add(new Task(input));
                }
                System.out.println(list.get(list.size() - 1).printTask());
                System.out.println("Now you have " + list.size()
                        + (list.size() == 1 ? " task in the list" : " tasks in the list."));
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! See you again soon!!");
    }
}
