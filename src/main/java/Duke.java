import java.util.Scanner;

public class Duke {

    private void echo() {
        Scanner scanner = new Scanner(System.in);
        String echoString = scanner.nextLine();
        if(echoString.equals("bye") || echoString.equals("Bye")) {
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                             + "  Bye. Hope to see you again soon!\n"
                             + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
        } else {
            System.out.println("=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=\n"
                    + "  " + echoString + "\n"
                    + "=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=~=");
            echo();
        }
    }

    public static void main(String[] args) {
        String logo = "                     ,,       \n" +
                "`7MM\"\"\"Yp,          *MM       \n" +
                "  MM    Yb           MM       \n" +
                "  MM    dP  ,pW\"Wq.  MM,dMMb. \n" +
                "  MM\"\"\"bg. 6W'   `Wb MM    `Mb\n" +
                "  MM    `Y 8M     M8 MM     M8\n" +
                "  MM    ,9 YA.   ,A9 MM.   ,M9\n" +
                ".JMMmmmd9   `Ybmd9'  P^YbmdP' "
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
                + "asciiart.eu/cartoons/spongebob-squarepants\n";

        System.out.println("Hello from\n" + logo);
        System.out.println("Let's Talk!");
        Duke duke = new Duke();
        duke.echo();
    }
}
