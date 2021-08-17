import java.util.Scanner;

import model.Storage;
import view.Response;

public class BobCat {
    public static void main(String[] args) {
        Scanner scanObj = new Scanner(System.in);

        boolean toTerminate = false;
        Response response = new Response();
        Storage storage = new Storage();

        Response.respond(new String[]{"Hello! I'm BobCat!", "What can I do for you?"});
        while(!toTerminate) {
            String inp = scanObj.nextLine();
            if (inp.equals("bye")) {
                toTerminate = true;
            }
            response.respond(inp, storage);
        }
        scanObj.close();
    }
}
