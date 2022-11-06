package md2html;

public abstract class MarkdownElement {
    protected String text;
    protected String type;

    public StringBuilder getText() {
        return new StringBuilder(this.text);
    }

    public String getType() {
        return this.type;
    }
}
