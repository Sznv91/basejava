package ru.topjava.basejava;

import java.io.File;
import java.util.Objects;

class MainFile {

    public static void main(String[] args) {
        File path = new File(".//");

        MainFile mf = new MainFile();
        mf.fileWalk(path);
    }

    //http://www.cyberforum.ru/post13277622.html
    private void fileWalk(File file) {
        int deepCounter = 0;
        File[] files = file.listFiles();
        for (File currentFile : Objects.requireNonNull(files)) {
            if (!currentFile.isDirectory()) {
                StringBuilder indentation = new StringBuilder();
                for (int i = 0; i <= deepCounter; i++){
                    indentation.append(" ");
                }
                System.out.println(indentation + "Is File " + currentFile.getName());
            }
            if (currentFile.isDirectory()) {
                try {
                    System.out.println("Is Directory " + currentFile.getName());
                    deepCounter++;
                    fileWalk(currentFile);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
