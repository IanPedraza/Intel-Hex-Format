package model;

import java.math.BigInteger;

public class IntelChecksum {
    
    public static String getChecksum(String input) {
        //Obtenemos un arreglo númerico a partir de la entrada en cadena
        int[] values = getValues(input.split(""));
        
        //Obtenemos la suma de los digitos
        int sum = getSum(values);
        
        //Hacemos el complemento a 1 y a2
        String ones = Long.toBinaryString(~sum);
        String twos = twosCompliment(ones);
        
        //Convertimos el complemento a 2 a hexadecimal
        String hex = new BigInteger(twos, 2).toString(16);

        //regresamos los últimos dos digitos del checksum
        return hex.length() > 2 ? hex.substring(hex.length() - 2) : hex;
    }
    
    private static int[] getValues(String[] input) {
        //Definimos el tamaños del arreglo necesario
        int[] values = input.length%2 == 0 ? new int[input.length/2] : new int[(input.length/2)+1];
        
        //iniciamos el indice para almacenar los datos
        int index = 0;
        
        for (int i = 0; i < input.length; i+=2) {
            //Tomamos el primer digito para contruir nuestro numero
            String format = input[i] + "";
            
            //validamos que haya un siguiente digito y lo agregamos
            format += i+1 < input.length ? input[i+1] : "";
            
            //convertimos el digito en cadena hexa a decimal
            int value = Integer.parseInt(format, 16);
            
            //agregamos el valor al resultado
            values[index] = value;
            index++;
        }
        
        return values;
    }
    
    private static int getSum(int[] values) {
        //Se hace la suma de un arreglo de enteros
        
        int sum = 0;
        
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
        }
        
        return sum;
    }
    
    private static String twosCompliment(String bin) {
        //Obtenemos el complemeneto a 2 a partir de una entrada de complemeneto a 1
        String twos;
        
        StringBuilder builder = new StringBuilder(bin);
        
        boolean b = false;
        
        for (int i = bin.length() - 1; i > 0; i--) {
            if (bin.charAt(i) == '1') {
                builder.setCharAt(i, '0');
            } else {
                builder.setCharAt(i, '1');
                b = true;
                break;
            }
        }
        
        if (!b) builder.append("1", 0, 7);

        twos = builder.toString();

        return twos;
    }
}
