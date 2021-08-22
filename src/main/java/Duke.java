import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {
    private static final String COMMAND_BYE = "bye";
    private static final String COMMAND_LIST = "list";
    private static final String COMMAND_DONE = "done";
    private static final String COMMAND_TODO = "todo";
    private static final String COMMAND_DDL = "deadline";
    private static final String COMMAND_EVENT = "event";
    private static final String COMMAND_DELETE = "delete";
    private static final String FILE_DIR = "data";
    private static final String FILE_NAME = "duke.txt";
    private static final DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd HHmm");
    private static DateTimeFormatter outputFormatter = DateTimeFormatter.ofPattern("dd MMM yyyy HHmm");
    private TaskList tasks;
    private Ui ui;
    private Storage storage;


//    private static List<Task> items = new ArrayList<>();

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);

        try {
            tasks = new TaskList(storage.load());
            ui.printWelcomeMessage();
        } catch (IOException e) {
            ui.printError(e.getMessage());
        }

    }



    /**
     * Marks a task as done.
     *
     * @param idx index of the task in the items array list.
     */
    private void doneTask(int idx) {
//        items.get(idx).markAsDone();
        tasks.markTaskDone(idx);

        ui.printHorizontalLine();
        System.out.println("Nice! I've marked this task as done:");
//        System.out.println(items.get(idx));
        System.out.println(tasks.getTask(idx));
        ui.printHorizontalLine();
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param userCommand command string entered by the user.
     */
    public void addTodo(String userCommand) {
        try {
            tasks.addTodo(userCommand);
//            Todo newTodo = new Todo(userCommand.substring(5));
//            items.add(newTodo);


            //            ui.printHorizontalLine();
            //            ui.printAddTask(newTodo);
            //            ui.printTaskNum(items.size());
            //            ui.printHorizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            //            ui.printHorizontalLine();
            System.out.println("Please add a description for your todo!");
            //            ui.printHorizontalLine();
        }
    }

    /**
     * Adds a Deadline Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    private void addDeadline(String userCommand) {
        try {
            int byIndex = userCommand.indexOf("/by");
            String by = userCommand.substring(byIndex + 4);
            LocalDateTime date = LocalDateTime.parse(by, inputFormatter);

            Deadline newDeadline = new Deadline(userCommand.substring(9, byIndex - 1), date);
            items.add(newDeadline);

            ui.printHorizontalLine();
            ui.printAddTask(newDeadline);
            ui.printTaskNum(items.size());
            ui.printHorizontalLine();

        } catch (StringIndexOutOfBoundsException e) {
            ui.printHorizontalLine();
            System.out.println("Please add a description and/or deadline!");
            ui.printHorizontalLine();
        } catch (DateTimeException e) {
            ui.printHorizontalLine();
            System.out.println("Please add a valid deadline of format yyyy/MM/dd HHmm (24-hour format)!");
            ui.printHorizontalLine();
        }
    }

    /**
     * Adds a Event Task to the items arraylist.
     *
     * @param userCommand command string entered by the user.
     */
    private void addEvent(String userCommand) {
        try {
            int atIndex = userCommand.indexOf("/at");
            String at = userCommand.substring(atIndex + 4);
            LocalDateTime date = LocalDateTime.parse(at, inputFormatter);
            Event newEvent = new Event(userCommand.substring(6, atIndex - 1), date);
            items.add(newEvent);


            ui.printHorizontalLine();
            ui.printAddTask(newEvent);
            ui.printTaskNum(items.size());
            ui.printHorizontalLine();
        } catch (StringIndexOutOfBoundsException e) {
            ui.printHorizontalLine();
            System.out.println("Please add a description and/or date for your event!");
            ui.printHorizontalLine();
        } catch (DateTimeException e) {
            ui.printHorizontalLine();
            System.out.println("Please add a valid event date of format yyyy/MM/dd HHmm (24-hour format)!");
            ui.printHorizontalLine();
        }
    }

    /**
     * Deletes a task in the items arraylist.
     *
     * @param deleteIdx index of the task in the items arraylist.
     */
    private void deleteTask(int deleteIdx) {
        Task taskToDelete = items.get(deleteIdx);

        items.remove(deleteIdx);

        ui.printHorizontalLine();
        System.out.println("Noted. I've removed this task:");
        System.out.println(taskToDelete);
        ui.printTaskNum(items.size());
        ui.printHorizontalLine();
    }

    private static void writeToFile(List<Task> tasks) throws IOException {
        // Solution adapted from:
        // https://stackoverflow.com/questions/9658297/java-how-to-create-a-file-in-a-directory-using-relative-path
        File dukeFile = new File(FILE_DIR, FILE_NAME);

        if (!dukeFile.exists()) {
            dukeFile.getParentFile().mkdir();
            dukeFile.createNewFile();
        }

        FileWriter fw = new FileWriter(dukeFile.getAbsoluteFile());

        for (Task t : tasks) {
            fw.write(t.toSaveString() + System.lineSeparator());
        }

        fw.close();
    }

    private static void readFile(String filePath) {
        try {
            File f = new File(filePath);
            if (f.exists()) {
                Scanner sc = new Scanner(f);
                while (sc.hasNext()) {
                    String t = sc.nextLine();
                    String[] task = t.split("\\|");
                    switch (task[0]) {
                    case "T":
                        items.add(new Todo(task[2], task[1].equals("1")));
                        break;

                    case "E":
                        items.add(new Event(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                        break;

                    case "D":
                        items.add(new Deadline(task[2], task[1].equals("1"), LocalDateTime.parse(task[3], outputFormatter)));
                        break;

                    default:
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Something went wrong: " + e.getMessage());
        }

    }

    public static void main(String[] args) {



        readFile("data/duke.txt");

        Scanner sc = new Scanner(System.in);

        while (true) {

            String userCommand = sc.nextLine();

            if (userCommand.replaceAll("\\s+","").equalsIgnoreCase(COMMAND_BYE)) {
                System.out.println("====================================================\n" +
                        "Goodbai. Hope to see you again soon! （ ● ___ ●.）" +
                        "\n====================================================");
                break;
            } else {
                String[] words = userCommand.split(" ");

                try {
                    switch (words[0].toLowerCase()) {
                    case COMMAND_LIST:
//                        ui.printHorizontalLine();
                        items.printList();
//                        ui.printHorizontalLine();
                        break;

                    case COMMAND_DONE:
                        int idx = Integer.parseInt(words[1]) - 1;
                        doneTask(idx);
                        writeToFile(items);
                        break;

                    case COMMAND_TODO:
                        addTodo(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_DDL:
                        addDeadline(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_EVENT:
                        addEvent(userCommand);
                        writeToFile(items);
                        break;

                    case COMMAND_DELETE:
                        int deleteIdx = Integer.parseInt(words[1]) - 1;
                        deleteTask(deleteIdx);
                        writeToFile(items);
                        break;

                    default:
                        throw new DukeException("I'm sorry, but I don't know what that means! D:");
                    }
                } catch (DukeException e) {
                    // unrecognisable input command
//                    printHorizontalLine();
                    System.out.println(e);
//                    printHorizontalLine();

                } catch (ArrayIndexOutOfBoundsException e) {
//                    printHorizontalLine();
                    System.out.println("Invalid input! D:");
//                    printHorizontalLine();

                } catch (IndexOutOfBoundsException e) {
                    // error when try to mark an invalid task as done / delete task

//                    printHorizontalLine();
                    if (items.size() > 0) {
                        System.out.printf("That task does not exist!\nPlease enter a number from 1 to %d.\n", items.size());
                    } else {
                        System.out.println("You have no tasks in your list to mark as done or delete.");
                    }
//                    printHorizontalLine();

                } catch (NumberFormatException e) {
                    // error encountered when command followed by done is not Number e.g. done one
//                    printHorizontalLine();
                    System.out.println("Please enter a numeric character to mark your task as done!");
//                    printHorizontalLine();
                } catch (IOException e) {
                    System.out.println("Something went wrong: " + e.getMessage());
                }
            }
        }
    }
}
