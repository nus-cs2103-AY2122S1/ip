package duke.core;

import java.util.ArrayList;
import java.util.Scanner;

import duke.databse.Database;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;


public class Duke {

    private Database database;
    private ArrayList<Task> taskList;
    private UI ui = new UI();

    /**
     * Constructor
     * @param filePath
     */
    public Duke(String filePath) {
        database = new Database(filePath);
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