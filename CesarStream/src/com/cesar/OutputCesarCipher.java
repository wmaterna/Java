package com.cesar;

import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.DeflaterOutputStream;

public class OutputCesarCipher extends DeflaterOutputStream {
    public OutputCesarCipher(OutputStream out) {
        super(out);
    }

    @Override
    public void write (byte[] b) throws IOException {
        byte[] help = new byte[b.length];

        for (int i = 0; i < help.length; i++) {
            help[i] = b[i];
            if(Character.isAlphabetic(b[i])){
                if(Character.isUpperCase(b[i])){
                    byte shift = (byte) ((b[i] - 'A' + 3) % ('Z' - 'A' + 1));
                    help[i] = (byte) ('A' + shift);
                }else{
                    byte shift = (byte) ((b[i] - 'a' + 3) % ('z' - 'a' + 1));
                    help[i] = (byte) ('a' + shift);
                }
            }
        }

        super.write(help);
    }

}
