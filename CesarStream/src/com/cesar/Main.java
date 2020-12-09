package com.cesar;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Main {

    public static void main(String[] args) {
        String code = "ZzAla ma kota\nKot ma ale";
        try(OutputCesarCipher inputCesarCipher = new OutputCesarCipher(new FileOutputStream("cezar_code.txt"))){
            inputCesarCipher.write(code.getBytes());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        int bufferSize = 5;
        byte[] data = new byte[bufferSize];
        byte[] help;
	    try(InputCesarCipher inputCesarCipher = new InputCesarCipher(new FileInputStream("cezar_code.txt"))){
            int result = inputCesarCipher.read(data,0,bufferSize);
            while(result > 0){
                help = new byte[result];
                for (int i = 0; i < result; i++) {
                    help[i] = data[i];
                }
               // System.out.println(result);
                String line = new String(help);
                System.out.print(line);
                result = inputCesarCipher.read(data,0,bufferSize);
            }
            System.out.println();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
