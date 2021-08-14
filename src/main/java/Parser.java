public class Parser {
    public static Command parse(String command) throws LifelineException {
        try {
            return Command.valueOf(command.split("\\s", 2)[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new LifelineException("I am sorry! I don't know what that means! ☹");
        }
    }
}
