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

    private static String home = System.getProperty("user.home");
    private static String dukeText = "/data/duke.txt";
    private static boolean directoryExists = new java.io.File(home + dukeText).exists();

    public static void newToDo(String taskName, TaskList tList) throws IOException {
        ToDo newToDo = new ToDo(taskName);
        tList.addTask(newToDo);

        if (directoryExists) {
            newToDo.appendToFile(home + dukeText,
                    newToDo.showType() + " | "
                            + ((newToDo.checkDone()).equals("[X]") ? "1" : "0") + " | "
                            + newToDo.showTask() + "\n");
        }

        String toDoMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newToDo.showType() + "]"
                            + newToDo.checkDone() + " "
                            + newToDo.showTask() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(toDoMessage);
    }

    public static void newDeadline(String taskName, TaskList tList) throws IOException {
        Deadline newDeadline = new Deadline(taskName);
        tList.addTask(newDeadline);

        if (directoryExists) {
            newDeadline.appendToFile(home + dukeText,
                    newDeadline.showType() + " | "
                            + ((newDeadline.checkDone()).equals("[X]") ? "1" : "0") + " | "
                            + newDeadline.showTaskOnly() + " | "
                            + newDeadline.showWhen() + "\n");
        }

        String deadlineMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newDeadline.showType() + "]"
                            + newDeadline.checkDone() + " "
                            + newDeadline.showTaskOnly() + " by "
                            + newDeadline.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                            + newDeadline.showTime() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(deadlineMessage);
    }

    public static void newEvent(String taskName, TaskList tList) throws IOException {
        Event newEvent = new Event(taskName);
        tList.addTask(newEvent);

        if (directoryExists) {
            newEvent.appendToFile(home + dukeText,
                    newEvent.showType() + " | "
                            + ((newEvent.checkDone()).equals("[X]") ? "1" : "0") + " | "
                            + newEvent.showTaskOnly() + " | "
                            + newEvent.showWhen() + "\n");
        }

        String eventMessage = "    ____________________________________________________________\n"
                + "     Got it. I've added this task:\n"
                + "       " + "[" + newEvent.showType() + "]"
                            + newEvent.checkDone() + " "
                            + newEvent.showTaskOnly() + " at "
                            + newEvent.showDate().format(DateTimeFormatter.ofPattern("MMM d yyyy")) + ", "
                            + newEvent.showTime() + "\n"
                + "     Now you have " + tList.length() + " tasks in the list.\n"
                + "    ____________________________________________________________";
        System.out.println(eventMessage);
    }

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
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
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
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
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
                } catch (IOException i) {
                    System.out.println("    ____________________________________________________________\n"
                            + "     " + "\uD83D\uDE41" + " OOPS!!! The file that you requested cannot be found.\n"
                            + "    ____________________________________________________________");
                }
                input = sc.nextLine();
                continue;
            }

            try {
                FileWriter writeToFile = new FileWriter(listSource);

                if (instruction[0].equals("delete")) {
                    try{
                        int deleteIndex = Integer.parseInt(instruction[1]);
                        taskList.deleteTask(deleteIndex);
                    } catch (IndexOutOfBoundsException | NumberFormatException i){
                        System.out.println("    ____________________________________________________________\n"
                                + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                                + "    ____________________________________________________________");
                    }

                    writeToFile.write(taskList.refreshList());
                    writeToFile.close();

                    input = sc.nextLine();
                    continue;
                }

                if (instruction[0].equals("done")) {
                    try{
                        int doneIndex = Integer.parseInt(instruction[1]);
                        taskList.markDone(doneIndex);
                    } catch (IndexOutOfBoundsException | NumberFormatException i){
                        System.out.println("    ____________________________________________________________\n"
                                + "     " + "\uD83D\uDE41" + " OOPS!!! Please enter a valid item number.\n"
                                + "    ____________________________________________________________");
                    }

                    writeToFile.write(taskList.refreshList());
                    writeToFile.close();

                    input = sc.nextLine();
                    continue;
                }

                if (input.equals("list")) {
                    taskList.showList();

                    writeToFile.write(taskList.refreshList());
                    writeToFile.close();

                    input = sc.nextLine();
                    continue;
                }

                System.out.println("    ____________________________________________________________\n"
                        + "     " + "\uD83D\uDE41" + " OOPS!!! I'm sorry, but I don't know what that means :-(\n"
                        + "    ____________________________________________________________");

                writeToFile.write(taskList.refreshList());
                writeToFile.close();

                input = sc.nextLine();
            } catch (IOException i) {
                System.out.println("    ____________________________________________________________\n"
                        + "     " + "\uD83D\uDE41" + " OOPS!!! Unable to write to file.\n"
                        + "    ____________________________________________________________");
            }

        }

        System.out.println(ByeMessage);
    }
}
