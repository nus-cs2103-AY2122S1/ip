package bobbybot.commands;

import bobbybot.exceptions.BobbyException;
import bobbybot.person.Address;
import bobbybot.person.Email;
import bobbybot.person.Name;
import bobbybot.person.Person;
import bobbybot.person.Phone;
import bobbybot.util.PersonList;
import bobbybot.util.Storage;
import bobbybot.util.TaskList;
import bobbybot.util.Ui;

public class AddContactCommand extends Command {
    private Person toAdd;

    /**
     * Prepares to add person to contact list
     * @param name name of person
     * @param email email of person
     * @param phone phone number of person
     * @param address address of person
     */
    public AddContactCommand(Name name, Email email, Phone phone, Address address) {
        this.toAdd = new Person(name, email, phone, address);
    }

    /**
     * Executes command
     *
     * @param tasks    task list
     * @param ui       ui
     * @param storage  storage
     * @param contacts
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, PersonList contacts) {
        contacts.addPerson(toAdd);
        try {
            storage.save(contacts);
        } catch (BobbyException e) {
            System.out.println(e.getMessage());
        }
        response = "Got it. I've added this contact:\n  " + toAdd + "\n"
                + "Now you have " + contacts.size() + " contacts";
    }
}
