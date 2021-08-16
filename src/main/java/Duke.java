import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {

        ChatBot bot = new ChatBot();
        System.out.println(bot.getStartMessage());

        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Boolean running = true;


        while (running) {

            String[] inputs = input.split(" ");
            // might want to change this to switch if if-else get too much
            String str = inputs[0];

            switch (str)
            {
                case "bye":
                    System.out.println(bot.getExitMessage());
                    running = false;
                    break;
                case "list":
                    System.out.println(bot.getListMessage());
                    input = sc.nextLine();
                    break;
                case "done":
                    String msg = bot.completeTask(Integer.parseInt(inputs[1]));
                    System.out.println(msg);
                    input = sc.nextLine();
                    break;
                default:
                    String message = bot.addItems(input);
                    System.out.println(message);
                    input = sc.nextLine();

            }
        }
        sc.close();


    }
}


