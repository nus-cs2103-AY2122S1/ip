public class DoneCommand extends Command {

    public DoneCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please enter \"done\" followed the number corresponding to " +
                    "the task you want to mark as completed");
        } else {
            int taskDone = Integer.parseInt(parsedUserInput[1]) - 1;
            tasks.getTasks().get(taskDone).markAsCompleted();
            storage.updateLS(tasks.getTasks());
            ui.reply("Nice! I've marked this task as done: \n" + printDoneTask(taskDone, "", tasks));
        }
    }

    public String printDoneTask(int pos, String toDoListToPrint, TaskList tasks) {
        Task currentTask = tasks.getTasks().get(pos);
        return toDoListToPrint + (pos + 1) + "." + currentTask.toString();
    }
}
