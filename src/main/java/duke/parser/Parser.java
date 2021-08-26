package duke.parser;

import duke.storage.Storage;
import duke.task.*;
import duke.exception.InvalidInputException;
import duke.exception.InvalidDescriptionException;
import duke.exception.InvalidTimeException;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Parser {

    public enum Keyword {
        TODO, EVENT, DEADLINE, LIST, DONE, DELETE, BYE
    }

    private TaskList tasks;
    private Storage storage;

    public Parser(TaskList tasks, Storage storage) {
        this.tasks = tasks;
        this.storage = storage;
    }

    private String getFirstWord(String input) {
        String arr[] = input.split(" ", 2);
        String firstWord = arr[0];
        return firstWord;
    }

    private String getSecondWord(String input) {
        String arr[] = input.split(" ", 2);
        String secondWord = arr[1].strip();
        return secondWord;
    }

    private Keyword getKeyword(String input) {
        Keyword keyword = Keyword.valueOf(getFirstWord(input).toUpperCase());
        return keyword;
    }

    private void addComplete(Task t) {
        tasks.add(t);
        System.out.println("Got it. I've added this task:");
        System.out.println(t.toString());
        tasks.printTaskNumber(tasks);
        storage.saveData(tasks);
    }

    private void addToDo(String description) {
        addComplete(new Todo(description));
    }

    private void addDeadline(String description, String time) throws InvalidInputException {
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

    private void addEvent(String description, String time) throws InvalidInputException {
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
    public boolean parseCommand() {

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
                    return false;
                case LIST:
                    tasks.printItemList(tasks);
                    break;
                case DONE:
                    tasks.markTaskDone(input);
                    break;
                case DELETE:
                    tasks.deleteTask(input);
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
                    String eventTiming = getSecondWord(arr_event[1]).substring(0, getSecondWord(
                            arr_event[1]).length() - 1);
                    addEvent(eventDescription, eventTiming);
                    break;
                default:
                    throw new InvalidInputException("Please check your input!");
                }
                System.out.println(lineBreak);
            }
        } catch (InvalidTimeException | InvalidDescriptionException e) {
            System.out.println(e.getMessage());
        } catch (StringIndexOutOfBoundsException | ArrayIndexOutOfBoundsException | InvalidInputException e) {
            System.out.println("☹ OOPS!!! Please include an appropriate description/time!");
        } catch (IllegalArgumentException e) {
            System.out.println("☹ OOPS!!! Unable to recognise command, please try again!");
        }
        return true;
    }
}
