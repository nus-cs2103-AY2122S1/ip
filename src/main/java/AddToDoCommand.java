public class AddToDoCommand extends AddCommand {
    public static final String KEYWORD = "todo";

    public AddToDoCommand (String userInput) {
        String inputData = userInput.substring(KEYWORD.length()).trim();
        this.task = new ToDoTask(inputData);
    }
}
