import java.util.Scanner;

public class Responses {

    protected static Task[] list = new Task[100];
    protected static int currLength = 0; 

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
        if (uResponse.equals ("bye")) {
            Exit.chat();
        } else if (uResponse.equals("list")) {
            List.chat();
        } else if (uResponse.startsWith("done")) {
            Done.chat(uResponse);
        } else {
            Echo.chat(uResponse);
        }
    } 
}
