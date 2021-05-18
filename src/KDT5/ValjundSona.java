package KDT5;

import java.util.ArrayList;

public class ValjundSona {
    ArrayList<String> list;
    int arv;

    public ValjundSona(ArrayList<String> list, int arv) {
        this.list = list;
        this.arv = arv;
    }

    public ArrayList<String> getList() {
        return list;
    }

    public void setList(ArrayList<String> list) {
        this.list = list;
    }

    public int getArv() {
        return arv;
    }

    public void setArv(int arv) {
        this.arv = arv;
    }
}
