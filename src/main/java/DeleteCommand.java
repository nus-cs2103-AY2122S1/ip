public class DeleteCommand extends Command{
    private String input;

    public DeleteCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
        if (input.length() == 6) {
            throw new DukeException(Ui.emptyDescriptionMsg("delete"));
        }
        super.taskList.removeTask(Integer.parseInt(input.substring(7)));
    }
}
