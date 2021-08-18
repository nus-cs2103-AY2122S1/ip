import java.util.Scanner;

public class Duke {
    public static void main(String[] args) {
        System.out.println("   (=^ ･ｪ･^=) < Hello! I'm TiTi~ ");
        System.out.println("                What would you like to do nya? ");

        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        while (!input.equals("bye")) {
            System.out.println("   (=^ ･ｪ･^=) < " + input);
            input = sc.nextLine();
        }

        System.out.println("   (=^ ‐ｪ‐^=) < Already done? Come back soon nya~");
    }
}
