public class Element<T> {
  protected Element<T> next;
  protected T info;
  
  /**
   * Class Constructor.
   * @param next Pointer to next element.
   * @param info value of Element.
   */
  public Element(Element<T> next, T info) {
    this.next = next;
    this.info = info;
  }
  
  /**
   * Next Getter.
   * @return next Element.
   */
  public Element<T> getNext() {
    return next;
  }
  
  /**
   * Set Next with another element.
   * @param next Element which position after this element.
   */
  public void setNext(Element<T> next) {
    this.next = next;
  }
  
  /**
   * Info Getter.
   * @return info value of Element.
   */
  public T getInfo() {
    return info;
  }
  
  /**
   * Info Setter, set info with new value.
   * @param info new value of Element.
   */
  @SuppressWarnings("unused")
  public void setInfo(T info) {
    this.info = info;
  }
}
