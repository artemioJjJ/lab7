package ru.itmo.p3131.student18.interim.commands.tools.parsers;

import ru.itmo.p3131.student18.client.tools.readers.InputStream;
import ru.itmo.p3131.student18.server.exeptions.NumberValueException;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.InvalidPathException;
import java.nio.file.Path;
import java.util.NoSuchElementException;
import java.util.Scanner;

/**
 * Class used to get access to file, containing script instructions.
 */
public class FileParser {
    private boolean parsingIsComplete = false;
    private Path pathToFile;
    private Scanner fileDataScanner = null;

    /**
     * This constructor is used for getting File object with path to file containing
     *  scripts for command Execute script.
     *
     * @param name name of file without filename extension.
     *
     * @throws FileNotFoundException - if file was not found.
     */
    public FileParser(String name) throws IOException, NumberValueException {
        this.fileDataScanner = new Scanner(scriptFileBuilder(name));
    }

    public Path getFilePath() {
        return pathToFile;
    }

    public String parseToString() throws NoSuchElementException {
        StringBuilder data = new StringBuilder();
        while (!parsingIsComplete) {
                while (fileDataScanner.hasNextLine()) {
                    String line = fileDataScanner.nextLine();
                    data.append(line).append("\n");
                }
                parsingIsComplete = true;
                fileDataScanner.close();
        }
        return data.toString();
    }

    /**
     * This method is used for getting a File object, containing a path to file with script to be executed.
     * @throws FileNotFoundException if file with the given name doesn't exists in resourses/scripts directory.
     * @throws SecurityException if user have no rights to read or write file.
     * @return File with path to starting data.
     * @param fileName name of file containing commands for executing automatically.
     */
    private Path scriptFileBuilder(String fileName) throws IOException, InvalidPathException {
        pathToFile = Path.of(fileName);
        return pathToFile;
    }


}

