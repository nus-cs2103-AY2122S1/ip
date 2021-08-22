package Command;

public class TodoCommand extends Command{
    protected TodoCommand(String args){
        super(args);
    }
    @Override
    public void execute(){
        System.out.println("todo from command");
    }
}
