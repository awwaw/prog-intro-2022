package md2html;

public class Paragraph extends MarkdownElement {
    Paragraph(String text) {
        this.text = text.substring(0, Math.max(text.length() - 1, 0));
    }
}
