package katheryne;

// import task classes
import katheryne.task.Deadline;
import katheryne.task.Event;
import katheryne.task.Task;
import katheryne.task.Todo;

import java.nio.charset.StandardCharsets;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.As.PROPERTY;
import static com.fasterxml.jackson.annotation.JsonTypeInfo.Id.NAME;
import com.fasterxml.jackson.annotation.PropertyAccessor;

import com.fasterxml.jackson.core.util.DefaultPrettyPrinter;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@JsonTypeInfo(use = NAME, include = PROPERTY)

/**
 * Chat bot katheryne.Katheryne, used for simple todo lists
 */
public class Katheryne {
    public static void main(String[] args) throws KatheryneExceptions {
        List<Task> lst = new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper().enableDefaultTyping();
        mapper.registerModule(new JavaTimeModule());

//        loadTaskList("tasks.json", mapper);
        if (Files.isReadable(Paths.get("tasks.json"))) {
            try {
                lst.addAll(Arrays.asList(mapper.readValue(
                        Paths.get("tasks.json").toFile(), 
                        Task[].class)));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Ad astra abyssosque! I am Katheryne, the receptionist here at the Adventurers' Guild.");
        System.out.println("How may I assist?");
        if (!lst.isEmpty()) {
            System.out.println("I still have your list of tasks from last time, it has " + lst.size() + " items.");
        }
        Scanner in = new Scanner(System.in);
        while (true) {
            try {
                String userInput = in.nextLine();
                String[] keywordInput = userInput.split(" ", 2);
                if (userInput.equalsIgnoreCase("BYE")) {
                    break;
                } else if (keywordInput[0].equalsIgnoreCase("done") || keywordInput[0].equalsIgnoreCase("complete")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("Traveller, please specify by index which task you completed.");
                    }
                    int i = Integer.parseInt(keywordInput[1]);
                    if (i <= lst.size() && i > 0) {
                        lst.get(i - 1).markAsDone();
                        System.out.println(lst.get(i - 1));
                    } else {
                        throw new KatheryneExceptions("That's not a valid index...");
                    }
                } else if (keywordInput[0].equalsIgnoreCase("delete")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("Traveller, please specify by index what I should delete.");
                    }
                    int i = Integer.parseInt(keywordInput[1]);
                    if (i <= lst.size() && i > 0) {
                        System.out.println("Okay, I'll delete the following item:");
                        System.out.println(lst.remove(i - 1));
                    } else {
                        throw new KatheryneExceptions("That's not a valid index...");
                    }
                } else if (userInput.equalsIgnoreCase("list")) {
                    // return the list of items stored
                    System.out.println("Here's the list I've stored for you:");
                    for (int i = 1; i <= lst.size(); i++) {
                        System.out.println(i + ") " + lst.get(i - 1));
                    }
                } else if (keywordInput[0].equalsIgnoreCase("deadline")) {
                    try {
                        String[] parsedDeadline = keywordInput[1].split("/by");
                        if (parsedDeadline[0].isEmpty()) {
                            throw new KatheryneExceptions(
                                    "A deadline needs a description and a /by time in the format 2007-12-03. The " 
                                            + "description is missing~");
                        }
                        if (parsedDeadline.length == 2) {
                            lst.add(new Deadline(parsedDeadline[0].trim(), LocalDate.parse(parsedDeadline[1].trim())));
                        } else {
                            throw new KatheryneExceptions(
                                    "A deadline needs a description and a /by time in the format 2007-12-03.");
                        }
                        System.out.println(
                                "'" + parsedDeadline[0] + "' added to your list, do by " + parsedDeadline[1]);
                    } catch (DateTimeParseException e) {
                        throw new KatheryneExceptions(
                                "The by time is in the wrong format. It must be in the format YYYY-MM-DD");
                    }
                } else if (keywordInput[0].equalsIgnoreCase("event")) {
                    try {
                        String[] parsedEvent = keywordInput[1].split(" /at ");
                        if (parsedEvent[0].isEmpty()) {
                            throw new KatheryneExceptions(
                                    "An event needs a description and an /at time when it occurs in the format " 
                                            + "2007-12-03. The description is missing");
                        }
                        if (parsedEvent.length == 2) {
                            lst.add(new Event(parsedEvent[0].trim(), LocalDate.parse(parsedEvent[1].trim())));
                            System.out.println(
                                    "'" + parsedEvent[0] + "' added to your list, scheduled for " + parsedEvent[1]);
                        } else {
                            throw new KatheryneExceptions(
                                    "An event needs a description and an /at time when it occurs in the format " 
                                            + "2007-12-03.");
                        }
                    } catch (DateTimeParseException e) {
                        throw new KatheryneExceptions(
                                "The at time is in the wrong format. It must be in the format YYYY-MM-DD");
                    }
                } else if (keywordInput[0].equalsIgnoreCase("todo")) {
                    if (keywordInput.length == 1) {
                        throw new KatheryneExceptions("A todo needs a description ");
                    } else {
                        lst.add(new Todo(keywordInput[1].trim()));
                        System.out.println("Todo item '" + keywordInput[1] + "' added to your list");
                    }
                } else {
                    throw new UnknownCommandException();
                }
                System.out.println("There are currently " + lst.size() + " items in your list.");
            } catch (UnknownCommandException e) {
                System.out.println(e.getMessage());
            } catch (KatheryneExceptions e) {
                System.out.println(e.getMessage());
            } catch (NumberFormatException e) {
                System.out.println("You need to specify a number in the correct format. ERROR: "
                        + e.getMessage());
            }
        }
        // give object mapper the ability to write the properties of tasks to json
        mapper.setVisibility(PropertyAccessor.FIELD, JsonAutoDetect.Visibility.ANY);
        ObjectWriter writer = mapper.writer(new DefaultPrettyPrinter());

        // try to save the file
        try {
            Files.write(Paths.get("tasks.json"), writer.writeValueAsString(lst).getBytes(StandardCharsets.UTF_8));
            System.out.println("Ad Astra Abyssoque, Traveller!");
        } catch (IOException e) {
            System.out.println("Oh wait! Traveller, I couldn't catch that...");
            e.printStackTrace();
        }
    }
}
