package duke.core;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

import duke.databse.Database;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.RecurringTask;
import duke.task.Task;
import duke.task.Todo;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class Duke extends Application {
    private static final String BYE = "bye";
    private static final String DONE = "done";
    private static final String DELETE = "delete";
    private static final String LIST = "list";
    private static final String FIND = "find";

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

    /**
     * Start Duke GUI
     * @param stage
     */
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
     * search for a task from task list using a keyword
     * @param tasklist a list that contains all the tasks
     * @param keyword the keyword for searching
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


    /**
     * give response to GUI when user types in text
     * @param input
     * @return
     */
    public String getResponse(String input) {
        if (input.equals("")) {
            return ui.logo + "\n" + "\n" + ui.greeting + "\n";
        }
        database = new Database("data/todoList2.txt");
        ArrayList<Task> task = database.getData();
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";

        String keywords = input;
        String[] keyword = keywords.split(" ");

        switch(keyword[0]) {
        case FIND:
            ArrayList<Task> result = searchTask(task, keyword[1]);
            if (result.size() == 0) {
                return ui.keywordNotFound;
            }
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
                    } else if (task.get(i) instanceof RecurringTask) {
                        s += (i + 1) + "." + " [R]";
                        s2 = task.get(i).getName() + " " + "(" + " " + ((RecurringTask) task.get(i)).getTime()
                                + " ) " + ((RecurringTask) task.get(i)).getCounter() + " lessons";
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
                if (task.get(num) instanceof RecurringTask) {
                    int counter = ((RecurringTask) task.get(num)).getCounter();
                    ((RecurringTask) task.get(num)).setCounter(counter - 1);
                    database.updateData(task.get(num), num + 1);
                } else {
                    task.get(num).setDone(true);
                    database.updateData(task.get(num), num + 1);
                }
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

                } else if (task.get(num) instanceof RecurringTask) {
                    s += (task.get(num).getIndex() + 1) + "." + " [R]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((RecurringTask) task.get(num)).getTime()
                            + " ) " + ((RecurringTask) task.get(num)).getCounter() + " lessons";

                }


                if (task.get(num) instanceof RecurringTask) {
                    s += "[ ]" + s2;
                    response += "Nice! I've remove one lesson from this task:\n";
                } else {
                    s += "[X]" + s2;
                    response += ui.doneMessage + "\n";

                }
                response += s;
                return response;

            } catch (NullPointerException e) {
                return ui.taskNumMessage;
            } catch (IndexOutOfBoundsException e) {
                return ui.taskNumMessage;
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
                } else if (task.get(num) instanceof RecurringTask) {
                    s += (task.get(num).getIndex() + 1) + "." + " [R]";
                    s2 = task.get(num).getName() + " " + "(" + " " + ((RecurringTask) task.get(num)).getTime() + " )";
                }
                if (task.get(num).isDone() == false) {
                    s += "[ ]" + s2;
                } else {
                    s += "[X]" + s2;
                }

                task.remove(num.intValue());
                response += s + "\n";
                response += indentation + "Now you have " + task.size() + " " + "tasks in the list." + "\n";
                return response;
            } catch (NullPointerException e) {
                return ui.noTaskMessage;
            } catch (IndexOutOfBoundsException e) {
                return ui.noTaskMessage;
            }
        case BYE:
            return ui.byeMessage;
        default:
            try {
                switch (keyword[0]) {
                case "deadline":
                    if (keyword.length == 1) {
                        return ui.lackContentMessage;
                    }
                    String taskNameDdl = "";
                    String taskTimeDdl = "";
                    boolean timePartDdl = false;
                    int count = 0;
                    for (int i = 1; i < keyword.length; i++) {
                        if (keyword[i].startsWith("/")) {
                            timePartDdl = true;
                            taskTimeDdl = keyword[i].substring(1) + ":";
                        } else if (timePartDdl) {
                            taskTimeDdl += " " + keyword[i];
                            if (count == 0) {
                                try {
                                    LocalDate date = LocalDate.parse(keyword[i]);
                                } catch (DateTimeException e) {
                                    return "Please follow the template:\n deadline [name] /by yyyy-mm-dd ....";
                                }
                            }
                            count++;
                        } else {
                            if (keyword[i + 1].startsWith("/")) {
                                taskNameDdl += keyword[i];
                            } else {
                                taskNameDdl += keyword[i] + " ";
                            }
                        }
                    }
                    if (taskTimeDdl.equals("")) {
                        return ui.lackContentMessage;
                    }

                    Task ddl = new Deadline(taskNameDdl, false, taskTimeDdl);
                    task.add(ddl);
                    database.writeToDatabase(ddl);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [D][ ] " + taskNameDdl + " ( " + taskTimeDdl + " )\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                case "recur":
                    if (keyword.length == 1) {
                        return ui.lackContentMessage;
                    }
                    String taskNameRecur = "";
                    String taskTimeRecur = "";
                    int counter = 0;
                    boolean timePartRecur = false;
                    for (int i = 1; i < keyword.length; i++) {
                        if (keyword[i].startsWith("/") && !timePartRecur) {
                            timePartRecur = true;
                            taskTimeRecur = keyword[i].substring(1) + ":";
                        } else if (keyword[i].startsWith("/") && timePartRecur) {
                            counter = Integer.parseInt(keyword[i].substring(1));
                        } else if (timePartRecur) {
                            taskTimeRecur += " " + keyword[i];
                        } else {
                            if (keyword[i + 1].startsWith("/")) {
                                taskNameRecur += keyword[i];
                            } else {
                                taskNameRecur += keyword[i] + " ";
                            }
                        }
                    }
                    if (taskTimeRecur.equals("")) {
                        return ui.lackContentMessage;
                    }
                    Task recur = new RecurringTask(taskNameRecur, false, taskTimeRecur, counter);
                    task.add(recur);
                    database.writeToDatabase(recur);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [R][ ] " + taskNameRecur + " ( " + taskTimeRecur + " ) "
                            + counter + " lessons\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                case "todo":
                    if (keyword.length == 1) {
                        return ui.lackContentMessage;
                    }
                    String taskNameTodo = "";
                    for (int i = 1; i < keyword.length; i++) {
                        if (i == keyword.length - 1) {
                            taskNameTodo += keyword[i];
                        } else {
                            taskNameTodo += keyword[i] + " ";
                        }

                    }
                    Task todo = new Todo(taskNameTodo, false);

                    task.add(todo);
                    database.writeToDatabase(todo);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [T][ ] " + taskNameTodo + "\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                case "event":
                    if (keyword.length == 1) {
                        return ui.lackContentMessage;
                    }

                    String taskNameEvent = "";
                    String taskTimeEvent = "";
                    int count1 = 0;
                    boolean timePartEvent = false;
                    for (int i = 1; i < keyword.length; i++) {
                        if (keyword[i].startsWith("/")) {
                            timePartEvent = true;
                            taskTimeEvent = keyword[i].substring(1) + ":";
                        } else if (timePartEvent) {
                            taskTimeEvent += " " + keyword[i];
                            System.out.println(keyword[i]);
                            if (count1 == 0) {
                                try {
                                    LocalDate date = LocalDate.parse(keyword[i]);
                                } catch (DateTimeException e) {
                                    return "Please follow the template: \n event [name] /at yyyy-mm-dd ....";
                                }
                            }
                            count1++;
                        } else {
                            if (keyword[i + 1].startsWith("/")) {
                                taskNameEvent += keyword[i];
                            } else {
                                taskNameEvent += keyword[i] + " ";
                            }

                        }
                    }
                    if (taskTimeEvent.equals("")) {
                        return ui.lackContentMessage;
                    }
                    Task event = new Event(taskNameEvent, false, taskTimeEvent);
                    task.add(event);
                    database.writeToDatabase(event);
                    taskNum++;
                    response += "Got it. I've added this task:" + "\n";
                    response += indentation + "   [E][ ] " + taskNameEvent + " ( " + taskTimeEvent + " )\n";
                    response += indentation + "Now you have" + " " + taskNum + " " + "tasks in the list \n";
                    return response;
                default:
                    return ui.unknownMessage;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return ui.indexMessage;
            }
        }
    }

//    /**
//     * start the duke
//     */
//    public void run() {
//
//        ArrayList<Task> task = database.getData();
//        int taskNum = task.size();
//        String indentation = "       ";
//        boolean isEnd = false;
//        System.out.println(ui.logo);
//        System.out.println(ui.line);
//        System.out.println(ui.greeting);
//        System.out.println(ui.line);
//        Scanner scanner = new Scanner(System.in);
//        while (!isEnd) {
//            String keywords = scanner.nextLine();
//            String[] keyword = keywords.split(" ");
//            switch(keyword[0]) {
//            case FIND:
//                ArrayList<Task> result = searchTask(task, keyword[1]);
//                try {
//                    for (int i = 0; i < result.size(); i++) {
//                        String s = indentation;
//                        String s2 = "";
//
//                        if (result.get(i) instanceof Todo) {
//                            s += (i + 1) + "." + " [T]";
//                            s2 = result.get(i).getName();
//                        } else if (result.get(i) instanceof Deadline) {
//                            s += (i + 1) + "." + " [D]";
//                            s2 = result.get(i).getName() + " " + "(" + " "
//                                    + ((Deadline) result.get(i)).getTime() + " )";
//                        } else if (result.get(i) instanceof Event) {
//                            s += (i + 1) + "." + " [E]";
//                            s2 = result.get(i).getName() + " " + "(" + " " + ((Event) result.get(i)).getTime() + " )";
//                        } else if (task.get(i) instanceof RecurringTask) {
//                            s += (i + 1) + "." + " [R]";
//                            s2 = task.get(i).getName() + " " + "(" + " "
//                                    + ((RecurringTask) task.get(i)).getTime() + " )";
//                        }
//                        if (result.get(i).isDone() == false) {
//                            s += "[ ] " + s2;
//                            System.out.println(s);
//                        } else {
//                            s += "[X] " + s2;
//                            System.out.println(s);
//                        }
//                    }
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println(indentation + e.getMessage());
//                }
//                System.out.println(ui.line);
//                break;
//            case LIST:
//                System.out.println(ui.line);
//                try {
//                    for (int i = 0; i < task.size(); i++) {
//                        String s = indentation;
//                        String s2 = "";
//
//                        if (task.get(i) instanceof Todo) {
//                            s += (i + 1) + "." + " [T]";
//                            s2 = task.get(i).getName();
//                        } else if (task.get(i) instanceof Deadline) {
//                            s += (i + 1) + "." + " [D]";
//                            s2 = task.get(i).getName() + " " + "(" + " " + ((Deadline) task.get(i)).getTime() + " )";
//                        } else if (task.get(i) instanceof Event) {
//                            s += (i + 1) + "." + " [E]";
//                            s2 = task.get(i).getName() + " " + "(" + " " + ((Event) task.get(i)).getTime() + " )";
//                        }
//                        if (task.get(i).isDone() == false) {
//                            s += "[ ] " + s2;
//                            System.out.println(s);
//                        } else {
//                            s += "[X] " + s2;
//                            System.out.println(s);
//                        }
//                    }
//
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println(indentation + e.getMessage());
//                }
//                System.out.println(ui.line);
//                break;
//            case DONE:
//                try {
//                    Integer num = Integer.valueOf(keyword[1]) - 1;
//                    task.get(num).setDone(true);
//                    database.updateData(task.get(num), num + 1);
//                    System.out.println(ui.line);
//                    System.out.println(ui.done_message);
//                    String s = indentation;
//                    String s2 = "";
//                    if (task.get(num) instanceof Todo) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [T]";
//                        s2 = task.get(num).getName();
//                    } else if (task.get(num) instanceof Deadline) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [D]";
//                        s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
//                    } else if (task.get(num) instanceof Event) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [E]";
//                        s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
//                    } else if (task.get(num) instanceof RecurringTask) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [R]";
//                        s2 = task.get(num).getName() + " " + "(" + " "
//                                + ((RecurringTask) task.get(num)).getTime() + " )";
//                    }
//                    s += "[X]" + s2;
//                    System.out.println(s);
//                    System.out.println(ui.line);
//                } catch (NullPointerException e) {
//                    System.out.println(ui.no_task_message);
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println(ui.task_num_message);
//                }
//                break;
//            case DELETE:
//                try {
//                    Integer num = Integer.valueOf(keyword[1]) - 1;
//                    database.deleteData(num + 1);
//                    String s = indentation + "     ";
//                    String s2 = "";
//                    if (task.get(num) instanceof Todo) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [T]";
//                        s2 = task.get(num).getName();
//                    } else if (task.get(num) instanceof Deadline) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [D]";
//                        s2 = task.get(num).getName() + " " + "(" + " " + ((Deadline) task.get(num)).getTime() + " )";
//                    } else if (task.get(num) instanceof Event) {
//                        s += (task.get(num).getIndex() + 1) + "." + " [E]";
//                        s2 = task.get(num).getName() + " " + "(" + " " + ((Event) task.get(num)).getTime() + " )";
//                    }
//                    System.out.println(ui.line);
//                    System.out.println(ui.remove_message);
//                    if (task.get(num).isDone() == false) {
//                        s += "[ ]" + s2;
//                        System.out.println(s);
//                    } else {
//                        s += "[X]" + s2;
//                        System.out.println(s);
//                    }
//                    task.remove(num.intValue());
//                    System.out.format(indentation + "Now you have %d tasks in the list.%n", task.size());
//                    System.out.println(ui.line);
//                } catch (NullPointerException e) {
//                    System.out.println(ui.no_task_message);
//                } catch (IndexOutOfBoundsException e) {
//                    System.out.println(ui.no_task_message);
//                }
//                break;
//            case BYE:
//                System.out.println(ui.bye_message);
//                scanner.close();
//                isEnd = true;
//                break;
//            default:
//                try {
//                    switch (keyword[0]) {
//                    case "deadline":
//                        if (keyword.length == 1) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        String taskname_ddl = "";
//                        String tasktime_ddl = "";
//                        boolean timepart_ddl = false;
//                        for (int i = 1; i < keyword.length; i++) {
//                            if (keyword[i].startsWith("/")) {
//                                timepart_ddl = true;
//                                tasktime_ddl = keyword[i].substring(1) + ":";
//                            } else if (timepart_ddl) {
//                                tasktime_ddl += " " + keyword[i];
//                            } else {
//                                if (keyword[i + 1].startsWith("/")) {
//                                    taskname_ddl += keyword[i];
//                                } else {
//                                    taskname_ddl += keyword[i] + " ";
//                                }
//                            }
//                        }
//                        if (tasktime_ddl.equals("")) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        Task ddl = new Deadline(taskname_ddl, false, tasktime_ddl);
//                        task.add(ddl);
//                        database.writeToDatabase(ddl);
//                        taskNum++;
//                        System.out.println(ui.line);
//                        System.out.println(ui.added_message);
//                        System.out.println(indentation + "   [D][ ] " + taskname_ddl + " ( " + tasktime_ddl + " )");
//                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
//                        System.out.println(ui.line);
//                        break;
//                    case "recur":
//                        if (keyword.length == 1) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        int counter = 0;
//                        String taskname_recur = "";
//                        String tasktime_recur = "";
//                        boolean timepart_recur = false;
//                        for (int i = 1; i < keyword.length; i++) {
//                            if (keyword[i].startsWith("/")) {
//                                timepart_recur = true;
//                                tasktime_recur = keyword[i].substring(1) + ":";
//                            } else if (keyword[i].startsWith("/") && timepart_recur == true) {
//                                counter = Integer.valueOf(keyword[i].substring(1));
//                            } else if (timepart_recur) {
//                                tasktime_recur += " " + keyword[i];
//                            } else {
//                                if (keyword[i + 1].startsWith("/")) {
//                                    taskname_recur += keyword[i];
//                                } else {
//                                    taskname_recur += keyword[i] + " ";
//                                }
//                            }
//                        }
//                        if (tasktime_recur.equals("")) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        Task recur = new RecurringTask(taskname_recur, false, tasktime_recur, counter);
//                        task.add(recur);
//                        database.writeToDatabase(recur);
//                        taskNum++;
//                        System.out.println(ui.line);
//                        System.out.println(ui.added_message);
//                        System.out.println(indentation + "   [D][ ] " + taskname_recur + " ( " + tasktime_recur + " )");
//                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
//                        System.out.println(ui.line);
//                        break;
//                    case "todo":
//                        if (keyword.length == 1) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        String taskname_todo = "";
//                        for (int i = 1; i < keyword.length; i++) {
//                            if (i == keyword.length - 1) {
//                                taskname_todo += keyword[i];
//                            } else {
//                                taskname_todo += keyword[i] + " ";
//                            }
//                        }
//                        Task todo = new Todo(taskname_todo, false);
//                        task.add(todo);
//                        database.writeToDatabase(todo);
//                        taskNum++;
//                        System.out.println(ui.line);
//                        System.out.println(ui.added_message);
//                        System.out.println(indentation + "   [T][ ] " + taskname_todo);
//                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
//                        System.out.println(ui.line);
//                        break;
//                    case "event":
//                        if (keyword.length == 1) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        String taskname_event = "";
//                        String tasktime_event = "";
//                        boolean timepart_event = false;
//                        for (int i = 1; i < keyword.length; i++) {
//                            if (keyword[i].startsWith("/")) {
//                                timepart_event = true;
//                                tasktime_event = keyword[i].substring(1) + ":";
//                            } else if (timepart_event) {
//                                tasktime_event += " " + keyword[i];
//                            } else {
//                                if (keyword[i + 1].startsWith("/")) {
//                                    taskname_event += keyword[i];
//                                } else {
//                                    taskname_event += keyword[i] + " ";
//                                }
//                            }
//                        }
//                        if (tasktime_event.equals("")) {
//                            System.out.println(ui.lack_content_message);
//                            break;
//                        }
//                        Task event = new Event(taskname_event, false, tasktime_event);
//                        task.add(event);
//                        database.writeToDatabase(event);
//                        taskNum++;
//                        System.out.println(ui.line);
//                        System.out.println(ui.added_message);
//                        System.out.println(indentation + "   [E][ ] " + taskname_event + " ( " + tasktime_event + " )");
//                        System.out.format(indentation + "Now you have %d tasks in the list%n", taskNum);
//                        System.out.println(ui.line);
//                        break;
//                    default:
//                        System.out.println(ui.unknown_message);
//                        break;
//                    }
//                } catch (ArrayIndexOutOfBoundsException e) {
//                    System.out.println(ui.index_message);
//                }
//            }
//        }
//    }
//
//    /**
//     * run duke
//     * @param args
//     */
//    public static void main(String[] args) {
//        Duke duke = new Duke("todoList.txt");
//        duke.run();
//    }

}
