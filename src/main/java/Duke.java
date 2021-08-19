import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine(); // scan the line for the user's input
        List list = new List();
        boolean shouldContinue = true;
        while (shouldContinue) {//create loop for the chat
            if (input.startsWith("done ")) {
                String[] parts = input.split(" ");
                if(parts.length <= 1) {
                    System.out.println("☹ OOPS!!! I'm sorry, you need to specify which task is done :)");
                    input = sc.nextLine();
                    continue;
                }
                String numStr = parts[1];
                int numInt = Integer.valueOf(numStr);
                System.out.println("Nice! I've marked this task as done: ");
                list.setIndexDone(numInt);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("delete ")) {
                String[] parts = input.split(" ");
                if(parts.length <= 1) {
                    System.out.println("☹ OOPS!!! I'm sorry, you need to specify which task is done :)");
                    input = sc.nextLine();
                    continue;
                }
                String numStr = parts[1];
                int numInt = Integer.valueOf(numStr);
                System.out.println("Noted. I've removed this task: ");
                list.deleteTask(numInt);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("todo ")) {
                String task = input.replaceFirst("todo ", "");
                if (task.isBlank()) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = sc.nextLine();
                    continue;
                }
                list.addToDo(task);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("deadline ")) {
                String task = input.replaceFirst("deadline ", "");
                if (task.isBlank()) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = sc.nextLine();
                    continue;
                }
                String[] parts = task.split("/by");
                list.addDeadline(parts[0], parts[1]);
                input = sc.nextLine();
                continue;
            } else if (input.startsWith("event ")) {
                String task = input.replaceFirst("event ", "");
                if (task.isBlank()) {
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = sc.nextLine();
                    continue;
                }
                String[] parts = task.split("/at");
                list.addEvent(parts[0], parts[1]);
                input = sc.nextLine();
                continue;
            }

            switch(input) {
                case "bye":
                case "Bye":
                case "BYE":
                    System.out.println("Bye. Hope to see you again soon!");
                    shouldContinue = false;
                    break;
                case "list":
                    list.show();
                    input = sc.nextLine();
                    break;

                default:
                    System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    input = sc.nextLine();
            }
        }
        sc.close();
    }
}
