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
                Task newTask;
                if (command.equals("todo")) {
                    newTask = new ToDo(commandAndArgument[1]);
                } else if (command.equals("deadline")) {
                    String taskDetails = commandAndArgument[1];
                    String[] descriptionAndDateTime = taskDetails.split(" /by ", 2);
                    newTask = new Deadline(descriptionAndDateTime[0], descriptionAndDateTime[1]);
                } else if (command.equals("event")) {
                    String taskDetails = commandAndArgument[1];
                    String[] descriptionAndDateTime = taskDetails.split(" /at ", 2);
                    newTask = new Event(descriptionAndDateTime[0], descriptionAndDateTime[1]);
                } else {
                    System.out.println("Invalid command. List of valid commands include: "
                            + "list|todo|deadline|event|done|bye");
                    continue;
                }
                taskList[currentIndex] = newTask;
                currentIndex++;
                String addTaskText = "____________________________________________________________\n"
                        + "Got it. I've added this task:\n"
                        + newTask + '\n'
                        + "Now you have " + currentIndex + (currentIndex == 1 ? " task" : " tasks") + " in the list.\n"
                        + "____________________________________________________________";
                System.out.println(addTaskText);
            }
        }
    }
}
