package Command;

public class EventCommand extends Command{
    protected EventCommand(String args){
        super(args);
    }
    @Override
    public void execute(){
        System.out.println("event from command");
    }
}
