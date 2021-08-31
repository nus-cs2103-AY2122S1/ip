package duke.core;

import java.util.ArrayList;
import java.util.Scanner;

import duke.databse.Database;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.scene.layout.Region;




public class Duke extends Application {

    private ArrayList<Task> taskList;
    private Database database;
    private UI ui = new UI();

    private ScrollPane scrollPane;
    private VBox dialogContainer;
    private TextField userInput;
    private Button sendButton;
    private Scene scene;


    /**
     * Constructor
     * @param filePath
     */
    public Duke(String filePath) {
        database = new Database(filePath);
    }

    /**
     * Constructor
     */
    public Duke() {
    }

    @Override
    public void start(Stage stage) {
        scrollPane = new ScrollPane();
        dialogContainer = new VBox();
        scrollPane.setContent(dialogContainer);

        userInput = new TextField();
        sendButton = new Button("Send");

        AnchorPane mainLayout = new AnchorPane();
        mainLayout.getChildren().addAll(scrollPane, userInput, sendButton);

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
        dialogContainer.setPrefHeight(Region.USE_COMPUTED_SIZE);

        userInput.setPrefWidth(325.0);

        sendButton.setPrefWidth(55.0);

        AnchorPane.setTopAnchor(scrollPane, 1.0);

        AnchorPane.setBottomAnchor(sendButton, 1.0);
        AnchorPane.setRightAnchor(sendButton, 1.0);

        AnchorPane.setLeftAnchor(userInput , 1.0);
        AnchorPane.setBottomAnchor(userInput, 1.0);

        sendButton.setOnMouseClicked((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        userInput.setOnAction((event) -> {
            dialogContainer.getChildren().add(getDialogLabel(userInput.getText()));
            userInput.clear();
        });

        scene = new Scene(mainLayout);

        stage.setScene(scene);
        stage.show();
    }

    /**
     * Iteration 1:
     * Creates a label with the specified text and adds it to the dialog container.
     * @param text String containing text to add
     * @return a label with the specified text that has word wrap enabled.
     */
    private Label getDialogLabel(String text) {
        // You will need to import `javafx.scene.control.Label`.
        Label textToAdd = new Label(text);
        textToAdd.setWrapText(true);

        return textToAdd;
    }


    /**
     * search for a task
     * @param tasklist
     * @param keyword
     * @return
     */
    public ArrayList<Task> searchTask(ArrayList<Task> tasklist, String keyword) {
        ArrayList<Task> result = new ArrayList<>();
        for (Task task: tasklist) {
            if (task.searchKeyword(keyword)) {
                result.add(task);
            }
        }
        return result;
    }

    public String getResponse(String input) {
        if (input.equals("")) {
            return ui.logo + "\n" + "\n" + ui.greeting + "\n";
        }
        database = new Database("todoList.txt");
        ArrayList<Task> task = database.getData();
        int taskNum = task.size();
        String indentation = "       ";
        final String LIST = "list";
        final String BYE = "bye";
        final String DONE = "done";
        final String DELETE = "delete";
        final String FIND = "find";
        String response = "";

        String keywords = input;
        String[] keyword = keywords.split(" ");

        switch(keyword[0]) {
        case FIND:
            ArrayList<Task> result = searchTask(task, keyword[1]);
            try {
                for (int i = 0; i < result.size(); i++) {
                    String s = indentation;
                    String s2 = "";

                    if (result.get(i) instanceof Todo) {
                        s += (i + 1) + "." + " [T]";
                        s2 = result.get(i).getName();
                    } else if (result.get(i) instanceof Deadline) {
                        s += (i + 1) + "." + " [D]";
                        s2 = result.get(i).getName() + " " + "(" + " "
                                + ((Deadline) result.get(i)).getTime() + " )";
                    } else if (result.get(i) instanceof Event) {
                        s += (i + 1) + "." + " [E]";
                        s2 = result.get(i).getName() + " " + "(" + " " + ((Event) result.get(i)).getTime() + " )";
                    }
                    if (result.get(i).isDone() == false) {
                        s += "[ ] " + s2 + "\n";
                        response += s;
                    } else {
                        s += "[X] " + s2;
                        response += s + "\n";
                    }
                }
                return response;
            } catch (IndexOutOfBoundsException e) {
                return indentation + e.getMessage();
            }
        case LIST:
            try {
                for (int i = 0; i < task.size(); i++) {
                    String s = indentation;
                    String s2 = "";

                    if (task.get(i) instanceof Todo) {
                        s += (i + 1) + "." + " [T]";
                        s2 = task.get(i).getName();
                    } else if (task.get(i) instanceof Deadline) {
                        s += (i + 1) + "." + " [D]";
                        s2 = task.get(i).getName() + " " + "(" + " " + ((Deadline) task.get(i)).getTime() + " )";
                    } else if (task.get(i) instanceof Event) {
                        s += (i + 1) + "." + " [E]";
                        s2 = task.get(i).getName() + " " + "(" + " " + ((Event) task.get(i)).getTime() + " )";
                    }
                    if (task.get(i).isDone() == false) {
                        s += "[ ] " + s2;
                        response += s + "\n";
                    } else {
                        s += "[X] " + s2;
                        response += s + "\n";
                    }
                }
                return response;

            } catch (IndexOutOfBoundsException e) {
                return indentation + e.getMessage();
            }

        case DONE:
            try {
                Integer num = Integer.valueOf(keyword[1]) - 1;
                task.get(num).setDone(true);
                database.updateData(task.get(num), num + 1);
                String s = indentation;
                String s2 = "";
                if (task.get(num) instanceof Todo) {

                    s += (task.get(num).getIndex() + 1) + "." + " [T]";
                    s2 = task.get(num).getName();
                } else if (task.get(num) instanceof Deadline) {
                    s += (task.get(num).getIndex() + 1) + "." + " [D]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
                } else if (task.get(num) instanceof Event) {
                    s += (task.get(num).getIndex() + 1) + "." + " [E]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
                }

                s += "[X]" + s2;
                response += ui.done_message + "\n";
                response += s;
                return response;


            } catch (NullPointerException e) {
                return ui.no_task_message;
            } catch (IndexOutOfBoundsException e) {
                return ui.task_num_message;
            }
        case DELETE:
            try {
                Integer num = Integer.valueOf(keyword[1]) - 1;

                database.deleteData(num + 1);
                String s = indentation + "     ";
                String s2 = "";

                if (task.get(num) instanceof Todo) {
                    s += (task.get(num).getIndex() + 1) + "." + " [T]";
                    s2 = task.get(num).getName();
                } else if (task.get(num) instanceof Deadline) {
                    s += (task.get(num).getIndex() + 1) + "." + " [D]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
                } else if (task.get(num) instanceof Event) {
                    s += (task.get(num).getIndex() + 1) + "." + " [E]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
                }
                if (task.get(num).isDone() == false) {
                    s += "[ ]" + s2;
                } else {
                    s += "[X]" + s2;
                }

                task.remove(num.intValue());
                response += s + "\n";
                response += indentation + "Now you have " + task.size() + " " + "tasks in the list.%n" + "\n";

                return response;
            } catch (NullPointerException e) {
                return ui.no_task_message;
            } catch (IndexOutOfBoundsException e) {
                return ui.no_task_message;
            }
        case BYE:
            return ui.bye_message;
        default:
            try {
                switch (keyword[0]) {
                case "deadline":
                    if (keyword.length == 1) {
                        return ui.lack_content_message;
                    }
                    String taskname_ddl = "";
                    String tasktime_ddl = "";
                    boolean timepart_ddl = false;
                    for (int i = 1; i < keyword.length; i++) {
                        if (keyword[i].startsWith("/")) {
                            timepart_ddl = true;
                            tasktime_ddl = keyword[i].substring(1) + ":";
                        } else if (timepart_ddl) {
                            tasktime_ddl += " " + keyword[i];
                        } else {
                            if (keyword[i + 1].startsWith("/")) {
                                taskname_ddl += keyword[i];
                            } else {
                                taskname_ddl += keyword[i] + " ";
                            }
                        }
                    }
                    if (tasktime_ddl.equals("")) {
                        return ui.lack_content_message;
                    }
                    Task ddl = new Deadline(taskname_ddl, false, tasktime_ddl);
                    task.add(ddl);
                    database.writeToDatabase(ddl);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [D][ ] " + taskname_ddl + " ( " + tasktime_ddl + " )\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                case "todo":
                    if (keyword.length == 1) {
                        return ui.lack_content_message;
                    }
                    String taskname_todo = "";
                    for (int i = 1; i < keyword.length; i++) {
                        if (i == keyword.length - 1) {
                            taskname_todo += keyword[i];
                        } else {
                            taskname_todo += keyword[i] + " ";
                        }

                    }
                    Task todo = new Todo(taskname_todo, false);

                    task.add(todo);
                    database.writeToDatabase(todo);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [T][ ] " + taskname_todo + "\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                case "event":
                    if (keyword.length == 1) {
                        return ui.lack_content_message;
                    }

                    String taskname_event = "";
                    String tasktime_event = "";
                    boolean timepart_event = false;
                    for (int i = 1; i < keyword.length; i++) {
                        if (keyword[i].startsWith("/")) {
                            timepart_event = true;
                            tasktime_event = keyword[i].substring(1) + ":";
                        } else if (timepart_event) {
                            tasktime_event += " " + keyword[i];
                        } else {
                            if (keyword[i + 1].startsWith("/")) {
                                taskname_event += keyword[i];
                            } else {
                                taskname_event += keyword[i] + " ";
                            }

                        }
                    }
                    if (tasktime_event.equals("")) {
                        return ui.lack_content_message;
                    }
                    Task event = new Event(taskname_event, false, tasktime_event);
                    task.add(event);
                    database.writeToDatabase(event);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [E][ ] " + taskname_event + " ( " + tasktime_event + " )\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";

                    return response;
                default:
                    return ui.unknown_message;
            }

            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.index_message;
            }
        }
    }

    /**
     * start the program
     */
    public void run() {

        ArrayList<Task> task = database.getData();
        int taskNum = task.size();
        String indentation = "       ";
        final String LIST = "list";
        final String BYE = "bye";
        final String DONE = "done";
        final String DELETE = "delete";
        final String FIND = "find";


        boolean isEnd = false;
        System.out.println(ui.logo);
        System.out.println(ui.line);
        System.out.println(ui.greeting);
        System.out.println(ui.line);
        Scanner scanner = new Scanner(System.in);


        while (!isEnd) {
            String keywords = scanner.nextLine();
            String[] keyword = keywords.split(" ");


            switch(keyword[0]) {
            case FIND:
                ArrayList<Task> result = searchTask(task, keyword[1]);
                try {
                    for (int i = 0; i < result.size(); i++) {
                        String s = indentation;
                        String s2 = "";

                        if (result.get(i) instanceof Todo) {
                            s += (i + 1) + "." + " [T]";
                            s2 = result.get(i).getName();
                        } else if (result.get(i) instanceof Deadline) {
                            s += (i + 1) + "." + " [D]";
                            s2 = result.get(i).getName() + " " + "(" + " "
                                    + ((Deadline) result.get(i)).getTime() + " )";
                        } else if (result.get(i) instanceof Event) {
                            s += (i + 1) + "." + " [E]";
                            s2 = result.get(i).getName() + " " + "(" + " " + ((Event) result.get(i)).getTime() + " )";
                        }
                        if (result.get(i).isDone() == false) {
                            s += "[ ] " + s2;
                            System.out.println(s);
                        } else {
                            s += "[X] " + s2;
                            System.out.println(s);
                        }
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + e.getMessage());
                }
                System.out.println(ui.line);
                break;
            case LIST:
                System.out.println(ui.line);
                try {
                    for (int i = 0; i < task.size(); i++) {
                        String s = indentation;
                        String s2 = "";

                        if (task.get(i) instanceof Todo) {
                            s += (i + 1) + "." + " [T]";
                            s2 = task.get(i).getName();
                        } else if (task.get(i) instanceof Deadline) {
                            s += (i + 1) + "." + " [D]";
                            s2 = task.get(i).getName() + " " + "(" + " " + ((Deadline) task.get(i)).getTime() + " )";
                        } else if (task.get(i) instanceof Event) {
                            s += (i + 1) + "." + " [E]";
                            s2 = task.get(i).getName() + " " + "(" + " " + ((Event) task.get(i)).getTime() + " )";
                        }
                        if (task.get(i).isDone() == false) {
                            s += "[ ] " + s2;
                            System.out.println(s);
                        } else {
                            s += "[X] " + s2;
                            System.out.println(s);
                        }
                    }

                } catch (IndexOutOfBoundsException e) {
                    System.out.println(indentation + e.getMessage());
                }
                System.out.println(ui.line);

                break;
            case DONE:

                try {
                    Integer num = Integer.valueOf(keyword[1]) - 1;
                    task.get(num).setDone(true);
                    database.updateData(task.get(num), num + 1);
                    System.out.println(ui.line);
                    System.out.println(ui.done_message);
                    String s = indentation;
                    String s2 = "";
                    if (task.get(num) instanceof Todo) {

                        s += (task.get(num).getIndex() + 1) + "." + " [T]";
                        s2 = task.get(num).getName();
                    } else if (task.get(num) instanceof Deadline) {
                        s += (task.get(num).getIndex() + 1) + "." + " [D]";
                        s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
                    } else if (task.get(num) instanceof Event) {
                        s += (task.get(num).getIndex() + 1) + "." + " [E]";
                        s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
                    }

                    s += "[X]" + s2;
                    System.out.println(s);

                    System.out.println(ui.line);
                } catch (NullPointerException e) {
                    System.out.println(ui.no_task_message);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ui.task_num_message);
                }

                break;
            case DELETE:
                try {
                    Integer num = Integer.valueOf(keyword[1]) - 1;

                    database.deleteData(num + 1);
                    String s = indentation + "     ";
                    String s2 = "";

                    if (task.get(num) instanceof Todo) {
                        s += (task.get(num).getIndex() + 1) + "." + " [T]";
                        s2 = task.get(num).getName();
                    } else if (task.get(num) instanceof Deadline) {
                        s += (task.get(num).getIndex() + 1) + "." + " [D]";
                        s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
                    } else if (task.get(num) instanceof Event) {
                        s += (task.get(num).getIndex() + 1) + "." + " [E]";
                        s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
                    }
                    System.out.println(ui.line);
                    System.out.println(ui.remove_message);
                    if (task.get(num).isDone() == false) {
                        s += "[ ]" + s2;
                        System.out.println(s);
                    } else {
                        s += "[X]" + s2;
                        System.out.println(s);
                    }
                    task.remove(num.intValue());
                    System.out.format(indentation + "Now you have %d tasks in the list.%n", task.size());
                    System.out.println(ui.line);
                } catch (NullPointerException e) {
                    System.out.println(ui.no_task_message);
                } catch (IndexOutOfBoundsException e) {
                    System.out.println(ui.no_task_message);
                }
                break;
            case BYE:
                System.out.println(ui.bye_message);
                scanner.close();
                isEnd = true;
                break;
            default:
                try {
                    switch (keyword[0]) {
                    case "deadline":
                        if (keyword.length == 1) {
                            System.out.println(ui.lack_content_message);
                            break;
                        }
                        String taskname_ddl = "";
                        String tasktime_ddl = "";
                        boolean timepart_ddl = false;
                        for (int i = 1; i < keyword.length; i++) {
                            if (keyword[i].startsWith("/")) {
                                timepart_ddl = true;
                                tasktime_ddl = keyword[i].substring(1) + ":";
                            } else if (timepart_ddl) {
                                tasktime_ddl += " " + keyword[i];
                            } else {
                                if (keyword[i + 1].startsWith("/")) {
                                    taskname_ddl += keyword[i];
                                } else {
                                    taskname_ddl += keyword[i] + " ";
                                }
                            }
                        }
                        if (tasktime_ddl.equals("")) {
                            System.out.println(ui.lack_content_message);
                            break;
                        }
                        Task ddl = new Deadline(taskname_ddl, false, tasktime_ddl);
                        task.add(ddl);
                        database.writeToDatabase(ddl);
                        taskNum++;
                        System.out.println(ui.line);
                        System.out.println(ui.added_message);
                        System.out.println(indentation + "   [D][ ] " + taskname_ddl + " ( " + tasktime_ddl + " )");
                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                        System.out.println(ui.line);
                        break;
                    case "todo":
                        if (keyword.length == 1) {
                            System.out.println(ui.lack_content_message);
                            break;
                        }
                        String taskname_todo = "";
                        for (int i = 1; i < keyword.length; i++) {
                            if (i == keyword.length - 1) {
                                taskname_todo += keyword[i];
                            } else {
                                taskname_todo += keyword[i] + " ";
                            }

                        }
                        Task todo = new Todo(taskname_todo, false);

                        task.add(todo);
                        database.writeToDatabase(todo);
                        taskNum++;
                        System.out.println(ui.line);
                        System.out.println(ui.added_message);
                        System.out.println(indentation + "   [T][ ] " + taskname_todo);
                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                        System.out.println(ui.line);
                        break;
                    case "event":
                        if (keyword.length == 1) {
                            System.out.println(ui.lack_content_message);
                            break;
                        }

                        String taskname_event = "";
                        String tasktime_event = "";
                        boolean timepart_event = false;
                        for (int i = 1; i < keyword.length; i++) {
                            if (keyword[i].startsWith("/")) {
                                timepart_event = true;
                                tasktime_event = keyword[i].substring(1) + ":";
                            } else if (timepart_event) {
                                tasktime_event += " " + keyword[i];
                            } else {
                                if (keyword[i + 1].startsWith("/")) {
                                    taskname_event += keyword[i];
                                } else {
                                    taskname_event += keyword[i] + " ";
                                }

                            }
                        }
                        if (tasktime_event.equals("")) {
                            System.out.println(ui.lack_content_message);
                            break;
                        }
                        Task event = new Event(taskname_event, false, tasktime_event);
                        task.add(event);
                        database.writeToDatabase(event);
                        taskNum++;
                        System.out.println(ui.line);
                        System.out.println(ui.added_message);
                        System.out.println(indentation + "   [E][ ] " + taskname_event + " ( " + tasktime_event + " )");
                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
                        System.out.println(ui.line);
                        break;
                    default:
                        System.out.println(ui.unknown_message);
                        break;

                    }

                } catch (ArrayIndexOutOfBoundsException e) {
                    System.out.println(ui.index_message);
                }

            }
        }



    }

    /**
     * run duke
     * @param args
     */
    public static void main(String[] args) {
        Duke duke = new Duke("todoList.txt");
        duke.run();
    }

}