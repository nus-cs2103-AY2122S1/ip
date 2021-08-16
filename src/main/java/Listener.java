import java.util.Scanner;

public class Listener {

    final Scanner sc;

    public Listener() {
        sc = new Scanner(System.in);
    }

    public void startListen() {
        while(true) {
            // Receive Input text
            String input = sc.nextLine();
            System.out.println(Display.LINE);

            // Stop listening if "gubbai" is mentioned
            if (input.equals("gubbai")) {
                break;
            }

            System.out.println(Display.OUTPUT_DISPLAY + input);
            System.out.println(Display.LINE);
        }

        // Quit the program
        System.out.println(
                Display.OUTPUT_DISPLAY + "Gubbai. 君の運命のヒトは僕じゃない\n"
                + Display.LINE
        );
    }


}
