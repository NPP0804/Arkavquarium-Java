public class Koin extends BendaMati{
    private int nilai;

    public Koin(){

    }
    public Koin(double x, double y){
        super(x,y);
        nilai = 0;
    }
    public Koin(double x, double y, int n){
        super(x,y);
        nilai = n;
    }

    public int getNilai(){
        return nilai;
    }
    public void setNilai(int n){
        nilai = n;
    }

    //Implementasi Operator==
    public boolean isSama(Koin k){
        return (getX() == k.getX() && getY() == k.getY() && getNilai() == k.getNilai());
    }
    //Implementasi Operator!=
    public boolean isBeda(Koin k){
        return (getX() != k.getX() || getY() != k.getY() || getNilai() != k.getNilai());
    }
}
