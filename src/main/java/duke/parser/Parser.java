package duke.parser;

import duke.data.DukeException;
import duke.data.TaskList;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Parser {
    enum SPECIAL_TASK {
        bye,
        done,
        list,
        todo,
        deadline,
        event,
        delete
    }
    protected DateTimeFormatter df;

    public Parser() {
        this.df = DateTimeFormatter.ofPattern("yyyy-M-d H:mm");
    }

    public String[] splitType(String input) {
        return input.split(" ", 2);
    }

    public int getIndex(String[] typeInput) {
        return Integer.parseInt(typeInput[1]) - 1;
    }

    public void checkDesc(String[] typeInput, String type) throws DukeException {
        if (typeInput.length < 2 || typeInput[1].equals("") || typeInput[1].equals(" ")) {
            throw new DukeException("The description of " + type + " cannot be empty!");
        }
    }

    public String[] furtherSplit(String input, String regex) {
        return input.split(regex, 2);
    }

    public void checkFurtherDesc(String[] furtherInput, String type) throws DukeException {
        if (furtherInput.length < 2 || furtherInput[0].equals("")) {
            throw new DukeException("The description of a " + type + " cannot be empty.\n" +
                    "Don't forget to use /by to indicate the deadline.");
        } else if (furtherInput[1].equals("") || furtherInput[1].equals(" ")) {
            throw new DukeException("Must come with a input date/time for the " + type + " .");
        }
    }

    public LocalDateTime parseTime(String timeString) {
        return LocalDateTime.parse(timeString.stripLeading(), df);
    }

    public boolean isBye(String input) {
        return input.equals(SPECIAL_TASK.bye.name());
    }

    public boolean isDone(String input) {
        return input.equals(SPECIAL_TASK.done.name());
    }

    public boolean isList(String input) {
        return input.equals(SPECIAL_TASK.list.name());
    }

    public boolean isTodo(String input) {
        return input.equals(SPECIAL_TASK.todo.name());
    }

    public boolean isDeadline(String input) {
        return input.equals(SPECIAL_TASK.deadline.name());
    }

    public boolean isEvent(String input) {
        return input.equals(SPECIAL_TASK.event.name());
    }

    public boolean isDelete(String input) {
        return input.equals(SPECIAL_TASK.delete.name());
    }

    public boolean isBlah(String input) {
        return input.equals("Blah");
    }

    public void parseTodo(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.todo.name());
    }

    public String[] parseDeadline(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.deadline.name());
        String[] furtherSplits = furtherSplit(splitInput[1], "/by");
        checkFurtherDesc(furtherSplits, SPECIAL_TASK.deadline.name());
        return furtherSplits;
    }

    public String[] parseEvent(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.event.name());
        String[] furtherSplits = furtherSplit(splitInput[1], "/at");
        checkFurtherDesc(furtherSplits, SPECIAL_TASK.event.name());
        return furtherSplits;
    }

    public int parseDelete(String[] splitInput) throws DukeException {
        checkDesc(splitInput, SPECIAL_TASK.delete.name());
        int index = getIndex(splitInput);
        return index;
    }

    public void checkTaskIndex(int index, TaskList taskList) throws DukeException {
        if (index >= taskList.size() || index <= 0) {
            throw new DukeException("duke.commands.Task number does not exist!");
        }
    }
}
