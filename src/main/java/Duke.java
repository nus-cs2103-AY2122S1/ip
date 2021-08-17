import java.util.Scanner;

public class Duke {
    //for division
    private static String ind = "    ";
    //for sentences
    private static String ind2 = "     ";
    private static String div = ind + "____________________________________________________________";
    private static String END = "bye";
    public static void main(String[] args) {
        greeting();
        Scanner sc = new Scanner(System.in);
        while (sc.hasNext()) {
            String next = sc.nextLine();
            if (next.equals(END)) {
                myPrint("Bye. Hope to see you again soon!");
                break;
            } else {
                myPrint(next);
            }
            }
    }

    private static void greeting() {
        String g = "Hello! I'm Duke";
        String g2 = "What can I do for you?";
        myPrint(g + "\n" + ind2+ g2);
    }

    private static void myPrint(String s) {
        System.out.println(div + "\n" + ind2 + s + "\n" + div);
    }
}
