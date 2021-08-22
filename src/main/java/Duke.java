import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.ArrayList;
import java.util.Locale;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Duke {

    final static String BOX_LINE = "__________________________________________________________________________________\n";
    final static String BOX_MIDDLE = "|  Cat:  ";

    public static String Box(String message) {
        return Duke.BOX_LINE + Duke.BOX_MIDDLE + message + "\n" + Duke.BOX_LINE;
    }

    public enum Commands {
        LIST,
        DONE,
        TODO,
        DEADLINE,
        EVENT,
        DELETE,
        SAVE
    }

    public enum TaskTypes {
        TODO,
        DEADLINE,
        EVENT
    }

    public static ArrayList<Task> taskList = new ArrayList<>();


    public static void main(String[] args) {
        // Credits to https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ and Mafumafu Line stickers
        String LOGO = "" +
                //"                              .......                                                               \n" +
                //"                       .,*#&@@@@@@@@@@@@@@&*                                                        \n" +
                //"                                  .,/(%&@&&@@@@@@@@@@@@@@@@@%* .,/(%&@@@@@@@@@@@@&.                 \n" +
                //"                                ,%@@@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
                //"                            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&%#(##@@@@@*                 \n" +
                //"                        .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(&&%&&&(@@@@@*                 \n" +
                "                     .,%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&%%&(@@@@@,                 \n" +
                "              .(@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(&#%@@@@&.                 \n" +
                "         *&@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/@@@@@/                  \n" +
                "     .@@@@@@@@@@&(*((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&%.                  \n" +
                "      *@@@@@&*(#&%%%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@&&&,                   \n" +
                "       ,@@@@@@(%%%&#@@@@@@@@&@@&@@@@@@@@@@@&@@@@@@@@@@@@#@(@@@@@@@@@@@@@@@@@@@@.                    \n" +
                "        .&@@@@@@*%&(@@@@@@@@@@@@@@@@@@@@@@@/@@@@@@@@@@@%%@@@(@@@@@@@@@@@@@&@@@@.                    \n" +
                "          ,@@@@@@@/%@@@@@@@@@@@@@@@@@@@@@@(&%@@@@@@@@@@(@@@@@@/@@@@@@@@@@@@&@@#@*                   \n" +
                "            *@@&&&&@@@@@@@@@@@@@@@@@@%@@@(@@(@@@@@@@@@&%@@@@@@@@/@@@@@@@@@@#@@@/.%.                 \n" +
                "              ,&&&@@@@@&@@@@@@@@@@@@@@@@&%@@#@@@@@@@@@/@@@@@@@@@@@/@@@@@@@@#&%@@,  ..               \n" +
                "                .%&@@@@#@@@@@@@@@@@@@@@@#@@@&%@@@@@@@%&@@%. ....... #@@@@@&#&& &&                   \n" +
                "                  %@@@@#@@@@@@@@@@@(@@@#@&@@@(@@@@@%@#@@@%**%%***/*@%&@@@@&(&&  (.                  \n" +
                "                  %&@@@@#@@@@@@%@@#.....,@@@@&&@@@%##@@@@@*((((/((/@&&@@@@&(@#   .                  \n" +
                "                 .(*@@@@%&@@@@@, .*//*@*/*@@@@(@(/@/@@@@@@@#*((#@&@@&&@@@&/%&.                      \n" +
                "                 ...%&&@@(@@@@@#@,*/(((((/@@@@@@@@@@@@@@@@@@@@(/#,&(#&%@&&.&                        \n" +
                "                    .&&%&@%@@@@&&@*((&,/@@@@@@@@@@@@@@@@@@@@@@@/#%%&#%.%@...                        \n" +
                "                      *#&%&&(@@@(@&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#/.#                           \n" +
                "                       .%*%&&%(&(*@&&&&&@@@@@@@@@@#%@@@@@@@@@@@@@@@&..,,..                          \n" +
                //"                       ....(&&*(#,%#@%**/&@@@@@@@@@@@@@@@@@@@@#,./#@%#/*//(.                        \n" +
                //"                            ,&.,,,.,,,,,,,,,*@@@@@@@@@@@@&,.,,,,,,,,,,,,,,,,.                       \n" +
                //"                           .(***.,,,,,,,,,,,*(,... #@@%..,,,/*,,,,,,,,,,,,,..                       \n" +
                //"                           .,,,,,,.....(*,,,./&@@...,,.@@@#,,,,,,.,*.,,,.....                       \n" +
                //"                            ...,,,,(@%#&@@@@@@@@@*.,//(@@@@@@@@@@&@@/.,.....                        \n" +
                //"                            ...,,,.@@@@@@@@@@@@@@/&%%(*@@@@@@@@@@@&@%#&*....                        \n" +
                "\n";
        String GREET = Box("Meow-ning!");
        String EXIT = Box("See you again, meow!");
        String SAVE = Box("Your list has been saved to DATA/DUKE.TXT");

        BinaryOperator<String> AddTaskMessage = (x, y) -> Box("Meow. I've added this task:\n   " +
                x + "\n" + "|  Now you have " + y + " tasks in the list.");
        UnaryOperator<String> DoneTaskMessage = (task) -> Box("Good job, meow! Marked this task as done:\n   " +
                task);
        BinaryOperator<String> DeleteTaskMessage = (x, y) -> Box("Understood, meow! Deleted this task:\n   " +
                x + "\n" + "|  Now you have " + y + " tasks in the list.");
        UnaryOperator<String> ListMessage = (items) -> Box("Here are the tasks in your list, meow:" + items);

        System.out.println(LOGO + GREET);

        Scanner sc = new Scanner(System.in);

        String input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String[] inputArr = input.trim().split("\\s+", 2);
            String commandStr = inputArr[0].toUpperCase();

            try {
                Commands command;
                try {
                    command = Commands.valueOf(commandStr);
                } catch (IllegalArgumentException e) {
                    throw new IllegalCommandException(commandStr);
                }

                switch (command) {
                case LIST:
                    if (taskList.size() == 0) {
                        throw new EmptyListException(command);
                    }

                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < taskList.size(); i++) {
                        s.append("\n   ").append(i + 1).append(". ").append(taskList.get(i));
                    }

                    System.out.println(ListMessage.apply(s.toString()));
                    break;

                case DONE:
                    if (taskList.size() == 0) {
                        throw new EmptyListException(command);
                    }

                    if (inputArr.length < 2) {
                        throw new NothingAfterCommand(command);
                    }

                    int i;

                    if (!inputArr[1].matches("[0-9]+")) {
                        // throw new TaskIndexNotInteger(taskList.size());
                        /*
                         * Extra Functionality: Search by Task Name.
                         *
                         * matches first item by task type and by non-case sensitive
                         * description (equals override in class Task)
                         *
                         * format: done TASK_TYPE TASK_NAME
                         */
                        inputArr = inputArr[1].split("\\s+", 2);
                        String taskType = inputArr[0].toUpperCase();
                        TaskTypes tt;
                        try {
                            tt = TaskTypes.valueOf(taskType);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalTaskTypeException(taskType);
                        }

                        if (inputArr.length < 2) {
                            throw new MissingArguments(command);
                        }

                        i = getTaskIndex(inputArr[1], tt);
                        if (i == -1)
                            throw new TaskNotFound(inputArr[1]);


                    } else {
                        try {
                            i = Integer.parseInt(inputArr[1].trim()) - 1;
                        } catch (NumberFormatException e) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }
                    }

                    if (i >= 0 && i < taskList.size()) {
                        Task t = taskList.get(i);
                        t.markAsDone();
                        System.out.println(DoneTaskMessage.apply(t.toString()));
                    } else {
                        throw new TaskIndexOutOfBounds(i, taskList.size());
                    }

                    break;

                case TODO:
                    if (inputArr.length < 2) {
                        throw new NothingAfterCommand(command);
                    }

                    /*
                     * Extra Functionality: No duplicate tasks
                     */
                    if (getTaskIndex(inputArr[1], TaskTypes.TODO) != -1) {
                        throw new TaskExistsException(TaskTypes.TODO, inputArr[1]);
                    }

                    Task todo = new Todo(inputArr[1]);
                    taskList.add(todo);
                    System.out.println(AddTaskMessage.apply(todo.toString(), Integer.toString(taskList.size())));
                    break;

                case DEADLINE:
                    if (inputArr.length < 2) {
                        throw new NothingAfterCommand(command);
                    }

                    if (!inputArr[1].contains("/by")) {
                        throw new MissingParams("by");
                    }

                    inputArr = inputArr[1].split(" /by ", 2);
                    if (inputArr.length < 2) {
                        throw new MissingArguments(command);
                    }

                    /*
                     * Extra Functionality: No duplicate tasks
                     */
                    if (getTaskIndex(inputArr[0], TaskTypes.DEADLINE) != -1) {
                        throw new TaskExistsException(TaskTypes.DEADLINE, inputArr[0]);
                    }

                    Task deadline;
                    if (inputArr[1].trim().split("\\s+").length < 2) {          // no time given
                        deadline = new Deadline(inputArr[0], parseDate(inputArr[1]));
                    } else {
                        deadline = new Deadline(inputArr[0], parseDateTime(inputArr[1]));
                    }

                    taskList.add(deadline);
                    System.out.println(AddTaskMessage.apply(deadline.toString(), Integer.toString(taskList.size())));
                    break;

                case EVENT:
                    if (inputArr.length < 2) {
                        throw new NothingAfterCommand(command);
                    }

                    if (!inputArr[1].contains("/at")) {
                        throw new MissingParams("at");
                    }

                    inputArr = inputArr[1].split(" /at ", 2);
                    if (inputArr.length < 2) {
                        throw new MissingArguments(command);
                    }

                    /*
                     * Extra Functionality: No duplicate tasks
                     */
                    if (getTaskIndex(inputArr[0], TaskTypes.EVENT) != -1) {
                        throw new TaskExistsException(TaskTypes.EVENT, inputArr[0]);
                    }

                    Task event;
                    if (inputArr[1].trim().split("\\s+").length < 2) {      // no time given
                        event = new Deadline(inputArr[0], parseDate(inputArr[1]));
                    } else {
                        event = new Deadline(inputArr[0], parseDateTime(inputArr[1]));
                    }

                    taskList.add(event);
                    System.out.println(AddTaskMessage.apply(event.toString(), Integer.toString(taskList.size())));
                    break;

                case DELETE:
                    if (taskList.size() == 0) {
                        throw new EmptyListException(command);
                    }

                    if (inputArr.length < 2) {
                        throw new NothingAfterCommand(command);
                    }

                    int j;

                    if (!inputArr[1].matches("[0-9]+")) {
                        //throw new TaskIndexNotInteger(taskList.size());
                        /*
                         * Extra Functionality: Search by Task Name
                         *
                         * format: delete TASK_TYPE TASK_NAME
                         */
                        inputArr = inputArr[1].split("\\s+", 2);
                        String taskType = inputArr[0].toUpperCase();
                        TaskTypes tt;
                        try {
                            tt = TaskTypes.valueOf(taskType);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalTaskTypeException(taskType);
                        }

                        if (inputArr.length < 2) {
                            throw new MissingArguments(command);
                        }

                        j = getTaskIndex(inputArr[1], tt);
                        if (j == -1)
                            throw new TaskNotFound(inputArr[1]);
                    } else {
                        try {
                            j = Integer.parseInt(inputArr[1].trim()) - 1;
                        } catch (NumberFormatException e) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }
                    }

                    if (j >= 0 && j < taskList.size()) {
                        Task t = taskList.remove(j);
                        System.out.println(DeleteTaskMessage.apply(t.toString(), Integer.toString(taskList.size())));
                    } else {
                        throw new TaskIndexOutOfBounds(j, taskList.size());
                    }

                    break;

                case SAVE:
                    if (taskList.size() == 0) {
                        throw new EmptyListException(command);
                    }

                    Path data = Path.of("data");
                    Path saveFile = data.resolve("duke.txt");
                    createIfNotExist(data, saveFile);
                    StringBuilder toWrite = new StringBuilder();
                    taskList.forEach((t) -> toWrite.append(t.saveString() + "\n"));
                    try {

                        Files.writeString(saveFile, toWrite.toString());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    System.out.println(SAVE);
                    break;

                default:
                    throw new IllegalCommandException("");      // should be unreachable by design
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                input = sc.nextLine();
            }

        }

        System.out.println(EXIT);

        sc.close();
    }

    public static int getTaskIndex(String taskName, TaskTypes tt) throws DukeException {

        int i;

        switch (tt) {
        case TODO:
            i = taskList.indexOf(new Todo(taskName));
            break;
        case DEADLINE:
            i = taskList.indexOf(new Deadline(taskName));
            break;
        case EVENT:
            i = taskList.indexOf(new Event(taskName));
            break;
        default:
            throw new DukeException("unreachable by design");
        }

        return i;
    }

    public static void createIfNotExist(Path dir, Path file) {
        if (Files.notExists(dir)) {
            try {
                Files.createDirectory(dir);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (Files.notExists(file)) {
            try {
                Files.createFile(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static LocalDateTime parseDateTime(String datetime) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(datetime,
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .parseLenient()
                            .parseDefaulting(ChronoField.YEAR, 2021)
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .appendLiteral(" ")
                            .appendOptional(DateTimeFormatter.ofPattern("HHmm"))
                            .appendOptional(DateTimeFormatter.ofPattern("h.mma"))
                            .appendOptional(DateTimeFormatter.ofPattern("ha"))
                            .toFormatter(Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(datetime);
        }

    }

    public static LocalDate parseDate(String date) throws DateTimeFormatException {
        try {
            return LocalDate.parse(date,
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .parseLenient()
                            .parseDefaulting(ChronoField.YEAR, 2021)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .toFormatter(Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(date);
        }
    }

}
