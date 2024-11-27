import java.util.ArrayList;

public class Transaksi {
    private ArrayList<Barang> barang;
    private Pembayaran pembayaran;

    public Transaksi(ArrayList<Barang> barang, Pembayaran pembayaran) {
        this.barang = barang;
        this.pembayaran = pembayaran;
    }

    public ArrayList<Barang> getBarang() {
        return barang;
    }

    public Pembayaran getPembayaran() {
        return pembayaran;
    }

    public double getTotalHarga() {
        double total = 0;
        for (Barang b : barang) {
            total += b.getHarga();
        }
        return pembayaran.hitungTotalHarga(total); // Menghitung total harga termasuk biaya admin
    }
}
