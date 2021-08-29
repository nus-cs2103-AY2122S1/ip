import java.util.ArrayList;

public class ListCommand extends Command {

    public ListCommand() {
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws NoSuchTaskException {
        ui.showListMessage();
        for (int i = 1; i <= tasklist.getTasklistSize(); i++){
            System.out.println(String.valueOf(i) + ". " + tasklist.getTask(i-1));
        }
    }
}