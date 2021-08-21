import java.io.IOException;
import java.util.Scanner;

import exception.LogicException;
import exception.ParserException;
import executor.ExecutionUnit;
import view.Response;

public class BobCat {
    public static void main(String[] args) throws IOException {
        Scanner scanObj = new Scanner(System.in);
        Response response = new Response();

        ExecutionUnit executor = new ExecutionUnit();

        response.respond(new String[]{"Hello! I'm BobCat!", "Trying to remember what happened..."});
        try {
            executor.initStorage();
        } catch (IOException e) {
            response.respond(new String[]{"Oops! File does not seem to be found / readable! Starting from blank state..."});
        } catch (ParserException | LogicException e) {
            response.respond("Memory may have been corrupted! Starting from blank state...");
            executor.storageClear();
        } finally {
            response.respond(new String[] {"Initialisation done!", "What can I do for you?"});
        }

        while(true) {
            String inp = scanObj.nextLine();
            response.respond(executor.executeCommand(inp));
            if (inp.equals("bye")) {
                break;
            }
        }
        scanObj.close();
    }
}
