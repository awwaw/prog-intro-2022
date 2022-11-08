package md2html;

public abstract class MarkdownElement {
    protected String text;

    public StringBuilder getText() {
        return new StringBuilder(this.text);
    }
}
