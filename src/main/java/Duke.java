import java.util.Scanner;
import java.lang.StringBuilder;
public class Duke {
    private enum TaskType {
        TODO,
        DEADLINE,
        EVENT
    }
    private static TaskList tList = new TaskList();
    private static String selfIntro = "Hello, I'm Duck\nWhat do you need?";

    private static String goodBye = "See ya next time! *quack* *quack* *quack*";

    private static String logo =
            "    __\n" +
            "___( o)>\n" +
            "\\ <_. )\n" +
            " `---'   hjw";

    private static void greet() {
        //System.out.println(logo);
        printLine(selfIntro);
    }

    private static void sayBye() {
        printLine(goodBye);
    }

    public static void printLine(String content) {
        System.out.println(
                "------------------------------------------------\n"
                + "Duck says:\n"
                + content
                + "\n------------------------------------------------"
            );

    }

    private static String addTask(String input, TaskType tType) throws DukeCommandException {
        String msg;
        String[] inputArr;
        String[] taskArr;
        switch (tType) {
            case TODO:
                inputArr = input.split(" ", 2);
                if(inputArr.length != 2 || inputArr[1].equals("")) {
                    throw new DukeCommandException("todo");
                }
                msg = tList.addTask(new ToDo(inputArr[1]));
                break;
                //Possible exception: no string after todo.
            case DEADLINE:
                inputArr = input.split(" ", 2);
                if (inputArr.length != 2) {
                    throw new DukeCommandException("deadline");
                }
                taskArr = inputArr[1].split(" /by ", 2);
                if (taskArr.length != 2 || taskArr[1].equals("")) {
                    throw new DukeCommandException("deadline");
                }
                msg = tList.addTask(new Deadline(taskArr[0], taskArr[1]));
                break;
            case EVENT:
                inputArr = input.split(" ", 2);
                if (inputArr.length != 2) {
                    throw new DukeCommandException("deadline");
                }
                taskArr = inputArr[1].split(" /at ", 2);
                if (taskArr.length != 2 || taskArr[1].equals("")) {
                    throw new DukeCommandException("deadline");
                }
                msg = tList.addTask(new Event(taskArr[0], taskArr[1]));
                break;
            default:
                msg = "If you see this, something has went terribly wrong";
        }
        return msg;
    }

    public static void runDuck() {
        Scanner sc = new Scanner(System.in);
        String userInput;
        Duke.greet();
        boolean bye = false;
        String[] inputArr;
        while (!bye) {
            userInput = sc.nextLine();
            inputArr = userInput.split(" ", 2);
            try {
                if (inputArr.length == 1) {
                    switch (inputArr[0]) {
                        case "bye":
                            bye = true;
                            Duke.sayBye();
                            break;
                        case "list":
                            printLine(tList.getTasks());
                            break;
                        case "todo":
                            throw new DukeCommandException("todo");
                        case "deadline":
                            throw new DukeCommandException("deadline");
                        case "done":
                            throw new DukeCommandException("done");
                        case "event":
                            throw new DukeCommandException("event");
                        default:
                            throw new DukeCommandException(inputArr[0]);
                    }
                } else {
                    switch (inputArr[0]) {
                        case "done":
                                printLine(
                                        tList.markComplete(
                                                Integer.parseInt(inputArr[1])
                                        )
                                );
                            break;
                        case "todo":
                            //todo: Currently all task adding may accept empty fields. Need to deal with that
                            printLine(addTask(userInput, TaskType.TODO));
                            break;
                        case "deadline":
                            printLine(addTask(userInput, TaskType.DEADLINE));
                            break;
                        case "event":
                            printLine(addTask(userInput, TaskType.EVENT));
                            break;
                        default:
                            throw new DukeCommandException(inputArr[0]);
                    }
                }
            }
            catch (DukeCommandException dce) {
                printLine(dce.getMsg());
            }
        }
    }

    public static void main(String[] args) {
        runDuck();
    }
}
