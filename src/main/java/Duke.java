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
                case "deadline":
                    String name = "";
                    String deadline = "";
                    // should not use '+' in for loop for strings
                    for (int i = 1; i < inputs.length; i++) {
                        if (inputs[i].equals("/by")) {
                            for (int j = i + 1; j < inputs.length; j++) {
                                deadline = deadline + " " + inputs[j];
                            }
                            break;
                        }
                        name = name + " " + inputs[i];
                    }
                    System.out.println(bot.addDeadline(name, deadline));
                    input = sc.nextLine();
                    break;
                case "todo":
                    String todo = "";
                    for (int i = 1; i < inputs.length; i++) {
                        todo = todo + " " + inputs[i];
                    }
                    System.out.println(bot.addTodo(todo));
                    input = sc.nextLine();
                    break;
                case "event":
                    String event = "";
                    String time = "";
                    // should not use '+' in for loop for strings
                    for (int i = 1; i < inputs.length; i++) {
                        if (inputs[i].equals("/at")) {
                            for (int j = i + 1; j < inputs.length; j++) {
                                time = time + " " + inputs[j];
                            }
                            break;
                        }
                        event = event + " " + inputs[i];
                    }
                    System.out.println(bot.addEvent(event, time));
                    input = sc.nextLine();
                    break;
                default:
                    System.out.println(bot.getCommand());
                    input = sc.nextLine();

            }
        }
        sc.close();


    }
}


