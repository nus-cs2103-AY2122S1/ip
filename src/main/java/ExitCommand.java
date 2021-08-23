class ExitCommand extends Command{

    Ui ui;

    ExitCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    protected void execute(){
        ui.end();
    }

    @Override
    protected boolean isExit() {
        return true;
    }
}
