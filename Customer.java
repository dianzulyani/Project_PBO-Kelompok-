public class Customer {
    private Akun akun;
    private Keranjang keranjang;

    public Customer(String username, String password) {
        this.akun = new Akun(username, password);
        keranjang = new Keranjang();
    }

    public void tambahKeKeranjang(Barang barang) {
        keranjang.tambahBarang(barang);
    }

    public Keranjang getKeranjang() {
        return keranjang;
    }

    public Akun getAkun() {
        return akun;
    }

    public void prosesPembayaran(Pembayaran pembayaran) {
        // Proses pembayaran yang dipilih
        pembayaran.prosesPembayaran();

        // Setelah pembayaran selesai, cetak invoice
        Transaksi transaksi = new Transaksi(keranjang.getBarangList(), pembayaran);
        Invoice invoice = new Invoice(transaksi);
        invoice.cetakInvoice();
    }
}