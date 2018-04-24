public class List<T> {
  protected Element<T> first;
  protected Element<T> last;
  
  /**
   * Class Constructor.
   */
  public List() {
    first = null;
    last = null;
  }
  
  /**
   * First Getter.
   * @return first element of List.
   */
  public Element<T> getFirst() {
    return first;
  }
  
  /**
   * Last Getter.
   * @return Last Element of List.
   */
  public Element<T> getLast() {
    return last;
  }
  
  /**
   * Check if the List is empty.
   * @return true if list is empty.
   */
  public boolean isEmpty() {
    return (first == null && last == null);
  }
  
  /**
   * Add Element with its info == info, add to last of list.
   * @param info the value of Element added.
   */
  public void add(T info) {
    if (isEmpty()) { //List is empty
      first = new Element<>(null, info);
      last = first;
    } else { //List is not empty
      if (last == first) { //List contains only one element
        last = new Element<>(null, info);
        first.setNext(last);
      } else { //List contains more than one element
        Element<T> newinfo = new Element<>(null, info);
        last.setNext(newinfo);
        last = newinfo;
      }
    }
  }
  
  /**
   * Delete the first list member that equals to element.
   * @param element the element that needs to be deleted.
   */
  @SuppressWarnings("ConstantConditions")
  public void remove(T element) {
    if (!isEmpty()) {
      if (first.getInfo() == element) { //the first list member is equal to element
        first = first.getNext();
        if (first == null) {
          last = null;
        }
      } else { //element is not found at the first list member
        Element<T> current = first;
        Element<T> prev = null;
        while (current.getInfo() != element && current != null) {
          prev = current;
          current = current.getNext();
        }
        if (current != null) { //Condition where the element found
          prev.setNext(current.getNext());
        }
      }
    }
  }
  
  /**
   * Value of element getter.
   * @param index of list where the value of list member is needed.
   * @return value of list member index-th.
   */
  @SuppressWarnings("unused")
  T get(int index) {
    if (index == 0) { //Get first element
      return first.getInfo();
    } else { //Find element in the right index
      Element<T> curr = first;
      for (int i = 0; i < index; ++i) {
        curr = curr.getNext();
      }
      return curr.getInfo();
    }
  }
  
  /**
   * Get the smallest index where element is found.
   * @param element the element that searched.
   * @return smallest index where element is found, return -1 if not found.
   */
  @SuppressWarnings("unused")
  int find(T element) {
    if (first.getInfo() == element) { //Base, if element found in first element
      return 0;
    } else { //Element not found in first
      int counter = 0;            //Mencari elemen yang sesuai
      Element<T> current = first;
      while (current.getNext() != null && current.getNext().getInfo() != element) {
        counter++;
        current = current.getNext();
      }
      if (current.getNext() != null) {
        return counter;
      } else {
        //elemen tidak ditemukan, mengembalikan nilai -1
        return -1;
      }
    }
  }
}