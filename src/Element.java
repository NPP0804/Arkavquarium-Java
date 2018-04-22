public class Element<T> {
    protected Element<T> next;
    protected T info;

    public Element(Element<T> next, T info) {
        this.next = next;
        this.info = info;
    }

    public Element<T> getNext() {
        return next;
    }

    public void setNext(Element<T> next) {
        this.next = next;
    }

    public T getInfo() {
        return info;
    }

    public void setInfo(T info) {
        this.info = info;
    }
}
