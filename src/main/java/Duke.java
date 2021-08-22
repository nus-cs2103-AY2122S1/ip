import java.io.*;

public class Duke {
    public static List todoList;
    public static void greet() {
        String message = "Hello! I'm Duke\nWhat can I do for you?";
        System.out.println(message);
    }

    public static void bye() {
        System.out.println("Bye. Hope to see you again soon!");
    }

    public static void main(String[] args)
            throws IOException
    {
        try {
            File myObj = new File("filename.txt");
            if (myObj.createNewFile()) {
                System.out.println("New File created: " + myObj.getName());
                Duke.todoList = new List();
            } else {
                System.out.println("Data exists");
                FileWriter myWriter = new FileWriter("filename.txt");
                myWriter.write("1");
                myWriter.write(System.lineSeparator());
                myWriter.write("2");
                myWriter.write(System.lineSeparator());
                myWriter.write("3");
                myWriter.close();
                Duke.todoList = new List();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

        greet();

        BufferedReader reader = new BufferedReader(
                new InputStreamReader(System.in));
        String input;

        while (!(input = reader.readLine()).equals("bye")) {
            Duke.todoList.addTask(input);
        }
        bye();
    }
}
