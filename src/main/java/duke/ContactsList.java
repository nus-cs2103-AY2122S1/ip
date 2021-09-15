package duke;

import duke.contact.Contact;
import java.util.ArrayList;

/**
 * The ContactsList class encapsulates the contacts list.
 *
 * @author Loh Wen Hao Aaron
 */
public class ContactsList {
    private ArrayList<Contact> listOfContacts = new ArrayList<>();
    private ArrayList<String> saveFileInput;
    private Storage storage;
    private Ui ui;

    /**
     * Default constructor to be used if a save file cannot be loaded.
     *
     */
    public ContactsList(Ui ui) {
        this.ui = ui;
    }

    /**
     * Overloaded constructor to be used if a save file is loaded.
     *
     * @param saveFileInput the ArrayList containing the save file.
     * @param storage the Storage object.
     * @param ui the Ui object.
     */
    public ContactsList(ArrayList<String> saveFileInput, Storage storage, Ui ui) {
        this.saveFileInput = saveFileInput;
        this.storage = storage;
        this.ui = ui;
        initialiseContactsList();
    }

    /**
     * Reads the contacts save file and adds the contacts in the save file
     * to the contacts list.
     *
     */
    public void initialiseContactsList() {
        System.out.println("Loading contacts list save file...");
        String[] strArray = new String[4];
        int index = 0;

        for (int i = 0; i < saveFileInput.size(); i++) {
            if (!saveFileInput.get(i).equals("")) {
                strArray[index] = saveFileInput.get(i);
                if (index == 3) {
                    Contact newContact = new Contact(strArray[0], strArray[1], strArray[2], strArray[3]);
                    listOfContacts.add(newContact);
                    index = 0;
                } else {
                    index++;
                }
            }
        }
    }

    /**
     * Adds a specified contact to the contacts list.
     *
     * @param c The contact that is to be added to the list.
     */
    public void addContact(Contact c) {
        ui.addDialog("Got it. I'll add this contact: \n" + c.toString(), true);
        listOfContacts.add(c);
        ui.addDialog("Now you've got " + listOfContacts.size() + " contacts in your list.", true);
        this.storage.updateContactsSaveFile(this);
    }

    /**
     * Adds a contact by specifying its fields manually into the contacts list.
     *
     * @param name The name of the contact.
     * @param phoneNumber The phone number of the contact.
     * @param email The email of the contact.
     * @param notes Notes attached to the contact.
     */
    public void addContact(String name, String phoneNumber, String email, String notes) {
        Contact newContact = new Contact(name, phoneNumber, email, notes);
        listOfContacts.add(newContact);
    }

    /**
     * Removes a specified contact from the list.
     *
     * @param i The index of the contact.
     */
    public void removeContact(int i) {
        if (i > listOfContacts.size()) {
            throw new DukeException("Contact does not exist!");
        } else {
            ui.addDialog("Got it. I'll remove this contact: \n" + listOfContacts.get(i), true);
            listOfContacts.remove(i);
            ui.addDialog("Now you've got " + listOfContacts.size() + " contacts in your list.", true);
            this.storage.updateContactsSaveFile(this);
        }
        this.storage.updateContactsSaveFile(this);
    }

    /**
     * Get a contact specified by the index.
     *
     * @param i The index of the contact.
     * @return The specified contact.
     */
    public Contact getContact(int i) {
        if (i > listOfContacts.size()) {
            throw new DukeException("Contact does not exist!");
        } else {
            return listOfContacts.get(i);
        }
    }

    /**
     * Prints all contacts in the contacts list.
     *
     * @return All contacts in the contacts list.
     */
    public void printAllContacts() {
        assert this.ui != null;
        ui.addDialog("Here are the contacts in your list:", true);
        String list = "";
        for (int i = 0; i < listOfContacts.size(); i++) {
            list += (i + 1) + ". " + listOfContacts.get(i).toString() + "\n" + "\n";
        }
        ui.addDialog(list, true);
    }

    /**
     * Prints all the tasks list that have the specified word in the description.
     *
     * @return The size of the contacts list.
     */
    public int numberOfContacts() {
        return this.listOfContacts.size();
    }
}
