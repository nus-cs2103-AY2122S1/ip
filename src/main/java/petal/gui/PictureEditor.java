package petal.gui;

import javafx.scene.effect.DropShadow;
import javafx.scene.paint.Color;
import javafx.scene.shape.Shape;

/**
 * The PictureEditor class encompasses methods that help to add
 * certain effects to an Image within a Shape, like a glow and outline
 * Picture refers to an Image within a shape.
 */
public class PictureEditor {

    public static final Color COLOR_SAKURA_PINK = Color.web("#ffb7c5");
    public static final Color COLOR_TEAL = Color.web("#b7fff1");

    /**
     * Sets a glow to the shape
     *
     * @param shape The shape being modified
     * @param colorOfGlow The color of the glow
     * @param sizeOfGlow The size of the glow
     */
    public static void setGlow(Shape shape, Color colorOfGlow, int sizeOfGlow) {
        DropShadow borderGlow = new DropShadow();
        borderGlow.setOffsetY(0f);
        borderGlow.setOffsetX(0f);
        borderGlow.setColor(colorOfGlow);
        borderGlow.setWidth(sizeOfGlow);
        borderGlow.setHeight(sizeOfGlow);
        shape.setEffect(borderGlow);
    }

    /**
     * Sets an outline to the shape
     *
     * @param shape The given shape
     * @param colorOfOutline The colour of the outline
     */
    public static void setOutline(Shape shape, Color colorOfOutline) {
        shape.setStroke(colorOfOutline);
    }
}
