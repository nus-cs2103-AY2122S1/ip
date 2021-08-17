import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    static ArrayList<Task> tasks = new ArrayList<>();

    private static void getLstItems() {
        int counter = 1;
        for (Task task : tasks) {
            System.out.println(String.valueOf(counter) + "." + task.toString());
            counter += 1;
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String horizontalLines = "-----------------------------------------";
        System.out.println("Hello! I'm Naruto\nWhat can I do for you?\n" + horizontalLines);
        Scanner in = new Scanner(System.in);
        String userInput;

        while (true) {
            userInput = in.nextLine();
            if (userInput.equals("bye")) {
                System.out.println(horizontalLines);
                System.out.println("See ya! Hope to see you again!" + "\n" + horizontalLines);
                return;
            } else if (userInput.equals("list")) {
                System.out.println(horizontalLines);
                System.out.println("Here are the tasks in your list:");
                getLstItems();
                System.out.println(horizontalLines);
                continue;
            } else if (userInput.substring(0, 4).equals("done")) {
                int taskNum = Integer.valueOf(userInput.substring(5)) - 1;
                Task currTask = tasks.get(taskNum);
                currTask.markAsDone();
                System.out.println("Good job! I've marked this task as done:");
                System.out.println(currTask.toString());
                System.out.println(horizontalLines);
            } else {
                // All other cases add it to tasks
                System.out.println(horizontalLines);
                Task newTask = new Task(userInput);
                tasks.add(newTask);
                System.out.println("added: " + userInput + "\n" + horizontalLines);
            }
        }

    }

}