import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static void main(String[] args) {
        ArrayList<Task> list = new ArrayList<>();
        Scanner sc = new Scanner(System.in);
        String starter =   "   (=^ ･ｪ･^=) < ";
        String buffer =    "                ";
        String sleepy =    "   (=^ ‐ｪ‐^=) < ";
        String confused =  "   (=^ ･x･^=)? < ";
        String happy =     "   (=^ ･ω･^=)❀ < ";

        // Welcome text
        System.out.println(starter + "Hello! I'm TiTi~ ");
        System.out.println(buffer + "What would you like to do nya? ");

        String input = sc.nextLine();
        while (true) {
            // exit cue
            if (input.equals("bye")) {
                System.out.println(sleepy + "Already done? Come back again soon nya~");
                break;
            }

            // display list
            if (input.equals("list")) {
                int numberOfTasks = list.size();
                // no tasks
                if (numberOfTasks == 0) {
                    System.out.println(starter + "Looks like you have no tasks nya~");
                    input = sc.nextLine();
                    continue;
                }
                // print task list
                System.out.println(starter + "Have you competed these tasks nya?");
                for (int i = 0; i < numberOfTasks; i++) {
                    System.out.println(buffer + (i + 1) + ". " + list.get(i));
                }
                input = sc.nextLine();
                continue;
            }

            // mark complete task
            if (input.startsWith("done ")) {
                int taskNumber = Integer.parseInt("" + input.charAt(5));
                int numberOfTasks = list.size();
                if (taskNumber > numberOfTasks) {
                    System.out.println(confused + "Nya?... I can't find the task... ");
                } else {
                    Task task = list.get(taskNumber - 1);
                    task.complete();
                    System.out.println(happy + "Nya! You've worked hard haven't you!");
                    System.out.println(buffer + "I'll mark this task as done: ");
                    System.out.println(buffer + task.toString());
                }
                input = sc.nextLine();
                continue;
            }

            // add input to list
            System.out.println(starter + "added a new task: " + input);
            System.out.println(buffer + "Don't forget to complete it nya~ ");
            list.add(new Task(input));
            input = sc.nextLine();
        }
    }
}
