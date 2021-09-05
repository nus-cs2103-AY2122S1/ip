package duke;

import duke.contact.Contact;

import java.util.ArrayList;

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

    public void addContact(Contact c) {
        ui.addDialog("Got it. I'll add this contact: \n" + c.toString(), true);
        listOfContacts.add(c);
        ui.addDialog("Now you've got " + listOfContacts.size() + " contacts in your list.", true);
        this.storage.updateContactsSaveFile(this);
    }

    public void addContact(String name, String phoneNumber, String email, String notes) {
        Contact newContact = new Contact(name, phoneNumber, email, notes);
        listOfContacts.add(newContact);
    }

    /**
     * Removes a specified task from the list.
     *
     * @param i The index of the task.
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

    public Contact getContact(int i) {
        if (i > listOfContacts.size()) {
            throw new DukeException("Contact does not exist!");
        } else {
            return listOfContacts.get(i);
        }
    }

    public void printAllContacts() {
        assert this.ui != null;
        ui.addDialog("Here are the contacts in your list:", true);
        String list = "";
        for (int i = 0; i < listOfContacts.size(); i++) {
            list += (i + 1) + ". " + listOfContacts.get(i).toString() + "\n" + "\n";
        }
        ui.addDialog(list, true);
    }

    public int numberOfContacts() {
        return this.listOfContacts.size();
    }
}
