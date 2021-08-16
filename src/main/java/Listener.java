import java.util.Scanner;


public class Listener {

    final Scanner sc;
    final TaskList itemList;

    public Listener() {
        sc = new Scanner(System.in);
        itemList = new TaskList();
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
                System.out.println(Display.OUTPUT_DISPLAY + "Displaying List:");
                itemList.displayList();
            } else {
                // Add input to list
                itemList.add(input);
                System.out.println(Display.OUTPUT_DISPLAY + "added: " + input);
            }

            System.out.println(Display.LINE);
        }

        // Quit the program after listening stops
        System.out.println(
                Display.OUTPUT_DISPLAY + "君の運命のヒトは僕じゃない\n"
                + Display.LINE
        );
    }


}
