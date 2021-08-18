import java.util.ArrayList;

public class Response {
    private final String horizontalLine = "    ____________________________________________________________\n";
    private ArrayList<Task> lst;
    private int count;

    public Response() {
        this.lst = new ArrayList<>();
        this.count = 1;
    }

    public void greet() {
        System.out.println(horizontalLine +
                "    Hello! I'm LebronChatBot\n" +
                "    What can I do for you?\n" +
                horizontalLine);
    }

    public void echo(Task task) {
        lst.add(task);
        System.out.println(horizontalLine + "    added: " +
                task.getName() + "\n" +
                horizontalLine);
    }

    public void exit() {
        System.out.println(horizontalLine +
                "    Bye. Hope to see you again soon!\n" +
                horizontalLine);
    }

    public void display() {
        this.count = 1;
        System.out.println(horizontalLine);
        System.out.println("    Here are the tasks in your list:");
        lst.forEach(item -> {
            System.out.println("    " + count + ". " + "[" + item.getStatusIcon() + "] " + item.getName());
            count++;
        });
        System.out.println(horizontalLine);
    }

    public void markDone(int pos) {
        Task task = lst.get(pos);
        task.mark();
        String format = String.format("[%s] ", task.getStatusIcon());
        System.out.println(horizontalLine + "    Nice! I've marked this task as done:\n" +
                "    " + format + task.getName() +
                "\n" + horizontalLine);
    }
}
