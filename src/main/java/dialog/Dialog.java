package dialog;

import java.util.ArrayList;
import java.util.HashMap;

import dialog.exceptions.DialogException;

/**
 * Dialog class deal with printing feedback to the user.
 * Dialog stores sentences into an ArrayList before printing them in separate line.
 *
 * @author Kan Jitpakdi
 * @author GitHub: kanjitp
 * @version 0.03
 * @since 0.02
 */
public class Dialog {

    /** archive to be shared with its subclasses */
    protected static HashMap<String, Dialog> archive = new HashMap<>();

    /** sentences stored in the dialog */
    protected final ArrayList<String> sentences;

    /**
     * Constructor for the dialog.
     * Set the sentences to the given sentences in the constructor.
     *
     * @param sentences sentences to be stored in the dialog.
     */
    protected Dialog(ArrayList<String> sentences) {
        this.sentences = sentences;
    }

    /**
     * Add sentence to current dialog.
     *
     * @param sentence sentence to be added on each line of the dialog.
     */
    public void add(String sentence) {
        this.sentences.add(sentence);
    }

    /**
     * Generate dialog of specified Id.
     * Dialog is not to have the same Id to avoid duplication.
     *
     * @param id the id of the dialog.
     * @return the dialog of specified id.
     * @throws DialogException dialog cannot have the same id while the app is running.
     */
    public static Dialog generate(String id) throws DialogException {
        if (archive.containsKey(id)) {
            throw new DialogException(id + " already exists");
        }
        final Dialog newDialog = new Dialog(new ArrayList<>());
        archive.put(id, newDialog);
        return newDialog;
    }

    /**
     * Return whether the archive of the Dialog class have the dialog with specified id.
     *
     * @param id id to check if the archive contains dialog of such id or not.
     * @return whether the archive have stored Dialog of specified Id.
     */
    public static boolean containsId(String id) {
        return archive.containsKey(id);
    }

    /**
     * Get existing dialog of existing Id.
     *
     * @param id id to get the dialog from the archive.
     * @return return the Dialog in archive with the specified id.
     */
    public static Dialog get(String id) throws DialogException {
        if (!containsId(id)) {
            throw new DialogException("The archive does not contain the id " + id);
        }
        return archive.get(id);
    }

    /**
     * Return string representation of Dialog with its sentences separated into separate lines.
     *
     * @return string representation of Dialog.
     */
    @Override
    public String toString() {
        return this.sentences.stream().reduce("", (s1, s2) -> s1 + s2 + "\n");
    }
}
