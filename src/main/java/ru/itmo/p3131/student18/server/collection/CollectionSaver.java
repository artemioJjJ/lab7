package ru.itmo.p3131.student18.server.collection;

import com.google.gson.Gson;
import ru.itmo.p3131.student18.interim.objectclasses.HumanBeing;
import ru.itmo.p3131.student18.server.Server;

import java.io.*;
import java.util.Stack;

public class CollectionSaver {
    private File writingFile;

    public CollectionSaver(File file) {
        writingFile = file;
    }

    public void save(Stack<HumanBeing> stack) {
        if (writingFile.canWrite()) {
            Gson gson = new Gson();
            String json = gson.toJson(stack);
            byte[] buffer = json.getBytes();
            try (BufferedOutputStream outPut = new BufferedOutputStream((new FileOutputStream(writingFile)))) {
                outPut.write(buffer, 0, buffer.length);
            } catch (FileNotFoundException e) {
                Server.printErr("No such file in project directory.");
            } catch (IOException e) {
                Server.printErr("Failed to reach the file.");
            }
        } else {
            Server.printErr("User have no right to write to the file.");
        }
    }
}
