public class QRIS extends Pembayaran {
    private static final double BIAYA_ADMIN = 5000;  // Misalnya biaya admin QRIS sebesar 5000
    
    public QRIS(String id) {
        super(id);
    }

    @Override
    public double hitungTotalHarga(double hargaBarang) {
        return hargaBarang + BIAYA_ADMIN; // Menambahkan biaya admin
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui QRIS berhasil.");
    }
}
