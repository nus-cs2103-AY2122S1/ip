public class DeleteCommand extends Command {
    private String userInput;

    DeleteCommand(String userInput) {
        this.userInput = userInput;
    }

    private void deleteTask(TaskList tasks, Ui ui) throws DukeException {
        if (this.userInput.length() <= (Ui.Commands.DELETE.getLength() + 1)) {
            // Missing user input for index of task to be deleted.
            throw new DukeException("An index must be provided to delete task at index.");
        } else {
            // Parses integer in user input. 1 space is accounted for as it separates command and index.
            int userNumInput = Parser.parseUserNumInput(this.userInput, Ui.Commands.DELETE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= tasks.size() || idx < 0) {
                throw new DukeException("Index provided for delete is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Deletes task at index and obtain the deleted task
            Task deletedTask = tasks.remove(idx);

            // Prints response to user after successfully deleting task at index.
            ui.showDeleteSuccess(deletedTask, tasks.size());
        }
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Deletes task at user specified index.
            this.deleteTask(tasks, ui);

            // Saves edited TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
