import java.util.Scanner;
import java.util.ArrayList;

public class Responses {

    protected static ArrayList<Task> list = new ArrayList<>();

    protected static void displayDukeResponse(String dResponse) {
        System.out.println(String.format("\t____________________________________________________________\n%s\t____________________________________________________________", dResponse));
    }

    protected static String getUserResponse() {
        Scanner sc = new Scanner(System.in);
        return sc.nextLine();
    }

    protected static void interact(String dResponse) {
        displayDukeResponse(dResponse);
        next(getUserResponse());
    }

    protected static void next(String uResponse) {
        try {
            if (uResponse.equals ("bye")) {
                Exit.chat();
            } else if (uResponse.equals("list")) {
                List.chat();
            } else if (uResponse.startsWith("done")) {
                Done.chat(uResponse);
            } else if (uResponse.startsWith("delete")) {
                Delete.chat(uResponse);
            } else {
                Echo.chat(uResponse);
            }
        } catch (DukeException e) {
            displayDukeResponse(String.format("\t%s\n", e.getMessage()));
            next(getUserResponse());
        } 
    } 

}
