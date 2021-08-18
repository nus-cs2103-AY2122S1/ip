import java.util.Scanner;

public class Duke {
    private static final String WELCOME_MSG = "wwwwwwwwwwwwwwwwhats up";
    private static final String EXIT_MSG = "gggggggggggggggooooooodbye";

    public static void main(String[] args) {

        System.out.println(WELCOME_MSG);

        Scanner sc  = new Scanner(System.in);
        String input = sc.nextLine();

        while(!input.equals("bye") ) {
            System.out.println(input);
            input = sc.nextLine();
        }

        System.out.println(EXIT_MSG);
    }
}
