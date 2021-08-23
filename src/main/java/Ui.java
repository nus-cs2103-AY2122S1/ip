import java.util.Scanner;

public class Ui {
    private Scanner scanner;
    private final String welcome =  "                     ,,\n" +
            "`7MM\"\"\"Yp,          *MM\n" +
            "  MM    Yb           MM\n" +
            "  MM    dP  ,pW\"Wq.  MM,dMMb.\n" +
            "  MM\"\"\"bg. 6W'   `Wb MM    `Mb\n" +
            "  MM    `Y 8M     M8 MM     M8\n" +
            "  MM    ,9 YA.   ,A9 MM.   ,M9\n" +
            ".JMMmmmd9   `Ybmd9'  P^YbmdP'"
            + "\n\n"
            + "      .--..--..--..--..--..--.\n"
            + "    .' \\  (`._   (_)     _   \\\n"
            + "  .'    |  '._)         (_)  |\n"
            + "  \\ _.')\\      .----..---.   /\n"
            + "  |(_.'  |    /    .-\\-.  \\  |\n"
            + "  \\     0|    |   ( O| O) | o|\n"
            + "   |  _  |  .--.____.'._.-.  |\n"
            + "   \\ (_) | o         -` .-`  |\n"
            + "    |    \\   |`-._ _ _ _ _\\ /\n"
            + "    \\    |   |  `. |_||_|   |\n"
            + "    | o  |    \\_      \\     |     -.   .-.\n"
            + "    |.-.  \\     `--..-'   O |     `.`-' .'\n"
            + "  _.'  .' |     `-.-'      /-.__   ' .-'\n"
            + ".' `-.` '.|='=.='=.='=.='=|._/_ `-'.'\n"
            + "`-._  `.  |________/\\_____|    `-.'\n"
            + "   .'   ).| '=' '='\\/ '=' |\n"
            + "   `._.`  '---------------'\n"
            + "           //___\\   //___\\\n"
            + "             ||       ||\n"
            + "             ||_.-.   ||_.-.\n"
            + "            (_.--__) (_.--__)\n"
            + "asciiart.eu/cartoons/spongebob-squarepants\n"
            + ".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.\n"
            + "What would you like me to add to the list?";

    public Ui() {
        scanner = new Scanner(System.in);
    }

    public void showWelcome() {
        System.out.println(welcome);
    }

    public void textFrame(String s) {
        System.out.println(".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.\n"
                + s + "\n"
                + ".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.");
    }

    public void errorFrame(String s) {
        System.out.println(".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.\n"
                + s + "\n"
                + ".-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-._.-.");
    }

    public String readCommand() {
        return scanner.nextLine();
    }
}
