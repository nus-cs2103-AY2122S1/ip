import java.util.ArrayList;
import java.util.Scanner;
import java.util.function.BinaryOperator;
import java.util.function.UnaryOperator;

public class Duke {

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
        String BOX_LINE = "____________________________________________________________\n";
        String BOX_MIDDLE = "|  Cat:  ";
        String GREET = BOX_LINE + BOX_MIDDLE + "Meow-ning!\n" + BOX_LINE;
        String EXIT = BOX_LINE + BOX_MIDDLE + "See you again, meow!\n" + BOX_LINE;
        String TASK_NOT_EXIST = BOX_LINE + BOX_MIDDLE + "Meow? I can't find that task...\n" + BOX_LINE;
        String LIST = BOX_LINE + BOX_MIDDLE + "Here are the tasks in your list, meow:";
        String DEADLINE_NO_BY = BOX_LINE + BOX_MIDDLE + "Meow? There's no deadline... Please use /by params. \n" + BOX_LINE;
        String EVENT_NO_AT = BOX_LINE + BOX_MIDDLE + "Meow? There's no date... Please use /at params. \n" + BOX_LINE;
        String NOTHING_AFT_CMD = BOX_LINE + BOX_MIDDLE + "Meow? There's nothing after your command... Meow meow meow?\n" + BOX_LINE;

        BinaryOperator<String> AddTaskMessage = (x, y) -> BOX_LINE + BOX_MIDDLE + "Meow. I've added this task:\n   "
                + x + "\n" + "|  Now you have " + y + " tasks in the list." + "\n" + BOX_LINE;
        UnaryOperator<String> DoneTaskMessage =  (task) -> BOX_LINE + BOX_MIDDLE +
                "Good job, meow! Marked this task as done:\n   " + task + "\n" + BOX_LINE;
        UnaryOperator<String> EchoMessage = (msg) -> BOX_LINE + BOX_MIDDLE + msg + ", meow!\n" + BOX_LINE;

        ArrayList<Task> taskList = new ArrayList<>();

        System.out.println(LOGO + GREET);

        Scanner sc = new Scanner(System.in);

        String input = "";
        input = sc.nextLine();

        while (!input.equalsIgnoreCase("bye")) {

            String[] inputArr = input.trim().split("\\s+",2);
            String command = inputArr[0].toUpperCase();

            switch (command) {
                case "LIST":
                    System.out.println(LIST);
                    for (int i = 0; i < taskList.size(); i++) {
                        System.out.println("   " + (i + 1) + ". " + taskList.get(i));
                    }
                    System.out.println(BOX_LINE);
                    break;

                case "DONE":
                    if (!input.matches("done [0-9]+")) {
                        System.out.println(TASK_NOT_EXIST);
                        break;
                    }

                    int i = -1;
                    try {
                        i = Integer.parseInt(input.split(" ", 2)[1]) - 1;
                    } catch (NumberFormatException e) {
                        System.out.println(TASK_NOT_EXIST);
                        break;
                    }

                    if (i >= 0 && i < taskList.size()) {
                        Task t = taskList.get(i);
                        t.markAsDone();
                        System.out.println(DoneTaskMessage.apply(t.toString()));
                    } else {
                        System.out.println(TASK_NOT_EXIST);
                    }

                    break;

                case "TODO":
                    if (inputArr.length < 2) {
                        System.out.println(NOTHING_AFT_CMD);
                        break;
                    }
                    Task todo = new Todo(inputArr[1]);
                    taskList.add(todo);
                    System.out.println(AddTaskMessage.apply(todo.toString(), Integer.toString(taskList.size())));
                    break;

                case "DEADLINE":
                    if (inputArr.length < 2) {
                        System.out.println(NOTHING_AFT_CMD);
                        break;
                    }

                    inputArr = inputArr[1].split(" /by ", 2);
                    if (inputArr.length < 2) {
                        System.out.println(DEADLINE_NO_BY);
                        break;
                    }
                    Task deadline = new Deadline(inputArr[0], inputArr[1]);
                    taskList.add(deadline);
                    System.out.println(AddTaskMessage.apply(deadline.toString(), Integer.toString(taskList.size())));
                    break;

                case "EVENT":
                    if (inputArr.length < 2) {
                        System.out.println(NOTHING_AFT_CMD);
                        break;
                    }

                    inputArr = inputArr[1].split(" /at ", 2);
                    if (inputArr.length < 2) {
                        System.out.println(EVENT_NO_AT);
                        break;
                    }
                    Task event = new Event(inputArr[0], inputArr[1]);
                    taskList.add(event);
                    System.out.println(AddTaskMessage.apply(event.toString(), Integer.toString(taskList.size())));
                    break;

                default:
                    System.out.println(EchoMessage.apply(input));
                    break;
            }
            input = sc.nextLine();

        }

        System.out.println(EXIT);

        sc.close();



    }
}
