package Command;

public class DeadlineCommand extends Command{
    protected DeadlineCommand(String args){
        super(args);
    }
    @Override
    public void execute(){
        System.out.println("deadline from command");
    }
}
