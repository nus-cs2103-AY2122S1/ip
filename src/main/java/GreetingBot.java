/**
 * Code for the main skeleton of the Bot. When Duke is run, An instance of this class is created and used to run it.
 *
 */


import java.io.*;
import java.util.Scanner;
import java.util.LinkedList;

public class GreetingBot {

    /**
     * The list of tasks that belongs to the bot.
     */
    private LinkedList<Task> myList = new LinkedList<>();

    /**
     * Constructor that takes in nothing.
     */
    public GreetingBot() {

    }

    /**
     * Method that runs the main functions of the bot.
     *
     */
    public void startBot() {
        greet();
        store();
        exit();

    }


    /**
     * Method that greets the user when bot is first run.
     *
     */
    private void greet() {
        String greetingMessage = "What's up! I'm Duke! What can I help you with?";
        System.out.println(greetingMessage);

    }

    /**
     * Method that reads input and decides what method to call to deal with the input.
     */
    private void store() {
        loadData();
        Scanner inputScanner = new Scanner(System.in);
        while (true) {
            try {
                String nextLine = inputScanner.nextLine();
                if (nextLine.startsWith("list")) {
                    list(myList);
                } else if (nextLine.equals("bye")) {
                    break;
                } else if (nextLine.startsWith("done")) {
                    setDone(nextLine);
                } else if (nextLine.startsWith("delete")) {
                    deleteTask(nextLine);
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

    /**
     * load data from list file in data folder.
     */
    @SuppressWarnings("unchecked")
    private void loadData() {
            // Create data folder if it does not exist.
            File dataFolder = new File("./data/");
            if (!dataFolder.exists()) {
                dataFolder.mkdir();
            }

        try {
            File dataFile = new File("./data/list.txt");
            if (dataFile.createNewFile()) {
                // If dataFile does not exist (has not been created before)
                System.out.println("Seems like a new list has been created!");
            } else {
                // dataFile already has been created before
                System.out.println("Let's load up your list!");
                FileInputStream listIn = new FileInputStream("./data/list.txt");
                ObjectInputStream in = new ObjectInputStream(listIn);
                myList = (LinkedList<Task>) in.readObject();
            }
        } catch (IOException | ClassNotFoundException error) {
            System.out.println("Something went wrong with loading a list up!");
        }
    }

    /**
     * Method that is called to set a task to done.
     * @param nextLine
     * @throws DukeException
     */
    private void setDone(String nextLine) throws DukeException {
        String[] splitWords = nextLine.split(" ");
        if (splitWords.length == 1 && splitWords[0].equals("done")) {
            throw new DukeException("Dude I don't think you told me which task you're talking about!");
        } else if (!splitWords[0].equals("done")) {
            throw new DukeException("Dude, I don't understand your instructions!");
        }
        try {
            int taskNumber = Integer.parseInt(splitWords[1]);
            if (taskNumber > myList.size()) {
                throw new DukeException("Dude I don't think you have a list THAT long!");
            } else if (myList.get(taskNumber - 1).getDone()) {
                throw new DukeException("This task is already done man!");
            } else {
                myList.get(taskNumber - 1).setDone(true);
            }
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Woah, enter the task number properly..");
        }
    }

    /**
     * Method that is called to delete a task from the list.
     * @param nextLine
     * @throws DukeException
     */
    private void deleteTask(String nextLine) throws DukeException {
        String[] splitWords = nextLine.split(" ");
        if (splitWords.length == 1 && splitWords[0].equals("delete")) {
            throw new DukeException("Dude I don't think you told me which task you're talking about!");
        } else if (!splitWords[0].equals("delete")) {
            throw new DukeException("Dude, I don't understand your instructions!");
        }
        try {
            int taskNumber = Integer.parseInt(splitWords[1]);
            if (taskNumber > myList.size()) {
                throw new DukeException("Dude I don't think you have a list THAT long!");
            } else {
                String infoOfTask = myList.get(taskNumber - 1).toString();
                myList.remove(taskNumber - 1);
                System.out.println("Noted. I've removed this task:\n" + infoOfTask);
            }
        }
        catch (NumberFormatException ex) {
            throw new DukeException("Woah, enter the task number properly..");
        }
    }


    /**
     * method that is called to create a new Task.
     * @param nextLine
     * @throws DukeException
     */
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
                System.out.println(nextTask.toString());
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
                System.out.println(nextTask.toString());
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
                System.out.println(nextTask.toString());
            }
        } else {
            throw new DukeException("Dude I don't understand what you're saying!");
        }
    }

    /**
     * Method that is called to display the list.
     * @param myList
     * @throws DukeException
     */
    private void list(LinkedList<Task> myList) throws DukeException {
        if (myList.isEmpty()) {
            throw new DukeException("Yo! Your list looks empty to me!");
        }
        System.out.println("Here are the tasks in your list:");
        int counter = 0;
        while(counter < myList.size()) {
            System.out.println((counter + 1) + ". " + myList.get(counter).toString());
            counter += 1;
        }
    }


    /**
     * Method to echo the input by the user.
     */
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

    /**
     * Method to print the exit message and stop the program.
     */
    private void exit() {
        updateData();
        String exitMessage = "Leaving just like that? Fine. See you soon I guess.";
        System.out.println(exitMessage);
    }

    /**
     * Update data in list file.
     */
    private void updateData() {
        try {
            FileOutputStream fileOut = new FileOutputStream("./data/list.txt");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(myList);
            out.close();
            fileOut.close();
        } catch (IOException error) {
            System.out.println("Something went wrong when I tried to save your list :(");
        }
    }
}



