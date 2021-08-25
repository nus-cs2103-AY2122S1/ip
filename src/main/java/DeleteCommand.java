public class DeleteCommand extends Command {

    public DeleteCommand(String userInput) {
        super(userInput);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] parsedUserInput = this.getUserInput().split(" ", 2);
        if (parsedUserInput.length == 1) {
            throw new DukeException("☹ OOPS!!! Please enter \"delete\" followed the number corresponding to " +
                    "the task you want deleted");
        } else {
            try {
                int taskToDelete = Integer.parseInt(parsedUserInput[1]) - 1;
                Task deletedTask = tasks.getTasks().get(taskToDelete);
                tasks.getTasks().remove(taskToDelete);
                storage.updateLS(tasks.getTasks());
                ui.reply("Noted. I've removed this task: \n" + deletedTask.toString() +
                        "\nNow you have " + tasks.getTasks().size() + " tasks in the list.");
            } catch (NumberFormatException e) {
                throw new DukeException("☹ OOPS!!! Please enter \"delete\" followed the number corresponding to " +
                        "the task you want deleted");
            }
        }
    }
}
