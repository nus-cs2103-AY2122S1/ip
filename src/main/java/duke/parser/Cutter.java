package duke.parser;

import duke.exception.DukeException;
import duke.exception.Messages;

public class Cutter {
    /**
     * Returns substring of input line
     * Trims after start word till end of string.
     *
     * @param input request line.
     * @param start substring after target word.
     * @return substring-ed line.
     * @throws DukeException if start word doesn't exist.
     */
    public static String cut(String input, String start) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1);
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }

        if (result.equals("")) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    };

    /**
     * Returns substrings input line
     * Trims between start word and end word.
     *
     * @param input request line.
     * @param start substring after target word.
     * @param end substring before target word.
     * @return substring-ed line.
     * @throws DukeException If start or end word doesn't exist.
     */
    public static String cut(String input, String start, String end) throws DukeException {
        String result;
        try {
            result = input.substring(input.indexOf(start) + start.length() + 1, input.indexOf(end));
        } catch (StringIndexOutOfBoundsException e) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }

        if (result.equals("")) {
            throw new DukeException(String.format(Messages.EMPTY.toString(), start));
        }
        return result;
    };
}
