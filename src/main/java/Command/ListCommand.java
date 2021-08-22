package Command;

public class ListCommand extends Command{
    protected ListCommand(){
        super("");
    }
    @Override
    public void execute(){
        System.out.println("list from command");
    }
}
