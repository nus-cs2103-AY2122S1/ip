package brobot.storage;

import java.awt.Dimension;
import java.io.File;
import javax.swing.JFileChooser;
import javax.swing.JPanel;

/**
 * Represents the GUI to choose a file.
 * Credits: https://stackoverflow.com/questions/10083447/selecting-folder-destination-in-java/10083508
 */
public class DemoJFileChooser extends JPanel {

    private JFileChooser chooser;
    private final Storage currentStorage;
    private final String chooserTitle = "Select directory";

    public DemoJFileChooser(Storage currentStorage) {
        this.currentStorage = currentStorage;
    }

    /**
     * Open a GUI to allow the user to select a directory
     * @return The selected directory
     */
    public File selectFile() {
        chooser = new JFileChooser();
        chooser.setCurrentDirectory(new java.io.File("."));
        chooser.setDialogTitle(chooserTitle);
        chooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);
        //
        // disable the "All files" option.
        //
        chooser.setAcceptAllFileFilterUsed(false);
        //
        if (chooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
            return chooser.getSelectedFile();
        } else {
            return currentStorage.getCurrentFile();
        }
    }

    public Dimension getPreferredSize() {
        return new Dimension(200, 200);
    }
}
