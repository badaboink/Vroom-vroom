package com.example.myapplication.Prices;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class SaveLoadData {
    public static final String savedPrice1 = "price1.txt";
    public static final String savedPrice2 = "price2.txt";
    public static final String savedPrice3 = "price3.txt";
    public static final String savedHour = "hour.txt";

    public static void savePrice(TextView row, String FILE_NAME, Context FILE) {
        String text = row.getText().toString();
        FileOutputStream fos = null;
        try {
            fos = FILE.openFileOutput(FILE_NAME, MODE_PRIVATE);
            fos.write(text.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static void saveDate(String date, String file, Context FILE) {
        FileOutputStream fos = null;
        try {
            fos = FILE.openFileOutput(file, MODE_PRIVATE);
            fos.write(date.getBytes());

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    public static String load(String file, Context FILE) {
        FileInputStream fis = null;
        try {
            fis = FILE.openFileInput(file);
            InputStreamReader isr = new InputStreamReader(fis);
            BufferedReader br = new BufferedReader(isr);
            StringBuilder sb = new StringBuilder();
            String text;
            while ((text = br.readLine()) != null) {
                sb.append(text).append("");
            }
            return sb.toString();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
}

