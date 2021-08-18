import java.util.Scanner;

/**
 * Main program with scanner logic
 */
public class Duke {
    /**
     * Print out the greetings to the user
     */
    private static void greetings() {
        String logo = "\t  ____        _        \n"
                + "\t |  _ \\ _   _| | _____ \n"
                + "\t | | | | | | | |/ / _ \\\n"
                + "\t | |_| | |_| |   <  __/\n"
                + "\t |____/ \\__,_|_|\\_\\___|\n";

        PrintUtil.insertSeparateLine();
        System.out.println(logo);
        PrintUtil.displayContent("Hello! I'm Duke");
        PrintUtil.displayContent("What can I do for you?");
        PrintUtil.insertSeparateLine();
    }

    public static void main(String[] args) {
        Duke.greetings();

        TaskList taskList = new TaskList();
        Scanner sc = new Scanner(System.in);
        String currentCommand = sc.nextLine().trim();

        while (!currentCommand.equals("bye")) {
            if (currentCommand.equals("list")) {
                taskList.displayTaskList();
            } else {
                String[] splitBySpaceCommand = currentCommand.trim().split("\\s+");
                if (splitBySpaceCommand[0].equals("done")) {
                    taskList.markTaskAsDone(Integer.parseInt(splitBySpaceCommand[1]));
                } else if (splitBySpaceCommand[0].equals("deadline")) {
                    String description = "";
                    String by;
                    StringBuilder temp = new StringBuilder();

                    for (int i = 1; i < splitBySpaceCommand.length; i++) {
                        if (splitBySpaceCommand[i].equals("/by")) {
                            description = temp.toString().trim();
                            temp = new StringBuilder();
                        } else {
                            temp.append(splitBySpaceCommand[i]);
                            temp.append(" ");
                        }
                    }
                    by = temp.toString().trim();
                    taskList.addTask(new Deadline(description, by));
                } else if (splitBySpaceCommand[0].equals("event")) {
                    String description = "";
                    String at;
                    StringBuilder current = new StringBuilder();

                    for (int i = 1; i < splitBySpaceCommand.length; i++) {
                        if (splitBySpaceCommand[i].equals("/at")) {
                            description = current.toString().trim();
                            current = new StringBuilder();
                        } else {
                            current.append(splitBySpaceCommand[i]);
                            current.append(" ");
                        }
                    }
                    at = current.toString().trim();
                    taskList.addTask(new Event(description, at));
                } else if (splitBySpaceCommand[0].equals("todo")) {
                    String description;
                    StringBuilder current = new StringBuilder();
                    for (int i = 1; i < splitBySpaceCommand.length; i++) {
                        current.append(splitBySpaceCommand[i]);
                        current.append(" ");
                    }
                    description = current.toString().trim();
                    taskList.addTask(new ToDo(description));
                }
            }
            currentCommand = sc.nextLine().trim();
        }

        PrintUtil.displayContentBetweenLines("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
