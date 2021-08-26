public class ExitCommand extends Command{

    public ExitCommand() {

    }

    @Override
    public void execute(TaskList taskList){
        Ui.showExitMessage();
    }

    @Override
    public String getType() {
        return "exit";
    }

}
