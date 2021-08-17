import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Duke {

    /**
     *
     * @param str
     * @return String of reply with top and bottom borders
     */
    private static String reply(String str) {
        String y_border = "------------------------------------------------------------\n";
        return y_border + "PEPPER JACK: " + str + "\n" + y_border;
    }

    public static void main(String[] args) {
        String logo =
                "$$$$$$$\\                                                             $$$$$\\                     $$\\       \n" +
                "$$  __$$\\                                                            \\__$$ |                    $$ |      \n" +
                "$$ |  $$ | $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\   $$$$$$\\              $$ | $$$$$$\\   $$$$$$$\\ $$ |  $$\\ \n" +
                "$$$$$$$  |$$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\ $$  __$$\\             $$ | \\____$$\\ $$  _____|$$ | $$  |\n" +
                "$$  ____/ $$$$$$$$ |$$ /  $$ |$$ /  $$ |$$$$$$$$ |$$ |  \\__|      $$\\   $$ | $$$$$$$ |$$ /      $$$$$$  / \n" +
                "$$ |      $$   ____|$$ |  $$ |$$ |  $$ |$$   ____|$$ |            $$ |  $$ |$$  __$$ |$$ |      $$  _$$<  \n" +
                "$$ |      \\$$$$$$$\\ $$$$$$$  |$$$$$$$  |\\$$$$$$$\\ $$ |            \\$$$$$$  |\\$$$$$$$ |\\$$$$$$$\\ $$ | \\$$\\ \n" +
                "\\__|       \\_______|$$  ____/ $$  ____/  \\_______|\\__|             \\______/  \\_______| \\_______|\\__|  \\__|\n" +
                "                    $$ |      $$ |                                                                        \n" +
                "                    $$ |      $$ |                                                                        \n" +
                "                    \\__|      \\__|                                                                        ";
        System.out.println("The name is\n" + logo);
        System.out.print(reply("This Pepper Jack, wassup!"));

        Scanner sc = new Scanner(System.in);

        while(true) {
            // Get user input
            System.out.print("YOU: ");
            String user_input = sc.nextLine();

            if (user_input.equals("bye")){
                // End conversation
                System.out.print(reply("Pepper Jack loves Fraggle Rock!"));
                break;
            }
            else {
                System.out.print(reply(user_input));
            }
        }
    }
}
