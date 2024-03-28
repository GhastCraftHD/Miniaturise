package de.leghast.miniaturise.manager;

import de.leghast.miniaturise.Miniaturise;
import de.leghast.miniaturise.miniature.Miniature;

import java.io.*;

public class SaveManager {

    private Miniaturise main;
    private boolean enabled;
    private File folder;

    public SaveManager(Miniaturise main){
        this.main = main;
        folder = createDataFolder();
    }

    private File createDataFolder() {
        try {
            File folder = new File(main.getDataFolder(), "miniatures");
            if (!folder.exists()) {
                folder.mkdirs();
            }
            enabled = true;
            return folder;
        } catch (Exception e) {
            enabled = false;
        }
        return null;
    }

    public void serialize(String name, Miniature miniature) throws IOException {
        if(enabled){
            FileOutputStream fileOut = new FileOutputStream(new File(folder, name + ".mcminiature"));
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(miniature);
            out.close();
            fileOut.close();
        }
    }

    public Miniature deserialize(String filename) throws IOException, ClassNotFoundException, InvalidClassException {
        if(enabled){
            FileInputStream fileIn = new FileInputStream(new File(folder, filename + ".mcminiature"));
            ObjectInputStream in = new ObjectInputStream(fileIn);
            Miniature miniature = (Miniature) in.readObject();
            in.close();
            fileIn.close();
            return miniature;
        }else{
            return null;
        }
    }

    public boolean fileExists(String filename){
        File file = new File(folder, filename + ".mcminiature");
        return file.exists();
    }

    public File[] getMiniatureFiles(){
        return folder.listFiles((dir, name) -> name.endsWith(".mcminiature"));
    }

    public int getMiniatureCount(){
        File[] files = getMiniatureFiles();
        return (files != null) ? files.length : 0;
    }

    public boolean isEnabled(){
        return enabled;
    }

}
