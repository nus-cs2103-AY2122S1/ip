import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Commands {
        public static ArrayList<String> arrayList = new ArrayList<>();
        public static void helloCommand() {
            String logo = " ____        _        \n"
                    + "|  _ \\ _   _| | _____ \n"
                    + "| | | | | | | |/ / _ \\\n"
                    + "| |_| | |_| |   <  __/\n"
                    + "|____/ \\__,_|_|\\_\\___|\n";
            System.out.println("____________________________________________________________");
            System.out.println("Hello! I'm \n" + logo);
            System.out.println("What can I do for you?");
            System.out.println("____________________________________________________________");
        }

        public static void byeCommand() {
            System.out.println("____________________________________________________________ \n" +
                    "Bye. Hope to see you again soon! \n" +
                    "____________________________________________________________");
        }

        public static void addCommand(String inputString) {
            System.out.println("____________________________________________________________ \n" +
                    "added: " + inputString + "\n" +
                    "____________________________________________________________");
            arrayList.add(inputString);
            printString();
        }

        public static void listCommand() {
            for(int i = 1; i < arrayList.size() + 1; i++) {
                System.out.println(i + ". " + arrayList.get(i - 1));
            }
        }
    }

    public static void printString() {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        if(inputString.equals("list")) {
            Commands.listCommand();
            printString();
        } else {
            if(inputString.equals("bye")) {
                Commands.byeCommand();
            } else {
                Commands.addCommand(inputString);
            }
        }
    }

    public static void main(String[] args) {
        Commands.helloCommand();
        printString();
    }
}
