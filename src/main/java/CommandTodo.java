public class CommandTodo extends Command {
    CommandTodo(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        checkCompleteCommand(inputTokens);
        Task newTask = new Todo(inputTokens[1]);
        tasks.add(newTask);
        ui.printAddMessage(newTask, tasks);
    }
}
