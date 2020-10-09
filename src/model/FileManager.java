package model;

import static java.awt.Desktop.getDesktop;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;

public class FileManager {
    
    public static boolean saveArray(ArrayList<String> data, String path) {
        try {
            FileWriter writer = new FileWriter(path); 
            int size =  data.size();
            
            for (int i = 0; i < size; i++) {
                String item = data.get(i);
                item += (i == size - 1? "" : "\n");
                writer.write(item);
            }

            writer.close();

            getDesktop().open(new File("data"));
            
            return true;
        } catch (Exception e) {
            System.out.println("Error al guardar el archivo");
            return false;
        }
    }
    
}
