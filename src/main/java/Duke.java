import java.io.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;

public class Duke extends Application {
    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;

    public static void main(String[] args) {

        List<Task> arr = new ArrayList<>();
        int count = 0;
        String logo = " ____        _        \n"
                + "|  _ \\ _   _| | _____ \n"
                + "| | | | | | | |/ / _ \\\n"
                + "| |_| | |_| |   <  __/\n"
                + "|____/ \\__,_|_|\\_\\___|\n";
        String greeting = "____________________________________________________________\n" +
                "Hello! I'm Duke\n" +
                "What can I do for you?\n" +
                "____________________________________________________________\n";
        System.out.println(greeting);
        File folder = new File("data");
        folder.mkdir();
        // System.out.println(file.mkdir());
        File file = new File(folder, "duke.txt");
        try {
            file.createNewFile();
        } catch (IOException e) {
            System.out.println(e);
        }

        Scanner myObj = new Scanner(System.in);
        while (true) {
            String input = myObj.nextLine();
            if (input.equals("bye")) {
                String bye = "____________________________________________________________\n" +
                        "Bye. Hope to see you again soon!\n" +
                        "____________________________________________________________";
                System.out.println(bye);
                return;
            } else if (input.equals("list")) {
                System.out.println("____________________________________________________________");
                System.out.println("Here are the tasks in your list:");
/*
                for (int i = 0; i < count; i++) {
                    System.out.println("  " + (i + 1) + "." + arr.get(i).toString());
                }*/
                try {
                    Scanner reader = new Scanner(file);
                    int n = 0;
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        // System.out.println(data);
                        String[] parsed = data.split(" \\| ");

                        String type = parsed[0];
                        String description = parsed[2];
                        n++;

                        if (type.equals("T")) {
                            Todo todo = new Todo(description);
                            if (parsed[1].equals("1")) {
                                todo.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + todo.toString());
                        } else if (type.equals("D")) {
                            Deadline deadline = new Deadline(description, LocalDate.parse(parsed[3]));
                            if (parsed[1].equals("1")) {
                                deadline.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + deadline.toString());
                        } else if (type.equals("E")) {
                            Event event = new Event(description, LocalDate.parse(parsed[3]));
                            if (parsed[1].equals("1")) {
                                event.markAsDone();
                            }
                            System.out.println("  " + (n) + "." + event.toString());
                        }
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                System.out.println("____________________________________________________________");
            } else if (input.startsWith("done ")) {
                int task = Integer.parseInt(input.split(" ")[1]);
                int n = 0;
                // arr.get(task).markAsDone();
                try {
                    Scanner reader = new Scanner(file);
                    List<String> lines = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        n++;
                        String data = reader.nextLine();
                        if (task == n) {
                            String update = data.substring(0,4)+'1'+data.substring(5);
                            // System.out.println(update);
                            // System.out.println(data);
                            lines.add(update);
                        } else {
                            lines.add(data);
                        }
                        /*
                        System.out.println("____________________________________________________________");
                        System.out.println("Nice! I've marked this task as done:");
                        System.out.println("    " + arr.get(task).toString());
                        System.out.println("____________________________________________________________");
                         */
                        // mark as done
                    }

                    file.delete();
                    try {
                        file.createNewFile();
                        PrintWriter writer = new PrintWriter(file);
                        for (String line : lines) {
                            writer.println(line);
                        }
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("todo")) {
                try {
                    if (input.split(" ").length == 1) {
                        throw new DukeException("____________________________________________________________\n" +
                                "☹ OOPS!!! The description of a todo cannot be empty.\n" +
                                "____________________________________________________________");
                    }
                    String task = input.split(" ")[1];
                    Todo todo = new Todo(task);
                    // arr.add(new Todo(task));
                    try {
                        Scanner reader = new Scanner(file);
                        List<String> lines = new ArrayList<>();
                        while (reader.hasNextLine()) {
                            String data = reader.nextLine();
                            lines.add(data);
                        }

                        file.delete();
                        try {
                            file.createNewFile();
                            PrintWriter writer = new PrintWriter(file);
                            for (String line : lines) {
                                writer.println(line);
                            }
                            writer.println(todo.addToFile());
                            writer.close();
                        } catch (IOException e) {
                            System.out.println(e);
                        }
                    } catch (FileNotFoundException e) {
                        System.out.println(e);
                    }
                    count++;
                    String s = "";
                    if (count > 1 && count != 0) {
                        s = "s";
                    }
                    String reply = "____________________________________________________________\n" +
                            "Got it. I've added this task:\n  " +
                            todo.toString() + "\n" +
                            "Now you have " + count + " task" + s + " in the list.\n" +
                            "____________________________________________________________";
                    System.out.println(reply);
                } catch (DukeException e) {
                    System.out.println(e);
                }
            } else if (input.startsWith("deadline")) {
                String[] n = input.split(" ", 2)[1].split(" /by ");
                // arr.add(new Deadline(n[0], n[1]));
                String task = n[0];
                LocalDate time = LocalDate.parse(n[1]);
                Deadline deadline = new Deadline(task, time);
                try {
                    Scanner reader = new Scanner(file);
                    List<String> lines = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        lines.add(data);
                    }

                    file.delete();
                    try {
                        file.createNewFile();
                        PrintWriter writer = new PrintWriter(file);
                        for (String line : lines) {
                            writer.println(line);
                        }
                        writer.println(deadline.addToFile());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        deadline.toString() + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("event")) {
                String[] n = input.split(" ", 2)[1].split(" /at ");
                // arr.add(new Event(n[0], n[1]));
                String task = n[0];
                LocalDate time = LocalDate.parse(n[1]);
                Event event = new Event(task, time);
                try {
                    Scanner reader = new Scanner(file);
                    List<String> lines = new ArrayList<>();
                    while (reader.hasNextLine()) {
                        String data = reader.nextLine();
                        lines.add(data);
                    }

                    file.delete();
                    try {
                        file.createNewFile();
                        PrintWriter writer = new PrintWriter(file);
                        for (String line : lines) {
                            writer.println(line);
                        }
                        writer.println(event.addToFile());
                        writer.close();
                    } catch (IOException e) {
                        System.out.println(e);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println(e);
                }
                count++;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Got it. I've added this task:\n  " +
                        event.toString() + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else if (input.startsWith("delete")) {
                int pos = Integer.parseInt(input.split(" ")[1]);
                Task task = arr.get(pos-1);
                arr.remove(pos-1);
                count--;
                String s = "";
                if (count > 1 && count != 0) {
                    s = "s";
                }
                String reply = "____________________________________________________________\n" +
                        "Noted. I've removed this task:\n  " +
                        task + "\n" +
                        "Now you have " + count + " task" + s + " in the list.\n" +
                        "____________________________________________________________";
                System.out.println(reply);
            } else {
                System.out.println(new DukeException("____________________________________________________________\n" +
                        "☹ OOPS!!! I'm sorry, but I don't know what that means :-(\n" +
                        "____________________________________________________________"));
            }
        }
    }

    @Override
    public void start(Stage stage) {
        //Step 1. Setting up required components

        //The container for the content of the chat to scroll.
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();

        //Step 2. Formatting the window to look as expected
        stage.setTitle("Duke");
        stage.setResizable(false);
        stage.setMinHeight(600.0);
        stage.setMinWidth(400.0);

        mainLayout.setPrefSize(400.0, 600.0);

        scrollPane.setPrefSize(385, 535);
        scrollPane.setHbarPolicy(ScrollPane.ScrollBarPolicy.NEVER);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        scrollPane.setVvalue(1.0);
        scrollPane.setFitToWidth(true);

        // You will need to import `javafx.scene.layout.Region` for this.
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        // more code to be added here later

        // more code to be added here later
    }
}


