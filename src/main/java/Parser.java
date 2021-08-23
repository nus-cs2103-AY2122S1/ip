public class Parser {
    private final Command[] commandList = {
            new ListCommand(""),
            new ByeCommand(""),
    };

    public Command parse(String input) {
        for(Command c : commandList) {
            if(input.equals(c.startsWith())) {
                return c.of(input.substring(c.startsWith().length()));
            }
        }
        return null;
    }

}
