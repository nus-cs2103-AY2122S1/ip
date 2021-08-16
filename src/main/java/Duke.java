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

            try {
                // i wonder if those length checking exceptions can be replaced with a method.
                switch (str) {
                    case "bye":
                        System.out.println(bot.getExitMessage());
                        running = false;
                        break;
                    case "list":
                        System.out.println(bot.getListMessage());
                        input = sc.nextLine();
                        break;
                    case "done":
                        // check if a number is specified
                        if (inputs.length == 1) {
                            throw new DukeException("The task number cannot be empty you dum dum\n");
                        }
                        // check if the following string is a number
                        int index = Integer.parseInt(inputs[1]);
                        // check if the number is valid.
                        if (index <= 0 || index > bot.getTotalTasks()) {
                            throw new DukeException("Please enter a valid number\n");
                        }
                        String msg = bot.completeTask(index);
                        System.out.println(msg);
                        input = sc.nextLine();
                        break;
                    case "deadline":
                        if (inputs.length == 1) {
                            throw new DukeException("Please specify the deadline description\n");
                        }
                        String[] info = inputs[1].split("/by");
                        if (info.length == 1) {
                            throw new DukeException("Please specify the deadline\n");
                        }
                        System.out.println(bot.addDeadline(info[0], info[1]));
                        input = sc.nextLine();
                        break;
                    case "todo":
                        if (inputs.length == 1) {
                            throw new DukeException("Please specify the todo description\n");
                        }
                        System.out.println(bot.addTodo(inputs[1]));
                        input = sc.nextLine();
                        break;
                    case "event":
                        if (inputs.length == 1) {
                            throw new DukeException("Please specify the event description\n");
                        }
                        info = inputs[1].split("/at");
                        if (info.length == 1) {
                            throw new DukeException("Please specify the time\n");
                        }
                        System.out.println(bot.addEvent(info[0], info[1]));
                        input = sc.nextLine();
                        break;
                    case "delete":
                        if (inputs.length == 1) {
                            throw new DukeException("The task number to delete cannot be empty you dum dum\n");
                        }
                        // check if the following string is a number
                        index = Integer.parseInt(inputs[1]);
                        // check if the number is valid.
                        if (index <= 0 || index > bot.getTotalTasks()) {
                            throw new DukeException("Please enter a valid number\n");
                        }
                        System.out.println(bot.deleteTask(index));
                        input = sc.nextLine();
                        break;
                    default:
                        //maybe add a help command
                        System.out.println(bot.getCommand());
                        input = sc.nextLine();

                }
            } catch (DukeException e) {
                System.out.println(e.getMessage());
                input = sc.nextLine();
            } catch (NumberFormatException e) {
                System.out.println(ChatBot.line + "Please enter a valid number after done\n" + ChatBot.line);
                input = sc.nextLine();
            }
        }
        sc.close();

    }
}


