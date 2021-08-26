public class ErrorCommand extends Command {
    private String error;
    private String emoticon;

    public ErrorCommand(String error) {
        this.error = error;
        this.emoticon = null;
    }

    public ErrorCommand(String error, String emoticon) {
        this.error = error;
        this.emoticon = emoticon;
    }

    @Override
    public void execute(List list, Ui ui, Storage storage) {
        if (this.emoticon != null) {
            ui.printError(this.error, this.emoticon);
        } else {
            ui.printError(this.error);
        }
    }
}
