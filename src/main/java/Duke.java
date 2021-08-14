import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasklist = new ArrayList<Task>();

    private enum Keywords {bye, list, done}

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);
        Scanner sc = new Scanner(System.in);

        System.out.println("What can I do for you today?");

        while (true) {
            String des = sc.nextLine();
            String command = checkForKeyword(des);
            if (command == null) {
                Task addedTask = new Task(des);
                tasklist.add(addedTask);
                Duke.printLine();
                System.out.println("added: " + des);
                Duke.printLine();
            } else {
                if (command == "bye") {
                    Duke.printLine();
                    Duke.byeCommand();
                    Duke.printLine();
                    break;
                }

                if (command == "list") {
                    Duke.printLine();
                    Duke.listCommand();
                    Duke.printLine();
                }

                if (command == "done") {
                    Duke.printLine();
                    Duke.doneCommand(des);
                    Duke.printLine();
                }
            }

        }

    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listCommand() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : tasklist) {
            System.out.print(count + ". " + t.getStatusIcon() + t.getDescription() + "\n");
            count++;
        }
    }

    public static void doneCommand(String des) {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > tasklist.size()) {
            System.out.println("The input number is not a valid task number.");
            System.out.println("Please refer to the task list using the \"list\" command.");
        } else {
            Task atHand = tasklist.get(num - 1);
            atHand.markAsDone();
            System.out.println("I see that you have completed a task. Keep up the good work!");
            System.out.println();
            System.out.println("This task has now been marked as completed");
            System.out.println(atHand.getStatusIcon() + atHand.getDescription());
        }
    }

    public static void removeCommand(String des) {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > tasklist.size()) {
            System.out.println("The input number is not a valid task number.");
            System.out.println("Please refer to the task list using the \"list\" command.");
        } else {
            tasklist.remove(num);
        }
    }

    public static String checkForKeyword(String des) {
        for (Keywords keyword : Keywords.values()) {
            if (keyword.name() == "bye" && des.equals(keyword.name())) {
                return "bye";
            } else if (keyword.name() == "list" && des.equals(keyword.name())) {
                return "list";
            } else if (keyword.name() == "done" && des.contains(keyword.name()) && des.substring(0, 4).equals("done")) {
                try {
                    String sNum = des.substring(des.lastIndexOf(' ') + 1);
                    int num = Integer.parseInt(sNum);
                    return "done";
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (keyword.name() == "delete" && des.contains(keyword.name()) && des.substring(0, 5).equals("delete")) {
                try {
                    String sNum = des.substring(des.lastIndexOf(' ') + 1);
                    int num = Integer.parseInt(sNum);
                    return "delete";
                } catch (NumberFormatException e) {
                    return null;
                }
            }
        }
        return null;
    }

    public static void printLine() {
        System.out.println("___________________________________________________________");
    }
}
