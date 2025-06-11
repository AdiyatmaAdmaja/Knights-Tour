package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

        // Membuat window utama aplikasi dengan judul "Knight's Tour"
        JFrame frame = new JFrame();
        frame.getContentPane().setBackground(new Color(6, 96, 107));
        frame.setLayout(new BorderLayout(10, 10));
        frame.setMinimumSize(new Dimension(1000, 1000));
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel boardContainer = new JPanel();
        boardContainer.setLayout(new GridBagLayout());
        boardContainer.setOpaque(false);
        frame.setTitle("Knight's Tour");

        // Membuat papan catur dan menambahkan ke frame
        Papan board = new Papan();
        boardContainer.add(board);
        frame.add(boardContainer, BorderLayout.CENTER);

        // Membuat panel kontrol di bagian bawah untuk tombol restart
        // dan menambahkan tombol untuk mengulang permainan
        JPanel controlPanel = new JPanel();
        controlPanel.setBackground(new Color(6, 96, 107));
        JButton restartButton = new JButton("Restart Tour");
        restartButton.setFont(new Font("Arial", Font.BOLD, 16));
        restartButton.setFocusable(false);
        restartButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                board.restartKnightTour();
            }
        });
        controlPanel.add(restartButton);
        frame.add(controlPanel, BorderLayout.SOUTH);

        // Menambahkan Dialog informasi sebagai petunjuk awal
        JOptionPane.showMessageDialog(frame, "Selamat datang di Knight's Tour!\n\n" +
                "Klik pada petak mana saja untuk memulai perjalanan kuda.\n" +
                "Kuda akan mencoba mengunjungi setiap petak di papan catur tanpa mengunjungi petak yang sama lebih dari sekali.",
                "Petunjuk", JOptionPane.INFORMATION_MESSAGE);

        // Menampilkan frame
        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}