public class DoneCommand extends Command{
    private String input;

    public DoneCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
        if (input.length() == 4) {
            throw new DukeException(Ui.emptyDescriptionMsg("done"));
        }
        taskList.markTaskAsDone(Integer.parseInt(input.substring(5)));
    }
}