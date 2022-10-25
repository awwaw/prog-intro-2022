package markup;

import java.util.List;

public class Text extends AbstractText implements Markdown {
    public Text(List<AbstractText> elements) {
        super(elements, "", "");
    }

    public Text(String txt) {
        super(txt, "", "");
    }
}

/**
 *__current-repo/java-solutions/markup/MarkupListTest.java:22: error: duplicate class: markup.MarkupListTest
 * public final class MarkupListTest {
 *              ^
 * 1 error
 * ERROR: Compilation failed
 */