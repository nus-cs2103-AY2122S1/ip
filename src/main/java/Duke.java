import java.util.Scanner;


public class Duke {

    public static void main(String[] args) {

        ChatBot bot = new ChatBot();
        System.out.println(bot.getStartMessage());

        // create object of Scanner to take inputs
        Scanner sc = new Scanner(System.in);
        String input = sc.nextLine();
        Boolean running = true;
        String commandList = "unknown command!";


        while (running) {

            String[] inputs = input.split(" ", 2);
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
                case "deadline":
                    String[] info = inputs[1].split("/by");
                    System.out.println(bot.addDeadline(info[0], info[1]));
                    input = sc.nextLine();
                    break;
                case "todo":
                    System.out.println(bot.addTodo(inputs[1]));
                    input = sc.nextLine();
                    break;
                case "event":
                    info = inputs[1].split("/at");
                    System.out.println(bot.addEvent(info[0], info[1]));
                    input = sc.nextLine();
                    break;
                default:
                    //maybe add a help command
                    System.out.println(bot.getCommand());
                    input = sc.nextLine();

            }
        }
        sc.close();


    }
}


