package com.mycompany.novelspider.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public abstract class PrintWriteUtil {

    public void printWrite(File file,String charset,String content) {
        PrintWriter printWriter = null;
        try {
            printWriter = new PrintWriter(file,charset);
            printWriter.write(content);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } finally {
            printWriter.close();
        }
    }
}
