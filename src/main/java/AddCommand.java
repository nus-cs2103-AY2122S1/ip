public class AddCommand extends Command{

    private final String taskDetails;
    private final String taskType;
    public static final String COMMAND_WORD = "add";


    public AddCommand(String taskDetails, String taskType) {

        this.taskDetails = taskDetails;
        this.taskType = taskType;

    }

    @Override
    public void execute(Tasklist list, Ui ui, Store storage) throws DukeException{
        String successMessage = list.addTask(this.taskDetails, this.taskType);
        ui.printMessage(successMessage);

    }
}
