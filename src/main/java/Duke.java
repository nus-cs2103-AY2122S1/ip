import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Duke {

    private Task[] tasks = new Task[100];

    private int count = 0;

    private void serve() {
        System.out.println("Good Day Sir/Mdm, I am Duke.\nWhat can I do for you?\n");

        Scanner sc = new Scanner(System.in);

        while (true) {

            if (!sc.hasNextLine()) {
                sc.close();
                break;
            }
            String input = sc.nextLine();

            if (input.equals("bye")) {
                System.out.println("Goodbye Sir/Mdm. Hope to serve you again soon!\n");
                sc.close();
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
                System.out.println("Good job Sir/Mdm! I shall mark this task as complete:\n   " +
                        tasks[taskToMark] + "\n");
            } else {

                Pattern todoPattern = Pattern.compile("(^(todo ))");
                Pattern deadlinePattern = Pattern.compile("(^(deadline ))");
                Pattern eventPattern = Pattern.compile("(^(event ))");


                if (todoPattern.matcher(input).find()) {
                    Task newTask = new Todo(input.substring(5, input.length()));
                    this.add(newTask);
                    System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
                    System.out.println("Now you have " + count + (count == 1 ? " task." : " tasks.")  + "\n");

                } else if (deadlinePattern.matcher(input).find()) {
                    int index = input.indexOf('/');
                    String description = input.substring(9, index).trim();
                    String date = input.substring(index + 1, input.length());
                    Task newTask = new Deadline(description, date);
                    this.add(newTask);
                    System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
                    System.out.println("Now you have " + count + (count == 1 ? " task." : " tasks.")  + "\n");

                } else if (eventPattern.matcher(input).find()) {
                    int index = input.indexOf('/');
                    String description = input.substring(6, index).trim();
                    String date = input.substring(index + 1, input.length());
                    Task newTask = new Event(description, date);
                    this.add(newTask);
                    System.out.println("Understood Sir/Mdm, I have added the indicated task: " + "\n   " + newTask);
                    System.out.println("Now you have " + count + (count == 1 ? " task." : " tasks.")  + "\n");

                }
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



