public class Processor {
    /**
     * Processes the user's input based on the commands contained in the input string.
     *
     * @param input the user's input
     * @throws InvalidInputException if the user's input is not a valid input
     */
    public static void process(String input) throws InvalidInputException {
        String[] tokens = Parser.tokenParser(input);
        int len = tokens.length;

        if (len == 0) {
            throw new EmptyCommandException();
        }

        String command = tokens[0];

        switch (Command.fromString(command)) {
        case LIST:
            if (len > 1) {
                throw new BadInputFormatException();
            }
            Memory.print();
            break;
        case DONE:
            Memory.markTaskAsDoneByIndex(tokens[1]);
            break;
        case DELETE:
            Memory.deleteTaskByIndex(tokens[1]);
            break;
        case TODO:
            Memory.add(Todo.of(Parser.descriptionParser(input, "todo ")));
            break;
        case DEADLINE:
            Memory.add(Deadline.of(Parser.descriptionParser(input, "deadline ")));
            break;
        case EVENT:
            Memory.add(Event.of(Parser.descriptionParser(input, "event ")));
            break;
        default:
            throw new UnknownCommandException();
        }
    }
}
