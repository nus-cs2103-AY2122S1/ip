import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private LocalDateTime eventDateTime;
    
    public EventCommand(String description, LocalDateTime eventDateTime) {
        this.description = description;
        this.eventDateTime = eventDateTime;
    }

    public void executeCommand(Tasklist taskList, UI ui, PersistentStorage storage) {
        Event event = new Event(this.description, this.eventDateTime);
        taskList.addTask(event);
        ui.showAddedTask(taskList, event);
    }
}
