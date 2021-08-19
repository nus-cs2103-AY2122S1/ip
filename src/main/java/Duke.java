import java.util.Scanner;

public class Duke {

    public Duke() {
        Response.greetingResponse();
        Scanner commandInput = new Scanner(System.in);
        TaskManager taskManager = new TaskManager(commandInput);
    }

    public static void main(String[] args) {
        Duke duke = new Duke();
    }
}
