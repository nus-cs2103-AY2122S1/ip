public class QuitCommand implements Command {

    public void execute(Ui ui, TaskList taskList, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
    };
    public boolean isQuit() { return true; }
}
