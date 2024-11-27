import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame {

    public Main() {
        // Set Title dan Ukuran Jendela Full-Screen
        setTitle("Login");
        setExtendedState(JFrame.MAXIMIZED_BOTH); // Membuat layar penuh
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setUndecorated(true); // Menghilangkan frame jendela
        setResizable(false); // Menghindari resize

        // Background setup
        JLabel background = new JLabel(new ImageIcon("1.jpg")); // Ganti dengan path gambar
        background.setLayout(new GridBagLayout()); // Menggunakan GridBagLayout untuk elemen yang responsif
        setContentPane(background);

        // Atur GridBagConstraints untuk menyesuaikan elemen
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Label Login
        JLabel label = new JLabel("Login Sebagai:", SwingConstants.CENTER);
        label.setForeground(Color.BLACK); // Warna teks yang kontras dengan background cerah
        label.setFont(new Font("Arial", Font.BOLD, 48));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        background.add(label, gbc);

        // Pilihan ComboBox untuk memilih User atau Admin
        String[] roles = { "Pilih Role", "Admin", "User" };
        JComboBox<String> roleComboBox = new JComboBox<>(roles);
        roleComboBox.setFont(new Font("Arial", Font.PLAIN, 24));
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        background.add(roleComboBox, gbc);

        // Tombol Login
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Arial", Font.BOLD, 24));
        loginButton.setBackground(Color.LIGHT_GRAY); // Warna latar belakang tombol
        loginButton.setForeground(Color.BLACK); // Warna teks tombol
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String selectedRole = (String) roleComboBox.getSelectedItem();
                if ("Admin".equals(selectedRole)) {
                    loginAdmin();
                } else if ("User".equals(selectedRole)) {
                    loginUser();
                } else {
                    JOptionPane.showMessageDialog(Main.this, "Pilih Role terlebih dahulu!");
                }
            }
        });
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        background.add(loginButton, gbc);

        // Tombol Keluar
        JButton exitButton = new JButton("Keluar");
        exitButton.setFont(new Font("Arial", Font.BOLD, 24));
        exitButton.setBackground(Color.LIGHT_GRAY);
        exitButton.setForeground(Color.BLACK);
        exitButton.addActionListener(e -> System.exit(0));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        background.add(exitButton, gbc);

        // Pusatkan Jendela
        setLocationRelativeTo(null);
        setVisible(true);
    }

    // Login Admin
    private void loginAdmin() {
        Admin admin = new Admin("admin", "admin123");
        String username = JOptionPane.showInputDialog(this, "Masukkan Username Admin:");
        String password = JOptionPane.showInputDialog(this, "Masukkan Password Admin:");

        if (username != null && password != null) {
            if (username.equals(admin.getAkun().getUsername()) && password.equals(admin.getAkun().getPassword())) {
                JOptionPane.showMessageDialog(this, "Login Admin Berhasil!");
                dispose(); // Tutup jendela login
                new AdminGUI().setVisible(true); // Buka AdminGUI
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password Admin Salah!");
            }
        }
    }

    // Login User
    private void loginUser() {
        Customer customer = new Customer("user", "user123");
        String username = JOptionPane.showInputDialog(this, "Masukkan Username User:");
        String password = JOptionPane.showInputDialog(this, "Masukkan Password User:");

        if (username != null && password != null) {
            if (username.equals(customer.getAkun().getUsername()) && password.equals(customer.getAkun().getPassword())) {
                JOptionPane.showMessageDialog(this, "Login User Berhasil!");
                dispose(); // Tutup jendela login
                new CustomerGUI().setVisible(true); // Buka CustomerGUI
            } else {
                JOptionPane.showMessageDialog(this, "Username atau Password User Salah!");
            }
        }
    }

    public static void main(String[] args) {
        new Main();
    }
}
