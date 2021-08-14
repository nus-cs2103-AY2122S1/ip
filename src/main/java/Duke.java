import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static ArrayList<Task> taskList = new ArrayList<Task>();

    private enum Keywords {bye, list, done, todo, deadline, event, allCmd}

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
                Duke.printLine();
                System.out.println(des + " is not a recognised command");
                System.out.println("Please refer to the available commands using the \"allCmd\" command");
                Duke.printLine();
            } else {
                Duke.printLine();
                if (command.equals("bye")) {
                    Duke.byeCommand();
                    Duke.printLine();
                    break;
                }

                if (command.equals("allCmd")) {
                    Duke.possibleCommands();
                }

                if (command.equals("list")) {
                    Duke.listCommand();
                }

                if (command.equals("done")) {
                    Duke.doneCommand(des);
                }

                if (command.equals("deadline")) {
                    Duke.deadlineCommand(des);
                }

                if (command.equals("event")) {
                    Duke.eventCommand(des);
                }

                if (command.equals("todo")) {
                    Duke.toDoCommand(des);
                }

                Duke.printLine();
            }

        }

    }

    public static void byeCommand() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void listCommand() {
        int count = 1;
        System.out.println("Here are the tasks in your list:");
        for (Task t : taskList) {
            System.out.print(count + ". ");
            if (t instanceof Deadline) {
                Deadline d = (Deadline) t;
                System.out.println(d);
            } else if (t instanceof Event) {
                Event e = (Event) t;
                System.out.println(e);
            } else if (t instanceof ToDo) {
                ToDo td = (ToDo) t;
                System.out.println(td);
            } else {
                System.out.println(t.toString());
            }
            count++;
        }
    }

    public static void doneCommand(String des) {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > taskList.size()) {
            System.out.println("The input number is not a valid task number.");
            System.out.println("Please refer to the task list using the \"list\" command.");
        } else {
            Task atHand = taskList.get(num - 1);
            if (atHand.getIsDone()) {
                System.out.println("You have already completed this task.");
            } else {
                atHand.markAsDone();
                System.out.println("I see that you have completed a task. Keep up the good work!");
                System.out.println();
                System.out.println("This task has now been marked as completed");
                System.out.println(atHand.getStatusIcon() + " " + atHand.getDescription());
            }
        }
    }

    public static void removeCommand(String des) {
        String sNum = des.substring(des.lastIndexOf(' ') + 1);
        int num = Integer.parseInt(sNum);
        if (num <= 0 || num > taskList.size()) {
            System.out.println("The input number is not a valid task number.");
            System.out.println("Please refer to the task list using the \"list\" command.");
        } else {
            taskList.remove(num);
        }
    }

    public static void deadlineCommand(String des) {
        String description = des.substring(9, des.indexOf('/') - 1);
        String date = des.substring(des.indexOf('/') + 4); //+4 as we do not want to include the "/by " in our output
        Task atHand = new Deadline(description, date);
        Deadline deadlineAtHand = (Deadline) atHand;
        taskList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(deadlineAtHand);
        Duke.numberOfTasks();
    }

    public static void eventCommand(String des) {
        String description = des.substring(6, des.indexOf('/') - 1);
        String timeframe = des.substring(des.indexOf('/') + 4); //+4 as we do not want to include the "/by " in our output
        Task atHand = new Event(description, timeframe);
        Event eventAtHand = (Event) atHand;
        taskList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(eventAtHand);
        Duke.numberOfTasks();
    }

    public static void toDoCommand(String des) {
        String description = des.substring(5);
        Task atHand = new ToDo(description);
        ToDo toDoAtHand = (ToDo) atHand;
        taskList.add(atHand);
        System.out.println("Sure. The following task has been added: ");
        System.out.println(toDoAtHand);
        Duke.numberOfTasks();
    }

    public static String checkForKeyword(String des) {
        for (Keywords keyword : Keywords.values()) {
            if (keyword.name().equals("allCmd") && des.equals(keyword.name())) {
                return "allCmd";
            } else if (keyword.name().equals("bye") && des.equals(keyword.name())) {
                return "bye";
            } else if (keyword.name().equals("list") && des.equals(keyword.name())) {
                return "list";
            } else if (keyword.name().equals("done") && des.substring(0, 4).equals("done")) {
                try {
                    String sNum = des.substring(des.lastIndexOf(' ') + 1);
                    int num = Integer.parseInt(sNum);
                    return "done";
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (keyword.name() == "delete" && des.contains("delete") && des.substring(0, 5).equals("delete")) {
                try {
                    String sNum = des.substring(des.lastIndexOf(' ') + 1);
                    int num = Integer.parseInt(sNum);
                    return "delete";
                } catch (NumberFormatException e) {
                    return null;
                }
            } else if (keyword.name().equals("deadline") && des.contains("deadline") && des.substring(0, 8).equals("deadline") && des.contains("/")) {
                return "deadline";
            } else if (keyword.name().equals("event") && des.contains("event") && des.substring(0, 5).equals("event") && des.contains("/")) {
                return "event";
            } else if (keyword.name().equals("todo") && des.contains("todo") && des.substring(0, 4).equals("todo")) {
                return "todo";
            }
        }
        return null;
    }

    public static void printLine() {
        System.out.println("_____________________________________________________________________________________________________________");
    }

    public static void numberOfTasks() {
        if (taskList.size() == 1) {
            System.out.println("You now have " + taskList.size() + " task in the list");
        } else {
            System.out.println("You now have " + taskList.size() + " tasks in the list");
        }
    }

    public static void possibleCommands() {
        System.out.println("The possible commands are as follows:");
        System.out.println();

        System.out.println("1. bye  -------- exit the Duke chatbot");
        System.out.println();

        System.out.println("2. list -------- list all tasks");
        System.out.println();

        System.out.println("3. done -------- Usage --> done x, where x is an integer.");
        System.out.println("               - Marks the corresponding task as completed");
        System.out.println();

        System.out.println("4. todo -------- Usage --> \"todo borrow book\"");
        System.out.println("               - Inputs the a ToDo task into the task list");
        System.out.println();

        System.out.println("5. deadline ---- Usage --> \"deadline submit essay /by Sunday \", remember not to miss the \"/by\" symbol!");
        System.out.println("               - Inputs the an Deadline task into the task list");
        System.out.println();

        System.out.println("6. event ------- Usage --> \"event project meeting /at Mon 2-4pm \", remember not to miss the \"/at\" symbol!");
        System.out.println("               - Inputs the an Event task into the task list");

    }
}
