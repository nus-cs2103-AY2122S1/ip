public interface ICommand {

    public abstract void execute(TaskManager tm, Ui ui, Storage storage);

}
