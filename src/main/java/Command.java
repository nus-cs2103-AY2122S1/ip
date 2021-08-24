public abstract class Command {
    private boolean isExit = false;
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExitCommand() {
        return isExit;
    }
    public void setExitTrue() {
        isExit = true;
    }
    static class ExitCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showByeMessage();
            super.setExitTrue();
        }
    }
    static class ListAllCommand extends Command {
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ui.showGettingAllTaskItemsInteraction(tasks);
        }
    }
    static class DoneCommand extends Command {
        private int taskNum;
        public DoneCommand(int taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            tasks.markTaskAsDone(this.taskNum);
            storage.markPersistedTaskAsDone(this.taskNum, tasks.getTask(this.taskNum));
            ui.showTaskDoneInteraction(tasks.getTask(this.taskNum));
        }
    }
    static class ToDoCommand extends Command {
        private String description;
        public ToDoCommand(String description) {
            this.description = description;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            ToDos newToDo = new ToDos(this.description, false);
            tasks.addTask(newToDo);
            ui.showTaskAddedInteraction(newToDo, tasks);
            storage.addTaskToPersistedData(newToDo);
        }
    }
    static class DeadlineCommand extends Command {
        private String description;
        private String dateTimeBy;
        public DeadlineCommand(String description, String dateTimeBy) {
            this.description = description;
            this.dateTimeBy = dateTimeBy;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Deadlines newDeadline = new Deadlines(description, dateTimeBy, false);
            tasks.addTask(newDeadline);
            ui.showTaskAddedInteraction(newDeadline, tasks);
            storage.addTaskToPersistedData(newDeadline);
        }
    }
    static class EventCommand extends Command {
        private String description;
        private String dateTimeAt;
        public EventCommand(String description, String dateTimeAt) {
            this.description = description;
            this.dateTimeAt = dateTimeAt;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Events newEvent = new Events(description, dateTimeAt, false);
            tasks.addTask(newEvent);
            ui.showTaskAddedInteraction(newEvent, tasks);
            storage.addTaskToPersistedData(newEvent);
        }
    }
    static class DeleteCommand extends Command {
        private int taskNum;
        public DeleteCommand(int taskNum) {
            this.taskNum = taskNum;
        }
        @Override
        public void execute(TaskList tasks, Ui ui, Storage storage) {
            Task taskToDelete = tasks.getTask(this.taskNum);
            tasks.deleteTask(this.taskNum);
            storage.removePersistedTask(this.taskNum);
            ui.showTaskDeletedInteraction(taskToDelete, tasks);
        }
    }
}
