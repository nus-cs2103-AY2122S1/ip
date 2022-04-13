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

import kayu.exception.KayuException;
import kayu.exception.StorageException;
import kayu.service.NoteList;
import kayu.service.TaskList;
import kayu.storage.NoteStorage;
import kayu.storage.TaskStorage;
import kayu.task.Event;
import kayu.task.Task;

public class EventCommandTest {

    private static final List<String> DATE_LIST = new ArrayList<>();
    private static final List<String> TIME_LIST = new ArrayList<>();
    private static final String RESOURCE_PATH = "src/test/resources";
    private static final String TASK_FILE_PATH = RESOURCE_PATH + "/task_storage_test_blank.txt";
    private static final String NOTE_FILE_PATH = RESOURCE_PATH + "/note_storage_test_blank.txt";

    private final TaskList taskList = new TaskList();
    private final TaskStorage taskStorage = TaskStorage.generate(TASK_FILE_PATH);
    private final NoteList noteList = new NoteList();
    private final NoteStorage noteStorage = NoteStorage.generate(NOTE_FILE_PATH);

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
        taskList.initializeTasks(taskStorage.load());
        noteList.initializeNotes(noteStorage.load());
    }

    @AfterEach
    public void tearDown() throws StorageException {
        taskStorage.save(new ArrayList<>());
        noteStorage.save(new ArrayList<>());
    }

    @Test
    public void testExecute() {
        String date = DATE_LIST.get(0);
        String time = TIME_LIST.get(0);
        String desc = "meeting with friends";
        String parameters = desc + " /at " + date + ' ' + time;
        Command eventCommand = new EventCommand(parameters);
        Task expectedTask = new Event(
                "meeting with friends",
                LocalDate.parse(DATE_LIST.get(0)),
                LocalTime.parse(TIME_LIST.get(0)));
        String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, 1);

        try {
            String feedback = eventCommand.execute(taskList, taskStorage, noteList, noteStorage);
            assertEquals(expectedFeedback, feedback);

        } catch (KayuException | StorageException exception) {
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
                Command eventCommand = new EventCommand(parameters);
                String feedback = eventCommand.execute(taskList, taskStorage, noteList, noteStorage);

                String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, idx + 1);
                assertEquals(expectedFeedback, feedback);

            } catch (KayuException | StorageException exception) {
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
                Command eventCommand = new EventCommand(parameters);
                String feedback = eventCommand.execute(taskList, taskStorage, noteList, noteStorage);

                String expectedFeedback = String.format(MESSAGE_CREATED_EVENT, expectedTask, idx + 1);
                assertEquals(expectedFeedback, feedback);

            } catch (KayuException | StorageException exception) {
                System.out.println(exception.getMessage());
                fail();
            }
        }
    }

    @Test
    public void execute_invalidDateFormat_throwsException() throws StorageException {
        String parameters = "meeting with friends /at 20.10.2021 13:45";
        Command eventCommand = new EventCommand(parameters);

        try {
            eventCommand.execute(taskList, taskStorage, noteList, noteStorage);
            fail();

        } catch (KayuException exception) {
            assertEquals(CommandMessage.ERROR_IMPROPER_DATE, exception.getMessage());
        }
    }

    @Test
    public void execute_invalidParamFormat_throwsException() throws StorageException {
        String parameters = "meeting with friends /at 13:45";
        Command eventCommand = new EventCommand(parameters);

        try {
            eventCommand.execute(taskList, taskStorage, noteList, noteStorage);
            fail();

        } catch (KayuException exception) {
            String expected = String.format(
                    ERROR_IMPROPER_FORMATTING,
                    EventCommand.COMMAND_WORD,
                    Event.SPLIT_WORD);
            assertEquals(expected, exception.getMessage());
        }
    }
}
