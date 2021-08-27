package skeltal;

public class Ui {

    public static String line = "---------------------------------------------";

    public static void introduction() {
        System.out.println(line);
        System.out.println("Hello! I am Skeltal! \n"
                + "What can I do for you?");
        System.out.println(line);
    }

    public static boolean bye() {
        System.out.println(Ui.line);
        System.out.println("Thanks for chatting! Hope to see you again soon! ");
        System.out.println(Ui.line);
        return true;
    }
}
