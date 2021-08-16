import java.util.Scanner;

public class Pib {
    private static String divider = "____________________________________________________________\n";

    public static void main(String[] args) {
        System.out.println(divider + "Hello! I'm Pib\n" +"Tell me something!\n" + divider);
        Scanner sc = new Scanner(System.in);

        while (sc.hasNextLine()) {
            String next = sc.nextLine();
            if (next.equalsIgnoreCase("BYE")) {
                System.out.println(divider + "Bye! See you next time!\n" + divider);
                sc.close();
                break;
            }
            System.out.println(divider + next + "\n" + divider);
        }
    }
}
