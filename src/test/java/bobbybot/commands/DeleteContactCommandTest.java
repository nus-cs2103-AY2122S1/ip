package bobbybot.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.File;
import java.util.ArrayList;
import java.util.Collections;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;

import bobbybot.person.Address;
import bobbybot.person.Email;
import bobbybot.person.Name;
import bobbybot.person.Person;
import bobbybot.person.Phone;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;


public class DeleteContactCommandTest {
    private static final String STORAGE_PATH = "test.txt";
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(STORAGE_PATH);
    private final PersonList contacts = new PersonList(Collections.emptyList());
    @AfterAll
    public static void cleanUp() {
        File file = new File(STORAGE_PATH);
        file.delete();
    }

    @Test
    public void deleteContactCommand_validInput_success() {

        Name name = new Name("test");
        Email email = new Email("test@test");
        Phone phone = new Phone("123");
        Address address = new Address("home");
        contacts.addPerson(new Person(name, email, phone, address));
        DeleteContactCommand c = new DeleteContactCommand(1);
        c.execute(tasks, ui, storage, contacts);
        assertEquals(0, tasks.getTasks().size());
    }

    @Test
    public void deleteCommand_lessThanOne_errorResponse() {
        int taskNumToDelete = 0;
        DeleteCommand c = new DeleteCommand(taskNumToDelete);
        c.execute(tasks, ui, storage, contacts);
        String response = c.getResponse();
        String expected = "Invalid delete command! Task number: " + taskNumToDelete + " does not exist\n"
                + "Use [list] to see available tasks!";
        assertEquals(expected, response);
    }

    @Test
    public void deleteCommand_moreThanSize_errorResponse() {
        int taskNumToDelete = 1;
        DeleteCommand c = new DeleteCommand(taskNumToDelete);
        c.execute(tasks, ui, storage, contacts);
        String response = c.getResponse();
        String expected = "Invalid delete command! Task number: " + taskNumToDelete + " does not exist\n"
                + "Use [list] to see available tasks!";
        assertEquals(expected, response);
    }
}
