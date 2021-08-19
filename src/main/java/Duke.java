import java.util.Scanner;

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
        Task[] taskList = new Task[100];
        Scanner sc = new Scanner(System.in);
        int i = 0;
        while (true) {
            String str = sc.nextLine();
            System.out.print(str + "\n");
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
            if (str.equals("bye")) {
                System.out.print("      Have a good day, friend!\n"
                        + "    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
                break;
            } else if (str.equals("list")) {
                for (int j = 0; j < i; j++) {
                    String listItem = "      "
                            + (j + 1)
                            + "."
                            + taskList[j].getTaskType()
                            + taskList[j].checkIsDone()
                            + " " + taskList[j].getDescription() + "\n";
                    System.out.print(listItem);
                }
            } else if(str.contains("done ")) {
                Integer listIndex = Integer.parseInt(str.substring(5)) - 1;
                taskList[listIndex].markAsDone();
                System.out.print("      Well Done, I'll get it marked:\n"
                        + "        "
                        + taskList[listIndex].checkIsDone()
                        + " " + taskList[listIndex].getDescription() + "\n");
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
                        taskList[i++] = newTask;
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

                }
                catch (DukeException e) {
                        System.out.print("      "
                                + e.toString()
                                + "\n");
                    }

                }
            System.out.print("    ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~\n");
        }
    }
}



