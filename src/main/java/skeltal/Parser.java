package skeltal;

import skeltal.task.*;
import skeltal.task.expense.Expense;
import skeltal.task.expense.ExpenseList;
import skeltal.task.types.Deadline;
import skeltal.task.types.Event;
import skeltal.task.types.ToDo;

import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.LocalDate;

/**
 * A Parser class that handles the parsing of userInputs, to determine Skeltal's response.
 */
public class Parser {
    final static DateTimeFormatter format = DateTimeFormatter.ISO_LOCAL_DATE;

    /**
     * This method parses the user's input and determines skeltal's response accordingly.
     * userInput should be in the form "command arg1 arg2".
     * @param userInput A String of the userInput.
     */
    public static String parse(String userInput) {
        String reply = "";
        try {
            String[] commandDescriptionArr = parseInput(userInput);
            if (commandDescriptionArr.length == 1) {
                reply = parseSingleArg(commandDescriptionArr);
            } else {
                reply = parseNotSingleArg(commandDescriptionArr);
            }
        } catch (SkeltalException e) {
            reply = e.getMessage();
        } finally {
            assert reply != "" : "Skeltal's reply cannot be empty";
            return reply;
        }
    }

    public static String parseSingleArg(String[] commandDescriptionArr) throws SkeltalException {
        String reply = "";
        String command = commandDescriptionArr[0];
       switch (command) {
           case "todo":
               throw new SkeltalException("OOPS! Todo messages cannot be empty!");
           case "list":
               reply = TaskList.listReply();
               break;
           case "store":
               reply += Storage.store();
               break;
           case "bank":
               reply = ExpenseList.listExpenses();
               break;
           case "sum":
               reply = ExpenseList.sum();
               break;
           default:
               throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
       }
       return reply;
    }

    public static String parseNotSingleArg(String[] commandDescriptionArr) throws SkeltalException {
        String reply = "";
        String command = commandDescriptionArr[0];
        String description = commandDescriptionArr[1];
        switch (command) {
            case "done":
                reply = TaskList.done(description) + "\n";
                reply += Storage.store();
                break;
            case "deadline":
                Deadline dead = new Deadline(description);
                reply = addThenStore(dead);
                break;
            case "event":
                Event event = new Event(description);
                reply = addThenStore(event);
                break;
            case "todo":
                ToDo todo = new ToDo(description);
                reply = addThenStore(todo);
                break;
            case "delete":
                reply = deleteThenStore(description);
                break;
            case "find":
                reply += TaskList.findMatchingTasks(description);
                break;
            case "expense":
                Expense expense = new Expense(description);
                reply += ExpenseList.addExpense(expense);
                break;
            case "minus":
                reply = ExpenseList.deleteExpense(description);
                break;
            default:
                throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return reply;
    }

    public static String parseDescription(String description, String taskType) throws SkeltalException {
        String[] taskAndTime = description.split("/", 2);

        if (taskAndTime.length == 1) {
            throw new SkeltalException("OOPS! The description of an " + taskType + " cannot be empty!");
        }

        String time = taskAndTime[1];
        String parsedTime = parseTime(time);

        return parsedTime;
    }

    private static String parseTime(String time) {
        assert time != null : "Time field cannot be empty";
        try {
            //Seperates time from the prefix
            if (time.startsWith("by ") | time.startsWith("at ")) {
                time = time.substring(3);
            }
            //Format time
            LocalDate date = LocalDate.parse(time, format);
            System.out.println(date);
            time = date.format(DateTimeFormatter.ofPattern("dd MMM yy"));
        } catch (DateTimeParseException e) {
            System.out.println(e);
        }

        return time;
    }

    private static String[] parseInput(String userInput) {
        String[] commandDescriptionArr = userInput.split(" ", 2);
        assert commandDescriptionArr[1] != null : "Description cannot be empty";

        return commandDescriptionArr;
    }

    public static String[] parseMoney(String descriptionAndAmount) {
        String[] commandDescriptionArr = descriptionAndAmount.split(" /", 2);
        return commandDescriptionArr;
    }

    private static String addThenStore(Task inputTask) throws SkeltalException {
        String reply = "";
        reply += TaskList.addTask(inputTask) + "\n";
        reply += Storage.store();
        return reply;
    }

    private static String deleteThenStore(String description) throws SkeltalException {
        String reply = "";
        reply += "Noted. I have removed this task\n";
        reply += TaskList.delete(description);
        reply += Storage.store();
        return reply;
    }

    private static void isUserInputValid(String[] isValid) throws SkeltalException {
        String command = isValid[0];
        switch (command) {
            case "todo":
                if (isValid.length == 1) {
                    throw new SkeltalException("OOPS! The description of todo cannot be empty!");
                }
            case "list":
                break;
            case "bank":
                break;
            default:
                if (isValid.length == 1) {
                    throw new SkeltalException("OOPS!!! I'm sorry," +
                            " but I don't know what that means :-(");
                }
        }
    }
}
