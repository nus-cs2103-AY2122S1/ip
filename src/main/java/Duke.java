import java.util.Scanner;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        ArrayList<Task> list = new ArrayList<>();
        greeting();
        String cmd = sc.nextLine();
        while (!cmd.equals("bye")) {
            Pattern p = Pattern.compile("done \\d+");
            Matcher m = p.matcher(cmd);
            if (cmd.equals("list")) {
                iterList(list);
            } else if (m.matches()) {
                completeATask(cmd, list);
            } else {
                addToList(list, new Task(cmd));
            }
            cmd = sc.nextLine();
        }
        sc.close();
        exit();
    }

    private static void greeting() {
        System.out.println("Aloha! I'm Sophia\nWhat can I do for you?");
    }

    private static void iterList(ArrayList<Task> ls) {
        System.out.println("Here are the tasks in your list:");
        int i = 1;
        for (Task s : ls) {
            System.out.println(i + "." + s.getStatusIcon() + " " + s);
            i++;
        }
    }

    private static void addToList(ArrayList<Task> ls, Task text) {
        ls.add(text);
        System.out.println("added: " + text);
    }

    private static void completeATask(String input, ArrayList<Task> ls) {
        String[] tokens = input.split(" ");
        int index = Integer.parseInt(tokens[1]) - 1;
        Task task = ls.get(index);
        task.completeTask();
        System.out.println("Nice! I've marked this task as done:\n  " + task.getStatusIcon() + " " + task);
    }

    private static void exit() {
        System.out.println("Bye. Hope to see you again soon!");
    }
}
