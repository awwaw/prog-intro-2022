package md2html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;

public class MyFileReader {
    public static StringBuilder readFile(final String filename) throws UnsupportedEncodingException, 
                                                                NoSuchFileException,
                                                                IOException
    {
        StringBuilder res = new StringBuilder();
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(filename),
                StandardCharsets.UTF_8.name()
            )
        );
        String line = reader.readLine();
        while (line != null) {
            res.append(line);
            line = reader.readLine();
        }
        reader.close();
        return res;
    }
}
