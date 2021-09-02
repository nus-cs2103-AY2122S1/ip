package duke;

import java.time.LocalDate;

/**
 * Represents <code>Parser</code> object to parse user input
 */
public class Parser {
    /**
     * Returns corresponding <code>Command</code> based on input supplied
     * @param input User input
     * @return <code>Command</code> object
     * @throws DukeException
     */
    public static Command parse(String input) throws DukeException {
        String[] inputArr = input.split(" ");
        String body = "";
        String date = inputArr[inputArr.length - 1];
        boolean hasDate = false;
        for (int i = 1; i < inputArr.length; i++) {
            if (inputArr[i].equals("/by") || inputArr[i].equals("/at")) {
                hasDate = true;
                break;
            } else {
                body += " " + inputArr[i];
            }
        }
        String lowerCaseInput = inputArr[0].toLowerCase();
        if (lowerCaseInput.equals("bye")) {
            return new ByeCommand();
        } else if (lowerCaseInput.equals("list")) {
            return new ListCommand();
        } else if (lowerCaseInput.equals("done")) {
            if (inputArr.length < 2) {
                DukeException exception = new DukeException("Please enter a number!");
                throw exception;
            }
            int index = Integer.parseInt(inputArr[1]);
            return new DoneCommand(index);
        } else if (lowerCaseInput.equals("todo")) {
            if (body.equals("")) {
                DukeException exception = new DukeException("Description of todo cannot be empty :(");
                throw exception;
            }
            Todo todo = new Todo(body);
            return new AddTodoCommand(todo);
        } else if (lowerCaseInput.equals("deadline")) {
            if (!hasDate) {
                DukeException exception = new DukeException("Deadline of deadline cannot be empty :(");
                throw exception;
            } else if (date.length() != 10) {
                DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                throw exception;
            } else {
                String[] dateArr = date.split("-");
                int year = Integer.parseInt(dateArr[0]);
                int month = Integer.parseInt(dateArr[1]);
                int day = Integer.parseInt(dateArr[2]);
                if (month > 12 || day > 31) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    throw exception;
                }
                LocalDate newDate = LocalDate.of(year, month, day);
                Deadline deadline = new Deadline(body, newDate);
                return new AddDeadlineCommand(deadline);
            }
        } else if (lowerCaseInput.equals("event")) {
            if (!hasDate) {
                DukeException exception = new DukeException("Date of event cannot be empty :(");
                throw exception;
            } else if (date.length() != 10) {
                DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                throw exception;
            } else {
                String[] dateArr = date.split("-");
                int year = Integer.parseInt(dateArr[0]);
                int month = Integer.parseInt(dateArr[1]);
                int day = Integer.parseInt(dateArr[2]);
                if (month > 12 || day > 31) {
                    DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                    throw exception;
                }
                LocalDate newDate = LocalDate.of(year, month, day);
                Event event = new Event(body, newDate);
                return new AddEventCommand(event);
            }
        } else if (lowerCaseInput.equals("delete")) {
            if (inputArr.length < 2) {
                DukeException exception = new DukeException("Please enter a number!");
                throw exception;
            }
            int index = Integer.parseInt(inputArr[1]);
            return new DeleteCommand(index);
        } else if (lowerCaseInput.equals("get")) {
            if (inputArr.length < 2) {
                DukeException exception = new DukeException("Please enter a date!");
                throw exception;
            }
            String[] dateArr = inputArr[1].split("-");
            int year = Integer.parseInt(dateArr[0]);
            int month = Integer.parseInt(dateArr[1]);
            int day = Integer.parseInt(dateArr[2]);
            if (month > 12 || day > 31) {
                DukeException exception = new DukeException("Please enter a valid date (yyyy-mm-dd) :(");
                throw exception;
            }
            LocalDate newDate = LocalDate.of(year, month, day);
            return new GetCommand(newDate);
        } else if (lowerCaseInput.equals("find")) {
            if (inputArr.length < 2) {
                DukeException exception = new DukeException("Please enter a description!");
                throw exception;
            }
            String matchString = inputArr[1];
            return new FindCommand(matchString);
        } else {
            DukeException exception = new DukeException("I'm sorry but I don't understand what that means :(");
            throw exception;
        }
    }
}
