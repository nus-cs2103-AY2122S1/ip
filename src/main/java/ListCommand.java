class ListCommand extends Command {

    @Override
    public CommandResult execute() {
        return new CommandResult(taskList.toString());
    }

}
