public class ListCommand extends Command {
    private String userInput;

    ListCommand(String userInput) {
        this.userInput = userInput;
    }

    private void printTasks(TaskList tasks, Ui ui) {
        ui.showListSuccess();
        for (int i = 0; i < tasks.size(); i++) {
            // Increment i by 1 so number matches display indexing which starts from 1.
            int idx = i + 1;

            // Format should be "?. taskDescription\n"
            System.out.printf("%d.%s%n", idx, tasks.get(i).toString());
        }
    }

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        // Prints tasks in tasks.
        this.printTasks(tasks, ui);

        try {
            // Saves edited TaskList to save file.
            storage.saveTasksToData(tasks);
        } catch (DukeException dukeException) {
            System.out.println(dukeException);
        }
    }
}
