package storage;

import model.vocab.VocabList;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.Collectors;

public class VocabularyStorage extends Storage {

    public static final String VOCAB_PATH = "/vocab";

    private VocabList vocabListRead;

    public VocabularyStorage(String fileName) throws IOException {
        this.filePath = DIRECTORY_PATH + VOCAB_PATH + "/" + fileName + ".txt";
        WRITER = new BufferedWriter(new FileWriter(filePath, true));
        READER = new BufferedReader(new FileReader(filePath));
    }

    public static boolean contains(String fileName) throws IOException {
        String full_file_name = fileName + ".txt";

        if (!haveSaveLocation()) {
            createSaveLocation();
        }
        return new ArrayList<>(Arrays.stream(Storage.getFilesFromDirectory(Storage.DIRECTORY_PATH + VOCAB_PATH))
                .map(File::getName).collect(Collectors.toList())).contains(full_file_name);
    }

    /**
     * load taskList from where the reader and writer is currently at
     *
     * @return TaskList read from the save file
     */
    public VocabList loadVocabList() {

        vocabListRead = new VocabList();

        READER.lines().forEach((line) -> {

        });

        return vocabListRead;
    }

}
