public class Parser {
    public Command getCommand(String taskName) {
        switch(taskName.toLowerCase()) {
            case "list":
                return new ListCommand();
            default:
                return new AddCommand(new Task(taskName));
        }
    }
}
