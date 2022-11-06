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

    private static final List<String> SPECIAL = List.of("**", "__", "*", "_", "--", "`");

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

    public static StringBuilder parseString(StringBuilder line) {
        if (pureString(line)) {
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
        
        int closed = line.indexOf(tag, index + tag.length());
        if (tag == "*"|| tag == "_") {
            while (true) {
                if (closed == -1) {
                    break;
                }
                if ((closed > 0 && line.charAt(closed - 1) != '\\' && line.charAt(closed - 1) != tag.charAt(0)) &&
                    (closed + 1 < line.length() && line.charAt(closed + 1) != tag.charAt(0)))
                {
                    break;
                }
                closed = line.indexOf(tag, closed + tag.length());
            }
        }
        String htmlOpenTag = "";
        String htmlCloseTag = "";
        if (closed != -1) { // Если этот тег в итоге закрывается, обернем его в html
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

        return parseString(left).append(htmlOpenTag)
                .append(parseString(mid)).append(htmlCloseTag)
                .append(parseString(right));
    }
}
