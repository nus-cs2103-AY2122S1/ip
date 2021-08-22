public class ListCommand extends Command {
    public ListCommand(String desc) {
        super(desc);
    }

    @Override
    boolean isExit() {
        return false;
    }

    @Override
    void execute(TaskList tasks, Storage storage) {
        if (tasks.size() == 0) {
            System.out.println("You have not added anything to the list, Master Wayne.");
        } else {
            System.out.println("-------------------------------------");
            for (int i = 0; i < tasks.size(); i++) {
                Task task = tasks.get(i);
                System.out.println((i + 1) + ". " + " " + task.toString());
            }
            System.out.println("-------------------------------------");
        }
    }
}
