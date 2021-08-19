import java.util.Scanner;
import java.util.ArrayList;

public class Duke {


    public static void main(String[] args) {

        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        System.out.println("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n"
                + "      My favorite partner is back! How can I help?\n"
                + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        ArrayList<Task> taskList = new ArrayList<Task>();
        Scanner sc = new Scanner(System.in);
        while (true) {
            String str = sc.nextLine();
            System.out.print(str + "\n");
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            if (str.equals("bye")) {
                System.out.print("      Have a good day, friend!\n"
                        + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            } else if (str.equals("list")) {
                for (int j = 0; j < Task.noOfTask; j++) {
                    String listItem = "      "
                            + (j + 1)
                            + "."
                            + taskList.get(j).getTaskType()
                            + taskList.get(j).checkIsDone()
                            + " " + taskList.get(j).getDescription() + "\n";
                    System.out.print(listItem);
                }
            } else if(str.contains("done ")) {
                Integer listIndex = Integer.parseInt(str.substring(5)) - 1;
                taskList.get(listIndex).markAsDone();
                System.out.print("      Well Done, I'll get it marked:\n"
                        + "        "
                        + taskList.get(listIndex).checkIsDone()
                        + " " + taskList.get(listIndex).getDescription() + "\n");
            } else if (str.contains("delete ")) {
                int removeTaskIndex = Integer.parseInt(str.substring(7)) - 1;
                Task removedTask = taskList.remove(removeTaskIndex);
                System.out.print("      Roger! I will remove this task from the list: \n"
                        + "        "
                        + removedTask.getTaskType()
                        + removedTask.checkIsDone()
                        + removedTask.getDescription() + "\n"
                        + "      Now you have "
                        + --Task.noOfTask
                        + " tasks left in the list.\n");
            } else {
                    try {
                        Task newTask = null;
                        if (str.contains("todo")) {
                            newTask = new Todo(str);
                        } else if (str.contains("deadline")) {
                            if (!str.contains("/")) {
                                newTask = new Deadline(str, "");
                            } else {
                                String[] parts = str.split("/");
                                newTask = new Deadline(parts[0], parts[1]);
                            }
                        } else if (str.contains("event")) {
                            if (!str.contains("/")) {
                                newTask = new Event(str, "");
                            } else {
                                String[] parts = str.split("/");
                                newTask = new Event(parts[0], parts[1]);
                            }
                        }
                        if (newTask != null) {
                            taskList.add(Task.noOfTask - 1, newTask);
                            System.out.print("      Roger! I will add this task in: \n"
                                    + "        "
                                    + newTask.getTaskType()
                                    + newTask.checkIsDone()
                                    + newTask.getDescription() + "\n"
                                    + "      Now you have "
                                    + Task.noOfTask
                                    + " tasks left in the list.\n");

                        } else {
                            throw new WrongInputException();
                        }

                    } catch (DukeException e) {
                        System.out.print("      "
                                + e.toString()
                                + "\n");
                    }
            }
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }
}



