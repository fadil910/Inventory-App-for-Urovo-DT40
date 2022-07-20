package com.fadil910.inventoryapp;

import android.content.Context;
import android.os.Environment;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Objects;

public class StorageTools {
    private static File createOrGetFile(File destination, String fileName, String folderName){

        File folder = new File(destination, folderName);

        return new File(folder, fileName);
    }

    // ----------------------------------
    // READ & WRITE ON STORAGE
    // ----------------------------------

    public static String readOnFile(Context context, File file){

        String result = null;
        if (file.exists()) {
            BufferedReader br;
            try {
                br = new BufferedReader(new FileReader(file));
                try {
                    StringBuilder sb = new StringBuilder();
                    String line = br.readLine();
                    while (line != null) {
                        sb.append(line);
                        sb.append("\n");
                        line = br.readLine();
                    }
                    result = sb.toString();
                }
                finally {
                    br.close();
                }
            }
            catch (IOException e) { e.printStackTrace();
            }
        }

        return result;
    }

    // ---

    public static void writeOnFile(Context context, String text, File file){

        try {
            Objects.requireNonNull(file.getParentFile()).mkdirs();
            FileOutputStream fos = null;
            try {
                fos = new FileOutputStream(file);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

            try (Writer w = new BufferedWriter(new OutputStreamWriter(fos))) {
                try {
                    w.write(text);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                w.flush();
                assert fos != null;
                fos.getFD().sync();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    ////////////////////

    public static String getTextFromStorage(File rootDestination, Context context, String fileName, String folderName){


        File file = createOrGetFile(rootDestination, fileName, folderName);


        return readOnFile(context, file);

    }

    public static void setTextInStorage(File rootDestination, Context context, String fileName, String folderName, String text){


        File file = createOrGetFile(rootDestination, fileName, folderName);


        writeOnFile(context, text, file);

    }

    // ----------------------------------


    // EXTERNAL STORAGE


    // ----------------------------------


    public static boolean isExternalStorageWritable() {


        String state = Environment.getExternalStorageState();


        return (Environment.MEDIA_MOUNTED.equals(state));

    }

    public static boolean isExternalStorageReadable() {


        String state = Environment.getExternalStorageState();


        return (Environment.MEDIA_MOUNTED.equals(state) || Environment.MEDIA_MOUNTED_READ_ONLY.equals(state));
    }

    /////////////////////////
}
