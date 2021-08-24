public class CommandTodo extends Command {
    CommandTodo(String[] inputTokens) {
        super(inputTokens);
    }

    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui)
            throws YoyoException {
        checkCompleteCommand(inputTokens);
        Task newTask = new Todo(inputTokens[1]);
        tasks.add(newTask);
        ui.printAddMessage(newTask, tasks);
    }
}
