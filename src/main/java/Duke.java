import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Scanner;

import java.lang.String;

public class Duke {

    private static ArrayList<Task> tasks;
    private static Storage storage;
    private static final String LOCATION = "./data/duke.txt";

    public enum Keyword {
        TODO, EVENT, DEADLINE, LIST, DONE, DELETE, BYE
    }

    private static void greet() {
        System.out.println("Hello! I'm Duke" + '\n' + "What can I do for you?");
    }

    private static String getFirstWord(String input) {
        String arr[] = input.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    private static String getSecondWord(String input) {
        String arr[] = input.split(" ", 2);
        String secondWord = arr[1].strip();
        return secondWord;
    }

    private static Integer getTaskNumber(String input) {
        String arr[] = input.split(" ", 2);
        Integer taskNumber = Integer.parseInt(arr[1]);
        return taskNumber;
    }

    private static void markTaskDone(String input) {
        try {
            int taskDoneNum = getTaskNumber(input);
            Task taskDone = tasks.get(taskDoneNum - 1);
            taskDone.markAsDone();
            System.out.println("Nice! I've marked this task as done:" + '\n' + taskDone.toString());
            storage.saveData(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be marked as done!");
            start();
        }
    }

    private static void deleteTask(String input) {
        try {
            int taskDeleteNum = getTaskNumber(input);
            Task taskToDelete = tasks.get(taskDeleteNum - 1);
            taskToDelete.markUndone();
            System.out.println("Noted. I've removed this task:" + '\n' + taskToDelete.toString());
            tasks.remove(taskDeleteNum - 1);
            printTaskNumber();
            storage.saveData(tasks);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("☹ OOPS!!! No such task can be deleted!");
            start();
        }
    }

    private static void printItemList() {
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println(i + 1 + "." + tasks.get(i).toString());
        }
    }

    private static void printTaskNumber() {
        System.out.println("Now you have " + tasks.size() + (tasks.size() == 1 ? " task" : " tasks") + " in the list.");
    }

    private static void addComplete(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        printTaskNumber();
        storage.saveData(tasks);
    }

    private static void addToDo(String description) {
        addComplete(new Todo(description));
    }

    private static void addDeadline(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Format should be in yyyy-mm-dd!");
        }
        if (split.length == 1) {
            addComplete(new Deadline(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Format should be in HH:mm");
            }
            addComplete(new Deadline(description, date, timing));
        }
    }

    private static void addEvent(String description, String time) throws InvalidInputException {
        String[] split = time.split(" ", 2);
        LocalDate date;
        LocalTime timing;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-M-d");
            date = LocalDate.parse(split[0], formatter);
        } catch (DateTimeParseException e) {
            throw new InvalidInputException("Format should be in yyyy-mm-dd!");
        }
        if (split.length == 1) {
            addComplete(new Event(description, date));
        } else {
            try {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
                timing = LocalTime.parse(split[1], formatter);
            } catch (DateTimeParseException e) {
                throw new InvalidInputException("Format should be in HH:mm");
            }
            addComplete(new Event(description, date, timing));
        }
    }

    private static Keyword getKeyword(String input) {
        Keyword keyword = Keyword.valueOf(getFirstWord(input).toUpperCase());
        return keyword;
    }

    private static void start() {
        String input;
        String lineBreak = "========================================================================";

        Scanner sc = new Scanner(System.in);
        System.out.println(lineBreak);

        try {
            while (sc.hasNextLine()) {
                input = sc.nextLine();
                Keyword keyword = getKeyword(input);

                switch (keyword) {
                    case BYE:
                        System.out.println("Bye. Hope to see you again soon!");
                        break;
                    case LIST:
                        printItemList();
                        break;
                    case DONE:
                        markTaskDone(input);
                        break;
                    case DELETE:
                        deleteTask(input);
                        break;
                    case TODO:
                        if (input.substring(5).length() < 1) {
                            throw new InvalidDescriptionException("☹ OOPS!!! The description of a todo cannot be empty.");
                        }
                        addToDo(input.substring(5));
                        break;
                    case DEADLINE:
                        String[] arr = input.split("\\(");
                        if (getSecondWord(arr[1]).length() < 1 || !getFirstWord(arr[1]).equals("by:")) {
                            throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable deadline!");
                        }
                        String description = getSecondWord(arr[0]);
                        String timing = getSecondWord(arr[1]).substring(0, getSecondWord(arr[1]).length() - 1);
                        addDeadline(description, timing);
                        break;
                    case EVENT:
                        String[] arr_event = input.split("\\(");
                        if (getSecondWord(arr_event[1]).length() < 1 || !getFirstWord(arr_event[1]).equals("at:")) {
                            throw new InvalidTimeException("☹ OOPS!!! Please enter a suitable event timing!");
                        }
                        String eventDescription = getSecondWord(arr_event[0]);
                        String eventTiming = getSecondWord(arr_event[1]).substring(0, getSecondWord(arr_event[1]).length() - 1);
                        addEvent(eventDescription, eventTiming);
                        break;
                }
                System.out.println(lineBreak);
            }
        } catch (InvalidTimeException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
            start();
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println("☹ OOPS!!! Please include an appropriate description/time!");
            start();
        } catch (IllegalArgumentException e) {
            System.out.println("☹ OOPS!!! Invalid command, please try again!");
            start();
        }
    }

    public static void main(String[] args) {
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        System.out.println("Hello from\n" + logo);

        greet();
        storage = new Storage(LOCATION);
        tasks = storage.loadData();
        start();
    }
}
