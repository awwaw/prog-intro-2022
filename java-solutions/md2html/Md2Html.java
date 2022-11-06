package md2html;

import java.util.*;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;

public class Md2Html {
    public static void main(String[] args) {
        String inputFilename = args[0];
        String outputFilename = args[1];
        StringBuilder result = new StringBuilder();
        try {
            ArrayList<MarkdownElement> elements = FileParser.parseFile(inputFilename);
            for (MarkdownElement element : elements) {
                String htmlOpenTag;
                String htmlCloseTag;
                StringBuilder text;
                if (element instanceof Header header) {
                    htmlOpenTag = String.format("<h%d>", header.getLevel());
                    htmlCloseTag = String.format("</h%d>\n", header.getLevel());
                    text = header.getText();
                }
                else {
                    htmlOpenTag = "<p>";
                    htmlCloseTag = "</p>\n";
                    text = element.getText();
                }
                result.append(htmlOpenTag)
                            .append(LineParser.parseString(text))
                            .append(htmlCloseTag);
            }

            BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(
                    new FileOutputStream(
                        outputFilename
                    ), 
                    StandardCharsets.UTF_8.name()
                )
            );
            try {
                writer.write(result.toString());
            }
            finally {
                writer.close();
            }

        }
        catch (NoSuchFileException e) {
            System.out.println(String.format("There are no file named %s", inputFilename));
        }
        catch (UnsupportedEncodingException e) {
            System.out.println("This file has unsopported encoding");
        }
        catch (IOException e) {
            System.out.println("An error occured while reading file:" + e.getMessage());
        }
    }
}
