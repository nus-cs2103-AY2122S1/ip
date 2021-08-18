public class InvalidCommand extends Command {
    private TaskList newTaskList;
    private String newLog;

    public InvalidCommand() {
        super("");
    }

    @Override
    public CommandLambda getCommandLambda() {
        return new CommandLambda() {
            @Override
            public LoggableTaskList updateTaskList(LoggableTaskList loggableTaskList) {
                updateLogAndTaskList(loggableTaskList);
                return new LoggableTaskList(newTaskList, newLog);
            }
        };
    }
    public void updateLogAndTaskList(LoggableTaskList loggableTaskList) {
        TaskList oldTaskList = loggableTaskList.getTaskList();
        this.newTaskList = oldTaskList;
        this.newLog = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    }
}
