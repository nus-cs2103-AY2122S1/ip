package aisu;
public class ShowListCommand extends Command {
    @Override
    public void execute(Tasklist tasklist, Storage storage, Ui ui) {
        System.out.println(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}