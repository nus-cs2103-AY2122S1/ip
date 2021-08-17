import java.util.HashMap;
import java.util.Scanner;

public class Duke {

    private Task[] tasks = new Task[100];

    private int count = 0;

    private void serve() {
        System.out.println("Good Day Sir/Mdm, I am Duke\nWhat can I do for you?\n");

        while (true) {
            Scanner sc = new Scanner(System.in);
            String input = sc.nextLine();
            if (input.equals("bye")) {
                System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
                break;
            } else if (input.equals("list")) {
                System.out.println("Here are your tasks Sir/Mdm:");
                this.list();
            } else if (input.split(" ")[0].equals("done")) {

                String[] parsedInput = input.split(" ");

                if (parsedInput.length != 2) {
                    System.out.println("Please specify a task you would like marked as done Sir/Mdm:");
                    this.list();
                    continue;
                }

                int taskToMark;

                try {
                    taskToMark = Integer.parseInt(parsedInput[1]) - 1;
                } catch (NumberFormatException e) {
                    System.out.println("Please enter a proper number within this range Sir/Mdm:");
                    this.list();
                    continue;
                }

                if (taskToMark < 0 || taskToMark > count - 1) {
                    System.out.println("Please specify a task within this range Sir/Mdm:");
                    list();
                    continue;
                }

                tasks[taskToMark].markAsDone();
                System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n" +
                        tasks[taskToMark] + "\n");
            } else {
                Task newTask = new Task(input);
                this.add(newTask);
                System.out.println("added: " + newTask + "\n");
            }
        }
    }

    private void add(Task task) {
        tasks[this.count] = task;
        this.count++;
    }

    private void list() {
        for (int i = 0; i < tasks.length; i++) {
            if (tasks[i] == null) break;
            System.out.println((i + 1) + ". " + tasks[i]);
        }
        System.out.println();

    }

    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.serve();
    }


}



