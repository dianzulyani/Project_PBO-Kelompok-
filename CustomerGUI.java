import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

public class CustomerGUI extends JFrame {
    private List<Barang> daftarBarang;
    private ArrayList<Barang> keranjang;

    public CustomerGUI() {
        daftarBarang = BarangManager.loadBarangFromFile();
        keranjang = new ArrayList<>();

        // Daftar Barang Dummy
        // daftarBarang.add(new Barang("Laptop", 15000000));
        // daftarBarang.add(new Barang("Mouse", 50000));
        // daftarBarang.add(new Barang("Keyboard", 150000));

        // GUI Setup
        setTitle("User Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Set Background
        JLabel background = new JLabel(new ImageIcon("1.jpg")); // Replace with your image path
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // List Barang
        DefaultListModel<String> barangModel = new DefaultListModel<>();
        for (Barang barang : daftarBarang) {
            barangModel.addElement(barang.toString()); // Menampilkan barang dengan format toString()
        }
        JList<String> barangListUI = new JList<>(barangModel);
        add(new JScrollPane(barangListUI), BorderLayout.CENTER);

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));
        buttonPanel.setOpaque(false); // Agar latar belakang terlihat

        // Tombol Tambah ke Keranjang
        JButton tambahKeranjangButton = new JButton("Tambah ke Keranjang");
        tambahKeranjangButton.setFont(new Font("Arial", Font.BOLD, 20));
        tambahKeranjangButton.addActionListener(e -> {
            int selectedIndex = barangListUI.getSelectedIndex();
            if (selectedIndex != -1) {
                Barang barangTerpilih = daftarBarang.get(selectedIndex);
                keranjang.add(barangTerpilih);
                JOptionPane.showMessageDialog(null, barangTerpilih.getNama() + " berhasil ditambahkan ke keranjang.");
            } else {
                JOptionPane.showMessageDialog(null, "Pilih barang yang ingin ditambahkan.");
            }
        });

        // Tombol Lihat Keranjang
        JButton lihatKeranjangButton = new JButton("Lihat Keranjang");
        lihatKeranjangButton.setFont(new Font("Arial", Font.BOLD, 20));
        lihatKeranjangButton.addActionListener(e -> lihatKeranjang());

        // Tombol Logout
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Anda telah logout.");
            dispose();
            new Main(); 
        });

        // Tambahkan Tombol ke Panel
        buttonPanel.add(tambahKeranjangButton);
        buttonPanel.add(lihatKeranjangButton);
        buttonPanel.add(logoutButton);

        add(buttonPanel, BorderLayout.SOUTH);

        // Pusatkan jendela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Fitur Lihat Keranjang
    private void lihatKeranjang() {
        JFrame keranjangFrame = new JFrame("Keranjang");
        keranjangFrame.setSize(500, 400);
        keranjangFrame.setLayout(new BorderLayout());

        // List Barang di Keranjang
        DefaultListModel<String> keranjangModel = new DefaultListModel<>();
        for (Barang barang : keranjang) {
            keranjangModel.addElement(barang.toString());
        }
        JList<String> keranjangListUI = new JList<>(keranjangModel);
        keranjangFrame.add(new JScrollPane(keranjangListUI), BorderLayout.CENTER);

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 2));

        // Tombol Checkout
        JButton checkoutButton = new JButton("Checkout");
        checkoutButton.setFont(new Font("Arial", Font.BOLD, 20));
        checkoutButton.addActionListener(e -> {
            if (keranjang.isEmpty()) {
                JOptionPane.showMessageDialog(null, "Keranjang kosong. Tambahkan barang terlebih dahulu.");
            } else {
                pilihMetodePembayaran();
                keranjang.clear();
                keranjangModel.clear();
                keranjangFrame.dispose();
            }
        });

        // Tombol Kembali
        JButton kembaliButton = new JButton("Kembali");
        kembaliButton.setFont(new Font("Arial", Font.BOLD, 20));
        kembaliButton.addActionListener(e -> keranjangFrame.dispose());

        // Tambahkan Tombol ke Panel
        buttonPanel.add(checkoutButton);
        buttonPanel.add(kembaliButton);

        keranjangFrame.add(buttonPanel, BorderLayout.SOUTH);
        keranjangFrame.setLocationRelativeTo(this); // Pusatkan terhadap jendela utama
        keranjangFrame.setVisible(true);
    }

    // Fitur Pilih Metode Pembayaran
    private void pilihMetodePembayaran() {
        String[] metodePembayaran = {"Bank Transfer", "COD", "QRIS"};
        String pilihan = (String) JOptionPane.showInputDialog(
                null,
                "Pilih metode pembayaran:",
                "Metode Pembayaran",
                JOptionPane.PLAIN_MESSAGE,
                null,
                metodePembayaran,
                metodePembayaran[0]
        );

        if (pilihan != null) {
            double totalHarga = hitungTotalHarga();
            double biayaAdmin = 0;

            // Menentukan biaya admin berdasarkan pilihan
            if (pilihan.equals("QRIS")) {
                biayaAdmin = 5000; // Misalnya biaya admin QRIS adalah 5000
            } else if (pilihan.equals("Bank Transfer")) {
                biayaAdmin = 10000; // Misalnya biaya admin Bank Transfer adalah 10000
            }

            // Menampilkan struk (invoice)
            tampilkanStruk(totalHarga, biayaAdmin, pilihan);
        } else {
            JOptionPane.showMessageDialog(null, "Pembayaran dibatalkan.");
        }
    }

    private double hitungTotalHarga() {
        double total = 0;
        for (Barang barang : keranjang) {
            total += barang.getHarga();
        }
        return total;
    }

    // Menampilkan struk atau invoice
    private void tampilkanStruk(double totalHarga, double biayaAdmin, String metodePembayaran) {
        double totalPembayaran = totalHarga + biayaAdmin;

        String struk = "====================\n";
        struk += "Struk Pembayaran\n";
        struk += "====================\n";
        for (Barang barang : keranjang) {
            struk += barang.getNama() + " - Rp. " + barang.getHarga() + "\n";
        }
        struk += "====================\n";
        struk += "Biaya Admin (" + metodePembayaran + "): Rp. " + biayaAdmin + "\n";
        struk += "====================\n";
        struk += "Total Pembayaran: Rp. " + totalPembayaran + "\n";
        struk += "====================\n";

        // Menampilkan struk di JOptionPane
        JTextArea textArea = new JTextArea(struk);
        textArea.setEditable(false);
        JOptionPane.showMessageDialog(null, new JScrollPane(textArea), "Invoice", JOptionPane.INFORMATION_MESSAGE);
    }

    public static void main(String[] args) {
        new CustomerGUI();
    }
}