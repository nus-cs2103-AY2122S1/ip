import java.util.ArrayList;
import java.util.Scanner;

public class Saber {
    protected static String logo = "                            __,    _,  _ __  ______ _ __\n" +
            "              />           (      / | ( /  )(  /   ( /  )\n" +
            "             /<             `.   /--|  /--<   /--   /--<\n" +
            "            /<            (___)_/   |_/___/ (/____//   \\_\n" +
            "           /<\n" +
            " |\\_______{o}----------------------------------------------------------_\n" +
            "[\\\\\\\\\\\\\\\\\\\\\\{*}:::<=============================================-       >\n" +
            " |/~~~~~~~{o}----------------------------------------------------------~\n" +
            "           \\<\n" +
            "            \\<\n" +
            "             \\>\n";

    protected static String lineBreak = "      |                                     |\n" +
            "{o)xxx|===============- * -===============|xxx(o}\n" +
            "      |                                     |\n";

    protected static String greeting = lineBreak + "\n      I hear your command, Master.\n" +
            "      What can I do for you?\n\n" + lineBreak;

    protected static String goodbye = lineBreak + "\n      Am I ... no longer needed, Master?\n" +
            "      I understand. I shall excuse myself.\n\n" + lineBreak;

    protected static String listMessage = "      Would you like to know what you\n" +
            "      told me to remember?\n" + "\n" +
            "      I'll list them for you, Master.\n";

    protected static String storageLoadingError = lineBreak + "\n      I'm sorry, Master. I can't ...\n"
            +  "      seem to remember what\n"
            +  "      you have previously told me\n\n" + lineBreak;

    protected static String storageStoringError = lineBreak + "\n      I'm really sorry, Master. I can't ...\n"
            +  "      the next time you talk to me ...\n"
            +  "      I won't be the Saber that you\n"
            +  "      know...I won't remember our\n"
            +  "      interaction either. A new\n"
            +  "      Saber will serve you instead.\n\n"
            +  "      Have I...done well, Master?\n\n" + lineBreak;

    protected TaskStorage storage;

    protected TaskList taskList;

    enum InputCommand {
        add,
        bye,
        done,
        deadline,
        delete,
        event,
        todo,
        list,
    }

    enum TaskType {
        deadline,
        event,
        normalTask,
        todo,
    }

    public Saber(String filePath) {
        storage = new TaskStorage(filePath);
        try {
            this.taskList = new TaskList(storage.load());
        } catch (SaberStorageLoadException e) {
            System.out.println(storageLoadingError);
            this.taskList = new TaskList(new ArrayList<>());
        }
    }

    public void handleAddTask(String task) {
        Task newTask = new Task(task, false);
        taskList.add(newTask);
        System.out.println(lineBreak + "\n      I have added: "  + task + "\n\n" + lineBreak);
    }

    public void handleDeadlineTask(String task, String time) {
        Task deadline = new Deadline(task, time, false);
        taskList.add(deadline);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Right, Master.\n"
                + "      I'll ensure that you will\n"
                + "      do this task before the deadline:\n"
                + "\n        " + deadline);
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        System.out.println("\n      Currently, Master has " + totalTask + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public void handleDeleteTask(int taskIndex) throws TaskNotFoundException {
        int totalTask = taskList.size();
        if (taskIndex >= totalTask || taskIndex < 0) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskList.get(taskIndex);
        taskList.delete(taskIndex);
        totalTask--;
        System.out.println(lineBreak + "\n      Understand, Master.\n"
                + "      I have deleted this task.\n"
                + "\n        " + task + "\n");
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        System.out.println("      Currently, Master has " + totalTask + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public void handleDoneTask(int taskIndex) throws TaskNotFoundException {
        int totalTask = taskList.size();
        if (taskIndex >= totalTask || taskIndex < 0) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        System.out.println(lineBreak + "\n      Understand, Master.\n" + "      I'll mark this done right away.\n"
                + "\n        " + task + "\n\n" + lineBreak);
    }

    public void handleEventTask(String task, String time) {
        Task event = new Event(task, time, false);
        taskList.add(event);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Right, Master.\n"
                +  "      I'll make sure you remember\n"
                + "      to come to this event:\n"
                + "\n        " + event);
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        System.out.println("\n      Currently, Master has " + totalTask + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public void handleListTask() {
        System.out.println(lineBreak + "\n" + listMessage);
        int totalTask = taskList.size();
        for (int i = 0; i < totalTask; i++) {
            Task task = taskList.get(i);
            System.out.println("      " + (i + 1) + ". " + task + "\n");
        }
        if (totalTask == 0) {
            System.out.println("      Ah, currently Master has no task.\n");
        }
        System.out.println(lineBreak);
    }

    public void handleTodoTask(String task) {
        Task todo = new ToDo(task, false);
        taskList.add(todo);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Yes, Master.\n"
                +  "      I'll add the following to your Todo list:\n"
                + "\n        " + todo);
        String taskPlural = totalTask <= 1 ? " task" : " tasks";
        System.out.println("\n      Currently, Master has " + totalTask + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public void endSaber() {
        System.out.println(goodbye);
        TaskType[] taskTypeArray = new TaskType[taskList.size()];
        for (int i = 0; i < taskList.size(); i++) {
            if (taskList.get(i) instanceof Deadline) {
                taskTypeArray[i] = TaskType.deadline;
            } else if (taskList.get(i) instanceof Event) {
                taskTypeArray[i] = TaskType.event;
            } else if (taskList.get(i) instanceof ToDo) {
                taskTypeArray[i] = TaskType.todo;
            } else {
                taskTypeArray[i] = TaskType.normalTask;
            }
        }
        try {
            storage.store(taskList, taskTypeArray);
        } catch (SaberStorageStoreException e) {
            System.out.println(storageStoringError);
        }
    }

    public void run() {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + logo);
        System.out.println(greeting);
        while (!end) {
            Runtime.getRuntime().addShutdownHook(new Thread(this::endSaber));
            String input = sc.nextLine().trim();
            Command command = new Command(input);
            InputCommand commandType ;
            try {
                commandType = command.getCommandType();
                switch (commandType) {
                    case add:
                        try {
                            String taskForAdd = command.getArgument();
                            handleAddTask(taskForAdd);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "\n      I'm sorry, Master.\n"
                                    + "      What ... exactly do you want me to add?\n\n"
                                    + lineBreak);
                        }
                        break;

                    case bye:
                        end = true;
                        break;

                    case done:
                        try {
                            int taskIndex = Integer.parseInt(command.getArgument());
                            handleDoneTask(taskIndex - 1);
                        } catch (MissingArgumentException | NumberFormatException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure which task you want me\n"
                                    + "      to mark as done...\n\n"
                                    + lineBreak);
                        } catch (TaskNotFoundException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I'm unable to find the task that\n"
                                    + "      you specified...\n\n"
                                    + lineBreak);
                        }
                        break;

                    case deadline:
                        try {
                            String deadlineTask = command.getArgument();
                            String deadlineTime = command.getTime();
                            handleDeadlineTask(deadlineTask, deadlineTime);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure what task you want me\n"
                                    + "      to add ...\n\n"
                                    + lineBreak);
                        } catch (MissingTimeException e) {
                            System.out.println(lineBreak + "\n      I'm sorry, Master.\n"
                                    + "      I won't be able to ensure that you\n"
                                    + "      do the task on time without\n"
                                    + "      knowing the deadline...\n\n"
                                    + lineBreak);
                        }
                        break;

                    case delete:
                        try {
                            int taskIndex = Integer.parseInt(command.getArgument());
                            handleDeleteTask(taskIndex - 1);
                        } catch (MissingArgumentException | NumberFormatException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure which task you want\n"
                                    + "      to delete...\n\n"
                                    + lineBreak);
                        } catch (TaskNotFoundException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I'm unable to find the task that\n"
                                    + "      you want to delete...\n\n"
                                    + lineBreak);
                        }
                        break;

                    case event:
                        try {
                            String eventTask = command.getArgument();
                            String eventTime = command.getTime();
                            handleEventTask(eventTask, eventTime);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "\n      I'm sorry, Master.\n"
                                    + "      I'm ... not sure what event you want me\n"
                                    + "      to add ...\n\n"
                                    + lineBreak);
                        } catch (MissingTimeException e) {
                            System.out.println(lineBreak + "\n      I'm really sorry, Master.\n"
                                    + "      I won't be able to remind you\n"
                                    + "      to come to this event without\n"
                                    + "      knowing the time of the event...\n\n"
                                    + lineBreak);
                        }
                        break;

                    case todo:
                        try {
                            String taskForTodo = command.getArgument();
                            handleTodoTask(taskForTodo);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "\n      I'm sorry, Master.\n"
                                    + "      What ... exactly do you want me to add\n"
                                    + "      to your Todo list?\n\n"
                                    + lineBreak);
                        }
                        break;

                    case list:
                        handleListTask();
                        break;

                    default:
                        System.out.println(lineBreak + "\n      I'm sorry, Master. I can't fulfill your command.\n"
                                + "      What you want from me is beyond my capability.\n\n"
                                + lineBreak);
                }
            } catch (SaberCommandNotFoundException e) {
                System.out.println(lineBreak + "\n      I'm sorry, Master.\n"
                        + "      I don't ... understand your command.\n\n"
                        + lineBreak);
            }

        }
        endSaber();
    }

    public static void main(String[] args) {
        new Saber(System.getProperty("user.dir")).run();
    }
}
