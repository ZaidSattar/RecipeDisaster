package src.main.java;

import javax.swing.*;
import java.awt.*;
import java.awt.font.TextLayout;
import java.awt.geom.Rectangle2D;

/**
 * A custom JLabel class for displaying outlined text.
 * 
 * @author viktoriya.li
 */
public class OutlinedLabel extends JLabel {
    private Color innerOutlineColor = Color.WHITE; // Color of the inner outline
    private Color outerOutlineColor = Color.BLACK; // Color of the outer outline
    private Color darkOrange = new Color(204, 85, 0); // Color used for text
    private int outerOutlineWidth = 7; // Width of the outer outline
    private int innerOutlineWidth = 3; // Width of the inner outline

    /**
     * Constructs an OutlinedLabel with the specified text.
     * @param text The text to display
     */
    public OutlinedLabel(String text) {
        super(text);
        setForeground(darkOrange);
    }

    /**
     * Sets the color of the inner outline.
     * @param innerOutlineColor The color of the inner outline
     */
    public void setInnerOutlineColor(Color innerOutlineColor) {
        this.innerOutlineColor = innerOutlineColor;
    }

    /**
     * Sets the color of the outer outline.
     * @param outerOutlineColor The color of the outer outline
     */
    public void setOuterOutlineColor(Color outerOutlineColor) {
        this.outerOutlineColor = outerOutlineColor;
    }

    /**
     * Sets the width of the outer outline.
     * @param outerOutlineWidth The width of the outer outline
     */
    public void setOuterOutlineWidth(int outerOutlineWidth) {
        this.outerOutlineWidth = outerOutlineWidth;
    }

    /**
     * Sets the width of the inner outline.
     * @param innerOutlineWidth The width of the inner outline
     */
    public void setInnerOutlineWidth(int innerOutlineWidth) {
        this.innerOutlineWidth = innerOutlineWidth;
    }

    @Override
    protected void paintComponent(Graphics g) {
        String text = getText();
        if (text == null || text.length() == 0) {
            super.paintComponent(g);
            return;
        }

        Graphics2D g2d = (Graphics2D) g.create();
        g2d.setFont(getFont());
        FontMetrics fm = g2d.getFontMetrics();
        
        // Enable Anti-aliasing
        g2d.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
        
        int textWidth = fm.stringWidth(text);
        int textHeight = fm.getHeight();

        // Draw the text outline
        TextLayout textLayout = new TextLayout(text, getFont(), g2d.getFontRenderContext());
        Rectangle2D bounds = textLayout.getBounds();
        double x = (getWidth() - bounds.getWidth()) / 2;
        double y = ((getHeight() - textHeight) / 2) + fm.getAscent();
        g2d.translate(x, y);

        // Draw the outer outline
        g2d.setColor(outerOutlineColor);
        g2d.setStroke(new BasicStroke(outerOutlineWidth));
        g2d.draw(textLayout.getOutline(null));

        // Draw the inner outline
        g2d.setColor(innerOutlineColor);
        g2d.setStroke(new BasicStroke(innerOutlineWidth));
        g2d.draw(textLayout.getOutline(null));

        // Draw the text
        g2d.setColor(getForeground());
        textLayout.draw(g2d, 0, 0);

        g2d.dispose();
    }
}