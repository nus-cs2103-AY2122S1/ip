import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println(Response.start());
        Scanner inputScanner = new Scanner(System.in);

        // Initialize storage for list
        int current = 0;
        String[] store = new String[100];
        
        while (inputScanner.hasNext()) {
            String input = inputScanner.nextLine();

            // Check if input is "bye"
            if (input.equals("bye")) {
                Response bye = new Response("Bye! Come back again!");
                System.out.println(bye);
                break;
            } 

            // Check if input is "list"
            else if (input.equals("list")) {
                String items = "";
                
                for (int i = 0; i < current; i++) {
                    String index = String.valueOf(i + 1);
                    items += (i != current - 1) ? (index + ". " + store[i] + "\n") : (index + ". " + store[i]);
                }

                Response itemList = new Response(items);
                System.out.println(itemList);
            }
            
            // Otherwise, add input to storage and inform user of addition
            else {
                store[current] = input;
                current++;
                System.out.println(Response.added(input));
            }
        }
        inputScanner.close();
    }
}
