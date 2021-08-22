package Command;

public class DoneCommand extends Command{
    protected DoneCommand(String args){
        super(args);
    }
    @Override
    public void execute(){
        System.out.println("done from command");
    }
}
