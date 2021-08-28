public class DeadlineCommand extends Command{
    private String input;
    public DeadlineCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
            if (input.length() == 8) {
                throw new DukeException(Ui.emptyDescriptionMsg("deadline"));
            }
            String[] infoArray = input.substring(9).split("/by ", 2);
            Deadline d = new Deadline(infoArray[0], infoArray[1]);
            super.taskList.addTask(d);
        }

}
