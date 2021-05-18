package KDT5;

public class Sona{
    String sone;
    int arv;

    public void setArv(int arv) {
        this.arv = arv;
    }

    public String getSone() {
        return sone;
    }

    public Sona(String sone, int arv) {
        this.sone = sone;
        this.arv = arv;
    }

    public int getArv() {
        return arv;
    }

    @Override
    public String toString() {
        return sone+" : "+arv+" Korda";
    }
}
