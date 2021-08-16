import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        //Greet
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");
        //Holds the list of tasks
        ArrayList<Task> task = new ArrayList<>();
        //Get input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        //Continuously gets input from user if input is not 'bye'
        while (!input.equals("bye")) {
            // 'list' command
            if (input.equals("list")) {
                System.out.println("Here are the tasks in your list:");
                // No tasks
                if (task.isEmpty()) {
                    System.out.println("There are no tasks!");
                } else {
                    // Display the list of tasks
                    for (int i = 1; i < task.size() + 1; i++) {
                        System.out.printf("  %d.%s%n", i, task.get(i - 1));
                    }
                }
            } else if (input.length() >= 4 && input.substring(0, 4).equals("done")) {
                //Mark task as done
                int taskNo = Integer.parseInt(input.substring(input.length() - 1));
                task.get(taskNo - 1).markAsDone();
                System.out.println("Nice! I've marked this task as done:\n  " + task.get(taskNo - 1));
            } else {
                //Adding task
                Task newTask = new Task("");

                try {
                    //Determine the type of task and content
                    int spaceIndex = input.indexOf(" ");
                    String typeofTask = input;
                    String content = input;
                    //If there is more than one word
                    if (spaceIndex != -1) {
                        typeofTask = input.substring(0, spaceIndex);
                        content = input.substring(spaceIndex + 1);
                    } else {
                        // 1 word means there is no description
                        content = " ";
                    }
                    //Type of task is not one of the three: todo, deadline, event
                    if (!typeofTask.equals("todo") && !typeofTask.equals("deadline") && !typeofTask.equals("event")) {
                        throw new InvalidTaskException("Not supported! Supported tasks: todo, deadline, event");
                    }

                    //There is no description
                    if (content.isBlank()) {
                        throw new NoDescriptionException("Description of task cannot be empty!");
                    }


                    switch (typeofTask) {
                        case "todo":
                            newTask = new Todo(content);
                            break;
                        case "deadline": {
                            int index = content.indexOf("by");
                            if (index == -1) {
                                throw new WrongDescriptionException("Deadline not included! Try: deadline ... /by ...");
                            } else {
                                newTask = new Deadline(content.substring(0, index - 2), content.substring(index + 3));
                            }
                            break;
                        }
                        case "event": {
                            int index = content.indexOf("at");
                            if (index == -1) {
                                throw new WrongDescriptionException("Time of event not included! Try: event ... /at ...");
                            } else {
                                newTask = new Event(content.substring(0, index - 2), input.substring(index + 3));
                            }
                            break;
                        }
                    }
                } catch (DukeException e) {
                    System.out.println(e.getMessage());
                    input = sc.nextLine();
                    continue;
                }

                task.add(newTask);
                System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                        newTask, task.size());

            }

            input = sc.nextLine();
        }

        System.out.println("Bye. Hope to see you again!");

    }
}