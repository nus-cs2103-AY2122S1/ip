public class Parser {
    public Command getCommand(String userInput) {
        String[] words = userInput.split(" ");
        String commandName = words[0];
        switch(commandName.toLowerCase()) {
            case "done":
                return new MarkDoneCommand(Integer.parseInt(words[1]));
            case "list":
                return new ListCommand();
            default:
                return new AddCommand(new Task(userInput));
        }
    }
}
