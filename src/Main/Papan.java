package Main;

import java.awt.*;
import javax.swing.*;

import Util.KnightTour;
import pieces.Kuda;

public class Papan extends JPanel {
    public int ukuranPetak = 100;
    int cols = 8;
    int rows = 8;

    private int[][] knightTourSolution;
    private KnightTour ktLogic;
    private boolean tourStarted = false;

    public Papan() { //
        this.setPreferredSize(new Dimension(cols * ukuranPetak, rows * ukuranPetak));
        ktLogic = new KnightTour(); //
        addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                knightTourMousePressed(evt);
            }
        });
    }

    private void knightTourMousePressed(java.awt.event.MouseEvent evt) {
        if (tourStarted)
            return;

        int col = evt.getX() / ukuranPetak;
        int row = evt.getY() / ukuranPetak;

        if (col < 0 || col >= cols || row < 0 || row >= rows)
            return;

        knightTourSolution = ktLogic.findTour(col, row);
        tourStarted = true;

        if (knightTourSolution.length == 1 && knightTourSolution[0][0] == -1) {
            JOptionPane.showMessageDialog(this, "Tidak ditemukan solusi dari titik awal ini!", "Info",
                    JOptionPane.WARNING_MESSAGE);
            tourStarted = false;
        }

        repaint();
    }

    public void restartKnightTour() {
        this.knightTourSolution = null;
        this.tourStarted = false;
        JOptionPane.showMessageDialog(this, "Papan telah di-reset. Silakan klik petak untuk memulai lagi.", "Restart",
                JOptionPane.INFORMATION_MESSAGE);
        repaint();
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        for (int r = 0; r < rows; r++)
            for (int c = 0; c < cols; c++) {
                g2d.setColor((c + r) % 2 == 0 ? new Color(162, 218, 90) : new Color(97, 46, 105));
                g2d.fillRect(c * ukuranPetak, r * ukuranPetak, ukuranPetak, ukuranPetak);
            }

        // Pengecekan gameMode dihapus
        if (knightTourSolution != null && tourStarted) { //
            drawKnightTourSolution(g2d);
        }
    }

    private void drawKnightTourSolution(Graphics2D g2d) {
        Point[] path = new Point[65];
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (knightTourSolution[r][c] != -1) {
                    path[knightTourSolution[r][c]] = new Point(c, r);
                }
            }
        }

        g2d.setColor(Color.RED);
        g2d.setStroke(new BasicStroke(3));

        for (int i = 1; i < 64; i++) {
            if (path[i] != null && path[i + 1] != null) {
                int x1 = path[i].x * ukuranPetak + ukuranPetak / 2;
                int y1 = path[i].y * ukuranPetak + ukuranPetak / 2;
                int x2 = path[i + 1].x * ukuranPetak + ukuranPetak / 2;
                int y2 = path[i + 1].y * ukuranPetak + ukuranPetak / 2;
                g2d.drawLine(x1, y1, x2, y2);
            }
        }

        g2d.setFont(new Font("Arial", Font.BOLD, 30));
        g2d.setColor(Color.BLACK);
        FontMetrics fm = g2d.getFontMetrics();
        for (int i = 1; i <= 64; i++) {
            if (path[i] != null) {
                String num = Integer.toString(i);
                int x = path[i].x * ukuranPetak + (ukuranPetak - fm.stringWidth(num)) / 2;
                int y = path[i].y * ukuranPetak + (ukuranPetak - fm.getHeight()) / 2 + fm.getAscent();
                g2d.drawString(num, x, y);
            }
        }

        try {
            if (path[1] != null) {
                Image knightImage = new Kuda(this, path[1].x, path[1].y, true, true).sprite;
                g2d.drawImage(knightImage, path[1].x * ukuranPetak, path[1].y * ukuranPetak, null);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}