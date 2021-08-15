import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Response.start());
        Scanner inputScanner = new Scanner(System.in);
        
        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();

            //Check if input is "bye"
            if (input.equals("bye")) {
                Response bye = new Response("Bye! Come back again!");
                System.out.println(bye);
                break;
            } 
            
            // Otherwise, echo response
            else {
                Response response = new Response(input);
                System.out.println(response);
            }
        }
        inputScanner.close();
    }
}
