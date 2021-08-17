import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello FROM\n" + logo);
        commands();
    }

    private static void commands() {
        Task[] store = new Task[100];
        int pointer = 1;
        System.out.println("Hello! I'm Duke\n");
        System.out.println("What can I do for you?");
        System.out.println("__________________________________________");
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNext()) {
            String command = scanner.nextLine();
            if (command.equals("bye")) {
                System.out.println("    ______________________________________");
                System.out.println("     Bye. Hope to see you again soon!");
                System.out.println("    ______________________________________");
                scanner.close();
                break;
            } else if (command.equals("list")){
                System.out.println("    ______________________________________");
                System.out.println("     Here are the tasks in your list:");
                for (int i = 0; i < pointer - 1; i++) {
                    System.out.printf("     %d.%s\n", i + 1, store[i].toString());
                }
                System.out.println("    ______________________________________");
            } else if (command.length() >= 6 && command.startsWith("done")) {
                String restOfCommand = command.substring(5);
               boolean numeric;
               try {
                   int temp = Integer.parseInt(restOfCommand);
                   numeric = true;
               } catch (NumberFormatException err) {
                   numeric = false;
                }
               if (numeric) {
                   int taskNum = Integer.parseInt(restOfCommand) - 1;
                   try {
                       Task currTask = store[taskNum];
                       currTask.setCompleted();
                       System.out.println("    ______________________________________");
                       System.out.println("     Nice! I've marked this task as done:");
                       System.out.printf("       %s\n", currTask);
                       System.out.println("    ______________________________________");
                   } catch (NullPointerException err) {
                       System.out.println("    ______________________________________");
                       System.out.println("     This task does not exist yet!");
                       System.out.println("    ______________________________________");
                   }
               } else {
                   System.out.println("    ______________________________________");
                   System.out.println("     Enter task number to delete after done!");
                   System.out.println("    ______________________________________");
               }
            } else if (command.startsWith("todo")) {
                String name = command.substring(5);
                Task task  = new ToDo(name);
                store[pointer - 1] = task;
                pointer += 1;
                System.out.println("    ______________________________________");
                System.out.println("     Got it. I've added this task: ");
                System.out.printf("       %s\n",task);
                System.out.printf("     Now you have %d tasks in the list\n", pointer - 1);
                System.out.println("    ______________________________________");
            } else if (command.startsWith("deadline")) {
                if (!command.contains("/by")) {
                    System.out.println("    ______________________________________");
                    System.out.println("     Enter valid command!");
                    System.out.println("    ______________________________________");
                } else {
                    int position = command.indexOf("/by");
                    String name = command.substring(9, position);
                    String date = command.substring(position + 4);
                    Task task  = new Deadline(name, date);
                    store[pointer - 1] = task;
                    pointer += 1;
                    System.out.println("    ______________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n",task);
                    System.out.printf("     Now you have %d tasks in the list\n", pointer - 1);
                    System.out.println("    ______________________________________");
                }
            } else if (command.startsWith("event")) {
                if (!command.contains("/at")) {
                    System.out.println("    ______________________________________");
                    System.out.println("     Enter valid command!");
                    System.out.println("    ______________________________________");
                } else {
                    int position = command.indexOf("/at");
                    String name = command.substring(6, position);
                    String date = command.substring(position + 4);
                    Task task  = new Event(name, date);
                    store[pointer - 1] = task;
                    pointer += 1;
                    System.out.println("    ______________________________________");
                    System.out.println("     Got it. I've added this task: ");
                    System.out.printf("       %s\n",task);
                    System.out.printf("     Now you have %d tasks in the list\n", pointer - 1);
                    System.out.println("    ______________________________________");
                }
            }
            else {
                System.out.println("    ______________________________________");
                System.out.println("     Enter valid command!");
                System.out.println("    ______________________________________");
            }
        }
    }
}
