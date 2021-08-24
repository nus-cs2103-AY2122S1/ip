import java.io.IOException;

public class Duke {

    public Duke() {
        Response.greetingResponse();    
    }
    
    public void botInit() throws IOException {
        BotManager botManager = new BotManager();
    }
    public static void main(String[] args) throws IOException {
        Duke duke = new Duke();
        duke.botInit();
    }
}
