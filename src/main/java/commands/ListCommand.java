package commands;
import java.util.List;

public class ListCommand extends ExecutableCommand {
    private static final String ListMessage = "Here are the tasks in your list:";
    private static final String COMPLETED = "[X]";
    private static final String NOTCOMPLETED = "[]";

    public ListCommand(String desc) {
        super(desc);
    }

    @Override
    public void execute(List<Command> commandList, List<Boolean> completedTask) {
        System.out.println(ListMessage);
        for (int i = 1; i <= commandList.size(); i++) {
            Command com = commandList.get(i - 1);
            StringBuilder sb = new StringBuilder("");
            sb.append(String.valueOf(i) + ".");
            if (completedTask.get(i - 1)) {
                sb.append(COMPLETED);
            } else {
                sb.append(NOTCOMPLETED);
            }
            sb.append(" " + com);
            System.out.println(sb.toString());
        }
    }

}