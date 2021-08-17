public class DeleteCommand implements Command {
    int index;

    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void run() {
        Task deleted = TaskStorage.getInstance().delete(index);
        Message.print(new String[] {
                "Okay, I have removed this task:",
                "\t" + deleted.toString(),
                String.format("Now you have %d task%s in your list.", TaskStorage.getInstance().getSize(), TaskStorage.getInstance().getSize() > 1 ? "s" : "")
        });
    }
}
