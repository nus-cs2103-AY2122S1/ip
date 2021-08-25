import java.io.FileNotFoundException;

public class Greetings extends Responses{
    
    public static void chat() {
        Responses.loadList();
        Responses.interact("\tHello! I'm Duke!\n\tWhat can I do for you?\n");
    }

}
