import java.io.IOException;

class ExitCommand extends Command {

    void execute(TaskList tasks, Ui ui, Storage storage) throws IOException {
        storage.write(tasks);
        ui.printExit();
    }

    boolean isExit() {
        return true;
    }

}