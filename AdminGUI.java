import java.util.List;
import javax.swing.*;
import java.awt.*;

public class AdminGUI extends JFrame {
    private List<Barang> daftarBarang;  // Menggunakan List<Barang> bukan ArrayList<Barang>

    public AdminGUI() {
        // Membaca daftar barang dari file saat aplikasi dimulai
        daftarBarang = BarangManager.loadBarangFromFile();  // Ini sekarang kompatibel dengan List<Barang>

        // GUI Setup
        setTitle("Admin Dashboard");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Fullscreen mode
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background setup
        JLabel background = new JLabel(new ImageIcon("1.jpg")); // Replace with your image path
        background.setLayout(new BorderLayout());
        setContentPane(background);

        // Label Welcome
        JLabel welcomeLabel = new JLabel("Selamat datang, Admin!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 48));
        welcomeLabel.setForeground(Color.WHITE);
        add(welcomeLabel, BorderLayout.NORTH);

        // Panel untuk tombol "Kelola Barang" dan "Logout"
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 20));  // Menempatkan tombol di tengah dengan jarak antar tombol
        buttonPanel.setOpaque(false);  // Agar panel tidak menutupi latar belakang

        // Tombol Kelola Barang dengan ukuran lebih kecil
        JButton kelolaBarangButton = new JButton("Kelola Barang");
        kelolaBarangButton.setFont(new Font("Arial", Font.BOLD, 18));  // Ukuran font lebih kecil
        kelolaBarangButton.setBackground(Color.LIGHT_GRAY);
        kelolaBarangButton.setForeground(Color.BLACK);
        kelolaBarangButton.setPreferredSize(new Dimension(200, 40));  // Mengatur ukuran tombol agar lebih kecil
        kelolaBarangButton.addActionListener(e -> kelolaBarang());
        buttonPanel.add(kelolaBarangButton);  // Menambahkan tombol Kelola Barang ke panel

        // Tombol Logout dengan ukuran lebih kecil
        JButton logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.BOLD, 18));  // Ukuran font lebih kecil
        logoutButton.setBackground(Color.LIGHT_GRAY);
        logoutButton.setForeground(Color.BLACK);
        logoutButton.setPreferredSize(new Dimension(200, 40));  // Mengatur ukuran tombol agar lebih kecil
        logoutButton.addActionListener(e -> {
            JOptionPane.showMessageDialog(null, "Anda telah logout.");
            dispose();
            new Main(); // Main class should exist
        });
        buttonPanel.add(logoutButton);  // Menambahkan tombol Logout ke panel

            // Menambahkan panel tombol ke tengah layar
            add(buttonPanel, BorderLayout.CENTER);

            // Center the window
            setLocationRelativeTo(null);
        }

    // Fitur Kelola Barang
    private void kelolaBarang() {
        JFrame kelolaFrame = new JFrame("Kelola Barang");
        kelolaFrame.setSize(800, 600);
        kelolaFrame.setLayout(new BorderLayout());

        // List Barang
        DefaultListModel<String> barangModel = new DefaultListModel<>();
        for (Barang barang : daftarBarang) {
            barangModel.addElement(barang.toString());
        }
        JList<String> barangListUI = new JList<>(barangModel);
        kelolaFrame.add(new JScrollPane(barangListUI), BorderLayout.CENTER);

        // Panel Tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(1, 3));

        // Tombol Tambah Barang
        JButton tambahButton = new JButton("Tambah");
        tambahButton.addActionListener(e -> {
            String nama = JOptionPane.showInputDialog("Masukkan Nama Barang:");
            String hargaStr = JOptionPane.showInputDialog("Masukkan Harga Barang:");
            try {
                double harga = Double.parseDouble(hargaStr);
                Barang barangBaru = new Barang(nama, harga);
                daftarBarang.add(barangBaru);
                barangModel.addElement(barangBaru.toString());
                BarangManager.saveBarangToFile(daftarBarang); // Simpan ke file setelah ditambah
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(null, "Harga harus berupa angka.");
            }
        });

        // Tombol Hapus Barang
        JButton hapusButton = new JButton("Hapus");
        hapusButton.addActionListener(e -> {
            int selectedIndex = barangListUI.getSelectedIndex();
            if (selectedIndex != -1) {
                daftarBarang.remove(selectedIndex);
                barangModel.remove(selectedIndex);
                BarangManager.saveBarangToFile(daftarBarang); // Simpan ke file setelah dihapus
            } else {
                JOptionPane.showMessageDialog(null, "Pilih barang yang ingin dihapus.");
            }
        });

        // Tombol Edit Barang
        JButton editButton = new JButton("Edit");
        editButton.addActionListener(e -> {
            int selectedIndex = barangListUI.getSelectedIndex();
            if (selectedIndex != -1) {
                Barang barangTerpilih = daftarBarang.get(selectedIndex);
                String namaBaru = JOptionPane.showInputDialog("Nama Baru:", barangTerpilih.getNama());
                String hargaBaruStr = JOptionPane.showInputDialog("Harga Baru:", barangTerpilih.getHarga());
                try {
                    double hargaBaru = Double.parseDouble(hargaBaruStr);
                    barangTerpilih.setNama(namaBaru);
                    barangTerpilih.setHarga(hargaBaru);
                    barangModel.set(selectedIndex, barangTerpilih.toString());
                    BarangManager.saveBarangToFile(daftarBarang); // Simpan ke file setelah diedit
                } catch (NumberFormatException ex) {
                    JOptionPane.showMessageDialog(null, "Harga harus berupa angka.");
                }
            } else {
                JOptionPane.showMessageDialog(null, "Pilih barang yang ingin diubah.");
            }
        });

        // Tambahkan Tombol ke Panel
        buttonPanel.add(tambahButton);
        buttonPanel.add(hapusButton);
        buttonPanel.add(editButton);

        kelolaFrame.add(buttonPanel, BorderLayout.SOUTH);
        kelolaFrame.setLocationRelativeTo(null); // Center the window
        kelolaFrame.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new AdminGUI().setVisible(true);
        });
    }
}