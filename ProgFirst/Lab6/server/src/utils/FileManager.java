package utils;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import stdgroup.StudyGroup;


import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.LinkedHashSet;
import java.util.NoSuchElementException;

public class FileManager {
    private Gson gson = new Gson();
    private String envVariable;

    public FileManager(String envVariable) {
        this.envVariable = envVariable;
    }
    /**
     * Reads collection from a file.
     * @return Read collection.
     */
    public LinkedHashSet<StudyGroup> readCollection() {
        if (System.getenv(envVariable) != null) {
            try (BufferedReader collectionStream = new BufferedReader(new InputStreamReader(new FileInputStream(new File(System.getenv(envVariable))), StandardCharsets.UTF_8))) {
                Type collectionType = new TypeToken<LinkedHashSet<StudyGroup>>() {}.getType();
                String str="",str1;
                while ((str1 = collectionStream.readLine()) != null) {
                    str+=str1;
                }
                LinkedHashSet<StudyGroup> collection = gson.fromJson( str, collectionType);
                System.out.println("Коллекция успешна загружена!");
                return collection;
            } catch (FileNotFoundException exception) {
                System.err.println("Загрузочный файл не найден!");
            } catch (NoSuchElementException exception) {
                System.err.println("Загрузочный файл пуст!");
            } catch (JsonParseException | NullPointerException exception) {
                System.err.println("В загрузочном файле не обнаружена необходимая коллекция!");
            } catch (IOException e) {
                System.err.println("Непредвиденная ошибка!");
            }
        } else System.err.println("Системная переменная с загрузочным файлом не найдена!");
        return new LinkedHashSet<>();
    }

    /**
     * Writes collection to a file.
     * @param collection  to write.
     */
    public void writeCollection(LinkedHashSet<?> collection) {
        if (System.getenv(envVariable) != null) {
            try (PrintWriter collectionWriter = new PrintWriter(System.getenv(envVariable))) {
                collectionWriter.write(gson.toJson(collection));
                System.out.println("Коллекция успешна сохранена в файл!");
            } catch (IOException exception) {
                System.err.println("Загрузочный файл является директорией/не может быть открыт!");
            }
        }
        else System.err.println("Системная переменная с загрузочным файлом не найдена!");
    }
}









