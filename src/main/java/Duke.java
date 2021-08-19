import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Duke {

    final static String BOX_LINE = "__________________________________________________________________________________\n";
    final static String BOX_MIDDLE = "|  Cat:  ";

    public static String Box(String message) {
        return Duke.BOX_LINE + Duke.BOX_MIDDLE + message + "\n" + Duke.BOX_LINE;
    }


    public static void main(String[] args) {
        // Credits to https://manytools.org/hacker-tools/convert-images-to-ascii-art/go/ and Mafumafu Line stickers
        String LOGO = "" +
                //"                              .......                                                               \n" +
                //"                       .,*#&@@@@@@@@@@@@@@&*                                                        \n" +
                //"                                  .,/(%&@&&@@@@@@@@@@@@@@@@@%* .,/(%&@@@@@@@@@@@@&.                 \n" +
                //"                                ,%@@@@@@@&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@,                 \n" +
                //"                            /@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&%#(##@@@@@*                 \n" +
                //"                        .(@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&(&&%&&&(@@@@@*                 \n" +
                "                     .,%@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&%%&(@@@@@,                 \n" +
                "              .(@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@(&#%@@@@&.                 \n" +
                "         *&@@@@@@@@@@@@@@@@@@@@@@@@@@&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@%/@@@@@/                  \n" +
                "     .@@@@@@@@@@&(*((@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@&&&&%.                  \n" +
                "      *@@@@@&*(#&%%%#@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@/&@@@@@@@@@@@@@@@@@@@&&&,                   \n" +
                "       ,@@@@@@(%%%&#@@@@@@@@&@@&@@@@@@@@@@@&@@@@@@@@@@@@#@(@@@@@@@@@@@@@@@@@@@@.                    \n" +
                "        .&@@@@@@*%&(@@@@@@@@@@@@@@@@@@@@@@@/@@@@@@@@@@@%%@@@(@@@@@@@@@@@@@&@@@@.                    \n" +
                "          ,@@@@@@@/%@@@@@@@@@@@@@@@@@@@@@@(&%@@@@@@@@@@(@@@@@@/@@@@@@@@@@@@&@@#@*                   \n" +
                "            *@@&&&&@@@@@@@@@@@@@@@@@@%@@@(@@(@@@@@@@@@&%@@@@@@@@/@@@@@@@@@@#@@@/.%.                 \n" +
                "              ,&&&@@@@@&@@@@@@@@@@@@@@@@&%@@#@@@@@@@@@/@@@@@@@@@@@/@@@@@@@@#&%@@,  ..               \n" +
                "                .%&@@@@#@@@@@@@@@@@@@@@@#@@@&%@@@@@@@%&@@%. ....... #@@@@@&#&& &&                   \n" +
                "                  %@@@@#@@@@@@@@@@@(@@@#@&@@@(@@@@@%@#@@@%**%%***/*@%&@@@@&(&&  (.                  \n" +
                "                  %&@@@@#@@@@@@%@@#.....,@@@@&&@@@%##@@@@@*((((/((/@&&@@@@&(@#   .                  \n" +
                "                 .(*@@@@%&@@@@@, .*//*@*/*@@@@(@(/@/@@@@@@@#*((#@&@@&&@@@&/%&.                      \n" +
                "                 ...%&&@@(@@@@@#@,*/(((((/@@@@@@@@@@@@@@@@@@@@(/#,&(#&%@&&.&                        \n" +
                "                    .&&%&@%@@@@&&@*((&,/@@@@@@@@@@@@@@@@@@@@@@@/#%%&#%.%@...                        \n" +
                "                      *#&%&&(@@@(@&&&&&&@@@@@@@@@@@@@@@@@@@@@@@@@@@@@#/.#                           \n" +
                "                       .%*%&&%(&(*@&&&&&@@@@@@@@@@#%@@@@@@@@@@@@@@@&..,,..                          \n" +
                //"                       ....(&&*(#,%#@%**/&@@@@@@@@@@@@@@@@@@@@#,./#@%#/*//(.                        \n" +
                //"                            ,&.,,,.,,,,,,,,,*@@@@@@@@@@@@&,.,,,,,,,,,,,,,,,,.                       \n" +
                //"                           .(***.,,,,,,,,,,,*(,... #@@%..,,,/*,,,,,,,,,,,,,..                       \n" +
                //"                           .,,,,,,.....(*,,,./&@@...,,.@@@#,,,,,,.,*.,,,.....                       \n" +
                //"                            ...,,,,(@%#&@@@@@@@@@*.,//(@@@@@@@@@@&@@/.,.....                        \n" +
                //"                            ...,,,.@@@@@@@@@@@@@@/&%%(*@@@@@@@@@@@&@%#&*....                        \n" +
                "\n";
        String GREET = Box("Meow-ning!");
        String EXIT = Box("See you again, meow!");

        /*
        String TASK_NOT_EXIST = BOX_LINE + BOX_MIDDLE + "Meow? I can't find that task...\n" + BOX_LINE;
        String DEADLINE_NO_BY = BOX_LINE + BOX_MIDDLE + "Meow? There's no deadline... Please use /by params. \n" + BOX_LINE;
        String EVENT_NO_AT = BOX_LINE + BOX_MIDDLE + "Meow? There's no date... Please use /at params. \n" + BOX_LINE;
        String NOTHING_AFT_CMD = BOX_LINE + BOX_MIDDLE + "Meow? There's nothing after your command... Meow meow meow?\n" + BOX_LINE;
        */

        BinaryOperator<String> AddTaskMessage = (x, y) -> Box("Meow. I've added this task:\n   " +
                x + "\n" + "|  Now you have " + y + " tasks in the list.");
        UnaryOperator<String> DoneTaskMessage =  (task) -> Box("Good job, meow! Marked this task as done:\n   " +
                task);
        BinaryOperator<String> DeleteTaskMessage =  (x, y) -> Box("Understood, meow! Deleted this task:\n   " +
                x + "\n" + "|  Now you have " + y + " tasks in the list.");
        UnaryOperator<String> ListMessage = (items) -> Box("Here are the tasks in your list, meow:" + items);
        UnaryOperator<String> EchoMessage = (msg) -> Box(msg + ", meow? Not a command...");

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(LOGO + GREET);

        Scanner sc = new Scanner(System.in);

        String input = "";
        input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String[] inputArr = input.trim().split("\\s+", 2);
            String command = inputArr[0].toUpperCase();

            try {
                switch (command) {
                    case "LIST":
                        if (taskList.size() == 0) {
                            throw new EmptyListException(command);
                        }

                        String s = "";
                        for (int i = 0; i < taskList.size(); i++) {
                            s += ("\n   " + (i + 1) + ". " + taskList.get(i));
                        }

                        System.out.println(ListMessage.apply(s));
                        break;

                    case "DONE":
                        if (taskList.size() == 0) {
                            throw new EmptyListException(command);
                        }

                        if (inputArr.length < 2) {
                            throw new NothingAfterCommand(command);
                        }

                        if (!inputArr[1].matches("[0-9]+")) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }

                        int i = -1;
                        try {
                            i = Integer.parseInt(inputArr[1].trim()) - 1;
                        } catch (NumberFormatException e) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }

                        if (i >= 0 && i < taskList.size()) {
                            Task t = taskList.get(i);
                            t.markAsDone();
                            System.out.println(DoneTaskMessage.apply(t.toString()));
                        } else {
                            throw new TaskIndexOutOfBounds(taskList.size());
                        }

                        break;

                    case "TODO":
                        if (inputArr.length < 2) {
                            throw new NothingAfterCommand(command);
                        }
                        Task todo = new Todo(inputArr[1]);
                        taskList.add(todo);
                        System.out.println(AddTaskMessage.apply(todo.toString(), Integer.toString(taskList.size())));
                        break;

                    case "DEADLINE":
                        if (inputArr.length < 2) {
                            throw new NothingAfterCommand(command);
                        }
                        if (!inputArr[1].contains("/by")) {
                            throw new MissingParams("by");
                        }
                        inputArr = inputArr[1].split(" /by ", 2);
                        if (inputArr.length < 2) {
                            throw new MissingArguments(command);
                        }
                        Task deadline = new Deadline(inputArr[0], inputArr[1]);
                        taskList.add(deadline);
                        System.out.println(AddTaskMessage.apply(deadline.toString(), Integer.toString(taskList.size())));
                        break;

                    case "EVENT":
                        if (inputArr.length < 2) {
                            throw new NothingAfterCommand(command);
                        }
                        if (!inputArr[1].contains("/at")) {
                            throw new MissingParams("at");
                        }
                        inputArr = inputArr[1].split(" /at ", 2);
                        if (inputArr.length < 2) {
                            throw new MissingArguments(command);
                        }
                        Task event = new Event(inputArr[0], inputArr[1]);
                        taskList.add(event);
                        System.out.println(AddTaskMessage.apply(event.toString(), Integer.toString(taskList.size())));
                        break;

                    case "DELETE":
                        if (taskList.size() == 0) {
                            throw new EmptyListException(command);
                        }

                        if (inputArr.length < 2) {
                            throw new NothingAfterCommand(command);
                        }

                        if (!inputArr[1].matches("[0-9]+")) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }

                        int j = -1;
                        try {
                            j = Integer.parseInt(inputArr[1].trim()) - 1;
                        } catch (NumberFormatException e) {
                            throw new TaskIndexNotInteger(taskList.size());
                        }

                        if (j >= 0 && j < taskList.size()) {
                            Task t = taskList.remove(j);
                            System.out.println(DeleteTaskMessage.apply(t.toString(), Integer.toString(taskList.size())));
                        } else {
                            throw new TaskIndexOutOfBounds(taskList.size());
                        }

                        break;

                    default:
                        System.out.println(EchoMessage.apply(input));
                        break;
                }

            } catch (DukeException e) {
                System.out.println(e.getMessage());
            } finally {
                input = sc.nextLine();
            }

        }

        System.out.println(EXIT);

        sc.close();
    }
}
