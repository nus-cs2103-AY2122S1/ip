package Command;

public class ByeCommand extends Command{
    protected ByeCommand(){
        super("");
    }
    @Override
    public void execute(){
        System.out.println("bye from command");
    }
}
