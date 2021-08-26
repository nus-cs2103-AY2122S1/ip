import java.io.IOException;

public class Done extends Task {
    int num;

    public Done(int num) {
        super("done", true);
        this.num = num;
    }

    @Override
    public void excute(TaskList task, Ui ui, Storage storage) throws IOException {
        task.markDone(num);
        ui.showDoneMessgae(task, num);
        storage.writeData(task.getTasks());

    }
}
