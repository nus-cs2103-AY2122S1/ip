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
        return y_border + "[PEPPER JACK] " + str + "\n" + y_border;
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

        ArrayList<Task> lst = new ArrayList<>();
        Scanner sc = new Scanner(System.in);

        while(true) {
            // Get user input
            System.out.print("[YOU] ");
            String user_input = sc.nextLine();
            String[] user_input_split = user_input.split(" ", 2);
            String command = user_input_split[0];

            if (user_input.equals("bye")){
                // End conversation
                System.out.print(reply("Pepper Jack loves Fraggle Rock!"));
                break;
            }
            else if (user_input.equals("list")){
                // Show list
                String lst_display = "\n\n";

                for (int i = 0; i < lst.size(); i++){
                    lst_display = lst_display + String.format("\t%d. %s\n", i + 1, lst.get(i));
                }
                System.out.print(reply(lst_display));
            }
            else if (command.equals("done")) {
                // Mark task as done
                int index = Integer.parseInt(user_input_split[1]) - 1;
                Task t = lst.get(index);
                t.setDone();
                System.out.print(reply("Noice! Pepper Jack marked this task as done:\n\t" + t));
            }
            else {
                // Add user input
                lst.add(new Task(user_input));
                System.out.print(reply("added: " + user_input));
            }
        }
    }
}
