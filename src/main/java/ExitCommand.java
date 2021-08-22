public class ExitCommand extends Command {

    @Override
    void execute(TaskList tasks, Ui ui, Storage storage) {
        // perform saving of taskList to disk here
        storage.save(tasks);

        String response = respond();
        ui.showResponse(response);
    }

    @Override
    boolean isExit() {
        return true;
    }

    public String respond() {
        String response = "Bye. Hope to see you again soon!";
        return response;
    }
}
