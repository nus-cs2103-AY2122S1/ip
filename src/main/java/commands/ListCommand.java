package commands;
import java.util.List;

public class ListCommand extends ExecutableCommand {
    private static final String ListMessage = "Here are the tasks in your list:";

    public ListCommand(String desc) {
        super(desc);
    }

    @Override
    public boolean isForStorage() {
        return false;
    }

    @Override
    public void execute(List<Command> commandList) {
        System.out.println(ListMessage);
        for (int i = 1; i <= commandList.size(); i++) {
            NonExecutableCommand com = (NonExecutableCommand) commandList.get(i - 1);
            NonExecutableCommand listedCom = com.isListed();
            commandList.set(i - 1, listedCom);
            StringBuilder sb = new StringBuilder("");
            String displayTaskInList = String.valueOf(i) + "." + listedCom;
            sb.append(displayTaskInList);
            System.out.println(sb.toString());
        }
    }

}