package duke;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.task.Deadline;
import duke.task.Event;
import duke.task.Todo;

/**
 * Encapsulates a Parser, which parses user inputs, interprets them as commands and
 * updates the TaskList.
 *
 * @author Hanif Kamal
 */
public class Parser {
    /** The TaskList that the Parser updates after command interpretation */
    private TaskList list;
    private NoteList noteList;

    /**
     * Class constructor to initialize a Parser instance.
     *
     * @param list The TaskList that is to be updated.
     */
    public Parser(TaskList list, NoteList noteList) {
        this.list = list;
        this.noteList = noteList;
    }

    /**
     * Takes in a String which represents user input, and parses it. It executes the
     * interpreted commands and then updates the TaskList accordingly.
     *
     * @param input The String that is input by the user.
     * @throws DukeException In the case of invalid inputs.
     */
    public String parse(String input) throws DukeException {
        String[] words = input.split(" ", 2);
        String command = words[0];
        if (words.length == 1) {
            return checkCase(command);
        } else {
            String commandInfo = words[1];
            return executeCases(command, commandInfo);
        }
    }

    private String checkCase(String command) throws DukeException {
        switch (command) {
        case ("list"):
            return list.printTasks();
        case ("notelist"):
            return noteList.printNotes();
        case ("done"):
            throw new DukeException("Please ensure that there is a number after the command 'done'. Try again.");
        case ("deadline"):
            throw new DukeException("Please ensure that there is a task description after the command 'deadline', "
                    + "and a deadline after '/by'. Try again.");
        case ("event"):
            throw new DukeException("Please ensure that there is a task description after the command 'event', "
                    + "and a date and time after '/at'. Try again.");
        case ("todo"):
            throw new DukeException("Please ensure that there is a task description after the command 'todo'. "
                    + "Try again.");
        case ("delete"):
            throw new DukeException("Please ensure that there is a number after the command 'delete'. Try again.");
        case ("notedelete"):
            throw new DukeException("Please ensure that there is a number after the command 'notedelete'. "
                    + "Try again.");
        case ("find"):
            throw new DukeException("Please ensure that there is a search term after the command 'find'. "
                    + "Try again.");
        case ("notefind"):
            throw new DukeException("Please ensure that there is a search term after the command 'notefind'. "
                    + "Try again.");
        case ("note"):
            throw new DukeException("Please ensure that there is a note description after the command 'note'. "
                    + "Try again.");
        default:
            throw new DukeException("I didn't quite get what you meant. Please enter a valid command.");
        }
    }

    private String executeCases(String command, String commandInfo) throws DukeException {
        switch (command) {
        case ("done"):
            return handleDone(commandInfo);
        case ("deadline"):
            return handleDeadline(commandInfo);
        case ("event"):
            return handleEvent(commandInfo);
        case ("todo"):
            return handleTodo(commandInfo);
        case ("note"):
            return handleNote(commandInfo);
        case ("delete"):
            return handleDelete(commandInfo);
        case ("notedelete"):
            return handleNoteDelete(commandInfo);
        case ("find"):
            return list.printFilteredTasks(commandInfo);
        case ("notefind"):
            return noteList.printFilteredNotes(commandInfo);
        default:
            throw new DukeException("I didn't quite get what you meant. Please enter a valid command.");
        }
    }

    private String handleDone(String listIndexString) throws DukeException {
        try {
            int listIndex = Integer.parseInt(listIndexString);
            if (listIndex <= list.size() && listIndex >= 1) {
                return list.completeTask(listIndex);
            } else {
                throw new DukeException("Please ensure that a valid number follows the command 'done'. "
                        + "Try again.");
            }
        } catch (NumberFormatException e) {
            throw new DukeException("Please ensure only a number follows the command 'done'. Try again.");
        }
    }

    private String handleDeadline(String deadlineInfo) throws DukeException {
        if (!deadlineInfo.contains("/by")) {
            throw new DukeException("Please state the deadline for this task with /by! Try again.");
        } else {
            try {
                String[] restOfDeadlineSplit = deadlineInfo.split(" /by ", 2);
                if (restOfDeadlineSplit.length < 2) {
                    throw new DukeException("Please ensure that there is a task description and deadline. "
                            + "Try again");
                }
                assert restOfDeadlineSplit.length == 2 : "Deadline should not have anything other than"
                        + " exactly 2 parts!";
                String deadlineDescription = restOfDeadlineSplit[0];
                String deadlineDateString = restOfDeadlineSplit[1];
                DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yy");
                LocalDate deadlineDate = LocalDate.parse(deadlineDateString, dtf);
                Deadline createdDeadlineTask = new Deadline(deadlineDescription, false, deadlineDate);
                return list.addToList(createdDeadlineTask);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please ensure that your deadline is formatted in the following "
                        + "way: DD/MM/YY");
            }
        }
    }

    private String handleEvent(String eventInfo) throws DukeException {
        if (!eventInfo.contains("/at")) {
            throw new DukeException("Please state the date and time for this event with /at! Try again.");
        } else {
            try {
                String[] restOfEventSplit = eventInfo.split(" /at ", 2);
                if (restOfEventSplit.length < 2) {
                    throw new DukeException("Please ensure that there is a task description with a date and "
                            + "time for this event. Try again");
                }
                assert restOfEventSplit.length == 2 : "Event should not have anything other than "
                        + "exactly 2 parts!";
                LocalDateTime eventDateTime = LocalDateTime.parse(restOfEventSplit[1],
                        DateTimeFormatter.ofPattern("dd/MM/yy HHmm"));
                Event createdEventTask = new Event(restOfEventSplit[0], false, eventDateTime);
                return list.addToList(createdEventTask);
            } catch (DateTimeParseException e) {
                throw new DukeException("Please ensure that your deadline is formatted as DD/MM/YY");
            }
        }
    }

    private String handleTodo(String todoInfo) {
        Todo createdTodoTask = new Todo(todoInfo, false);
        return list.addToList(createdTodoTask);
    }

    private String handleNote(String noteInfo) {
        Note createdNote = new Note(noteInfo, LocalDateTime.now());
        return noteList.addToList(createdNote);
    }

    private String handleDelete(String toDeleteIndexString) throws DukeException {
        try {
            int toDeleteIndex = Integer.parseInt(toDeleteIndexString);
            return list.deleteTask(toDeleteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Please make sure only a number follows the command 'delete'. "
                    + "Try again.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please add a number after the command 'delete'. Try again.");
        }
    }

    private String handleNoteDelete(String toDeleteNoteIndexString) throws DukeException {
        try {
            int toDeleteNoteIndex = Integer.parseInt(toDeleteNoteIndexString);
            return noteList.deleteNote(toDeleteNoteIndex);
        } catch (NumberFormatException e) {
            throw new DukeException("Please make sure only a number follows the command 'notedelete'. "
                    + "Try again.");
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException("Please add a number after the command 'notedelete'. Try again.");
        }
    }
}

