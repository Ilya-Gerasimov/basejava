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

        // Вторая часть ДЗ8. Файлы директории выводились вперемешку с директориями, поэтому вынес в отдельный цикл

//        File folder = new File("./src/com/urise/webapp");
//
//        for (File files : folder.listFiles()) {
//            if (files != null) {
//                if (files.isDirectory()) {
//                    System.out.println(files.getName());
//                    for (String d : files.list()) {
//                        System.out.println("     " + d);
//                    }
//                }
//            }
//        }
//        System.out.println("Файлы текущей директории");
//
//        for (File files : folder.listFiles()) {
//            if (files != null) {
//                if (!file.isDirectory())
//                    System.out.println("     " + files.getName());
//            }
//        }
        dir = new File("./src");
        String str = " ";
        printDirectoryDeeply(dir, str);
    }

    public static void printDirectoryDeeply(File dir, String str) {
        File[] files = dir.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isFile()) {
                    System.out.println(str + "  File: " + file.getName());
                } else if (file.isDirectory()) {
                    System.out.println("\n" + str + "Directory: " + file.getName());
                    str = str + " ";
                    printDirectoryDeeply(file, str);
                }
            }
        }
    }
}