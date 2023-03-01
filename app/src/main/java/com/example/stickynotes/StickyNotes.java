package com.example.stickynotes;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;

public class StickyNotes {
    String getStick(Context context) {
        File file = new File(context.getFilesDir().getPath() + "/sticky.txt");
        StringBuilder text = new StringBuilder();
        try
        {
            BufferedReader br= new BufferedReader(new FileReader(file));
            String line;
            while((line=br.readLine())!=null){
                text.append(line);
                text.append("\n");


            }
            br.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return text.toString();
    }

    void setStick(String sticknote,Context context)
    {
        String text =sticknote;
        FileOutputStream fos = null;
        try{
            fos =context.getApplicationContext().openFileOutput("sticky.txt",Context.MODE_PRIVATE);
            fos.write(text.getBytes());
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            if(fos!=null) {
                try {
                    fos.close();
                } catch (Exception e)
                {
                    e.printStackTrace();
               }
            }
        }

    }
}
