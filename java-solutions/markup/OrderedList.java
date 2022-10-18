package markup;

import java.util.List;

public class OrderedList extends AbstractList implements TexElement {
    public OrderedList(List<ListItem> elements) {
        super(elements, "enumerate");
    }
}
