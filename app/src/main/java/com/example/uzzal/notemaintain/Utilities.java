package com.example.uzzal.notemaintain;

import android.content.Context;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Utilities {

    public static final String FILE_EXTENSION = ".bin";

    public static boolean saveNotes(Context context, Note note){

        String fileName = String.valueOf(note.getmDateTime())+ FILE_EXTENSION;

        FileOutputStream fos;
        ObjectOutputStream oos;

        try{
            fos = context.openFileOutput(fileName, context.MODE_PRIVATE);
            oos = new ObjectOutputStream(fos);
            oos.writeObject(note);
            oos.close();
            fos.close();
        } catch (Exception e){
            e.printStackTrace();
            return false;   //tell the User something wait wrong;
        }



        return true;
    }
    public static ArrayList<Note> getAllSavedNotes(Context context) {
        ArrayList<Note> notes = new ArrayList<>();

        File filesDr = context.getFilesDir();
        ArrayList<String> noteFiles = new ArrayList<>();

        for (String file : filesDr.list()) {
            if (file.endsWith(FILE_EXTENSION)) {
                noteFiles.add(file);
            }
        }
        FileInputStream fis;
        ObjectInputStream ois;

        for(int i = 0; i<noteFiles.size(); i++){

            try {
                fis = context.openFileInput(noteFiles.get(i));
                ois = new ObjectInputStream(fis);

                notes.add((Note) ois.readObject());
                fis.close();
                ois.close();

            }catch (IOException | ClassNotFoundException e){
                e.printStackTrace();
                return null;
            }
        }
        return notes;

    }

    public static Note getNoteByName(Context context, String fileName){
        File file = new File(context.getFilesDir(),fileName);
        Note note;

        if(file.exists()){
            FileInputStream fis;
            ObjectInputStream ois;

            try{
                fis = context.openFileInput(fileName);
                ois = new ObjectInputStream(fis);

                note = (Note) ois.readObject();
                fis.close();
                ois.close();

            }catch (IOException |ClassNotFoundException  e){
                e.printStackTrace();
                return null;
            }
            return note;
        }
        return null;
    }

    public static void deleteNote(Context context, String fileName) {
        File dir = context.getFilesDir();
        File file = new File(dir,fileName);

        if(file.exists()){
            file.delete();
        }
    }
}
