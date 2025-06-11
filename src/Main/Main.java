package Main;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main {
    public static void main(String[] args) throws Exception {

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
        Papan board = new Papan(); //
        boardContainer.add(board);
        frame.add(boardContainer, BorderLayout.CENTER);

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

        JOptionPane.showMessageDialog(frame, "Silakan klik petak mana saja untuk memulai Knight's Tour.",
                "Informasi", JOptionPane.INFORMATION_MESSAGE);

        frame.pack();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }
}