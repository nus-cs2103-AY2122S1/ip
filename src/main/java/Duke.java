import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> tasklist = new ArrayList<Task>();

    private enum Keywords {bye, list}

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
            if (!checkForKeyword(des)) {
                Task addedTask = new Task(des);
                tasklist.add(addedTask);
                Duke.printLine();
                System.out.println("added: " + des);
                Duke.printLine();
            } else {
                if (des.equals(Keywords.bye.name())) {
                    Duke.printLine();
                    Duke.byeCommand();
                    Duke.printLine();
                    break;
                }

                if (des.equals(Keywords.list.name())) {
                    Duke.printLine();
                    Duke.listCommand();
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
        for (Task t : tasklist) {
            System.out.print(count + ". " + t.getDescription() + "\n");
            count++;
        }
    }

    public static boolean checkForKeyword(String des) {
        for (Keywords keyword : Keywords.values()) {
            if (des.equals(keyword.name())) {
                return true;
            }
        }
        return false;
    }
    
    public static void printLine() {
        System.out.println("___________________________________________________________");
    }
}
