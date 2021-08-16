import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // ASCII divider to clean up the output
        final String divider = "---------------------------";

        // Initialise the taskList
        ArrayList<Task> taskList = new ArrayList<>();

        Scanner in = new Scanner(System.in);
        System.out.println(
                "Hello! I'm Duke" +
                        "\n" +
                        "What can I do for you?" +
                        "\n" +
                        divider);

        while (true) {
            try {
                String nextTask = in.nextLine();  // Read user input

                // Uses a series of checks to only check for "done (number)"
                if (nextTask.startsWith("done ")) {
                    if (Character.isDigit(nextTask.charAt(5))) {
                        int taskIndex = Character.getNumericValue(nextTask.charAt(5)) - 1;
                        taskList.get(taskIndex).markAsDone();
                        System.out.println("Nice! I've marked this task as done: " +
                                "\n" +
                                taskList.get(taskIndex) +
                                "\n" +
                                divider);
                    }
                    // case where "done" is a task e.g. "done my homework"
                    else {
                        taskList.add(new Task(nextTask));
                        System.out.println(divider + "\n" + "added: " + nextTask + "\n" + "\n" + divider);
                    }
                    continue;
                }

                // If we want to show the list, it has to not add it to taskList
                else if (nextTask.equals("list")) {
                    System.out.println(divider);
                    System.out.println("Here are the items in your task list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                    System.out.println("\n" + divider);
                    continue;
                } else if (nextTask.equals("bye")) {
                    System.out.println("Bye. Hope to see you again soon!");
                    break;
                }


                // new todo task
                else if (nextTask.startsWith("todo")) {
                    try {
                        String todoDesc = nextTask.substring(5);
                        taskList.add(new Todo(todoDesc));
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty." + "\n" + divider + "\n");
                    }

                }

                // new event task
                else if (nextTask.startsWith("event")) {
                    // need to check that for event and deadline they use the /at and /by properly else reject

                    if (!nextTask.contains("/at ")) {
                        throw new DukeException("Please use this format: 'event <task> /at <date and time>' to specify the date and time!");
                    }

                    int eventDateIndex = nextTask.indexOf("/at ") + 4;
                    String eventDesc = nextTask.substring(6, eventDateIndex - 4); //skip "event "
                    taskList.add(new Event(eventDesc, nextTask.substring(eventDateIndex)));

                }

                // new deadline task
                else if (nextTask.startsWith("deadline")) {
                    // need to check that for event and deadline they use the /at and /by properly else reject

                    if (!nextTask.contains("/by ")) {
                        throw new DukeException("Please use this format: 'deadline <task> /by <date and time>' to specify the date and time!");
                    }
                    //System.out.println(e.getMessage());

                    int deadlineDateIndex = nextTask.indexOf("/by ") + 4;
                    String deadlineDesc = nextTask.substring(9, deadlineDateIndex - 4); //skip the "deadline "
                    taskList.add(new Deadline(deadlineDesc, nextTask.substring(deadlineDateIndex)));

                } else {
                    throw new DukeException("Please enter a valid command");

                }

                System.out.println(taskList.size() > 1
                        ? divider + "\n" + "added: " + taskList.get(taskList.size() - 1) + "\n" +
                          "now you have: " + taskList.size() + " tasks! type 'list' to see them!" + "\n" + divider
                        : divider + "\n" + "added: " + taskList.get(taskList.size() - 1) + "\n" +
                          "now you have: " + taskList.size() + " task! type 'list' to see them!" + "\n" + divider);


            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } catch (Exception e) {
                System.out.println("Please enter a valid task");
            }

        }
    }
}
