import java.util.Scanner;

public class Nature {

    private String message;

    public String greeting() {
        return "Hello! I'm Nature.\n"
                + "What can I do for you?";
    }

    public String farewell() {
        return "Bye darling. \n"
                + "Hope to see you again soon!";
    }

    public static void main(String[] args) {
        Nature chatbot = new Nature();
        System.out.println(chatbot.greeting());
        Scanner sc = new Scanner(System.in);
        while(sc.hasNext()) {
            String s = sc.nextLine();
            if (s.equals("bye")) break;
            System.out.println(s);
        }
        sc.close();
        System.out.println(chatbot.farewell());
    }
}
