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
    /**
     * The constant FORMATTERS.
     */
    final static DateTimeFormatter[] FORMATTERS = {DateTimeFormatter.ofPattern("dd-MM-yy"),
            DateTimeFormatter.ofPattern("dd-MM-yyyy"), DateTimeFormatter.ofPattern("dd/MM/yyyy"),
            DateTimeFormatter.ofPattern("dd/MM/yyyy"), DateTimeFormatter.ofPattern("ddMMyyyy"),
            DateTimeFormatter.ofPattern("dd MMM yy")};

    /**
     * Parses the user's input and determines skeltal's response accordingly.
     * userInput should be in the form "command arg1 arg2".
     *
     * @param userInput A String of the userInput.
     * @return The String reply from Skeltal.
     */
    public static String parse(String userInput) {
        userInput = userInput.trim();
        String reply = "";
        try {
            reply = execute(parseInput(userInput));
        } catch (SkeltalException e) {
            reply = e.getMessage();
        } finally {
            assert reply != "" : "Skeltal's reply cannot be empty";
            return reply;
        }
    }

    /**
     * Execute the command in the array.
     *
     * @param commandDescriptionArr The String[] that contains the commands and the arguments.
     * @return A String that represents Skeltal's reply.
     * @throws SkeltalException
     */
    public static String execute(String[] commandDescriptionArr) throws SkeltalException {
        String reply = "";
        String command = commandDescriptionArr[0];
        if (commandDescriptionArr.length == 1) {
            reply = executeSingle(command);
        } else {
            String description = commandDescriptionArr[1];
            reply = executeMoreThanOne(command, description);
        }
        return reply;
    }

    /**
     * Execute command with no args.
     *
     * @param commandDescriptionArr the command description arr.
     * @return Skeltal's reply.
     * @throws SkeltalException
     */
    public static String executeSingle(String command) throws SkeltalException {
        String reply = "";
        switch (command) {
        case "todo":
            throw new SkeltalException("OOPS! Todo messages cannot be empty!");
        case "list":
            reply = TaskList.listReply();
            break;
        case "store":
            reply = Storage.write(Storage.SKELTAL_PATH, "task");
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

    /**
     * Execute command that has arguments.
     *
     * @param commandDescriptionArr the command description arr
     * @return the string
     * @throws SkeltalException the skeltal exception
     */
    public static String executeMoreThanOne(String command, String description) throws SkeltalException {
        String reply = "";
        switch (command) {
        case "done":
            reply = TaskList.done(description);
            break;
        case "deadline":
            Deadline dead = Deadline.of(description);
            reply = TaskList.addTask(dead);
            break;
        case "event":
            Event event = Event.of(description);
            reply = TaskList.addTask(event);
            break;
        case "todo":
            ToDo todo = ToDo.of(description);
            reply = TaskList.addTask(todo);
            break;
        case "delete":
            reply = TaskList.delete(description);
            break;
        case "find":
            reply = TaskList.findMatchingTasks(description);
            break;
        case "expense":
            Expense expense = Expense.of(description);
            reply = ExpenseList.addExpense(expense);
            break;
        case "minus":
            reply = ExpenseList.deleteExpense(description);
            break;
        default:
            throw new SkeltalException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
        return reply;
    }

    /**
     * Parse description String to split it into an array.
     *
     * @param description the description of the task
     * @param taskType    the task type
     * @return the processed string []
     * @throws SkeltalException the skeltal exception
     */
    public static String[] parseDescription(String description, String taskType) throws SkeltalException {
        String[] taskAndTime = description.split("/", 2);

        if (taskAndTime.length == 1 || taskAndTime[1].isEmpty()) {
            throw new SkeltalException("OOPS! The description of an " +
                    taskType + " cannot be empty!");
        }

        String time = taskAndTime[1];
        String parsedTime = parseTime(time);
        taskAndTime[1] = parsedTime;

        return taskAndTime;
    }

    /**
     * Parse the description and amount to split the amount from the description.
     *
     * @param descriptionAndAmount The description and amount of the expense.
     * @return the string [] containing the description and amount.
     */
    public static String[] parseExpense(String descriptionAndAmount) {
        String[] descriptionAmountArr = descriptionAndAmount.split(" \\$", 2);
        return descriptionAmountArr;
    }

    private static String parseTime(String time) {
        assert time != null : "Time field cannot be empty";
        //Seperates time from the prefix
        if (time.startsWith("by ") | time.startsWith("at ")) {
            time = time.substring(3);
        }
        //Format time
        time = tryParseDate(time);
        return time;
    }

    private static String tryParseDate(String date) {
        LocalDate dateObj = null;
        for (DateTimeFormatter formatter : FORMATTERS) {
            try {
                dateObj = LocalDate.parse(date, formatter);
                return dateObj.format(DateTimeFormatter.ofPattern("dd MMM yy"));
            } catch (DateTimeParseException e) {
            }
        }
        return date;
    }

    private static String[] parseInput(String userInput) {
        String[] commandDescriptionArr = userInput.split(" ", 2);
        assert commandDescriptionArr[1] != null : "Description cannot be empty";

        return commandDescriptionArr;
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
