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
            String[] word = inputString.split(" ", 2);
            String command = word[0];
            String desc = word[1];
            switch (command) {
                case "todo": {
                    ToDo task = new ToDo(inputString);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println(task.toString());
                    arrayList.add(task);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                case "deadline": {
                    String[] descriptions = desc.split("/", 2);
                    String description = descriptions[0];
                    String timeline = descriptions[1];
                    Deadline task = new Deadline(description, timeline);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println(task.toString());
                    arrayList.add(task);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                case "event": {
                    String[] descriptions = desc.split("/", 2);
                    String description = descriptions[0];
                    String timeline = descriptions[1];
                    Event task = new Event(description, timeline);
                    System.out.println("____________________________________________________________\n" +
                            "Got it. I've added this task:");
                    System.out.println(task.toString());
                    arrayList.add(task);
                    System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                    System.out.println("____________________________________________________________");
                    break;
                }
                default:
                    System.out.println("____________________________________________________________");
                    System.out.println("There is no task command starting with : '" + command + "'!");
                    System.out.println("The commands for setting tasks are : 'todo', 'deadline', and 'event'.");
                    System.out.println("____________________________________________________________");
                    break;
            }
        }

        public static void listCommand() {
            System.out.println("____________________________________________________________");
            System.out.println("Here are the tasks in your list:");
            for (int i = 1; i < arrayList.size() + 1; i++) {
                Task currTask = arrayList.get(i - 1);
                System.out.println(i + "." + currTask.toString());
            }
            System.out.println("____________________________________________________________");
        }

        public static void removeCommand(int i) {
            if (arrayList.size() < i) {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry! There are no tasks with index number " + i + "! :(");
                System.out.println("____________________________________________________________");
            } else {
                Task iTask = arrayList.get(i - 1);
                System.out.println("____________________________________________________________");
                System.out.println("You have removed task : " + i + ". " + iTask.getDescription() + ".");
                System.out.println("____________________________________________________________");
                arrayList.remove(i - 1);
            }
        }

        public static void doneCommand(int i) {
            if (arrayList.size() < i) {
                System.out.println("____________________________________________________________");
                System.out.println("Sorry! I can't find the tasks you ask for! :(");
                System.out.println("____________________________________________________________");
            } else {
                Task iTask = arrayList.get(i - 1);
                iTask.statusDone();
                System.out.println("____________________________________________________________");
                System.out.println("Nice! I've marked this task as done: ");
                System.out.println(i + ". " + iTask.toString());
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static void printString() {
        Scanner sc = new Scanner(System.in);
        String inputString = sc.nextLine();
        String[] word = inputString.split(" ", 2);
        String command = word[0];
        if (command.equals("done")) {
            int secondWord = Integer.parseInt(word[1]);
            Commands.doneCommand(secondWord);
            printString();
        } else if (command.equals("remove")) {
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