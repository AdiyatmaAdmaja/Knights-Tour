package pieces;

import java.awt.image.BufferedImage;

import Main.Papan;

public class Kuda extends Bidak {

    // Fungsi untuk Membuat Kuda
    public Kuda(Papan board, int col, int row, boolean isWhite, boolean direction) {
        super(board);
        this.col = col;
        this.row = row;
        this.xPos = col * board.ukuranPetak;
        this.yPos = row * board.ukuranPetak;

        this.isWhite = isWhite;
        this.Name = "Kuda";

        // Ambil gambar kuda dari sheet
        BufferedImage kudaImage = sheet.getSubimage( 3 * sheetScale, isWhite ? 0 : sheetScale, sheetScale, sheetScale);

        // Resize sprite
        this.sprite = kudaImage.getScaledInstance(board.ukuranPetak, board.ukuranPetak, BufferedImage.SCALE_SMOOTH);
    }
}
