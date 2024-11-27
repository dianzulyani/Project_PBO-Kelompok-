import java.util.ArrayList;

public class Admin {
    private Akun akun;
    private ArrayList<Barang> barangList;

    public Admin(String username, String password) {
        this.akun = new Akun(username, password);
        barangList = new ArrayList<>();
    }

    public Akun getAkun() {
        return akun;
    }

    public void tambahBarang(Barang barang) {
        barangList.add(barang);
    }

    public ArrayList<Barang> getBarangList() {
        return barangList;
    }

    public void hapusBarang(Barang barang) {
        barangList.remove(barang);
    }
}
