import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_CYAN = "\u001B[36m";

    private final List<Task> taskList;

    public Duke() {
        taskList = new ArrayList<>();
    }

    private void greet() {
        System.out.println("\n");
        String buffer = "     ";
        String logo =   buffer + " ____        _\n"
                      + buffer + "|  _ \\ _   _| | _____\n"
                      + buffer + "| | | | | | | |/ / _ \\\n"
                      + buffer + "| |_| | |_| |   <  __/\n"
                      + buffer + "|____/ \\__,_|_|\\_\\___|\n";
        Display("Hello from\n" + logo);

        System.out.println("______________________________________________________________________");
        Display("Hello! I'm Duke");
        Display("What can I do for you?");
        System.out.println("______________________________________________________________________\n");
    }

    private void Display(String message) {
        System.out.println(ANSI_CYAN + "     " + message + ANSI_RESET);
    }

    private void Interact() {
        Scanner getInput = new Scanner(System.in);
        String taskDescription, taskObjective;
        int first_space_index, listIndex;
        boolean flag;

        do {
            taskDescription = getInput.nextLine().trim();
            first_space_index = taskDescription.indexOf(' ');

            // Get the first Word of the Input
            if (first_space_index == -1) {
                taskObjective = taskDescription;
                flag = true;
            } else {
                taskObjective = taskDescription.substring(0, first_space_index);
                flag = false;
            }

            System.out.println("______________________________________________________________________");

            try {
                // Check if the command is "bye" and exit
                if ((taskObjective.compareToIgnoreCase("bye") == 0) && flag) {
                    Display("Bye. Hope to see you again soon! \\_(\"v\")_/");

                    // Check if the command is "list" and display all the tasks in the list
                } else if ((taskObjective.compareToIgnoreCase("list") == 0) && flag) {
                    if (taskList.size() == 0) {
                        throw new DukeException("OOPS! There are no Tasks in the System");
                    } else {
                        Display("Here are the tasks in your list:");
                        for (int i = 0; i < taskList.size(); i++) {
                            Display((i + 1) + "." + taskList.get(i).toString());
                        }
                    }

            /*
             * Check if the command is "done", Check if the second word in the command is an Integer and if it is then
               mark that task as complete. If not throw NumberFormatException and add command to the list.
             */
                } else if ((taskObjective.compareToIgnoreCase("done") == 0) && !flag) {

                    try {
                        listIndex = Integer.parseInt(taskDescription.substring(first_space_index + 1)) - 1;

                    /*
                     * Validate that the index number if within the bounds of 0 - List Size. If valid mark task as
                       complete else inform the user about the invalid list index
                     */
                        if (listIndex >= 0 && listIndex < taskList.size()) {
                            taskList.get(listIndex).changeStatus();
                            Display("Nice! I've marked this task as done:");
                            Display("  " + taskList.get(listIndex).toString());
                        } else {
                            throw new DukeException("OOPS! The Wrong Index! The Number of Tasks in the list is "
                                    + taskList.size());
                        }
                    } catch (NumberFormatException e) {
                        throw new DukeException("OOPS! The done Command should be followed by a Task Index!");
                    }

                    // Add the Deadline Task to the taskList
                } else if (taskObjective.compareToIgnoreCase("deadline") == 0) {
                    if (!flag) {
                        String taskDetails = taskDescription.substring(first_space_index + 1).trim();
                        int number_index = taskDetails.indexOf('/');
                        if ((number_index != -1 && number_index != 0) && taskDetails.substring(number_index).trim().length() > 4) {
                            String checkForBy = taskDetails.substring(number_index, number_index + 4);
                            if (checkForBy.compareToIgnoreCase("/by ") == 0) {
                                DeadlineTask task = new DeadlineTask(taskDetails.substring(0, number_index - 1),
                                        taskDetails.substring(number_index + 4));
                                taskList.add(task);
                                Display("Got it. I've added this task:");
                                Display("  " + task);
                                Display("Now you have " + taskList.size() + " tasks in the list.");
                            } else {
                                throw new DukeException("OOPS! Please provide the Deadline");
                            }
                        } else if ((number_index == 0) && (taskDetails.substring(number_index).trim().length() < 4)) {
                            throw new DukeException("OOPS! Please provide the Description and the Deadline");
                        } else if ((number_index == -1) || (taskDetails.substring(number_index).trim().length() < 4)) {
                            throw new DukeException("OOPS! Please provide the Deadline");
                        } else if (number_index == 0) {
                            throw new DukeException("OOPS! Please provide the Description");
                        } else {
                            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
                        }
                    } else {
                        throw new DukeException("OOPS! Please provide the Description and the Deadline");
                    }

                    // Add the Event Task to the taskList
                } else if (taskObjective.compareToIgnoreCase("event") == 0) {
                    if (!flag) {
                        String taskDetails = taskDescription.substring(first_space_index + 1).trim();
                        int number_index = taskDetails.indexOf('/');
                        if ((number_index != -1 && number_index != 0) && taskDetails.substring(number_index).trim().length() > 4) {
                            String checkForBy = taskDetails.substring(number_index, number_index + 4);
                            if (checkForBy.compareToIgnoreCase("/at ") == 0) {
                                EventTask task = new EventTask(taskDetails.substring(0, number_index - 1),
                                        taskDetails.substring(number_index + 4));
                                taskList.add(task);
                                Display("Got it. I've added this task:");
                                Display("  " + task);
                                Display("Now you have " + taskList.size() + " tasks in the list.");
                            } else {
                                throw new DukeException("OOPS! Please provide the Details");
                            }
                        } else if ((number_index == 0) && (taskDetails.substring(number_index).trim().length() < 4)) {
                            throw new DukeException("OOPS! Please provide the Description and the Details");
                        } else if ((number_index == -1) || (taskDetails.substring(number_index).trim().length() < 4)) {
                            throw new DukeException("OOPS! Please provide the Details");
                        } else if (number_index == 0) {
                            throw new DukeException("OOPS! Please provide the Description");
                        } else {
                            throw new DukeException("OOPS! I'm sorry, but I don't know that command");
                        }
                    } else {
                        throw new DukeException("OOPS! Please provide the Description and the Details");
                    }

                    // Add the task to the taskList
                } else if (taskObjective.compareToIgnoreCase("todo") == 0) {
                    if (!flag) {
                        String taskDetails = taskDescription.substring(first_space_index + 1);
                        ToDoTask task = new ToDoTask(taskDetails);
                        taskList.add(task);
                        Display("Got it. I've added this task:");
                        Display("  " + task);
                        Display("Now you have " + taskList.size() + " tasks in the list.");
                    } else {
                        throw new DukeException("OOPS! Please provide the Description");
                    }
                } else {
                    throw new DukeException("OOPS! I'm sorry, but I don't know that command");
                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
            }

            System.out.println("______________________________________________________________________\n");
        } while (!(taskDescription.equals("bye")));
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.greet();
        instance.Interact();
    }
}