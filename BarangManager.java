import java.io.*;
import java.util.*;

public class BarangManager {
    private static final String FILE_NAME = "barang.txt"; // Nama file teks

    // Membaca barang dari file teks dan mengonversinya menjadi objek Barang
    public static List<Barang> loadBarangFromFile() {
        List<Barang> daftarBarang = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                String nama = data[0].trim(); // Nama barang
                double harga = Double.parseDouble(data[1].trim()); // Harga barang
                daftarBarang.add(new Barang(nama, harga)); // Menambahkan barang ke daftar
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return daftarBarang;
    }

    // Menyimpan daftar barang ke file
    public static void saveBarangToFile(List<Barang> daftarBarang) {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Barang barang : daftarBarang) {
                bw.write(barang.getNama() + "," + barang.getHarga());
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}