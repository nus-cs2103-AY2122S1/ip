import java.util.Scanner;
import java.util.LinkedList;
public class GreetingBot {

    private LinkedList<Task> myList = new LinkedList<>();

    public GreetingBot() {

    }

    public void startBot() {
        greet();
        store();
        exit();

    }


    private void greet() {
        String greetingMessage = "What's up! I'm Duke! What can I help you with?";
        System.out.println(greetingMessage);

    }

    private void store() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            try {
                String nextLine = inputScanner.nextLine();
                if (nextLine.equals("list")) {
                    list(myList);
                } else if (nextLine.equals("bye")) {
                    break;
                } else if (nextLine.startsWith("done")) {
                    String[] splitWords = nextLine.split(" ");
                    int taskIndex = Integer.parseInt(splitWords[1]);
                    setDone(taskIndex);
                } else if (nextLine.startsWith("delete")) {
                    String[] splitWords = nextLine.split(" ");
                    int taskIndex = Integer.parseInt(splitWords[1]);
                    deleteTask(taskIndex);
                } else {
                    newTask(nextLine);
                }
                    int totalTasks = myList.size();
                    System.out.println("Now you have " + totalTasks + " tasks in the list. You're welcome!");
                }
            catch (DukeException ex) {
                System.out.println(ex.toString());
                continue;
            }
        }
    }

    private void setDone(int taskNumber) throws DukeException {
        if (myList.size() > taskNumber) {
            throw new DukeException("Dude I don't think you have a list THAT long!");
        } else if (myList.get(taskNumber - 1).getDone()) {
            throw new DukeException("This task is already done man!");
        } else {
            myList.get(taskNumber - 1).setDone(true);
        }
    }

    private void deleteTask(int taskNumber) throws DukeException {
        if (myList.size() > taskNumber) {
            throw new DukeException("Dude I don't think you have a list THAT long!");
        } else {
            String infoOfTask = myList.get(taskNumber - 1).toString();
            myList.remove(taskNumber - 1);
            System.out.println("Noted. I've removed this task:\n" + infoOfTask);
        }
    }


    private void newTask(String nextLine) throws DukeException {
        if (nextLine.startsWith("todo")) {
            if (nextLine.replaceAll("\\s", "").length() == 4) {
                throw new DukeException("Seems like your todo task was incomplete!");
            } else {
                String[] splitLine = nextLine.split("todo");
                String title = splitLine[1];
                Task nextTask = new Todo(title);
                System.out.println("Got it. I've added this task:");
                myList.add(nextTask);
                System.out.println(nextTask.getInfo());
            }
        } else if (nextLine.startsWith("deadline")) {
            if (nextLine.replaceAll("\\s", "").length() == 8) {
                throw new DukeException("Seems like your deadline task was incomplete!");
            } else {
                String[] splitLine = nextLine.split("/by ");
                String date = splitLine[1];
                String title = splitLine[0].split("deadline")[1];
                Task nextTask = new Deadline(title, date);
                System.out.println("Got it. I've added this task:");
                myList.add(nextTask);
                System.out.println(nextTask.getInfo());
            }
        } else if (nextLine.startsWith("event")) {
            if (nextLine.replaceAll("\\s", "").length() == 5) {
                throw new DukeException("Seems like your event task was incomplete!");
            } else {
                String[] splitLine = nextLine.split("/at ");
                String date = splitLine[1];
                String title = splitLine[0].split("event")[1];
                Task nextTask = new Event(title, date);
                System.out.println("Got it. I've added this task:");
                myList.add(nextTask);
                System.out.println(nextTask.getInfo());
            }
        } else {
            throw new DukeException("Dude I don't understand what you're saying!");
        }
    }

    private void list(LinkedList<Task> myList) throws DukeException{
        if (myList.isEmpty()) {
            throw new DukeException("Yo! Your list looks empty to me!");
        }
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        while(counter < myList.size()) {
            System.out.println((counter + 1) + "." + myList.get(counter).toString());
            counter += 1;
        }
    }




    private void echo() {
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            String nextLine = inputScanner.nextLine();
            if (nextLine.equals("bye")) {
                break;
            } else {
                System.out.println(nextLine);
            }
        }
    }

    private void exit() {
        String exitMessage = "Leaving just like that? Fine. See you soon I guess.";
        System.out.println(exitMessage);
    }
}



