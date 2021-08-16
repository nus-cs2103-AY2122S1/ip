import java.util.Scanner;

public class Responses {
    
    protected static String[] list = new String[100];
    protected static int listLength = 0;

    protected static void displayDukeResponse(String dResponse) {
        String str = String.format("\t____________________________________________________________\n%s\t____________________________________________________________", dResponse);
        System.out.println(str);
    }

    protected static String getUserResponse() {
        Scanner sc = new Scanner(System.in);
        String uResponse = sc.nextLine();
        return uResponse;
    }

    protected static String interact(String dResponse) {
        displayDukeResponse(dResponse);
        return getUserResponse();
    }
}
