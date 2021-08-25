package winston;

public class FindCommand extends Command {
    private String str;
    
    public FindCommand(TaskList taskList, String str) {
        super(taskList);
        this.str = str;
    }
    
    @Override
    public void run() {
        TaskList tList = taskList.find(this.str);
        Ui.printMatchingTasks();
        Ui.printList(tList);
    }
}
