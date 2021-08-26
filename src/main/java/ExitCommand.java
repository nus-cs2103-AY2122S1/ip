class ExitCommand extends Command {

    @Override
    public CommandResult execute() {
        return new CommandResult("Bye bye");
    }

}
