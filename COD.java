public class COD extends Pembayaran {
    private static final double BIAYA_ADMIN = 0;  // Tidak ada biaya admin untuk COD
    
    public COD(String id) {
        super(id);
    }

    @Override
    public double hitungTotalHarga(double hargaBarang) {
        return hargaBarang; // Tidak ada biaya admin, harga tetap
    }

    @Override
    public void prosesPembayaran() {
        System.out.println("Pembayaran melalui COD berhasil.");
    }
}
