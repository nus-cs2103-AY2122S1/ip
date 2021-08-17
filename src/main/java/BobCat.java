import java.util.Scanner;

import model.Storage;
import view.Response;

public class BobCat {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);

        boolean toTerminate = false;
        Storage storage = new Storage();

        Response.respond(new String[]{"Hello! I'm BobCat!", "What can I do for you?"});
        while(!toTerminate) {
            String inp = scanObj.nextLine();
            if (inp.equals("bye")) {
                toTerminate = true;
            }
            Response.respond(inp, storage);
        }
        scanObj.close();
    }
}
