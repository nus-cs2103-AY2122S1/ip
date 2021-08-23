import java.time.LocalDate;

public class Command {
    private boolean isBye;
    private final String commandName;
    private int index;
    private LocalDate desiredDate;
    private Task newTask;
    
    public Command(String commandName) {
        this.commandName = commandName;
        isBye = false;
    }

    public Command(String commandName, int index) {
        this.commandName = commandName;
        this.index = index;
        isBye = false;
    }

    public Command(String commandName, LocalDate desiredDate) {
        this.commandName = commandName;
        this.desiredDate = desiredDate;
        isBye = false;
    }

    public Command(String commandName, Task newTask) {
        this.commandName = commandName;
        this.newTask = newTask;
        isBye = false;
    }
    
    public void execute(Ui ui, TaskList taskList) throws DukeException {
        switch (commandName) {
        case "bye":
            ui.showBye();
            isBye = true;
            break;
        case "list":
            taskList.showList();
            break;
        case "done":
        case "delete":
            taskList.setTaskDone(index);
            break;
        case "find":
            taskList.findTasks(desiredDate);
            break;
        case "deadline":
        case "event":
        case "todo":
            taskList.addTask(newTask);
            break;
        }
    }
    
    public boolean isBye() {
        return isBye;
    }
}
