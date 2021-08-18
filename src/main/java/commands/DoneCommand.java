public class DoneCommand extends Command {
    private int index;

    public DoneCommand(String userInput) {
        super(userInput);
        this.index = this.getIndex();
    }

    @Override
    public CommandLambda getCommandLambda() {
        return new CommandLambda() {
            @Override
            public LoggableTaskList updateTaskList(LoggableTaskList loggableTaskList) {
                TaskList newTaskList = DoneCommand.this.getNewTaskList(loggableTaskList);
                String newLog = DoneCommand.this.getNewLog(loggableTaskList);
                return new LoggableTaskList(newTaskList, newLog);
            }
        };
    }

    public String getNewLog(LoggableTaskList loggableTaskList) {
        String log = "";
        log += "Nice! I've marked this task as done:\n";
        log += loggableTaskList.getTaskList().getTask(this.index);
        return log;
    }

    public TaskList getNewTaskList(LoggableTaskList loggableTaskList) {
        TaskList oldTaskList = loggableTaskList.getTaskList();
        oldTaskList.markAsDone(this.index);
        return oldTaskList;
    }

    public int getIndex() {
        return Integer.parseInt(this.userInputList.get(1)) - 1;
    }
}

