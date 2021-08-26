public class ByeCommand extends Command {

    /**
     * Call Ui to print goodbye
     * Returns true to break main loop
      */
    @Override
    public boolean execute(Ui ui, Storage storage) {
        ui.greet(false);
        return true;
    }
}
