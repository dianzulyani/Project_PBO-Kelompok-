public abstract class Pembayaran {
    private String id;
    
    // Biaya admin bisa didefinisikan di sini untuk metode pembayaran umum
    public Pembayaran(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }

    // Method untuk menghitung total harga setelah biaya admin
    public abstract double hitungTotalHarga(double hargaBarang);

    public abstract void prosesPembayaran();
}
