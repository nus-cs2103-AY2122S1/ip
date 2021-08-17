import java.util.Scanner;
import java.util.ArrayList;

public class Duke {
    public static void main(String[] args) {

        // ASCII divider to clean up the output
        final String divider = "---------------------------";

        // Initialise the taskList
        ArrayList<Task> taskList = new ArrayList<>();

        // Init the scanner
        Scanner in = new Scanner(System.in);

        // Intro message
        System.out.println(
                "Hello! I'm Duke" + "\n" +
                        "What can I do for you?" + "\n" +
                        divider);

        while (true) {
            try {
                String nextTask = in.nextLine();  // Read user input

                // Case where user marks a task as done
                // Uses a series of checks to only check for "done (number)"
                if (nextTask.startsWith("done")) {

                    // there must be a number following "done"
                    if (nextTask.length() == 4) {
                        throw new DukeException("Please use this format: 'done (task number)'");
                    }

                    try {
                        int taskIndex = Integer.parseInt(nextTask.substring(5)) - 1;

                        if (taskIndex >= taskList.size() || taskIndex < 0) {
                            throw new DukeException("Invalid task number!");
                        }

                        taskList.get(taskIndex).markAsDone();
                        System.out.println(divider + "\n" + "Nice! I've marked this task as done: " + "\n" +
                                taskList.get(taskIndex) + "\n" +
                                divider);
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a proper task number!");
                    }
                    continue;

                    // case where user wants to delete a task item, similar to done
                } else if (nextTask.startsWith("delete")) {

                    if (nextTask.length() == 6) {
                        throw new DukeException("Please use this format: 'delete (task number)'");
                    }

                    try {
                        int taskIndex = Integer.parseInt(nextTask.substring(7)) - 1;

                        if (taskIndex >= taskList.size() || taskIndex < 0) {
                            throw new DukeException("Invalid task number!");
                        }

                        System.out.println(divider + "\n" + "Nice! Noted. I've removed this task: " + "\n" +
                                taskList.get(taskIndex) + "\n" +
                                "You now have " + (taskList.size() - 1) + " tasks remaining!" + "\n" +
                                divider);

                        taskList.remove(taskIndex);
                        continue;
                    } catch (NumberFormatException e) {
                        throw new DukeException("Please enter a proper task number!");
                    }
                }

                // Case where user wants to see the entire task list
                else if (nextTask.equals("list")) {
                    System.out.println(divider + "\n" + "Here are the items in your task list: ");
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println(i + 1 + ". " + taskList.get(i));
                    }
                    System.out.println(divider);
                    continue;

                    // Case where user exits the program
                } else if (nextTask.equals("bye")) {
                    System.out.println(divider + "\n" + "Bye. Hope to see you again soon!" + "\n" + divider);
                    break;
                }

                // Case where user wants to add a new to do task
                else if (nextTask.startsWith("todo")) {
                    try {
                        String todoDesc = nextTask.substring(5);
                        taskList.add(new Todo(todoDesc));

                        // catch the exception created by .substring method and throw a new DukeException which is caught at the end
                    } catch (IndexOutOfBoundsException e) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                }

                // Case where user wants to add a new event task
                else if (nextTask.startsWith("event")) {

                    // need to check that for event they use the /at properly else reject
                    if (!nextTask.contains("/at ")) {
                        throw new DukeException("Please use this format: 'event <task> /at <date and time>' to specify the date and time!");
                    }

                    int eventDateIndex = nextTask.indexOf("/at ") + 4;
                    String eventDesc = nextTask.substring(6, eventDateIndex - 4); //skip "event "
                    taskList.add(new Event(eventDesc, nextTask.substring(eventDateIndex)));

                }

                // Case where user wants to add a new deadline task
                else if (nextTask.startsWith("deadline")) {

                    // need to check that for deadline they use the /by properly else reject
                    if (!nextTask.contains("/by ")) {
                        throw new DukeException("Please use this format: 'deadline <task> /by <date and time>' to specify the date and time!");
                    }

                    int deadlineDateIndex = nextTask.indexOf("/by ") + 4;
                    String deadlineDesc = nextTask.substring(9, deadlineDateIndex - 4); //skip the "deadline "
                    taskList.add(new Deadline(deadlineDesc, nextTask.substring(deadlineDateIndex)));

                    // Else case for all non-recognised user inputs
                } else {
                    throw new DukeException("Please enter a valid command");

                }

                // When adding a new task, this message be printed
                System.out.println(taskList.size() > 1
                        ? divider + "\n" + "added: " + taskList.get(taskList.size() - 1) + "\n" +
                        "now you have: " + taskList.size() + " tasks! type 'list' to see them!" + "\n" + divider
                        : divider + "\n" + "added: " + taskList.get(taskList.size() - 1) + "\n" +
                        "now you have: " + taskList.size() + " task! type 'list' to see them!" + "\n" + divider);

                // catch all the custom exceptions and displays the message
            } catch (DukeException e) {
                System.out.println(divider + "\n" + e.getMessage() + "\n" + divider);

                // catch the remaining exceptions
            } catch (Exception e) {
                System.out.println(divider + "\n" + "Please enter a valid task" + "\n" + divider + "\n");
            }
        }
    }
}
