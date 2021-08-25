import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Duke {
    private static String logo = " ____        _        \n"
            + "|  _ \\ _   _| | _____ \n"
            + "| | | | | | | |/ / _ \\\n"
            + "| |_| | |_| |   <  __/\n"
            + "|____/ \\__,_|_|\\_\\___|\n";

    private static String WelcomeMessage = "    ____________________________________________________________\n"
            + "     Hello! I'm Duke\n"
            + "     What can I do for you?\n"
            + "    ____________________________________________________________";

    private static String ByeMessage = "    ____________________________________________________________\n"
            + "     Bye. Hope to see you again soon!\n"
            + "    ____________________________________________________________";

    public static void newToDo(String taskName, TaskList tList) {
        ToDo newToDo = new ToDo(taskName);
        tList.addTask(newToDo);
        String toDoMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newToDo.showType() + newToDo.checkDone() + " " + newToDo.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(toDoMessage);
    }

    public static void newDeadline(String taskName, TaskList tList) {
        Deadline newDeadline = new Deadline(taskName);
        tList.addTask(newDeadline);
        String deadlineMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newDeadline.showType()
                            + newDeadline.checkDone() + " "
                            + newDeadline.showTaskOnly() + " by "
                            + newDeadline.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                            + newDeadline.showTime() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(deadlineMessage);
    }

    public static void newEvent(String taskName, TaskList tList) {
        Event newEvent = new Event(taskName);
        tList.addTask(newEvent);
        String eventMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + newEvent.showType()
                + newEvent.checkDone() + " "
                + newEvent.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(eventMessage);
    }

    private static String home = System.getProperty("user.home");
    private static String dukeText = home + "/data/duke.txt";

    public static void main(String[] args) {
        System.out.println("Hello from\n" + logo + WelcomeMessage);

        TaskList taskList = new TaskList();

        File listSource = new File(home + dukeText);

        try{
            Scanner listScan = new Scanner(listSource);
            while(listScan.hasNext()) {
                String listInput = listScan.nextLine();
                String[] input = listInput.split("\\|", 3);
                for (int i = 0; i < input.length; i++) {
                    input[i] = input[i].trim();
                }

                switch (input[0]) {
                    case "T":
                        ToDo todo = new ToDo(input[2]);
                        if (input[1].equals("1")) {
                            todo.isDone();
                        }
                        taskList.addTask(todo);
                        break;
                    case "D":
                        String[] deadlineTask = input[2].split("\\|", 2);
                        String deadlineInput = deadlineTask[0].trim() + "|" + deadlineTask[1].trim();
                        Deadline deadline = new Deadline(deadlineInput);
                        if (input[1].equals("1")) {
                            deadline.isDone();
                        }
                        taskList.addTask(deadline);
                        break;
                    case "E":
                        String[] eventTask = input[2].split("\\|", 2);
                        String eventInput = eventTask[0] + " / " + eventTask[1];
                        Event event = new Event(eventInput);
                        if (input[1].equals("1")) {
                            event.isDone();
                        }
                        taskList.addTask(event);
                        break;
                }
            }
            listScan.close();
        } catch (FileNotFoundException f){
            System.out.println("    ____________________________________________________________\n"
                    + "     " + "\uD83D\uDE41" + " OOPS!!! This file does not exist!\n"
                    + "    ____________________________________________________________");
        }

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();

        while (!(input.equals("bye"))) {
            String[] instruction = input.split(" ", 2);
            if (instruction[0].equals("todo")) {
                try {
                    newToDo(instruction[1], taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                                    + "     " + "\uD83D\uDE41" + " OOPS!!! The description of a todo cannot be empty.\n"
                                    + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }

            if (instruction[0].equals("deadline")) {
                try {
                    newDeadline(instruction[1], taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The description of a deadline cannot be empty.\n"
                            + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }
            if (instruction[0].equals("event")) {
                try {
                    newEvent(instruction[1], taskList);
                } catch (ArrayIndexOutOfBoundsException a) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The description of an event cannot be empty.\n"
                            + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }

            if (instruction[0].equals("delete")) {
                try{
                    int deleteIndex = Integer.parseInt(instruction[1]);
                    taskList.deleteTask(deleteIndex);
                } catch (IndexOutOfBoundsException i){
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                            + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }

            if (instruction[0].equals("done")) {
                try{
                    int doneIndex = Integer.parseInt(instruction[1]);
                    Task taskItem = taskList.extractTask(doneIndex - 1);
                    taskItem.isDone();
                    System.out.println(taskItem.taskDone());
                } catch (IndexOutOfBoundsException i){
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                            + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }

            if (input.equals("list")) {
                taskList.showList();
                input = sc.nextLine();
                continue;
            }

            System.out.println("    ____________________________________________________________\n"
                    + "     " + "\uD83D\uDE41" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                    + "    ____________________________________________________________");
            input = sc.nextLine();
        }

        System.out.println(ByeMessage);
    }
}
