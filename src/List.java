public class List<T> {
    protected Element<T> first;	//Pointer ke elemen pertama
    protected Element<T> last;	//Pointer ke elemen terakhir

    //CTOR
	public List() {
        first = null;
        last = null;
    }

    public Element<T> getFirst() {
        return first;
    }

    public Element<T> getLast() {
        return last;
    }

    public boolean isEmpty() { 	//Mengembalikan true jika list kosong
        return (first==null && last == null);
    }

    //Menambah elemen T ke dalam list
    public void add(T info) {
        if(first == null) { 	//Kondisi list kosong
            first = new Element<T>(null, info);
            last = first;
        } else { 		//Kondisi list tidak kosong
            if(last == first) { //Satu elemen
                last = new Element<T>(null, info);
                first.setNext(last);
            } else { 	//Kondisi list memiliki lebih dari satu elemen
                Element<T> newinfo = new Element<T>(null, info);
                last.setNext(newinfo);
                last = newinfo;
            }
        }
    }

    //Menghapus elemen T pada list
    public void remove(T element){
        if(!isEmpty()){
            if (first.getInfo() == element) {	//elemen ada di elemen pertama
                Element<T> del = first;
                first = first.getNext();
                if(first == null){
                    last = null;
                }
            } else {						//elemen tidak di elemen pertama
                Element<T> current = first;
                Element<T> prev = null;
                while (current.getInfo() != element && current != null) {
                    //Iterasi sampai elemen terakhir atau sampai elemen ditemukan
                    prev = current;
                    current = current.getNext();
                }
                if (current != null) { //Jika elemen ditemukan, dihapus dari list
                    Element<T> del = current;
                    prev.setNext(current.getNext());
                }
            }
        }
    }

    T get(int index) {		//Mengembalikan elemen di indeks tertentu
        if(index == 0) {	//Ambil elemen pertama
            return first.getInfo();
        } else {			//Mencari elemen dengan indeks yang tepat
            Element<T> curr = first;
            for(int i = 0; i < index; ++i) {
                curr = curr.getNext();
            }
            return curr.getInfo();
        }
    }

    int find(T element) {                //Mengembalikan indeks terkecil tempat T ditemukan
        if (first.getInfo()== element) {    //Kondisi elemen ditemukan di elemen pertama
            return 0;
        } else {
            int counter = 0;            //Mencari elemen yang sesuai
            Element<T> current = first;
            while (current.getNext() != null && current.getNext().getInfo() != element) {
                counter++;
                current = current.getNext();
            }
            if (current.getNext()!= null) {
                //Kondisi perulangan berhenti karena elemen ditemukan, mengembalikan indeks
                return counter;
            } else {
                //elemen tidak ditemukan, mengembalikan nilai -1
                return -1;
            }
        }
    }

}
