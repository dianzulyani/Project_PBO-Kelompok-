public class Bank extends Pembayaran {
    private static final double BIAYA_ADMIN = 10000;  // Misalnya biaya admin Bank sebesar 10000
    
    public Bank(String id) {
        super(id);
    }

    @Override
    public double hitungTotalHarga(double hargaBarang) {
        return hargaBarang + BIAYA_ADMIN; // Menambahkan biaya admin
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui Bank berhasil.");
    }
}
