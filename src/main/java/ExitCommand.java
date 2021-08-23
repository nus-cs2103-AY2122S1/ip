public class ExitCommand extends Command{
  public ExitCommand() {
  }

  @Override
  public void execute(TaskList tasks, Ui ui, Storage storage) {
    String response = "Bye. Hope to see you again soon!";
    ui.showResponse(response);
    ui.closeScanner();
  }

  @Override
  public boolean isExit() {
    return true;
  }
}
