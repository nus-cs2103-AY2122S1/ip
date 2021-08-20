public class MarkCommand extends Command {
    private String userInput;

    MarkCommand(String userInput) {
        this.userInput = userInput;
    }

    private void markTask(TaskList tasks, Ui ui) throws DukeException {
        if (userInput.length() <= (Ui.Commands.DONE.getLength() + 1)) {
            // Missing user input for index of task to be marked as done.
            throw new DukeException("An index must be provided to mark task at the index as done.");
        } else {
            // Parses integer in user input.
            int userNumInput = Parser.parseUserNumInput(this.userInput, Ui.Commands.DONE);

            // Decrement integer from user input to match indexing of tasks.
            int idx = userNumInput - 1;

            // Checks for invalid index.
            if (idx >= tasks.size() || idx < 0) {
                throw new DukeException("Index provided for done is either less than 1 or exceeds the length of the list, hence invalid.");
            }

            // Marks task at index as done.
            tasks.get(idx).markAsDone();

            // Prints response to user after successfully marking task at index as done.
            ui.showMarkSuccess(tasks.get(idx));
        }
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            // Marks Task at user specified index in TaskList.
            this.markTask(tasks, ui);

            // Saves edited TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
