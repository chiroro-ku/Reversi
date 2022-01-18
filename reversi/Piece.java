package reversi;

import java.awt.Color;

import javax.swing.plaf.ColorUIResource;

/**
 * 駒：駒の情報を掌握していて、自身の数を計算する
 */
public class Piece extends Object {

    /**
     * 駒の色をint型で掌握する。
     */
    private Integer color;

    /**
     * 駒の数を掌握する。
     */
    private Integer number;

    /**
     * ビューで表示する駒の色を掌握する。
     */
    private Color uiColor;

    /**
     * コンストラクトである。
     * 
     * @param color 駒の色
     */
    public Piece(Integer color){
        this.color = color;
        this.number = 0;
        this.uiColor = uiColorRandom();
        return;
    }

    /**
     * ビューで表示する色を応答する。
     * 
     * @return カラー
     */
    private Color uiColorRandom() {

        if (color == 1)
            return Color.WHITE;
        if (color == 2)
            return Color.BLACK;

        // ランダムなRGBを設定する。
        int r = (int) (Math.random() * 256);
        int g = (int) (Math.random() * 256);
        int b = (int) (Math.random() * 256);

        Color color = new ColorUIResource(r, g, b);
        return color;
    }

    /**
     * 駒の数を増やす。
     */
    public void increase() {
        this.number++;
        return;
    }

    /**
     * 駒の数を減らす。
     */
    public void decrease() {
        this.number--;
        return;
    }

    /**
     * 駒の色に応答する。
     * 
     * @return 色
     */
    public Integer getColor() {
        return color;
    }

    /**
     * 駒の数に応答する。
     * 
     * @return 数
     */
    public Integer getNumber() {
        return number;
    }

    /**
     * ビューで表示する色を応答する。
     * 
     * @return カラー
     */
    public Color getUIColor(){
        return uiColor;
    }
}
