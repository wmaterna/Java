package com.cesar;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.InflaterInputStream;
//GZIPInputStream(InputStream in) : Creates a new input stream with a default buffer size.
//GZIPInputStream(InputStream in, int size) : Creates a new input stream with the specified buffer size.
//potrzebuje bufora, implementacji funkcji: close(),
// int read(byte[] buf, int off, int len) :
// (Reads uncompressed data into an array of bytes.
// If len is not zero, the method will block until some input can
// be decompressed; otherwise, no bytes are read and 0 is returned.)
//GZIPOutputStream :
//read(): Reads a byte of data. Present in FileInputStream.
//write():Writes a byte of data, Present in FileOutputStream


public class InputCesarCipher extends InflaterInputStream {
    public InputCesarCipher(InputStream in) {
        super(in);
    }

    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        byte[] help = new byte[b.length];
        int result = super.read(help, off, len);
        if(result >= 0){
            for (int i = 0; i < result; i++) {
                b[i] = help[i];
                if(Character.isAlphabetic(help[i])){
                    if(Character.isUpperCase(help[i])){
                        byte shift = (byte) ((help[i] - 'A' - 3 + ('Z' - 'A' + 1)) % ('Z' - 'A' + 1));
                        b[i] = (byte) ('A' + shift);
                    }else{
                        byte shift = (byte) ((help[i] - 'a' - 3 +('Z' - 'A' + 1) ) % ('z' - 'a' + 1));
                        b[i] = (byte) ('a' + shift);
                    }
                }
            }
        }
        return result;
    }
}
