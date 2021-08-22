import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class Duke {
    private static final ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String userInput = "";
        readTasks();

        String logo = "┏━━┓╋╋╋┏━━┓╋╋╋┏┓\n" +
                      "┃┏┓┃╋╋╋┃┏┓┃╋╋┏┛┗┓\n" +
                      "┃┗┛┗┳━┳┫┗┛┗┳━┻┓┏┛\n" +
                      "┃┏━┓┃┏╋┫┏━┓┃┏┓┃┃\n" +
                      "┃┗━┛┃┃┃┃┗━┛┃┗┛┃┗┓\n" +
                      "┗━━━┻┛┗┻━━━┻━━┻━┛";

        System.out.println("Greetings from\n" + logo);
        printLineSeparator();
        System.out.println("What can I do for you?");
        printLineSeparator();

        while (!userInput.equals("bye")) {
            userInput = sc.nextLine();
            String[] words = userInput.split(" ", 2);
            try {
                switch (words[0]) {
                case "bye":
                    sayGoodBye();
                    saveData();
                    break;
                case "list":
                    printList();
                    break;
                case "done":
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please specify which task you wish to complete.");
                    }
                    int doneIndex = Integer.parseInt(words[1]) - 1;
                    if (doneIndex < 0 || doneIndex >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! You just gave an invalid task to be completed.");
                    }
                    markTask(doneIndex);
                    break;
                case "delete":
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please specify which task you wish to delete.");
                    }
                    int delIndex = Integer.parseInt(words[1]) - 1;
                    if (delIndex < 0 || delIndex >= tasks.size()) {
                        throw new DukeException("☹ OOPS!!! You just gave an invalid task to be deleted.");
                    }
                    deleteTask(delIndex);
                    break;
                case "todo":
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a todo cannot be empty.");
                    }
                    addTask(words[1], "", "",TaskType.TODO);
                    break;
                case "deadline":
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of a deadline cannot be empty.");
                    }
                    String restDeadline = words[1];
                    String[] separatedDeadline = restDeadline.split(" /by ");
                    if (separatedDeadline.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please ensure that the '/by' keyword is used and "
                                              + "that a description and due date is given.");
                    }
                    String deadlineDescription = separatedDeadline[0];
                    String[] deadLineDateTimeArr = separatedDeadline[1].split(" ");
                    String deadLineDateString = deadLineDateTimeArr[0];
                    String deadLineTimeString = deadLineDateTimeArr[1];
                    addTask(deadlineDescription, deadLineDateString, deadLineTimeString, TaskType.DEADLINE);
                    break;
                case "event":
                    if (words.length < 2) {
                        throw new DukeException("☹ OOPS!!! The description of an event cannot be empty.");
                    }
                    String restEvent = words[1];
                    String[] separatedEvent = restEvent.split(" /at ");
                    if (separatedEvent.length < 2) {
                        throw new DukeException("☹ OOPS!!! Please ensure that the '/at' keyword is used and "
                                + "that a description and a timing is given.");
                    }
                    String eventDescription = separatedEvent[0];
                    String[] eventDateTimeArr = separatedEvent[1].split(" ");
                    String eventDateString = eventDateTimeArr[0];
                    String eventTimeString = eventDateTimeArr[1];
                    addTask(eventDescription, eventDateString, eventTimeString, TaskType.EVENT);
                    break;
                default:
                    throw new DukeException("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                }
            } catch (DukeException e) {
                printLineSeparator();
                System.out.println(e.getMessage());
                printLineSeparator();
            }
        }
    }

    private static void readTasks() {
        File savedTasksFile = new File("./src/main/data/tasks.txt");
        if (!savedTasksFile.exists()) {
            return;
        } else {
            try {
                Scanner reader = new Scanner(savedTasksFile);
                while (reader.hasNextLine()) {
                    String taskString = reader.nextLine();
                    String[] taskStringArr = taskString.split("\\|");
                    String type = taskStringArr[0];
                    Task task;
                    switch(type) {
                    case "T":
                        task = new Todo(taskStringArr[2]);
                        break;
                    case "D":
                        task = new Deadline(taskStringArr[2], taskStringArr[3]);
                        // addTask(taskStringArr[2], taskStringArr[3], TaskType.DEADLINE);
                        break;
                    case "E":
                        task = new Event(taskStringArr[2], taskStringArr[3]);
                        // addTask(taskStringArr[2], taskStringArr[3], TaskType.EVENT);
                        break;
                    default:
                        throw new IllegalStateException("Unexpected value: " + type);
                    }
                    if (taskStringArr[1].equals("1")) {
                        task.markAsDone();
                    }
                    tasks.add(task);
                }
            } catch (FileNotFoundException e) {
                System.out.println("An error has occurred");
                e.printStackTrace();
            }
        }
    }

    private static void saveData() {
        File dataDirectory = new File("./src/main/data");
        if (!dataDirectory.exists()) {
            dataDirectory.mkdirs();
        }
        File savedTasksFile = new File("./src/main/data/tasks.txt");
        try {
            savedTasksFile.createNewFile();
            FileWriter writer = new FileWriter(savedTasksFile, false);
            for (int i = 0; i < tasks.size(); i++) {
                StringBuilder builder = new StringBuilder();
                Task task = tasks.get(i);
                builder.append(task.getType());
                builder.append("|");
                if (task.getStatusIcon() == "X") {
                    builder.append("1|");
                } else {
                    builder.append("0|");
                }
                builder.append(task.getDescription());
                builder.append("|");
                builder.append(task.getTiming());
                builder.append("\n");
                writer.write(builder.toString());

            }
            writer.close();
        } catch (IOException e) {
            System.out.println("AN ERROR HAS OCCURRED!!!");
            e.printStackTrace();
        }


    }

    private static void deleteTask(int delIndex) {
        Task task = tasks.get(delIndex);
        tasks.remove(delIndex);
        printLineSeparator();
        System.out.println("Noted. I've removed this task:");
        System.out.println("  " + task);
        printLineSeparator();
    }

    private static void markTask(int index) {
        Task task = tasks.get(index);
        task.markAsDone();
        printLineSeparator();
        System.out.println("Nice! I've marked this task as done:");
        System.out.println("  " + task);
        printLineSeparator();
    }

    private static void printAddedMessage(Task task) {
        printLineSeparator();
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        printLineSeparator();
    }

    private static void addTask(String description,
                                String dateString, String timeString, TaskType taskType) throws DukeException {
        Task task;
        switch (taskType) {
        case TODO:
            task = new Todo(description);
            tasks.add(task);
            printAddedMessage(task);
            break;
        case DEADLINE:
            task = new Deadline(description, dateString, timeString);
            tasks.add(task);
            printAddedMessage(task);
            break;
        case EVENT:
            task = new Event(description, dateString, timeString);
            tasks.add(task);
            printAddedMessage(task);
            break;
        }
    }

    private static void printList() {
        printLineSeparator();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i+1) + ". " + tasks.get(i));
        }
        printLineSeparator();
    }

    private static void sayGoodBye() {
        printLineSeparator();
        System.out.println("Bye. Hope to see you soon!");
        printLineSeparator();
    }

    private static void printLineSeparator() {
        System.out.println("____________________________________________________________");
    }
}
