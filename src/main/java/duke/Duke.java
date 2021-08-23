package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoField;
import java.util.Locale;
import java.util.Scanner;

public class Duke {

    private final Storage storage;
    private final TaskList tasks;
    private final Ui ui;


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

    public Duke(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        tasks = new TaskList();

        // future: Load
        /*
        try {
            tasks = new TaskList(storage.load());
        } catch (DukeException e) {
            ui.showLoadingError();
            tasks = new TaskList();
        }
        */
    }


    public void run() {
        ui.showLogo();
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
                    ui.print(tasks.toString());
                    break;

                case DONE:
                case DELETE:
                    if (tasks.isEmpty())
                        throw new EmptyListException(command);

                    if (inputArr.length < 2)
                        throw new NothingAfterCommand(command);

                    // Extra Functionality: Done or delete by task type and name

                    if (!inputArr[1].matches("[0-9]+")) {
                        inputArr = inputArr[1].split("\\s+", 2);
                        String taskType = inputArr[0].toUpperCase();
                        Duke.TaskTypes tt;
                        try {
                            tt = Duke.TaskTypes.valueOf(taskType);
                        } catch (IllegalArgumentException e) {
                            throw new IllegalTaskTypeException(taskType);
                        }

                        if (inputArr.length < 2)
                            throw new MissingArguments(command);

                        if (command == Commands.DONE)
                            ui.markedDone(tasks.markDone(inputArr[1], tt));
                        else if (command == Commands.DELETE)
                            ui.deleted(tasks.remove(inputArr[1], tt), tasks.size());

                    } else {
                        int i;
                        try {
                            i = Integer.parseInt(inputArr[1].trim()) - 1;
                        } catch (NumberFormatException e) {
                            throw new TaskIndexNotInteger(tasks.size());
                        }

                        if (command == Commands.DONE)
                            ui.markedDone(tasks.markDone(i));
                        else if (command == Commands.DELETE)
                            ui.deleted(tasks.remove(i), tasks.size());

                    }

                    break;

                case TODO:
                    if (inputArr.length < 2)
                        throw new NothingAfterCommand(command);

                    // Extra Functionality: No duplicate tasks
                    if (tasks.getTaskIndex(inputArr[1], TaskTypes.TODO) != -1)
                        throw new TaskExistsException(TaskTypes.TODO, inputArr[1]);

                    Task todo = new Todo(inputArr[1]);
                    tasks.add(todo);
                    ui.added(todo, tasks.size());
                    break;

                case DEADLINE:
                    if (inputArr.length < 2)
                        throw new NothingAfterCommand(command);

                    if (!inputArr[1].contains("/by"))
                        throw new MissingParams("by");

                    inputArr = inputArr[1].split(" /by ", 2);
                    if (inputArr.length < 2)
                        throw new MissingArguments(command);

                    // Extra Functionality: No duplicate tasks
                    if (tasks.getTaskIndex(inputArr[0], TaskTypes.DEADLINE) != -1)
                        throw new TaskExistsException(TaskTypes.DEADLINE, inputArr[0]);

                    Task deadline;
                    if (inputArr[1].trim().split("\\s+").length < 2) {          // no time given
                        deadline = new Deadline(inputArr[0], parseDate(inputArr[1]));
                    } else {
                        deadline = new Deadline(inputArr[0], parseDateTime(inputArr[1]));
                    }

                    tasks.add(deadline);
                    ui.added(deadline, tasks.size());
                    break;

                case EVENT:
                    if (inputArr.length < 2)
                        throw new NothingAfterCommand(command);

                    if (!inputArr[1].contains("/at"))
                        throw new MissingParams("at");

                    inputArr = inputArr[1].split(" /at ", 2);

                    if (inputArr.length < 2)
                        throw new MissingArguments(command);

                    // Extra Functionality: No duplicate tasks
                    if (tasks.getTaskIndex(inputArr[0], TaskTypes.EVENT) != -1)
                        throw new TaskExistsException(TaskTypes.EVENT, inputArr[0]);

                    Task event;
                    if (inputArr[1].trim().split("\\s+").length < 2) {      // no time given
                        event = new Event(inputArr[0], parseDate(inputArr[1]));
                    } else {
                        event = new Event(inputArr[0], parseDateTime(inputArr[1]));
                    }

                    tasks.add(event);
                    ui.added(event, tasks.size());
                    break;


                case SAVE:
                    if (tasks.isEmpty())
                        throw new EmptyListException(command);

                    storage.save(tasks);
                    ui.saved();
                    break;

                default:
                    throw new IllegalCommandException("");      // should be unreachable by design
                }

            } catch (DukeException e) {
                ui.print(e.getMessage());
            } finally {
                input = sc.nextLine();
            }

        }

        ui.bye();

        sc.close();
    }


    public static LocalDateTime parseDateTime(String datetime) throws DateTimeFormatException {
        try {
            return LocalDateTime.parse(datetime,
                    new DateTimeFormatterBuilder()
                            .parseCaseInsensitive()
                            .parseLenient()
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, 2021)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .appendLiteral(" ")
                            .appendOptional(DateTimeFormatter.ofPattern("HHmm"))
                            .appendOptional(DateTimeFormatter.ofPattern("h.mma"))
                            .parseDefaulting(ChronoField.MINUTE_OF_HOUR, 0)
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
                            .appendOptional(DateTimeFormatter.ofPattern("d/M/yyyy"))
                            .appendOptional(DateTimeFormatter.ofPattern("yyyy-M-d"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMMyyyy"))
                            .parseDefaulting(ChronoField.YEAR_OF_ERA, 2021)
                            .appendOptional(DateTimeFormatter.ofPattern("d/M"))
                            .appendOptional(DateTimeFormatter.ofPattern("dMMM"))
                            .toFormatter(Locale.ENGLISH));
        } catch (DateTimeParseException e) {
            throw new DateTimeFormatException(date);
        }
    }

    public static void main(String[] args) {
        new Duke("data/tasks.txt").run();
    }
}
