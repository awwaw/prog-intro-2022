package md2html;

public class Header extends MarkdownElement {
    private int level = 0;

    Header(int level, String text) {
        this.level = level;
        this.text = text.substring(0, Math.max(text.length() - 1, 0));
        this.type = "Header";
    }

    public int getLevel() {
        return this.level;
    }
}
