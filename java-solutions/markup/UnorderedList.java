package markup;

import java.util.List;

public class UnorderedList extends AbstractList implements TexElement {
    public UnorderedList(List<ListItem> elements) {
        super(elements, "itemize");
    }
}
