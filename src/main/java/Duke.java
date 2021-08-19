import java.util.Arrays;
import java.util.Scanner;

public class Duke {


    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        Dialog greeting = Dialog.generate("greeting");


        // not sure if we are allowed to change the file name
        String logo =
                "     ___       __       __    ______  _______ \n" +
                "        /   \\     |  |     |  |  /      ||   ____|\n" +
                "       /  ^  \\    |  |     |  | |  ,----'|  |__   \n" +
                "      /  /_\\  \\   |  |     |  | |  |     |   __|  \n" +
                "     /  _____  \\  |  `----.|  | |  `----.|  |____ \n" +
                "    /__/     \\__\\ |_______||__|  \\______||_______|\n";

        greeting.add(logo);
        greeting.add("Hello! I'm Alice, your personal assistant");
        greeting.add("What can I do for you?");
        Dialog commandsList = Dialog.generate("commands");
        commandsList.add("This is the following commands, I can perform:\n");
        commandsList.add("1. 'add <task description>' - add the task to the list");
        commandsList.add("2. 'list' - show the current task list");
        commandsList.add("3. 'done <task index> - mark that task as done");
        commandsList.add("4. 'commands' - show this current command window");
        commandsList.add("5. 'bye' - end session");
        System.out.println(greeting);
        System.out.println(commandsList);
        System.out.print("> ");
        String input = sc.nextLine();
        TaskDialog list = (TaskDialog) TaskDialog.generate("list");
        while (!input.equals("bye")) {
            String[] commands = input.split(" ");
            switch(commands[0]) {
                case "list":
                    System.out.println(list);
                    break;
                case "add":
                    list.addTask(new Task(input.substring(("add ").length())));
                    break;
                case "done":
                    list.markTaskAsDone(Integer.parseInt(commands[1]) - 1);
                    break;
                case "commands":
                    System.out.println(commandsList);
                    break;
                default:
                    Dialog unknownCommand = Dialog.generate("unknownCommand");
                    unknownCommand.add("☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    System.out.println(unknownCommand);
                    break;
            }
            System.out.print("> ");
            input = sc.nextLine();
        }

        Dialog bye = Dialog.generate("bye");
        bye.add("Bye. Hope to see you again soon!");
        System.out.println(bye);
    }
}
