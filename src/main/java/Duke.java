import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Commands {
        public static ArrayList<Task> arrayList = new ArrayList<>();

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
            Task inputTask = new Task(inputString);
            arrayList.add(inputTask);
        }

        public static void listCommand() {
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < arrayList.size() + 1; i++) {
                Task currTask = arrayList.get(i - 1);
                System.out.println(i + "." + "[" + currTask.getStatusIcon() + "] " + currTask.getDescription());
            }
        }

        public static void removeCommand(int i) {
            if (arrayList.size() < i) {
                System.out.println("Sorry! There are no tasks with index number " + i + "! :(");
            } else {
                Task iTask = arrayList.get(i - 1);
                System.out.println("You have removed task : " + iTask.getDescription() + ".");
                arrayList.remove(i - 1);
            }
        }

        public static void doneCommand(int i) {
            if (arrayList.size() < i) {
                System.out.println("Sorry! I can't find the tasks you ask for! :(");
            } else {
                Task iTask = arrayList.get(i - 1);
                iTask.statusDone();
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(i + ". [" + iTask.getStatusIcon() + "] " + iTask.getDescription());
            }
        }
    }

    public static void printString() {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] word = inputString.split(" ", 2);
        String firstWord = word[0];
        if (firstWord.equals("done")) {
            int secondWord = Integer.parseInt(word[1]);
            Commands.doneCommand(secondWord);
            printString();
        } else if (firstWord.equals("remove")) {
            int secondWord = Integer.parseInt(word[1]);
            Commands.removeCommand(secondWord);
            printString();
        } else {
            if (inputString.equals("list")) {
                Commands.listCommand();
                printString();
            } else if (inputString.equals("bye")) {
                Commands.byeCommand();
            } else {
                Commands.addCommand(inputString);
                printString();
            }
        }
    }

    public static void main(String[] args) {
        Commands.helloCommand();
        printString();
    }
}