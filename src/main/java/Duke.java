import java.util.Scanner;
import java.util.ArrayList;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;

public class Duke {
    public enum Type {
        TODO,
        DEADLINE,
        EVENT
    }

    public static void main(String[] args) {
        Scanner myObj = new Scanner(System.in);
        boolean exit = false;
        ArrayList<Task> tasks = new ArrayList<>();

        try {
            File data_file = new File("../ip/src/main/data.txt");
            // if data file already exists, load the file's data into tasks, else create a new data file
            if (!data_file.createNewFile()) {
                Scanner dataScanner = new Scanner(data_file);
                while (dataScanner.hasNextLine()) {
                    String[] arr = dataScanner.nextLine().split(",");
                    // if task is a Todo
                    if (arr[0].equals("T")) {
                        tasks.add(new Todo(arr[2]));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    } else if (arr[0].equals("D")) {
                        tasks.add(new Deadline(arr[2], arr[3]));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    } else {
                        tasks.add(new Event(arr[2], arr[3]));
                        // if task is done, mark as done
                        if (arr[1].equals("1")) {
                            tasks.get(tasks.size() - 1).markAsDone();
                        }
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        System.out.println("____________________________________________________________");
        System.out.println("Hello! I'm Duke");
        System.out.println("What can I do for you?");
        System.out.println("____________________________________________________________");

        while (!exit) {
            String userInput = myObj.nextLine();

            if (userInput.equals("bye")) {
                System.out.println("____________________________________________________________");
                System.out.println("Bye. Hope to see you again soon!");
                System.out.println("____________________________________________________________");
                exit = true;
            } else if (userInput.equals("list")) {
                System.out.println("____________________________________________________________");
                for (int i = 0; i < tasks.size(); i++) {
                    System.out.println((i + 1) + "." + tasks.get(i).getTask());
                }
                System.out.println("____________________________________________________________");
            } else if (isDoneCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(5));
                System.out.println("____________________________________________________________");
                if (tasks.get(index - 1) != null) {
                    tasks.get(index - 1).markAsDone();
                    markAsDoneData(index - 1);
                    System.out.println("Nice! I've marked this task as done: ");
                    System.out.println("  " + tasks.get(index - 1).getTask());
                } else {
                    System.out.println("There is no such task.");
                }
                System.out.println("____________________________________________________________");
            } else if (isRemoveCall(userInput)) {
                int index = Integer.parseInt(userInput.substring(7));
                System.out.println("____________________________________________________________");
                if (tasks.get(index - 1) != null) {
                    System.out.println("Noted. I've removed this task: ");
                    removeData(index - 1);
                    System.out.println("  " + tasks.get(index - 1).getTask());
                    tasks.remove(index - 1);
                    System.out.println("Now you have " + tasks.size() + " tasks in the list.");
                } else {
                    System.out.println("There is no such task.");
                }
            } else {
                System.out.println("____________________________________________________________");
                try {
                    if (userInput.startsWith("todo")) {
                        parseInput(userInput, tasks, Type.TODO);
                    } else if (userInput.startsWith("deadline")) {
                        parseInput(userInput, tasks, Type.DEADLINE);
                    } else if (userInput.startsWith("event")) {
                        parseInput(userInput, tasks, Type.EVENT);
                    } else {
                        throw new IllegalArgumentException(" ☹ OOPS!!! I'm sorry, but I don't know what that means :-(");
                    }

                } catch (IllegalArgumentException e) {
                    System.out.println(e.getMessage());
                }
                System.out.println("____________________________________________________________");
            }
        }
    }

    public static boolean isDoneCall (String strNum) {
        if (strNum == null) {
            return false;
        }
        if (strNum.length() < 6) {
            return false;
        }
        try {
            int d = Integer.parseInt(strNum.substring(5));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!strNum.startsWith("done ")) {
            return false;
        }
        return true;
    }

    public static boolean isRemoveCall (String str) {
        if (str == null) {
            return false;
        }
        if (str.length() < 8) {
            return false;
        }
        try {
            int d = Integer.parseInt(str.substring(7));
        } catch (NumberFormatException nfe) {
            return false;
        }
        if (!str.startsWith("remove ")) {
            return false;
        }
        return true;
    }

    public static void parseInput(String userInput, ArrayList<Task> tasks, Type type) throws IllegalArgumentException {
        if (type == Type.TODO) {
            if (userInput.substring(4).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a todo cannot be empty.");
            }
            tasks.add(new Todo(userInput.substring(5)));
            newTaskToDataFile(userInput.substring(5), Type.TODO, "");
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else if (type == Type.DEADLINE) {
            if (userInput.substring(8).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of a deadline cannot be empty.");
            }
            int slash = userInput.indexOf("/by");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /by");
            }
            tasks.add(new Deadline(userInput.substring(9, slash - 1), userInput.substring(slash + 4)));
            newTaskToDataFile(userInput.substring(9, slash - 1), Type.DEADLINE, userInput.substring(slash + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        } else {
            if (userInput.substring(5).trim().isEmpty()) {
                throw new IllegalArgumentException(" ☹ OOPS!!! The description of an event cannot be empty.");
            }
            int slash = userInput.indexOf("/at");
            if (slash == -1) {
                throw new IllegalArgumentException(" Please set a deadline by adding /at");
            }
            tasks.add(new Event(userInput.substring(6, slash - 1), userInput.substring(slash + 4)));
            newTaskToDataFile(userInput.substring(6, slash - 1), Type.EVENT, userInput.substring(slash + 4));
            System.out.println("Got it. I've added this task:");
            System.out.println("  " + tasks.get(tasks.size() - 1).getTask());
            System.out.println("Now you have " + tasks.size() + " tasks in the list.");
        }
    }

    public static void newTaskToDataFile(String taskName, Type type, String time) {
        try {
            FileWriter dataWriter = new FileWriter("../ip/src/main/data.txt", true);
            if (type == Type.TODO) {
                dataWriter.write("T,0," + taskName + ", \n");
                dataWriter.close();
            } else if (type == Type.DEADLINE) {
                dataWriter.write("D,0," + taskName + "," + time + "\n");
                dataWriter.close();
            } else {
                dataWriter.write("E,0," + taskName + "," + time + "\n");
                dataWriter.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void markAsDoneData(int number) {
        File fileToBeModified = new File("../ip/src/main/data.txt");
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current == number) {
                    String[] arr = nextLine.split(",");
                    newContent = newContent + arr[0] + ",1," + arr[2] + "," + arr[3] + "\n";
                } else {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }

    public static void removeData(int number) {
        File fileToBeModified = new File("../ip/src/main/data.txt");
        String newContent = "";
        try {
            Scanner scanner = new Scanner(fileToBeModified);
            int current = 0;
            while (scanner.hasNextLine()) {
                String nextLine = scanner.nextLine();
                if (current != number) {
                    newContent = newContent + nextLine + "\n";
                }
                current++;
            }
            FileWriter writer = new FileWriter(fileToBeModified);
            writer.write(newContent);

            try {
                //Closing the resources
                scanner.close();
                writer.close();
            }
            catch (IOException e) {
                e.printStackTrace();
            }

        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
