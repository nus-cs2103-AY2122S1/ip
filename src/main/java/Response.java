import java.util.ArrayList;

public class Response {
    private final String horizontalLine = "    ____________________________________________________________\n";
    private ArrayList<String> lst;
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

    public void echo(String text) {
        lst.add(text);
        System.out.println(horizontalLine + "    " + "added: " +
                text + "\n" +
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
        lst.forEach(item -> {
            System.out.println("    " + count + ". " + item);
            count++;
        });
        System.out.println(horizontalLine);
    }
}
