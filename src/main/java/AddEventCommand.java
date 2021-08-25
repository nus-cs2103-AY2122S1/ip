public class AddEventCommand extends AddTaskCommand {

    String time;

    AddEventCommand(String desc, Boolean isDone, String time) {
        super(desc, isDone);
        this.time = time;
    }

    @Override
    public void execute(Ui ui, TaskList taskList, Storage storage) {
        Event newEvent = new Event(this.desc, this.isDone, this.time);
        taskList.addTask(newEvent);
        ui.printAddTask(newEvent);
    }
}
