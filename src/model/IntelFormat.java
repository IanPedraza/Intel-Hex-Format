package model;

import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

public class IntelFormat {
    
    public static boolean getFormat(String path) {

        try {
            //Abrir el archivo
            File file = new File(path);
            Scanner scanner = new Scanner(file);
            
            //inicializar variables
            int index = 1;
            String line, byteCount, recordType, checksum, builder, holder = "", dir = "", decoder[];
            Boolean eof;
            ArrayList<String> data = new ArrayList();
            
            //Obtenemos los datos  
            while(scanner.hasNextLine()) {
                line = scanner.nextLine();
                decoder = line.split(",");
                
                //Validamos si hay una siguiente linea o es el fin de archivo
                eof = !scanner.hasNextLine();
                
                //Validamos que tenga un dato la dirección
                holder += decoder.length == 2 ? decoder[1] : "";
                
                //Guardamos la primera dirección del bloque de instrucciones
                if (dir.isEmpty()) dir = decoder[0];
                
                //Si ya se completo un bloque de 16 o es fin del archivo creamos el formato
                if (index%16 == 0 || eof) {
                    //Obtenemos el número de datos
                    byteCount = Integer.toHexString(holder.length()/2);
                    
                    //Formateamos el número de datos a dos digitos
                    byteCount = byteCount.length() == 1 ? "0" + byteCount : byteCount;
                    
                    //Le colocamos un tipo de registro 00 : datos y 01 : EOF
                    recordType = eof ? "01" : "00";
                    
                    //Obtenemos el checksum
                    checksum = IntelChecksum.getChecksum(byteCount + dir + recordType + holder);
                    
                    //Construimos el formato
                    builder = (":" + byteCount + dir + recordType + holder + checksum).toUpperCase();                    

                    //Reiniciamos los datos
                    dir = "";
                    holder = "";
                    data.add(builder);
                }
                
                index++;
            }   
            
            FileManager.saveArray(data, "data/output.hex");            
            return true;
        } catch (Exception ex) {
            System.out.println("Error al abrir archivo: Formato de archivo inválido");
            return false;
        }
    }   
   
}
