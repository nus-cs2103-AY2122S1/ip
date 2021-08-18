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
                System.out.println("Here are the tasks on your list");
                for (int i = 1; i <= list.size(); i++) {
                    System.out.println(i + ". " + list.get(i-1).printTask());
                }
            } else if (input.length() >= 4 && input.startsWith("done")){
                String i = input.substring(input.length()-1);
                Task task = list.get(Integer.parseInt(i) - 1);
                task.markAsDone();
                System.out.println("Nice! I have marked this task as done!");
                System.out.println(task.printTask());
            } else {
                list.add(new Task(input));
                System.out.println("added: " + input);
            }
            input = sc.nextLine();
        }
        System.out.println("Bye! See you again soon!!");
    }
}
