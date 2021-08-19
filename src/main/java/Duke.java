import java.util.*;
import java.util.logging.Logger;

public class Duke {
	public enum Command {
	    EXIT("bye"),
        LIST("list"),
        DELETE("delete"),
        COMPLETE("done"),
        TODO("todo"),
        DEADLINE("deadline"),
        EVENT("event");

	    public final String label;

	    private Command(String label) {
	        this.label = label;
        }

        public static Command valueOfLabel(String label) {
            for (Command e : values()) {
                if (e.label.equals(label)) {
                    return e;
                }
            }
            return null;
        }
    }

    public static void main(String[] args) throws DukeException {
        Logger logger = Logger.getLogger("Duke");
        boolean isActive = true;
        ArrayList<Task> list = new ArrayList<>(100);
        String welcomeMsg = "Hey, I'm Duke.\n"
                + "What's up?\n";
        String exitMsg = "Bye! Hope I helped!\n"
                + "See you next time :)\n";

        System.out.println(welcomeMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        Scanner sc = new Scanner(System.in);

        while (isActive) {
            String[] inputs = sc.nextLine().strip().split("\\s+", 2);
            String eventType = inputs[0];
            String remainder = inputs.length == 1 ? null : inputs[1];
            try {
                switch (Command.valueOfLabel(eventType)) { // Exit routine
                    case EXIT:
                        isActive = false;
                        break;
                    case LIST:
                        if (list.size() == 0) {
                            throw DukeException.emptyList();
                        }
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println(arrayToString(list));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    case DELETE: {
                        int index = Integer.parseInt(remainder) - 1;
                        if (index < 0 || index > list.size() - 1) {
                            throw DukeException.invalidIndex();
                        } else {
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("This item will be removed:\n" +
                                    list.get(index).toString() + "\n");
                            list.remove(index);
                            System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        }
                        break;
                    }
                    case COMPLETE: {
                        int index = Integer.parseInt(remainder) - 1;
                        if (index < 0 || index > list.size() - 1) {
                            throw DukeException.invalidIndex();
                        } else {
                            list.get(index).markAsDone();
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                            System.out.println("Awesome! I marked this as done:\n" +
                                    list.get(index).toString());
                            System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        }
                        break;
                    }
                    case TODO: {
                        if (remainder == null) {
                            throw DukeException.emptyDesc();
                        }
                        Todo event = new Todo(remainder);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                        list.add(event);
                        System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    }
                    case DEADLINE: {
                        if (remainder == null) {
                            throw DukeException.emptyDesc();
                        }
                        String[] tokens = remainder.split(" /by ", 2);
                        Deadline event = new Deadline(tokens[0], tokens[1]);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                        list.add(event);
                        System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    }
                    case EVENT: {
                        if (remainder == null) {
                            throw DukeException.emptyDesc();
                        }
                        String[] tokens = remainder.split(" /at ", 2);
                        Event event = new Event(tokens[0], tokens[1]);
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        System.out.println("Alright! I added this task: \n" + event.toString() + "\n");
                        list.add(event);
                        System.out.println(String.format("You have %d task(s) at the moment!\n", list.size()));
                        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                        break;
                    }
                    default:
                        throw DukeException.invalidCommand();
                        //System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                }
            } catch (DukeException e) {
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
                System.out.println(e.getMessage() + "\n");
                System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
            }
        }

        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
        System.out.println(exitMsg);
        System.out.println("~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~");
    }

    public static String arrayToString(ArrayList<Task> list) {
        String answer = "";
        int counter = 1;
        for (Task item : list) {
            answer += String.format("%d: %s\n", counter, item.toString());
            counter++;
        }
        return answer;
    }
}
