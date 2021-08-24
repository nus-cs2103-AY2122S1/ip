package duke;

public class ExitCommand extends Command {
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.bye();
    }

    @Override
    public boolean isExit(){
        return true;
    }

    @Override
    public boolean equals(Object obj){
        if(obj instanceof ExitCommand){
           return true;
        } else {
            return false;
        }
    }
}
