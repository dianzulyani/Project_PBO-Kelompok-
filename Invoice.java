public class Invoice {
    private Transaksi transaksi;

    public Invoice(Transaksi transaksi) {
        this.transaksi = transaksi;
    }

    public void cetakInvoice() {
        System.out.println("Invoice Transaksi:");
        double totalHarga = 0;

        for (Barang b : transaksi.getBarang()) {
            System.out.println(b.getNama() + " - Rp. " + b.getHarga());
            totalHarga += b.getHarga();
        }

        System.out.println("Total Harga Barang: Rp. " + totalHarga);
        System.out.println("Total Harga setelah biaya admin: Rp. " + transaksi.getTotalHarga());
    }
}