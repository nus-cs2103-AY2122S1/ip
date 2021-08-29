public class Parser {
    public Command parseInput(String commandLine) {
        String[] commandLineParts = commandLine.split("\\s+", 2);

        Command command;

        if (commandLineParts.length == 2) {
            command = new Command(commandLineParts[0], commandLineParts[1]);
        } else {
            command = new Command(commandLineParts[0], "");
        }

        return command;
    }
}
