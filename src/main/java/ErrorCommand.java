public class ErrorCommand implements Command {

    String msg;

    ErrorCommand(String message) {
        this.msg = message;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        System.out.println(msg);
    }

    @Override
    public boolean isQuit() {
        return false;
    }
}
