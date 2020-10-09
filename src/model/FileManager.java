package model;

import static java.awt.Desktop.getDesktop;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {
    
    public static boolean saveArray(ArrayList<String> data, String path) {
        try {
            FileWriter writer = new FileWriter(path); 
        
            for(String item: data) {
                writer.write(item+"\n");
            }

            writer.close();

            getDesktop().edit(new File(path));
            
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo");
            return false;
        }
    }
    
}
