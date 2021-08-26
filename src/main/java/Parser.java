public class Parser {
    public static CommandType parseCommand(String input) throws DukeUnknownCommandException {
        String command = input.split(" ", 2)[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeUnknownCommandException(command);
        }
    }

    public static String parseArgument(String input) {
        return input.split(" ", 2)[1];
    }
}
