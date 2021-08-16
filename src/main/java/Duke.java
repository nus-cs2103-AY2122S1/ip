import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = "____________________________________________________________\n"
                + " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n"
                + "Hello! I'm Duke\n"
                + "What can I do for you?\n"
                + "____________________________________________________________";
        System.out.println(logo);

        Scanner sc = new Scanner(System.in);
        Task[] taskList = new Task[100];
        int currentIndex = 0;

        while (true) {
            String userInput = sc.nextLine();
            String[] commandAndArgument = userInput.split(" ", 2);
            String command = commandAndArgument[0];

            if (command.equals("bye")) {
                String exitText = "____________________________________________________________\n"
                        + "Bye. Hope to see you again soon!\n"
                        + "____________________________________________________________";
                System.out.println(exitText);
                sc.close();
                break;
            } else if (command.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
                for (int i = 0; i < currentIndex; i++) {
                    System.out.println(String.format("%d.%s", i + 1, taskList[i]));
                }
                System.out.println("____________________________________________________________");
            } else if (command.equals("done")) {
                try {
                    int taskIndex = Integer.parseInt(commandAndArgument[1]) - 1;
                    if (taskIndex >= currentIndex) {
                        System.out.println("____________________________________________________________\n"
                                + "Please enter a valid task number\n"
                                + "____________________________________________________________");
                    } else {
                        taskList[taskIndex].markAsDone();
                        String markedAsDoneText = String.format(
                                "____________________________________________________________\n"
                                        + "Nice! I've marked this task as done:\n"
                                        + "%s\n"
                                        + "____________________________________________________________",
                                taskList[taskIndex]);
                        System.out.println(markedAsDoneText);
                    }
                } catch (ArrayIndexOutOfBoundsException | NumberFormatException e) {
                    System.out.println("____________________________________________________________\n"
                            + "Please enter a valid task number\n"
                            + "____________________________________________________________");
                }
            } else {
                taskList[currentIndex] = new Task(userInput);
                currentIndex++;
                String addTaskText = "____________________________________________________________\n"
                        + "added: " + userInput + '\n'
                        + "____________________________________________________________";
                System.out.println(addTaskText);
            }
        }
    }
}
