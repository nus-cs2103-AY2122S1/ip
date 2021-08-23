public class Parser {
    protected static int parseTaskId(String index) throws UnableToParseException {
        int i;

        try {
            i = Integer.parseInt(index);
        } catch (NumberFormatException e) {
            throw new UnableToParseException("task id");
        }

        return i;
    }

    protected static Event parseEvent(String args) throws InvalidArgumentsException {
        String[] splitArgs = args.split(" /at ");
        if (splitArgs.length != 2) {
            throw new InvalidArgumentsException("event [task] /at [time period]");
        }
        return new Event(splitArgs[0], splitArgs[1]);
    }

    protected static Deadline parseDeadline(String args) throws InvalidArgumentsException {
        String[] splitArgs = args.split(" /by ");
        if (splitArgs.length != 2) {
            throw new InvalidArgumentsException("deadline [task] /by [time]");
        }
        return new Deadline(splitArgs[0], splitArgs[1]);
    }
}
