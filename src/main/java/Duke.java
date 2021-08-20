import java.util.Scanner;

public class Duke {

    public Duke() {
        Response.greetingResponse();    
    }
    
    public void botInit() {
        Scanner commandInput = new Scanner(System.in);
        BotManager botManager = new BotManager(commandInput);
    }
    public static void main(String[] args) {
        Duke duke = new Duke();
        duke.botInit();
    }
}
