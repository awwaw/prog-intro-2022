package md2html;
import java.util.*;

public class LineParser {
    private final static Map<String, String> SPECIAL_CHARACTERS = new HashMap<>(
        Map.of(
            "**", "strong",
            "__", "strong",
            "*", "em",
            "_", "em",
            "--", "s",
            "`", "code"
        )
    );

    private static final Map<Character, String> ESCAPED = new HashMap<>(
        Map.of(
            '<', "&lt;",
            '>', "&gt;", 
            '&', "&amp;"
        )
    );

    private static final List<String> SPECIAL = List.of("**", "__", "*", "_", "--", "`", "[");

    private static final Map<String, String> CLOSING_BRACKETS = new HashMap<>(Map.of(
        "[", ")",
        "**", "**",
        "__", "__",
        "*", "*",
        "_", "_",
        "`", "`",
        "--", "--"
        
    ));

    private static boolean pureString(StringBuilder line) {
        for (String special : SPECIAL_CHARACTERS.keySet()) {
            int ind = line.indexOf(special);
            if (ind != -1) {
                if (line.indexOf(special, ind + special.length()) != -1) {
                    return false;
                }
            }
        }
        return true;
    }

    private static StringBuilder escapeChars(String line) {
        StringBuilder res = new StringBuilder();
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            if (c == '*' || c == '_') {
                if (res.length() > 0 && res.charAt(res.length() - 1) == '\\') {
                    res.deleteCharAt(res.length() - 1);
                }
            }
            res.append(ESCAPED.getOrDefault(Character.valueOf(c), Character.toString(c)));
        }
        return res;
    }

    private static boolean checkForValidPreviousChar(int index, StringBuilder line, String tag) {
        return (index > 0 && line.charAt(index - 1) != '\\' && line.charAt(index - 1) != tag.charAt(0));
    }

    private static boolean checkForValidNextChar(int index, StringBuilder line, String tag) {
        return (index + 1 < line.length() && line.charAt(index + 1) != tag.charAt(0));
    }

    private static boolean checkForLink(StringBuilder line) {
        int ind1 = line.indexOf("[");
        int ind2 = line.indexOf("](");
        int ind3 = line.indexOf(")");
        return (ind1 != -1 && ind2 != -1 && ind3 != -1) && 
                (ind1 < ind2 && ind2 < ind3);
    }

    private static StringBuilder parseLink(StringBuilder link) {
        // System.out.println(link);
        // System.out.println("@@@@@");
        String edge = "](";
        int index = link.indexOf(edge);
        int startIndex = link.indexOf("[");
        int endIndex = link.indexOf(")");
        StringBuilder left = new StringBuilder(link.substring(0, startIndex));
        StringBuilder right = endIndex + 1 < link.length() ?
                                 new StringBuilder(link.substring(endIndex + 1)) :
                                  new StringBuilder();
        // System.out.println(startIndex + " " + index + " " + endIndex);
        StringBuilder text = parseString(new StringBuilder(link.substring(startIndex + 1, index)));
        StringBuilder lnk = new StringBuilder(link.substring(index + 2, endIndex));
        StringBuilder res = new StringBuilder("<a href='");
        return left.append(res).append(lnk)
                .append("'>")
                .append(text)
                .append("</a>")
                .append(right);
    }

    public static StringBuilder parseString(StringBuilder line) {
        if (pureString(line)) {
            if (checkForLink(line)) {
                return parseLink(escapeChars(line.toString()));
            }
            return escapeChars(line.toString());
        }

        StringBuilder left = new StringBuilder();
        StringBuilder mid = new StringBuilder();
        StringBuilder right = new StringBuilder();

        // Найдем первое вхождение специального символа
        String tag = "";
        int index = line.length() + 1;
        for (String special : SPECIAL) {
            int ind = line.indexOf(special);
            if (ind != -1) {
                if (ind < index) {
                    index = ind;
                    tag = special;
                }
            }
        }
        
        int closed = line.indexOf(CLOSING_BRACKETS.get(tag), index + tag.length());
        if (tag == "*"|| tag == "_") {
            while (true) {
                if (closed == -1) {
                    break;
                }
                if (checkForValidPreviousChar(closed, line, tag) &&
                    (checkForValidNextChar(closed, line, tag) || closed + 1 == line.length()))
                {
                    break;
                }
                closed = line.indexOf(tag, closed + tag.length());
            }
        }

        // System.out.println("!!!!!!!!!!!");
        // System.out.println(tag);
        // System.out.println("!!!!!!!!!!!!");

        String htmlOpenTag = "";
        String htmlCloseTag = "";
        if (closed != -1) { // Если этот тег в итоге закрывается, обернем его в html
            if (tag == "[") {
                // System.err.println(index);
                // System.err.println(closed);
                StringBuilder parsedLink = parseLink(new StringBuilder(line.substring(index, closed + 1)));
                // System.out.println("-----");;
                // System.out.println(line.substring(index, closed));
                // System.out.println(parsedLink);
                // System.out.println("----");
                left.append(line.substring(0, index));
                mid.append(parsedLink);
                right.append(line.substring(closed + tag.length(), line.length()));
                return left.append(mid).append(parseString(right));
            }
            htmlOpenTag = String.format("<%s>", SPECIAL_CHARACTERS.get(tag));
            htmlCloseTag = String.format("</%s>", SPECIAL_CHARACTERS.get(tag));
            left.append(line.substring(0, index));
            mid.append(line.substring(index + tag.length(), closed));
            right.append(line.substring(closed + tag.length(), line.length()));
        }
        else { // тег не закрывается, поэтому mid будет пустым и, по сути, следовало бы запускаться только от суффикса строки
            left.append(line.substring(0, index + tag.length()));
            right.append(escapeChars(line.substring(index + tag.length(), line.length())));
        }

        return left.append(htmlOpenTag)
                .append(parseString(mid)).append(htmlCloseTag)
                .append(parseString(right));
    }
}
