public class EventCommand extends Command{
    private String input;

    public EventCommand(TaskList tasklist, String input){
        super(tasklist);
        this.input = input;
    }

    public void execute(){
        if (input.length() == 5) {
            throw new DukeException(Ui.emptyDescriptionMsg("event"));
        }
        String[] infoArray = input.substring(6).split("/at ", 2);
        Event e = new Event(infoArray[0], infoArray[1]);
        taskList.addTask(e);
    }
}