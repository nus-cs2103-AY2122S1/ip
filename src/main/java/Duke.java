import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    private List<Task> taskList;

    public Duke() {
        taskList = new ArrayList<Task>();
    }

    private void greet() {
        String buffer = "     ";
        System.out.println("\n");
        String logo =     buffer + " ____        _        \n"
                        + buffer + "|  _ \\ _   _| | _____ \n"
                        + buffer + "| | | | | | | |/ / _ \\\n"
                        + buffer + "| |_| | |_| |   <  __/\n"
                        + buffer + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println(buffer + "Hello from\n" + logo);

        System.out.println("______________________________________________________________________");
        System.out.println(buffer + "Hello! I'm Duke");
        System.out.println(buffer + "What can I do for you?");
        System.out.println("______________________________________________________________________\n");
    }

    private void InputOutput() {
        Scanner getInput = new Scanner(System.in);
        String buffer = "     ", taskDescription = "", taskObjective = "";
        int first_space_index, listIndex;

        do {
            taskDescription = getInput.nextLine();
            first_space_index = taskDescription.indexOf(' ');

            // Get the first Word of the Input
            if (first_space_index == -1) {
                taskObjective = taskDescription;
            } else {
                taskObjective = taskDescription.substring(0, first_space_index);
            }

            System.out.println("______________________________________________________________________");

            // Check if the command is "bye" and exit
            if (taskObjective.compareToIgnoreCase("bye") == 0) {
                System.out.println(buffer + "Bye. Hope to see you again soon!");

                // Check if the command is "list" and display all the tasks in the list
            } else if (taskObjective.compareToIgnoreCase("list") == 0) {
                System.out.println(buffer + "Here are the tasks in your list:");
                for (int i = 0; i < taskList.size(); i++) {
                    System.out.println(buffer + (i + 1) + "." + taskList.get(i).toString());
                }

            /*
             * Check if the command is "done", Check if the second word in the command is an Integer and if it is then
               mark that task as complete. If not throw NumberFormatException and add command to the list.
             */
            } else if (taskObjective.compareToIgnoreCase("done") == 0) {

                int number_index = taskDescription.substring(first_space_index + 1).indexOf(' ');

                try {
                    // Get the index number of the task in the list
                    if (number_index == -1) {
                        listIndex = Integer.valueOf(taskDescription.substring(first_space_index + 1)) - 1;

                        /*
                         * Validate that the index number if within the bounds of 0 - List Size. If valid mark task as
                           complete else inform the user about the invalid list index
                         */
                        if (listIndex >= 0 && listIndex < taskList.size()) {
                            taskList.get(listIndex).changeStatus();
                            System.out.println(buffer + "Nice! I've marked this task as done:");
                            System.out.println(buffer + "  " + taskList.get(listIndex).toString());
                        } else {
                            System.out.println(buffer + "OOPS! The Wrong Index! The Number of Tasks in the list is "
                                    + taskList.size());
                        }
                    } else {
                        Task task = new Task(taskDescription);
                        taskList.add(task);
                        System.out.println(buffer + "added: " + task.getDescription());
                    }

                } catch(NumberFormatException e) {
                    Task task = new Task(taskDescription);
                    taskList.add(task);
                    System.out.println(buffer + "added: " + task.getDescription());
                }

            } else {
                Task task = new Task(taskDescription);
                taskList.add(task);
                System.out.println(buffer + "added: " + task.getDescription());
            }

            System.out.println("______________________________________________________________________\n");
        } while (!(taskDescription.equals("bye")));
    }

    public static void main(String[] args) {
        Duke instance = new Duke();
        instance.greet();
        instance.InputOutput();
    }
}