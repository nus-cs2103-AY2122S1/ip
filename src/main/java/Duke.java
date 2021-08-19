import java.util.Scanner;

public class Duke {
    //    public static int extractInt(String input) {
//        String[] parts = input.split(" ");
//        if(parts.length <= 1) {
////          throw error
//        }
//        String numStr = parts[1];
//        return Integer.valueOf(numStr);
//    }
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
            try {
                if (input.startsWith("done ")) {
                    String[] parts = input.split(" ");
                    if (parts.length <= 1) {
                        throw new DukeException();
                    }
                    String numStr = parts[1];
                    int numInt = Integer.valueOf(numStr);
                    list.setIndexDone(numInt);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("delete ")) {
                    String[] parts = input.split(" ");
                    if (parts.length <= 1) {
                        throw new DukeException();
                    }
                    String numStr = parts[1];
                    int numInt = Integer.valueOf(numStr);
                    list.deleteTask(numInt);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("todo ")) {
                    String task = input.replaceFirst("todo ", "");
                    if (task.isBlank()) {
                        throw new DukeException();
                    }
                    list.addToDo(task);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("deadline ")) {
                    String task = input.replaceFirst("deadline ", "");
                    if (task.isBlank()) {
                        throw new DukeException();
                    }
                    String[] parts = task.split("/by");
                    list.addDeadline(parts[0], parts[1]);
                    input = sc.nextLine();
                    continue;
                } else if (input.startsWith("event ")) {
                    String task = input.replaceFirst("event ", "");
                    if (task.isBlank()) {
                        throw new DukeException();
                    }
                    String[] parts = task.split("/at");
                    list.addEvent(parts[0], parts[1]);
                    input = sc.nextLine();
                    continue;
                }

                switch (input) {
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
                        throw new DukeException();
                }
            } catch (DukeException e) {
                System.out.println("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                input = sc.nextLine();
            }
        }
        sc.close();
    }
}
