import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.text.DateFormat;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

/**
 * A class to create chatBot called MyJournal.
 *
 * @author Felissa Faustine
 */
public class MyJournal {
    private Storage storage;
    private TaskList tasks;

    public MyJournal(String filepath) {
        storage = new Storage(filepath);
        try {
            tasks = new TaskList(storage.load());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            tasks = new TaskList();
        }
    }

    /**
     * Prints out the statement after a task is added.
     *
     * @param taskList The list of all tasks.
     * @return The statement to be printed after a task is added.
     */
    public static String taskAddPrint(TaskList taskList) {
        return "Okay!! I've added the following task:\n"
                + taskList.getTask(taskList.getSize() - 1) + "\n"
                + "Now you have " + taskList.getSize() + " in the list";
    }

    public boolean isInteger(String string) {
        try {
            Integer.parseInt(string);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void run() throws IOException {
        String input;
        Scanner reader = new Scanner(System.in);
        System.out.println("Hello!\n"
                + "1. Type a task (todo/event/deadline) to be added into your task list.\n"
                + "2. Type 'list' if you want to generate your task list.\n"
                + "3. Type 'done [number]' to mark a task as done.\n"
                + "4. Type 'delete [number]' to delete a task.\n"
                + "5. Type 'bye' to exit");
        while (true) {
            try {
                input = reader.nextLine();
                Scanner line = new Scanner(input);
                String firstWord = line.next();
                if (input.equals("bye")) {
                    break;
                } else if (firstWord.equals("done")) {
                    if (!line.hasNextInt()) {
                        throw new InvalidTaskNumberException("OOPS!!! Please specify the task "
                                + "that needs to be marked as done!");
                    }
                    int index = line.nextInt() - 1;
                    if (index >= tasks.getSize() || index < 0 || tasks.getTask(index) == null) {
                        throw new InvalidTaskNumberException("OOPS!!! Please enter a valid task number!");
                    }
                    tasks.getTask(index).setState(true);
                    System.out.println("Okay!! I have marked this task as done:\n" + tasks.getTask(index));
                } else if (firstWord.equals("delete")) {
                    if (!line.hasNextInt()) {
                        throw new InvalidTaskNumberException("OOPS!!! Please specify the task "
                                + "that needs to be deleted!");
                    }
                    int index = line.nextInt() - 1;
                    if (index >= tasks.getSize() || index < 0 || tasks.getTask(index) == null) {
                        throw new InvalidTaskNumberException("OOPS!!! Please enter a valid task number!");
                    } else {
                        Task temp = tasks.getTask(index);
                        tasks.deleteTask(index);
                        System.out.println("Okay!! I have removed the following task:\n"
                                + temp);
                    }
                } else if (firstWord.equals("list")) {
                    if (tasks.getSize() == 0) {
                        System.out.println("You have no task!");
                    } else {
                        for (int i = 0; i < tasks.getSize(); i++) {
                            System.out.println((i + 1) + "." + tasks.getTask(i));
                        }
                    }
                } else {
                    String taskName = "";
                    switch (firstWord) {
                        case "todo":
                            if (!line.hasNext()) {
                                throw new EmptyDescriptionException("OOPS!!! Please specify the todo!!");
                            }
                            while (line.hasNext()) {
                                String currWord = line.next();
                                taskName = taskName + currWord + " ";
                            }
                            tasks.addTask(new Todo(taskName));
                            System.out.println(taskAddPrint(tasks));
                            break;
                        case "event":
                            if (!line.hasNext()) {
                                throw new EmptyDescriptionException("OOPS!!! Please specify the event!!");
                            }
                            while (line.hasNext()) {
                                String currWord = line.next();
                                if (currWord.charAt(0) == '/') {
                                    break;
                                }
                                taskName = taskName + currWord + " ";
                            }
                          //  String date = getDateTime(line);
                            String time = "";
                            while(line.hasNext()) {
                                time = time + " " + line.hasNext();
                            }
                            tasks.addTask(new Event(taskName, time));
                                    //date));
                            System.out.println(taskAddPrint(tasks));
                            break;
                        case "deadline":
                            if (!line.hasNext()) {
                                throw new EmptyDescriptionException("OOPS!!! Please specify the deadline!!");
                            }
                            while (line.hasNext()) {
                                String currWord = line.next();
                                if (currWord.charAt(0) == '/') {
                                    break;
                                }
                                taskName = taskName + currWord + " ";
                            }
                            time = "";
                            while(line.hasNext()) {
                                time = time + " " + line.hasNext();
                            }
                            tasks.addTask(new Deadline(taskName, time));
                                    //getDateTime(line)));
                            System.out.println(taskAddPrint(tasks));
                            break;
                        default:
                            throw new InvalidTypeException("OOPS!!! Please put either todo/event/deadline!");
                    }
                }
                storage.saveFile(tasks.toString());
            } catch (EmptyDescriptionException exception) {
                System.out.println(exception.toString());
            } catch (InvalidTypeException exception) {
                System.out.println(exception.toString());
            } catch (InvalidTaskNumberException exception) {
                System.out.println(exception.toString());
            }
        }
        System.out.println("Bye. Hope to see you again soon!:)");
    }
    /**
     * The main method of the MyJournal class.
     *
     * @param args An input of an array of strings.
     */
    public static void main(String[] args) {
        try {
            new MyJournal("./tasks.txt").run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}