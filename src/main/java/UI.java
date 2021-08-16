import java.util.Scanner;

public class UI {

    private static final String GREET = "Hi there! My name's Duke, how can I help you today?";
    private static final String EXIT = "Bye! See you next time!";

    private Scanner sc;

    public UI () {
        this.sc = new Scanner(System.in);
    }

    public String echoCommand () {
        return sc.nextLine();
    }

    /**
     public String formattedEchoCommand () {
     return String.format("...............\n %s \n...............", echoCommand());
     }
     */

    public String greet () {
        return GREET;
    }

    public String exit () {
        return EXIT;
    }

}
