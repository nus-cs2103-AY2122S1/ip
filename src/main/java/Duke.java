import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    public static class Commands {
        public static ArrayList<Task> arrayList = new ArrayList<>();
        public static final String noCommand1 = "____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a todo cannot be empty.\n" +
                "____________________________________________________________";
        public static final String noCommand2 = "____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of a deadline cannot be empty.\n" +
                "____________________________________________________________";
        public static final String noCommand3 = "____________________________________________________________\n" +
                "     ☹ OOPS!!! The description of an event cannot be empty.\n" +
                "____________________________________________________________";
        public static final String wrongCommand1 = "____________________________________________________________\n" +
                "The command format for deadline is wrong. The command format is supposed to be : \n" +
                "deadline <your task> / <your deadline> ! \n" +
                "____________________________________________________________";
        public static final String wrongCommand2 = "____________________________________________________________\n" +
                "The command format for event is wrong. The command format is supposed to be : \n" +
                "event <your task> / <your event time> ! \n" +
                "____________________________________________________________";

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
            if(!command.equals("todo") && !command.equals("deadline") && !command.equals("event")) {
                System.out.println("____________________________________________________________");
                System.out.println("There is no task command starting with : '" + command + "'!");
                System.out.println("The commands for setting tasks are : 'todo', 'deadline', and 'event'.");
                System.out.println("____________________________________________________________");
            } else {
                if (word.length < 2) {
                    switch (command) {
                        case "todo": {
                            System.out.println(noCommand1);
                            break;
                        }
                        case "deadline": {
                            System.out.println(noCommand2);
                            break;
                        }
                        case "event": {
                            System.out.println(noCommand3);
                            break;
                        }
                    }
                } else {
                    String desc = word[1];
                    switch (command) {
                        case "todo": {
                            ToDo task = new ToDo(desc);
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
                            if (descriptions.length < 2) {
                                System.out.println(wrongCommand1);
                            } else {
                                String timeline = descriptions[1];
                                Deadline task = new Deadline(description, timeline);
                                System.out.println("____________________________________________________________\n" +
                                        "Got it. I've added this task:");
                                System.out.println(task.toString());
                                arrayList.add(task);
                                System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                                System.out.println("____________________________________________________________");
                            }
                            break;
                        }
                        case "event": {
                            String[] descriptions = desc.split("/", 2);
                            String description = descriptions[0];
                            if (descriptions.length < 2) {
                                System.out.println(wrongCommand2);
                            } else {
                                String timeline = descriptions[1];
                                Event task = new Event(description, timeline);
                                System.out.println("____________________________________________________________\n" +
                                        "Got it. I've added this task:");
                                System.out.println(task.toString());
                                arrayList.add(task);
                                System.out.println("Now you have " + arrayList.size() + " task(s) in the list.");
                                System.out.println("____________________________________________________________");
                            }
                            break;
                        }
                    }
                }
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
                int removedSize = arrayList.size() - 1;
                System.out.println("____________________________________________________________");
                System.out.println("Noted. I've removed this task: ");
                System.out.println(iTask.toString());
                System.out.println("Now you have " + removedSize + " task(s) in the list.");
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
        } else if (command.equals("delete")) {
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