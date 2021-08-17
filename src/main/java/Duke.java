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
            Task task = new Task(command);
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
                    System.out.printf("     %d.[%s] %s\n", i + 1, store[i].getStatusIcon() ,store[i].toString());
                }
                System.out.println("    ______________________________________");
            } else if (command.length() >= 6 && command.substring(0,4).equals("done")) {
                String restOfCommand = command.substring(5,command.length());
               boolean numeric = false;
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
                       System.out.printf("       [%s] %s\n", currTask.getStatusIcon(), currTask.toString());
                       System.out.println("    ______________________________________");
                   } catch (NullPointerException err) {
                       System.out.println("    ______________________________________");
                       System.out.println("     This task does not exist yet!");
                       System.out.println("    ______________________________________");
                   }
               } else {
                   store[pointer - 1] = task;
                   pointer = pointer += 1;
                   System.out.println("    ______________________________________");
                   System.out.printf("     added: %s\n",task.toString());
                   System.out.println("    ______________________________________");
               }
            } else  {
                store[pointer - 1] = task;
                pointer = pointer += 1;
                System.out.println("    ______________________________________");
                System.out.printf("     added: %s\n",task.toString());
                System.out.println("    ______________________________________");
            }
        }
    }
}
