package duke.parser;

import duke.exceptions.DeadlineFormatException;
import duke.exceptions.EmptyDescriptionException;
import duke.exceptions.EventFormatException;
import duke.exceptions.OutOfBoundException;
import duke.tasks.Event;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.ResolverStyle;

public class Parser {
    private static String DIVIDER = "____________________________________________________________";
    private String input;
    private String[] inputCommandAndDescription;

    public Parser(String input) {
        this.input = input;
        this.inputCommandAndDescription = input.split(" ", 2);
    }

    public String getCommandWord() {
        return inputCommandAndDescription[0];
    }

    public int getIndex(int size) throws OutOfBoundException {
        int index = Integer.parseInt(input.split(" ")[1]);
        System.out.println("GET INDEX FUNCTION");
        if (index > size || index < 1) {
            throw new OutOfBoundException(size);
        }
        return index - 1;
    }

    public String getTodoDescription() throws EmptyDescriptionException {
        if (inputCommandAndDescription.length == 1) {
            throw new EmptyDescriptionException();
        }
        return inputCommandAndDescription[1];
    }

    public String getDeadlineDescription() throws EmptyDescriptionException, DeadlineFormatException {
        if (inputCommandAndDescription.length == 1) {
            throw new EmptyDescriptionException();
        } else if (!inputCommandAndDescription[1].contains("/by ")) {
            throw new DeadlineFormatException();
        }
        String deadlineDescription = inputCommandAndDescription[1];
        return deadlineDescription.split("/by ", 2)[0].trim();
    }

    public String getDeadlineDate() throws DeadlineFormatException {
        String deadlineDescription = inputCommandAndDescription[1];
        String date = deadlineDescription.split("/by ", 2)[1].trim();
        if (!isValidDateTime(date)) {
            throw new DeadlineFormatException();
        }
        return date;
    }

    public String getEventDescription() throws EmptyDescriptionException, EventFormatException {
        if (inputCommandAndDescription.length == 1) {
            throw new EmptyDescriptionException();
        } else if (!inputCommandAndDescription[1].contains("/from ") || !inputCommandAndDescription[1].contains("/to ")) {
            throw new EventFormatException();
        }
        String eventDescription = inputCommandAndDescription[1];
        return eventDescription.split("/from ", 2)[0].trim();
    }

    public String getEventDate() throws EventFormatException {
        String eventDescription = inputCommandAndDescription[1];
        String[] textAndDuration = eventDescription.split("/from ", 2);
        String text = textAndDuration[0].trim();
        String duration = textAndDuration[1].trim();

        // split start date and end date
        String[] startAndEnd = duration.split("/to ", 2);
        String startDate = startAndEnd[0].trim();
        String endDate = startAndEnd[1].trim();

        if (!isValidDateTime(startDate) || !isValidDateTime(endDate)) {
            throw new EventFormatException();
        }
        return startDate + " " + endDate;
    }

    public String getKeyword() {
        return inputCommandAndDescription[1];
    }

    public boolean isValidDateTime(String date) {
        Boolean isValid = true;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            date = date.replace("T", " ");
            LocalDateTime dateTime = LocalDateTime.from(formatter.parse(date));
        } catch (DateTimeParseException e) {
            isValid = false;
            System.out.println(isValid);
        }
        return isValid;
    }
}
