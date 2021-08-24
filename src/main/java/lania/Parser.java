package lania;

import lania.exception.LaniaEmptyDescriptionException;

public class Parser {

    public String parseCommand(String command) {
        String[] split = command.split(" ");
        return split[0];
    }

    public String parseTaskDescription(String command) throws LaniaEmptyDescriptionException {
        String[] split = command.split(" ", 2);
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException(split[0]);
        } else {
            return split[1];
        }
    }

    public String[] parseDeadline(String command) throws LaniaEmptyDescriptionException {
        String split[] = command.split(" /by ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        } else {
            return split;
        }
    }

    public String[] parseEvent(String command) throws LaniaEmptyDescriptionException {
        String split[] = command.split(" /at ");
        if (split.length == 1) {
            throw new LaniaEmptyDescriptionException("date/time");
        } else {
            return split;
        }
    }

    public int getIndex(String command) {
        String[] split = command.split(" ");
        return Integer.parseInt(split[1]);
    }
}
