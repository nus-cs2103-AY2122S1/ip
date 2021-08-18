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

    protected static String listMessage = "      Would you like to know what you \n" +
            "      told me to remember?\n" + "\n" +
            "      I'll list them for you, Master.\n";

    protected static ArrayList<Task> taskList = new ArrayList<>();

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

    public static void handleAddTask(String task) {
        Task newTask = new Task(task);
        taskList.add(newTask);
        System.out.println(lineBreak + "\n      I have added: "  + task + "\n\n" + lineBreak);
    }

    public static void handleDeadlineTask(String task, String time) {
        Task deadline = new Deadline(task, time);
        taskList.add(deadline);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Right, Master.\n"
                +  "      I'll ensure that you will \n      do this task before the deadline: \n"
                + "\n        " + deadline);
        String taskPlural = totalTask <= 1 ? "has " + totalTask + " task "
                : "have " + totalTask + " tasks ";
        System.out.println("\n      Currently, Master " + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public static void handleDeleteTask(int taskIndex) throws TaskNotFoundException {
        int totalTask = taskList.size();
        if (taskIndex >= totalTask || taskIndex < 0) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskList.get(taskIndex);
        taskList.remove(taskIndex);
        totalTask--;
        System.out.println(lineBreak + "\n      Understand, Master.\n"
                + "      I have deleted this task.\n"
                + "\n        " + task + "\n");
        String taskPlural = totalTask <= 1 ? "has " + totalTask + " task "
                : "have " + totalTask + " tasks ";
        System.out.println("      Currently, Master " + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public static void handleDoneTask(int taskIndex) throws TaskNotFoundException {
        int totalTask = taskList.size();
        if (taskIndex >= totalTask || taskIndex < 0) {
            throw new TaskNotFoundException("Task not found");
        }
        Task task = taskList.get(taskIndex);
        task.markAsDone();
        System.out.println(lineBreak + "\n      Understand, Master.\n" + "      I'll mark this done right away.\n"
                + "\n        " + task + "\n\n" + lineBreak);
    }

    public static void handleEventTask(String task, String time) {
        Task event = new Event(task, time);
        taskList.add(event);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Right, Master.\n"
                +  "      I'll make sure you remember \n      to come to this event: \n"
                + "\n        " + event);
        String taskPlural = totalTask <= 1 ? "has " + totalTask + " task "
                : "have " + totalTask + " tasks ";
        System.out.println("\n      Currently, Master " + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public static void handleListTask() {
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

    public static void handleTodoTask(String task) {
        Task todo = new ToDo(task);
        taskList.add(todo);
        int totalTask = taskList.size();
        System.out.println(lineBreak + "\n      Yes, Master.\n"
                +  "      I'll add the following to your Todo list: \n"
                + "\n        " + todo);
        String taskPlural = totalTask <= 1 ? "has " + totalTask + " task "
                : "have " + totalTask + " tasks ";
        System.out.println("\n      Currently, Master " + taskPlural +
                "\n      in the list." + "\n\n" + lineBreak);
    }

    public static void main(String[] args) {
        boolean end = false;
        Scanner sc = new Scanner(System.in);
        System.out.println("\n" + logo);
        System.out.println(greeting);
        while (!end) {
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
                            System.out.println(lineBreak + "      I'm sorry, Master.\n"
                                    + "      What ... exactly do you want me to add?\n"
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
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure which task you want me\n"
                                    + "      to mark as done...\n"
                                    + lineBreak);
                        } catch (TaskNotFoundException e) {
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I'm unable to find the task that\n"
                                    + "      you specified...\n"
                                    + lineBreak);
                        }
                        break;

                    case deadline:
                        try {
                            String deadlineTask = command.getArgument();
                            String deadlineTime = command.getTime();
                            handleDeadlineTask(deadlineTask, deadlineTime);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure what task you want me\n"
                                    + "      to add ...\n"
                                    + lineBreak);
                        } catch (MissingTimeException e) {
                            System.out.println(lineBreak + "      I'm sorry, Master.\n"
                                    + "      I won't be able to ensure that you\n"
                                    + "      do the task on time without \n"
                                    + "      knowing the deadline...\n"
                                    + lineBreak);
                        }
                        break;

                    case delete:
                        try {
                            int taskIndex = Integer.parseInt(command.getArgument());
                            handleDeleteTask(taskIndex - 1);
                        } catch (MissingArgumentException | NumberFormatException e) {
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I'm ... not sure which task you want\n"
                                    + "      to delete...\n"
                                    + lineBreak);
                        } catch (TaskNotFoundException e) {
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I'm unable to find the task that\n"
                                    + "      you want to delete...\n"
                                    + lineBreak);
                        }
                        break;

                    case event:
                        try {
                            String eventTask = command.getArgument();
                            String eventTime = command.getTime();
                            handleEventTask(eventTask, eventTime);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "      I'm sorry, Master.\n"
                                    + "      I'm ... not sure what event you want me\n"
                                    + "      to add ...\n"
                                    + lineBreak);
                        } catch (MissingTimeException e) {
                            System.out.println(lineBreak + "      I'm really sorry, Master.\n"
                                    + "      I won't be able to remind you\n"
                                    + "      to come to this event without\n"
                                    + "      knowing the time of the event...\n"
                                    + lineBreak);
                        }
                        break;

                    case todo:
                        try {
                            String taskForTodo = command.getArgument();
                            handleTodoTask(taskForTodo);
                        } catch (MissingArgumentException e) {
                            System.out.println(lineBreak + "      I'm sorry, Master.\n"
                                    + "      What ... exactly do you want me to add\n"
                                    + "      to your Todo list?\n"
                                    + lineBreak);
                        }
                        break;

                    case list:
                        handleListTask();
                        break;

                    default:
                        System.out.println(lineBreak + "      I'm sorry, Master. I can't fulfill your command.\n"
                                + "      What you want from me is beyond my capability.\n"
                                + lineBreak);
                }
            } catch (SaberCommandNotFoundException e) {
                System.out.println(lineBreak + "      I'm sorry, Master.\n"
                        + "      I don't ... understand your command.\n"
                        + lineBreak);
            }
        }
        System.out.println(goodbye);
    }
}
