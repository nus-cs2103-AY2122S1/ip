package duke.utils;

import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import duke.task.Task;

// Level 7: Created duke.utils.Storage class to maintain storage state
public class Storage {

    private final File storageFile;
    private static final String tempFileName = "temp.txt";
    private final String fileName;
    private final String folderPath;
    private final String storagePath;


    public Storage(String folderPath, String fileName) {
        this.folderPath = folderPath;
        this.fileName = fileName;
        storagePath = folderPath
                .concat("\\")
                .concat(fileName);
        storageFile = getFile();
    }

    public File getFile() {
        try {
            createFile(folderPath,fileName);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new File(storagePath);
    }

    private void createFile(String folderPath, String fileName) throws IOException{
        File folder = new File(folderPath);
        if (!folder.exists()) {
            if (!folder.mkdirs()) {
                throw new IOException("\t" + "Internal error occurred while creating storage path");
            }
        }
        File fileObj = new File(folderPath
                .concat("\\")
                .concat(fileName)
        );
        if (!fileObj.exists()) {
            if (!fileObj.createNewFile()) {
                throw new IOException("\t" + "Internal error occurred while creating storage path");
            }
        }
    }

    // Level 7: save newly added items to the storage
    public void saveEntry(Task t) {
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter(storageFile,true));
            writer.write(t.getType().name()
                .concat(" | ")
                .concat(Boolean.toString(t.getState()))
                .concat(" | ")
                .concat(t.getDescription())
            );
            if (t.getBy() != null) {
                writer.write(" | ".concat(t.getBy().toString()));
            }
            if (t.getAt() != null) {
                writer.write(" | ".concat(t.getAt()));
            }
            writer.newLine();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Level 7: delete selected items to the storage
    public void deleteEntry(Task t) {
        try {
            File tempFile = new File(folderPath
                    .concat("\\")
                    .concat(tempFileName)
            );
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile,true));
            BufferedReader reader = new BufferedReader(new FileReader(storageFile));
            String toDelete = t.getType().name()
                    .concat(" | ")
                    .concat(Boolean.toString(t.getState()))
                    .concat(" | ")
                    .concat(t.getDescription());
            String curr;
            while(true){
                curr = reader.readLine();
                if (curr == null) {
                    break;
                }
                if (curr.trim().equals(toDelete)) {
                    continue;
                }
                writer.write(curr);
                writer.newLine();
            }
            writer.close();
            reader.close();
            if (!storageFile.delete()) {
                System.out.println("unsuccessful delete");
            }
            if (tempFile.renameTo(storageFile)) {
                System.out.println("unsuccessful rename");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
