public class Parser {

    public static Command parse(String[] input)  {
        if (input[0].equals("bye") || input[0].equals("exit")) {
            return Command.EXIT;
        } else if (input[0].equals("list")) {
            return Command.LIST;
        } else  if (input[0].equals("done") || input[0].equals("delete")) {
            return Command.INDEXCOMMAND;
        } else if (input[0].equals("todo") || input[0].equals("deadline") || input[0].equals("event")) {
            return Command.ADDCOMMAND;
        } else {
            return Command.UNKNOWN;
        }
    }
}
