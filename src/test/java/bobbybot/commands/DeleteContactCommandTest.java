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
    private static final String CONTACTS_STORAGE_PATH = "contacts_test.txt";
    private final TaskList tasks = new TaskList(new ArrayList<>());
    private final Ui ui = new Ui();
    private final Storage storage = new Storage(STORAGE_PATH, CONTACTS_STORAGE_PATH);
    private final PersonList contacts = new PersonList(new ArrayList<>());
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
        assertEquals(1, contacts.size());
        DeleteContactCommand c = new DeleteContactCommand(1);
        c.execute(tasks, ui, storage, contacts);
        assertEquals(0, contacts.size());
    }

    @Test
    public void deleteContactCommand_lessThanOne_errorResponse() {
        int contactNumToDelete = 0;
        DeleteContactCommand c = new DeleteContactCommand(contactNumToDelete);
        c.execute(tasks, ui, storage, contacts);
        String response = c.getResponse();
        String expected = "Invalid delete command! Contact number: " + contactNumToDelete + " does not exist\n"
                + "Use [list_contact] to see available contacts!";
        assertEquals(expected, response);
    }

    @Test
    public void deleteCommand_moreThanSize_errorResponse() {
        int contactNumToDelete = 1;
        DeleteContactCommand c = new DeleteContactCommand(contactNumToDelete);
        c.execute(tasks, ui, storage, contacts);
        String response = c.getResponse();
        String expected = "Invalid delete command! Contact number: " + contactNumToDelete + " does not exist\n"
                + "Use [list_contact] to see available contacts!";
        assertEquals(expected, response);
    }
}
