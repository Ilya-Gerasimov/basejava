package com.urise.webapp;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class MainFile {
    public static void main(String[] args) {
        String filePath = ".\\.gitignore";

        File file = new File(filePath);
        try {
            System.out.println(file.getCanonicalPath());
        } catch (IOException e) {
            throw new RuntimeException("Error", e);
        }

        File dir = new File("./src/com/urise/webapp");
        System.out.println(dir.isDirectory());
        String[] list = dir.list();
        if (list != null) {
            for (String name : list) {

                System.out.println("     " + name);
            }
        }

        try (FileInputStream fis = new FileInputStream(filePath)) {
            System.out.println(fis.read());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    // Вторая часть ДЗ. Файлы директории выводились вперемешку с директориями, поэтому вынес в отдельный цикл

        File folder = new File("./src/com/urise/webapp");

        for (File files : folder.listFiles()) {
            if (files.isDirectory()) {
                System.out.println(files.getName());
                for (String d : files.list()) {
                    System.out.println("     " + d);
                }
            }
        }
        System.out.println("Файлы текущей директории");

        for (File files : folder.listFiles()) {

            if (!file.isDirectory())

                System.out.println("     " + files.getName());
        }
    }
}