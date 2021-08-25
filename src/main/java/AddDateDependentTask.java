import java.time.LocalDate;

public class AddDateDependentTask extends Command {
    DateDependentTask dateDependentTask;

    public AddDateDependentTask(String taskType, String description, LocalDate date) throws KermitException {
        switch(taskType) {
            case "deadline":
                dateDependentTask = new Deadline(description, date);
                break;
            case "event":
                dateDependentTask = new Event(description, date);
                break;
            default:
                throw new KermitException("Invalid tasktype!");
        }
    }

    @Override
    void execute(ToDo taskList, Ui ui, Storage storage) throws KermitException {
        taskList.add(dateDependentTask);
        ui.showAddTaskMessage(dateDependentTask, taskList);
        storage.save(taskList);
    }

    @Override
    boolean isExit() {
        return false;
    }
}