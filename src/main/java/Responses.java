import java.util.Scanner;

public class Responses {
    
    public static void displayDukeResponse(String dResponse) {
        String str = String.format("\t____________________________________________________________\n%s\t____________________________________________________________", dResponse);
        System.out.println(str);
    }

    public static String getUserResponse() {
        Scanner sc = new Scanner(System.in);
        String uResponse = sc.nextLine();
        return uResponse;
    }

    public static String interact(String dResponse) {
        displayDukeResponse(dResponse);
        return getUserResponse();
    }

}
