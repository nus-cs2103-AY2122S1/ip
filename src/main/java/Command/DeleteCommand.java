package Command;

public class DeleteCommand extends Command{
    protected DeleteCommand(String args){
        super(args);
    }
    @Override
    public void execute(){
        System.out.println("delete from command");
    }
}
