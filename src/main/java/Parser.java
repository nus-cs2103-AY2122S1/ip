public class Parser {

    public static Command parse(String command) {
        String[] tokens = command.split(" ");
        try {
            if (tokens.length == 0){
                throw new IllegalArgumentException("Command cannot be empty");
            } else if (tokens[0].equals("todo")) {
                return new CommandAdd(new ToDo(tokens[1]));
            } else if (tokens[0].equals("event")) {
                return new CommandAdd(new Event(tokens[1], tokens[3]));
            } else if (tokens[0].equals("deadline")) {
                return new CommandAdd(new Deadline(tokens[1], tokens[3]));
            } else if (tokens[0].equals("list")) {
                return new CommandShowList();
            } else if (tokens[0].equals("delete")) {
                return new CommandDelete(tokens[1]);
            } else if (tokens[0].equals("done")) {
                return new CommandDone(tokens[1]);
            } else if (tokens[0].equals("exit")) {
                return new CommandExit();
            } else {
                throw new IllegalArgumentException("Command not recognised");
            }
        } catch (IllegalArgumentException ex) {
            return new CommandError(ex.getMessage());
        }
    }
}
