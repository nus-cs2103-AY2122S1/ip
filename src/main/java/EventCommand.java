import java.time.LocalDateTime;

public class EventCommand extends Command{
    String action;
    public EventCommand(String action) {
        super(false);
        this.action = action;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws InvalidInputException,
                NoTimeException, NoActionException, SaveFileException{
            if (action.trim().length() == 0) {
                throw new NoActionException("Command 'event' requires a task action");
            }
            String[] eventInputs = action.split("/at", 2);
            if (eventInputs.length <=1){
                throw new NoTimeException(
                        "Command 'event' requires a time to be specified. Use /at to specify a time.");
            }
            LocalDateTime date = Parser.parseDate(eventInputs[1].trim());
            Task newTask = new Event(eventInputs[0].trim(), date);
            tasks.add(newTask);
            ui.showTaskAdded(newTask, tasks);
            storage.save(tasks);
    }
}
