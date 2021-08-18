public class DeadlineCommand extends Command {
    private int byIndex;
    private String newLog = "";
    private TaskList newTaskList;

    public DeadlineCommand(String userInput) {
        super(userInput);
        this.byIndex = this.getByIndex();
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

        try {
            String description = this.getDescription();
            String time = this.getTime();

            //Update TaskList
            Deadline deadline = new Deadline(description, time);
            oldTaskList.addTask(deadline);
            this.newTaskList = oldTaskList;

            //Update Log
            this.newLog += Util.taskAddConfirmation(deadline, this.newTaskList.getNumTask());

        } catch (DukeException e) {
            //Unchanged TaskList
            this.newTaskList = loggableTaskList.getTaskList();
            //Log out error message
            this.newLog = e.getMessage();
        }
    }

    public String getTime() throws EmptyTimeException {
        String time = String.join(" ", userInputList.subList(this.byIndex + 1, userInputList.size()));

        if (time.equals("")) {
            throw new EmptyTimeException("deadline");
        } else {
            return time;
        }
    }

    public String getDescription() throws EmptyDescriptionException {
        //Get description
        String description = String.join(" ", userInputList.subList(1, byIndex));
        if (description.equals("")) {
            throw new EmptyDescriptionException("deadline");
        } else {
            return description;
        }
    }

    public int getByIndex() {
        return userInputList.indexOf("/by");
    }
}
