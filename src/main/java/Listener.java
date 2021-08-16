import java.util.Scanner;
import java.util.ArrayList;

public class Listener {

    final Scanner sc;
    final ArrayList<String> itemList;

    public Listener() {
        sc = new Scanner(System.in);
        itemList = new ArrayList<>();
    }

    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Display.LINE);

            if (input.equals("gubbai")) {
                // Stop listening if "gubbai" is mentioned
                break;
            } else if (input.equals("list")) {
                // Display items
                for (int i = 0; i < itemList.size(); i++){
                    System.out.println((i+1) + "." + itemList.get(i));
                }
                System.out.println("End of list :)");

            } else {
                // Add input to list
                itemList.add(input);
                System.out.println(Display.OUTPUT_DISPLAY + "added: " + input);
            }

            System.out.println(Display.LINE);


        }

        // Quit the program
        System.out.println(
                "君の運命のヒトは僕じゃない\n"
                + Display.LINE
        );
    }


}
