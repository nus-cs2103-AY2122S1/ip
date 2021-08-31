package kayu.commands;

import static kayu.commands.CommandMessage.ERROR_IMPROPER_FORMATTING;
import static kayu.commands.CommandMessage.MESSAGE_CREATED_EVENT;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import kayu.exception.DukeException;
import kayu.exception.StorageException;
import kayu.parser.DateTimeFormat;
import kayu.service.TaskList;
import kayu.storage.Storage;
import kayu.task.Event;
import kayu.task.Task;

public class EventCommandTest {

    private static final List<String> DATE_LIST = new ArrayList<>();
    private static final List<String> TIME_LIST = new ArrayList<>();
    private static final String RESOURCE_PATH = "src/test/resources";
    private static final String FILE_PATH = RESOURCE_PATH + "/storage_test_blank.txt";
    
    private TaskList taskList;
    private DateTimeFormat dateTimeFormat;
    private Storage storage;
    
    @BeforeAll
    public static void setFormats() {
        DATE_LIST.clear();
        DATE_LIST.add("2021-08-20"); // default format
        DATE_LIST.add("2021-8-20");
        DATE_LIST.add("20-08-2021");
        DATE_LIST.add("20-8-2021");
        DATE_LIST.add("2021/08/20");
        DATE_LIST.add("2021/8/20");
        DATE_LIST.add("20/08/2021");
        DATE_LIST.add("20/8/2021");
        
        TIME_LIST.clear();
        TIME_LIST.add("22:30"); // default format
        TIME_LIST.add("2230");
        TIME_LIST.add("10:30 PM");
        TIME_LIST.add("10:30 pm");
        TIME_LIST.add("1030 PM");
        TIME_LIST.add("1030 pm");
    }

    @BeforeEach
    public void setUp() {
        taskList = new TaskList();
        dateTimeFormat = DateTimeFormat.generateInstance();
        storage = new Storage();
        storage.setDirectoryAndFilePath(FILE_PATH);
    }
    
    @AfterEach
    public void tearDown() throws StorageException {
        storage.saveTasks(new ArrayList<>());
    }
    
    @Test
    public void testExecute() {
        String date = DATE_LIST.get(0);
        String time = TIME_LIST.get(0);
        String desc = "meeting with friends";
        String parameters = desc + " /at " + date + ' ' + time;
        Command eventCommand = new EventCommand(parameters, dateTimeFormat);
        Task expectedTask = new Event(
                "meeting with friends",
                LocalDate.parse(DATE_LIST.get(0)),
                LocalTime.parse(TIME_LIST.get(0)));
        String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, 1);
        
        try {
            String feedback = eventCommand.execute(taskList, storage);
            assertEquals(expectedFeedback, feedback);
            
        } catch (DukeException | StorageException exception) {
            System.out.println(exception.getMessage());
            fail();
        }
    }

    @Test
    public void testExecuteWithVariedDateFormats() {
        String time = TIME_LIST.get(0);
        String desc = "meeting with friends";
        String paramFormat = desc + " /at %s " + time;
        Task expectedTask = new Event(
                "meeting with friends",
                LocalDate.parse(DATE_LIST.get(0)),
                LocalTime.parse(TIME_LIST.get(0)));
    
        for (int idx = 0; idx < DATE_LIST.size(); idx++) {
            try {
                String parameters = String.format(paramFormat, DATE_LIST.get(idx));
                Command eventCommand = new EventCommand(parameters, dateTimeFormat);
                String feedback = eventCommand.execute(taskList, storage);
                
                String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, idx + 1);
                assertEquals(expectedFeedback, feedback);
                
            } catch (DukeException | StorageException exception) {
                System.out.println(exception.getMessage());
                fail();
            }
        }
    }

    @Test
    public void testExecuteWithVariedTimeFormats() {
        String date = DATE_LIST.get(0);
        String desc = "meeting with friends";
        String paramFormat = desc + " /at " + date + " %s";
        Task expectedTask = new Event(
                "meeting with friends",
                LocalDate.parse(DATE_LIST.get(0)),
                LocalTime.parse(TIME_LIST.get(0)));

        for (int idx = 0; idx < TIME_LIST.size(); idx++) {
            try {
                String parameters = String.format(paramFormat, TIME_LIST.get(idx));
                Command eventCommand = new EventCommand(parameters, dateTimeFormat);
                String feedback = eventCommand.execute(taskList, storage);

                String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, idx + 1);
                assertEquals(expectedFeedback, feedback);

            } catch (DukeException | StorageException exception) {
                System.out.println(exception.getMessage());
                fail();
            }
        }
    }

    @Test
    public void execute_invalidDateFormat_throwsException() throws StorageException {
        String parameters = "meeting with friends /at 20.10.2021 13:45";
        Command eventCommand = new EventCommand(parameters, dateTimeFormat);

        try {
            eventCommand.execute(taskList, storage);
            fail();

        } catch (DukeException exception) {
            assertEquals(CommandMessage.ERROR_IMPROPER_DATE, exception.getMessage());
        }
    }

    @Test
    public void execute_invalidParamFormat_throwsException() throws StorageException {
        String parameters = "meeting with friends /at 13:45";
        Command eventCommand = new EventCommand(parameters, dateTimeFormat);

        try {
            eventCommand.execute(taskList, storage);
            fail();

        } catch (DukeException exception) {
            String expected = String.format(
                    ERROR_IMPROPER_FORMATTING,
                    EventCommand.COMMAND_WORD,
                    Event.SPLIT_WORD);
            assertEquals(expected, exception.getMessage());
        }
    }
}
