import java.util.ArrayList;

public class Keranjang {
    private ArrayList<Barang> barangList;

    public Keranjang() {
        barangList = new ArrayList<>();
    }

    public void tambahBarang(Barang barang) {
        barangList.add(barang);
    }

    public ArrayList<Barang> getBarangList() {
        return barangList;
    }

    public double totalHarga() {
        double total = 0;
        for (Barang b : barangList) {
            total += b.getHarga();
        }
        return total;
    }
}
