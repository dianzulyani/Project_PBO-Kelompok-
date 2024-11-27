public class Barang {
    private String nama;
    private double harga;

    // Constructor
    public Barang(String nama, double harga) {
        this.nama = nama;
        this.harga = harga;
    }

    // Getter dan Setter
    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public double getHarga() {
        return harga;
    }

    public void setHarga(double harga) {
        this.harga = harga;
    }

    // Method untuk representasi string yang lebih jelas (menambahkan format harga dengan ribuan separator)
    @Override
    public String toString() {
        return nama + " - Rp " + String.format("%,.0f", harga);  // Format harga dengan ribuan separator
    }

    // Menambahkan metode untuk menghasilkan informasi barang dalam format struk
    public String toStruk() {
        return nama + " - Rp " + String.format("%,.0f", harga); // Format struk agar lebih rapi
    }
}