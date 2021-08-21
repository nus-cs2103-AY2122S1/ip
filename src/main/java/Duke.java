import java.io.IOException;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.nio.file.Path;
import java.nio.file.Files;
import java.util.regex.Pattern;

public class Duke {

    private enum Command {
        DONE,
        DELETE
    }

    private enum Format {
        LIST,
        SAVE
    }

    private static String wrapOutput(String s) {
        // Align list items properly
        // Adapted regex from https://stackoverflow.com/questions/15888934/how-to-indent-a-multi-line-paragraph-being-written-to-the-console-in-java

        String mstr = s.replaceAll("(?m)^", "\t\t\t ");
        return "\n\t@Herb:~$" + mstr.substring(3) + "\n";
    }

    private static ArrayList<Task> loadTaskList(String relativePath) {
        String home = System.getProperty("user.dir");
        Path path = Paths.get(home, relativePath);
        boolean directoryExists = Files.exists(path);

        if (directoryExists) {
            try {
                List<String> text = Files.readAllLines(path);
                ArrayList<Task> taskList = new ArrayList<>();
                for (String x: text) {
                    String[] data = x.split(Pattern.quote(" | "));
                    System.out.println(Arrays.toString(data));
                    Task t;
                    switch (data[1]) {
                        case "T":
                            t = new ToDo(data[3]);
                            break;
                        case "E":
                            LocalDate date = LocalDate.parse(data[4]);
                            if (data.length == 5) {
                                t = new Event(data[3], date);
                            } else {
                                String hour = data[5].replace(":", "").substring(0, 2);
                                String minute = data[5].replace(":", "").substring(2, 4);
                                LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                t = new Event(data[3], date, time);
                            }
                            break;
                        case "D":
                            LocalDate date2 = LocalDate.parse(data[4]);
                            if (data.length == 5) {
                                t = new Deadline(data[3], date2);
                            } else {
                                String hour = data[5].replace(":", "").substring(0, 2);
                                String minute = data[5].replace(":", "").substring(2, 4);
                                LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                t = new Deadline(data[3], date2, time);
                            }
                            break;
                        default:
                            System.out.println("Invalid task found when loading, skipped!");
                            continue;
                    }

                    if (data[2].equals("1")) {
                        t.markAsDone();
                    }
                    taskList.add(t);
                }
                return taskList;
            } catch (IOException e) {
                System.out.println("Couldn't load file :(");
                return new ArrayList<>();
            }
        } else {
            System.out.println("File not found :(");
            return new ArrayList<>();
        }
    }

    private static void saveTaskList(ArrayList<Task> taskList) {
        String home = System.getProperty("user.dir");
        String text = handleList(taskList, Format.SAVE);
        Path path = Paths.get(home, "data");
        boolean directoryExists = Files.exists(path);

        if (!directoryExists) {
            try {
                Files.createDirectory(path);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        try {
            Files.write(Paths.get(home,"data", "taskList.txt"), text.getBytes());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void handleAction(ArrayList<Task> taskList, String[] in, Command c) throws DukeException {
        if (in.length != 2) {
            throw new InvalidFormatException("`" + c.toString().toLowerCase() + " ${i}`");
        } else {
            int index;
            try {
                index = Integer.parseInt(in[1]);
            } catch (NumberFormatException ex) {
                throw new InvalidIntegerException();
            }
            if (index < 1 || index > taskList.size()) {
                throw new InvalidTaskNumberException();
            } else {
                if (c == Command.DONE) {
                    Task t = taskList.get(index - 1);
                    t.markAsDone();
                    System.out.println(wrapOutput("Nice, I've marked this task as done!\n   " +
                            t.toString()));
                } else if (c == Command.DELETE) {
                    Task t = taskList.remove(index - 1);

                    String plurality = " task";
                    if (taskList.size() != 1) {
                        plurality += "s";
                    }

                    System.out.println(wrapOutput("Noted, I've removed this task:\n   " +
                            t.toString() + "\nNow you have " + taskList.size()
                            + plurality + " in the list."));
                }
            }
        }
    }

    private static String handleList(ArrayList<Task> taskList, Format format) {
        if (taskList.size() == 0) {
            return "No tasks added yet!";
        }
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < taskList.size(); i++) {
            Task t = taskList.get(i);
            res.append(i + 1).append(". ").append(
                    format == Format.LIST ? t.toString() : t.toSaveString()
                ).append("\n");
        }
        return res.substring(0, res.length() - 1);
    }

    public static void main(String[] args) {
        String welcomeMessage = "\n\tHi! I'm Herbert, you can call me Herb  ٩(˘◡˘)۶\n"
                + "\tHow can I help you?\n\n"
                + "\tYou can type:\n"
                + "\t\t `list` to get a list of tasks\n"
                + "\t\t `todo ${item}` to add a todo\n"
                + "\t\t `deadline ${item} /by ${time}` to add a deadline\n"
                + "\t\t `event ${item} /at ${time}` to add an event\n"
                + "\t\t `done ${i}` to mark task i as completed\n"
                + "\t\t `delete ${i}` to delete task i\n"
                + "\t\t `bye` to end this chat\n";
        String endMessage = "\n\tSad to see you go :(\n\t...shutting down...";

        ArrayList<Task> taskList = loadTaskList("data/taskList.txt");

        System.out.println(welcomeMessage);
        Scanner sc = new Scanner(System.in);

        while (true) {
            try {
                System.out.print("--> ");
                String input = sc.nextLine();

                if (input.equals("bye")) {
                    System.out.println(endMessage);
                    break;
                } else if (input.equals("list")) {
                    System.out.println(wrapOutput(handleList(taskList, Format.LIST)));
                } else {
                    String[] words = input.split(" ");
                    String mainCommand = words[0];
                    Task t;

                    switch (mainCommand) {
                        case "done":
                            handleAction(taskList, words, Command.DONE);
                            saveTaskList(taskList);
                            continue;
                        case "delete":
                            handleAction(taskList, words, Command.DELETE);
                            saveTaskList(taskList);
                            continue;
                        case "todo": {
                            String[] split = input.split(" ");
                            if (split.length < 2) {
                                throw new MissingDescriptionException();
                            }
                            t = new ToDo(input.substring(5).trim());
                            break;
                        }
                        case "deadline": {
                            String[] split = input.split("/by ");
                            if (split.length < 2) {
                                throw new InvalidFormatException("`deadline ${item} /by ${time}`");
                            }
                            String[] datetime = split[1].trim().split(" ");
                            if (datetime.length == 1) {
                                try {
                                    LocalDate date = LocalDate.parse(datetime[0].replace("/", "-"));
                                    t = new Deadline(split[0].substring(9).trim(), date);
                                } catch (Exception e) {
                                    throw new InvalidDateTimeException();
                                }
                            } else if (datetime.length == 2) {
                                try {
                                    LocalDate date = LocalDate.parse(datetime[0].replace("/", "-"));
                                    String hour = datetime[1].replace(":", "").substring(0, 2);
                                    String minute = datetime[1].replace(":", "").substring(2, 4);
                                    LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                    t = new Deadline(split[0].substring(9).trim(), date, time);
                                } catch (Exception e) {
                                    throw new InvalidDateTimeException();
                                }
                            } else {
                                throw new InvalidDateTimeException();
                            }
                            break;
                        }
                        case "event": {
                            String[] split = input.split("/at ");
                            if (split.length < 2) {
                                throw new InvalidFormatException("`event ${item} /at ${time}`");
                            }
                            String[] datetime = split[1].trim().split(" ");
                            if (datetime.length == 1) {
                                try {
                                    LocalDate date = LocalDate.parse(datetime[0].replace("/", "-"));
                                    t = new Event(split[0].substring(5).trim(), date);
                                } catch (Exception e) {
                                    throw new InvalidDateTimeException();
                                }
                            } else if (datetime.length == 2) {
                                try {
                                    LocalDate date = LocalDate.parse(datetime[0].replace("/", "-"));
                                    String hour = datetime[1].replace(":", "").substring(0, 2);
                                    String minute = datetime[1].replace(":", "").substring(2, 4);
                                    LocalTime time = LocalTime.of(Integer.parseInt(hour), Integer.parseInt(minute));
                                    t = new Event(split[0].substring(5).trim(), date, time);
                                } catch (Exception e) {
                                    throw new InvalidDateTimeException();
                                }
                            } else {
                                throw new InvalidDateTimeException();
                            }
                            break;
                        }
                        default:
                            throw new UnknownCommandException();
                    }

                    taskList.add(t);
                    String plurality = " task";
                    if (taskList.size() != 1) {
                        plurality += "s";
                    }
                    System.out.println(wrapOutput("Got it! I've added this task:\n   "
                            + t.toString() + "\nNow you have " + taskList.size()
                            + plurality + " in the list."));


                    saveTaskList(taskList);
                }
            } catch (DukeException e) {
                System.out.println(wrapOutput(e.getMessage()));
            }
        }
    }
}