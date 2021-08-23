public class Parser {
    public void parse(String input) throws DukeException {
        String firstWord = input.split(" ")[0];

        for (Commands command : Commands.values()) {
            if (command.isCommand(firstWord)) {
                command.getCommand().parse(input);
                // terminate out of loop once the command is found
                return;
            }
        }

        // handle case where all commands don't match
        throw new DukeException("Unsupported command");
    }
}
