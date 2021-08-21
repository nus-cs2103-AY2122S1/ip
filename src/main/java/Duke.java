import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    public enum Command {
        LIST("list"), DONE("done"), TODO("todo"),
        DEADLINE("deadline"), EVENT("event"), DELETE("delete");

        public final String command;

        private Command(String command) {
            this.command = command;
        }
    }

    public static void main(String[] args) {
        //Greet
        System.out.println("Hello! I'm Duke!\nWhat can I do for you?\n");
        //Holds the list of tasks
        ArrayList<Task> task = new ArrayList<>();
        //Get input
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!input.equals("bye")) {
            String[] parsed = input.split(" ", 2);

            try {
                boolean validCommand = false;
                for (Command current : Command.values()) {
                    if (current.command.equals(parsed[0])) {
                        validCommand = true;
                        switch (current) {
                            case LIST: {
                                if (task.isEmpty()) {
                                    System.out.println("There are no tasks!");
                                } else {
                                    System.out.println("Here are the tasks in your list:");
                                    for (int i = 1; i < task.size() + 1; i++) {
                                        System.out.printf("  %d.%s%n", i, task.get(i - 1));
                                    }
                                }
                                break;
                            }

                            case DONE: {

                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int taskNo = Integer.parseInt(description);
                                    task.get(taskNo - 1).markAsDone();
                                    System.out.println("Nice! I've marked this task as done:\n  " + task.get(taskNo - 1));
                                    break;
                                }
                            }

                            case DELETE: {

                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int taskNo = Integer.parseInt(description);
                                    Task deletedTask = task.get(taskNo - 1);
                                    task.remove(taskNo - 1);
                                    System.out.printf("Noted. I've removed this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                            deletedTask, task.size());
                                    break;
                                }
                            }

                            case TODO: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    Task newTask = new Todo(description);
                                    task.add(newTask);
                                    System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                            newTask, task.size());
                                }
                                break;
                            }

                            case DEADLINE: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int index = description.indexOf("by");
                                    if (index == -1) {
                                        throw new WrongDescriptionException("Deadline not included! Try: deadline ... /by ...");
                                    } else {
                                        Task newTask = new Deadline(description.substring(0, index - 2), description.substring(index + 3));
                                        task.add(newTask);
                                        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                                newTask, task.size());
                                    }
                                }
                                break;
                            }

                            case EVENT: {
                                if (parsed.length == 1) {
                                    throw new NoDescriptionException("Description of task cannot be empty!");
                                } else {
                                    String description = parsed[1];
                                    int index = description.indexOf("at");
                                    if (index == -1) {
                                        throw new WrongDescriptionException("Deadline not included! Try: deadline ... /at ...");
                                    } else {
                                        Task newTask = new Deadline(description.substring(0, index - 2), description.substring(index + 3));
                                        task.add(newTask);
                                        System.out.printf("Got it. I've added this task:\n  %s\nNow you have %d task(s) in the list.%n",
                                                newTask, task.size());
                                    }
                                }
                                break;
                            }
                        }
                    }
                }

                if (!validCommand) {
                    throw new InvalidTaskException("Invalid command! Please enter the following commands only:\n" +
                            "list\ndone (task number)\n" +
                            "delete (task number)\ntodo (description)\n" +
                            "deadline (description) /by (time)\nevent (description) /at (time)");
                }

                input = sc.nextLine();

            } catch (DukeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            }
        }
        
        System.out.println("Bye. Hope to see you again!");
    }
}