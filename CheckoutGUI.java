import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CheckoutGUI extends JFrame {
    private Customer customer;

    public CheckoutGUI(Customer customer) {
        this.customer = customer;
        setTitle("Checkout");
        setSize(400, 500); // Menambah tinggi untuk menampung struk
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Background setup
        JLabel background = new JLabel(new ImageIcon("1.jpg")); // Replace with your image path
        background.setLayout(new BorderLayout());
        setContentPane(background);

        JPanel panel = new JPanel();
        panel.setLayout(new GridLayout(4, 2));

        JButton btnBank = new JButton("Bank Transfer");
        JButton btnQRS = new JButton("QRIS");
        JButton btnCOD = new JButton("COD");
        JButton btnKembali = new JButton("Kembali");

        panel.add(btnBank);
        panel.add(btnQRS);
        panel.add(btnCOD);
        panel.add(btnKembali);

        add(panel, BorderLayout.CENTER);

        // Struk Panel (untuk menampilkan invoice)
        JPanel strukPanel = new JPanel();
        strukPanel.setLayout(new BoxLayout(strukPanel, BoxLayout.Y_AXIS));
        JTextArea textArea = new JTextArea(10, 30);
        textArea.setEditable(false);
        strukPanel.add(new JScrollPane(textArea));
        add(strukPanel, BorderLayout.SOUTH);

        // Pilih metode pembayaran - Bank Transfer
        btnBank.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesPembayaran("Bank Transfer", textArea);
            }
        });

        // Pilih metode pembayaran - QRIS
        btnQRS.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesPembayaran("QRIS", textArea);
            }
        });

        // Pilih metode pembayaran - COD
        btnCOD.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                prosesPembayaran("COD", textArea);
            }
        });

        // Kembali ke halaman customer
        btnKembali.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                new CustomerGUI().setVisible(true); // Kembali ke halaman customer
            }
        });
    }

    private void prosesPembayaran(String metodePembayaran, JTextArea textArea) {
        // Simulasi daftar barang yang ada di keranjang
        double totalHarga = 0;
        StringBuilder struk = new StringBuilder();
        struk.append("********** STRUK PEMBAYARAN **********\n");
        for (Barang barang : customer.getKeranjang().getBarangList()) {
            struk.append(barang.getNama() + " - Rp. " + barang.getHarga() + "\n");
            totalHarga += barang.getHarga();
        }

        // Menambahkan biaya admin untuk pembayaran QRIS
        double biayaAdmin = 0;
        if (metodePembayaran.equals("QRIS")) {
            biayaAdmin = 5000; // Misalnya biaya admin QRIS sebesar Rp 5.000
            struk.append("\nBiaya Admin QRIS: Rp. " + biayaAdmin + "\n");
        }

        // Menampilkan informasi total harga
        double totalPembayaran = totalHarga + biayaAdmin;
        struk.append("\nTotal Harga: Rp. " + totalHarga + "\n");
        struk.append("Total Pembayaran (termasuk biaya admin): Rp. " + totalPembayaran + "\n");
        struk.append("\nMetode Pembayaran: " + metodePembayaran + "\n");
        struk.append("**************************************\n");

        // Menampilkan struk di text area
        textArea.setText(struk.toString());

        // Menampilkan dialog pembayaran berhasil
        JOptionPane.showMessageDialog(this, "Pembayaran melalui " + metodePembayaran + " berhasil!");
    }

    public static void main(String[] args) {
        // Contoh customer dan barang di keranjang
        Customer customer = new Customer("user123", "password123");
        Barang barang1 = new Barang("Laptop", 15000000);
        Barang barang2 = new Barang("Mouse", 50000);
        customer.tambahKeKeranjang(barang1);
        customer.tambahKeKeranjang(barang2);

        // Membuka jendela Checkout
        new CheckoutGUI(customer).setVisible(true);
    }
}