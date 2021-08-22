public interface Command {

    public void execute();

    public boolean isExit();

    public void invalidArguments();
}
