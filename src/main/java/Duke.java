import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {
        ArrayList<Task> tasks = new ArrayList<Task>();
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("What do you need to do today?");
        System.out.println("_______________________");

        Scanner input = new Scanner(System.in);
        String task = input.nextLine();

        while (!task.equals("bye")) {
            System.out.println("_______________________");

            try {
                if (task.equals("list")) {
                    System.out.println("Here's your to do list:");
                    int j = 0;
                    for (Task item : tasks) {
                        System.out.printf("%d. %s%n", j + 1, tasks.get(j));
                        j += 1;
                    }

                } else if (task.matches("done \\d{1}")) {
                    int taskNo = Integer.parseInt(task.substring(5));
                    int taskIndex = taskNo - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Sorry, I cannot find that task no. please enter a valid number :)");
                    } else {
                        tasks.get(taskIndex).markDone();
                        System.out.println("Good job! I've marked this task as done:");
                        System.out.println(tasks.get(taskIndex));
                    }

                } else if (task.matches("delete \\d{1}")) {
                    int taskNo = Integer.parseInt(task.substring(7));
                    int taskIndex = taskNo - 1;

                    if (taskIndex < 0 || taskIndex >= tasks.size()) {
                        throw new DukeException("Sorry, I cannot delete that task no. please enter a valid number :)");
                    } else {
                        System.out.println("Got it! I've removed this task:");
                        System.out.println(tasks.get(taskIndex));
                        tasks.remove(taskIndex);
                        System.out.printf("Now you have %d tasks in your list.%n", tasks.size());
                    }

                } else if (task.matches("todo (.*)")) {
                    if (task.length() < 6) {
                        throw new DukeException("Oops! The description field of a todo can't be empty.");
                    }
                    tasks.add(new ToDo(task.substring(5)));
                    System.out.printf("added: %s%n", tasks.get(tasks.size() - 1));
                    System.out.printf("Now you have %d tasks in your list.%n", tasks.size());

                } else if (task.matches("deadline (.*)/by (.*)")) {
                    int n = task.indexOf(" /by");
                    tasks.add(new Deadline(task.substring(9, n), task.substring(n + 5)));
                    System.out.printf("added: %s%n", tasks.get(tasks.size() - 1));
                    System.out.printf("Now you have %d tasks in your list.%n", tasks.size());

                } else if (task.matches("event (.*)/at (.*)")) {
                    int n = task.indexOf(" /at");
                    tasks.add(new Event(task.substring(6, n), task.substring(n + 5)));
                    System.out.printf("added: %s%n", tasks.get(tasks.size() - 1));
                    System.out.printf("Now you have %d tasks in your list.%n", tasks.size());

                } else if (task.matches("deadline (.*)") || task.matches("event (.*)"))  {
                    throw new DukeException("Oops! Please include deadline/event time.");

                } else {
                    throw new DukeException("I'm sorry, but I don't understand what that means :(");
                }
            }
            catch (DukeException e) {
                System.out.println(e.getMessage());
            }
            System.out.println("_______________________");
            task = input.nextLine();
        }

        System.out.println("_______________________");
        System.out.println("See you! Have a nice day!");
        System.out.println("_______________________");

    }
}
