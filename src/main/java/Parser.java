public class Parser {
    public Command getCommand(String userInput) {
        String[] words = userInput.split(" ");
        String commandName = words[0];
        int taskStart = 0, taskEnd = 0;
        int start = 0, end = 0;
        String task = userInput, timeDate = userInput;

        switch(commandName.toLowerCase()) {
            case "event":
                taskStart = userInput.indexOf(" ") + 1;
                taskEnd = userInput.indexOf("/") - 1;
                task = userInput.substring(taskStart, taskEnd);
                start = userInput.indexOf("/") + 4;
                end = userInput.length() ;
                timeDate = userInput.substring(start, end);
                return new AddCommand(new EventTask(task, timeDate));
            case "deadline":
                taskStart = userInput.indexOf(" ") + 1;
                taskEnd = userInput.indexOf("/") - 1;
                task = userInput.substring(taskStart, taskEnd);
                start = userInput.indexOf("/") + 4;
                end = userInput.length();
                timeDate = userInput.substring(start, end);
                return new AddCommand(new DeadlineTask(task, timeDate));
            case "todo":
                taskStart = userInput.indexOf(" ") + 1;
                taskEnd = userInput.length();
                return new AddCommand(new ToDoTask(userInput.substring(taskStart, taskEnd)));
            case "done":
                return new MarkDoneCommand(Integer.parseInt(words[1]));
            case "list":
                return new ListCommand();
            default:
                return new AddCommand(new Task(userInput));
        }
    }
}
