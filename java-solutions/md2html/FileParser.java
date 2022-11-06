package md2html;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.nio.file.NoSuchFileException;
import java.util.ArrayList;

public class FileParser {
    private static int checkForHeader(String line, int linesCount, boolean prevBlankLine) {
        int level = 0;
        int ptr = 0;
        while (line.charAt(ptr) == '#' && ptr < line.length()) {
            level++;
            ptr++;
        }
        if (ptr < line.length() && line.charAt(ptr) != ' ') {
            level = 0;
        }
        if (linesCount == 0 || prevBlankLine) {
            return level;
        }
        else {
            return 0;
        }
    }


    public static ArrayList<MarkdownElement> parseFile(String filename) throws IOException,
                                                                     NoSuchFileException,
                                                                      UnsupportedEncodingException
    {
        BufferedReader reader = new BufferedReader(
            new InputStreamReader(
                new FileInputStream(filename),
                StandardCharsets.UTF_8.name()
            )
        );
        ArrayList<MarkdownElement> res = new ArrayList<>();
        String line = reader.readLine();

        int levelOfOpenedHeader = 0;
        StringBuilder block = new StringBuilder();
        
        boolean prevBlankLine = false;
        int linesCount = 0;
        while (true) {
            if (line == null || line.length() == 0) {
                if (!prevBlankLine && linesCount > 0) {
                    if (levelOfOpenedHeader > 0) {
                        res.add(new Header(levelOfOpenedHeader, block.toString()));
                        levelOfOpenedHeader = 0;
                        block = new StringBuilder();
                    }
                    else {
                        res.add(new Paragraph(block.toString()));
                        block = new StringBuilder();
                    }
                }
                prevBlankLine = true;
                if (line == null) break;
            }
            else {
                line = new StringBuilder(line).append("\n").toString();
                int lvl = checkForHeader(line, linesCount, prevBlankLine);
                if (lvl > 0) {
                    levelOfOpenedHeader = lvl;
                    block.append(line.substring(lvl + 1));
                }
                else {
                    block.append(line);
                }
                prevBlankLine = false;
            }
            line = reader.readLine();
            linesCount++;
        }
        reader.close();
        return res;
    }
}
