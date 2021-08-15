public class ListCommand implements Command{
    @Override
    public void run() {
        if (Duke.count == 0) {
            Message.print("No task added yet.");
            return;
        }
        String[] messages = new String[Duke.count];
        for (int i = 0; i < Duke.count; i++) {
            messages[i] = String.format("%d - %s", i + 1, Duke.tasks[i].toString());
        }
        Message.print(messages);
    }
}
