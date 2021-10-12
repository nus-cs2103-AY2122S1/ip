package duke.core;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.ArrayList;

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
    private static final String DEADLINE = "deadline";
    private static final String RECUR = "recur";
    private static final String TODO = "todo";
    private static final String EVENT = "event";


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
     * execute find command
     * @param input
     * @param task
     * @return
     */
    public String executeFind(String input, ArrayList<Task> task) {
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";

        String keywords = input;
        String[] keyword = keywords.split(" ");
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
    }

    /**
     * execute list command
     * @param input
     * @param task
     * @return
     */
    public String executeList(String input, ArrayList<Task> task) {

        int taskNum = task.size();
        String indentation = "       ";
        String response = "";

        String keywords = input;
        String[] keyword = keywords.split(" ");
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
    }

    /**
     * execute done command
     * @param input
     * @param task
     * @return
     */
    public String executeDone(String input, ArrayList<Task> task) {
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
        try {
            Integer num = Integer.valueOf(keyword[1]) - 1;
            if (task.get(num) instanceof RecurringTask) {
                int counter = ((RecurringTask) task.get(num)).getCounter();
                RecurringTask recurringTask = (RecurringTask) task.get(num);
                recurringTask.setCounter(counter - 1);
            } else {
                task.get(num).setDone(true);
            }
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
    }

    /**
     * execute delete command
     * @param input
     * @param task
     * @return
     */
    public String executeDelete(String input, ArrayList<Task> task) {
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
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
    }

    /**
     * execute bye command
     * @return
     */
    public String executeBye() {
        return ui.byeMessage;
    }

    /**
     * execute deadline command
     * @param input
     * @param task
     * @return
     */
    public String executeDeadline(String input, ArrayList<Task> task) {
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
        if (keyword.length == 1) {
            return ui.lackContentMessage;
        }
        String taskNameDdl = "";
        String taskTimeDdl = "";
        boolean timePartDdl = false;
        int count = 0;
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.indexMessage;
        }

    }

    /**
     * execute event command
     * @param input
     * @param task
     * @return
     */
    public String executeEvent(String input, ArrayList<Task> task) {
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
        if (keyword.length == 1) {
            return ui.lackContentMessage;
        }

        String taskNameEvent = "";
        String taskTimeEvent = "";
        int count1 = 0;
        boolean timePartEvent = false;
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.indexMessage;
        }

    }

    /**
     * execute todo command
     * @param input
     * @param task
     * @return
     */
    public String executeTodo(String input, ArrayList<Task> task) {
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
        if (keyword.length == 1) {
            return ui.lackContentMessage;
        }
        String taskNameTodo = "";
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.indexMessage;
        }

    }

    /**
     * execute recur command
     * @param input
     * @param task
     * @return
     */
    public String executeRecur(String input, ArrayList<Task> task) {
        int taskNum = task.size();
        String indentation = "       ";
        String response = "";
        String keywords = input;
        String[] keyword = keywords.split(" ");
        if (keyword.length == 1) {
            return ui.lackContentMessage;
        }
        String taskNameRecur = "";
        String taskTimeRecur = "";
        int counter = 0;
        boolean timePartRecur = false;
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            return ui.indexMessage;
        }

    }
    /**
     * parse the input command line for execution
     * @param input
     * @return
     */
    public String commandParser(String input) {
        if (input.equals("")) {
            return ui.logo + "\n" + "\n" + ui.greeting + "\n";
        }
        String keywords = input;
        String[] keyword = keywords.split(" ");
        database = new Database("data/todoList2.txt");
        ArrayList<Task> task = database.getData();

        switch(keyword[0]) {
        case FIND:
            return executeFind(input, task);
        case LIST:
            return executeList(input, task);
        case DONE:
            return executeDone(input, task);
        case DELETE:
            return executeDelete(input, task);
        case BYE:
            return executeBye();
        case TODO:
            return executeTodo(input, task);
        case EVENT:
            return executeEvent(input, task);
        case RECUR:
            return executeRecur(input, task);
        case DEADLINE:
            return executeDeadline(input, task);
        default:
            return ui.unknownMessage;
        }
    }

    /**
     * give response to GUI when user types in text
     * @param input
     * @return
     */
    public String getResponse(String input) {
        return commandParser(input);
    }

}
