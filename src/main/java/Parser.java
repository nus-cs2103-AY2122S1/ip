public class Parser {
    public static CommandType parseCmd(String input) throws DukeUnknownCommandException {
        String command = input.split(" ", 2)[0];
        try {
            return CommandType.valueOf(command.toUpperCase());
        } catch (IllegalArgumentException e){
            throw new DukeUnknownCommandException(command);
        }
    }

    public static String parseArgs(String input) throws DukeUnknownCommandException {
        return input.split(" ", 2)[1];
    }
}
