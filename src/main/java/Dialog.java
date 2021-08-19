import java.util.ArrayList;
import java.util.HashMap;

public class Dialog {
    protected static HashMap<String, Dialog> archive = new HashMap<>();
    protected final ArrayList<String> sentences;

    protected Dialog(ArrayList<String> sentences) {
        this.sentences = sentences;
    };

    public void add(String sentence) {
        this.sentences.add(sentence);
    }

    public int length() {
        return sentences.size();
    }

    public static boolean have(String id) {
        return archive.containsKey(id);
    }

    public static Dialog generate(String id) throws DialogException {
        if (archive.containsKey(id)) {
            throw new DialogException(id + " already exists");
        } else {
            final Dialog newDialog = new Dialog(new ArrayList<>());
            archive.put(id, newDialog);
            return newDialog;
        }
    }

    public static Dialog getDialog(String id) {
        return archive.get(id);
    }

    @Override
    public String toString() {
        String dialogs = this.sentences.stream().reduce("    ", (s1, s2) -> s1 + s2 + "\n    ");
        return "    ____________________________________________________________\n" +
                dialogs +
                "____________________________________________________________";
    }
}
