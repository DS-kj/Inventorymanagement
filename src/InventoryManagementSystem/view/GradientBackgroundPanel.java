/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package InventoryManagementSystem.view;

import java.awt.Color;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;

/**
 *
 * @author ACER
 */
public class GradientBackgroundPanel extends javax.swing.JPanel {
    private final Color colorStart;
    private final Color colorEnd;
    private final boolean diagonal;

    /**
     * Creates a panel with a gradient background.
     * @param colorStart The start color of the gradient.
     * @param colorEnd The end color of the gradient.
     * @param diagonal True for diagonal gradient, false for vertical.
     */
    public GradientBackgroundPanel(Color colorStart, Color colorEnd, boolean diagonal) {
        this.colorStart = colorStart;
        this.colorEnd = colorEnd;
        this.diagonal = diagonal;
        setOpaque(false); // Important for custom painting
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g.create();

        GradientPaint gp;
        if (diagonal) {
            gp = new GradientPaint(0, 0, colorStart, getWidth(), getHeight(), colorEnd);
        } else {
            gp = new GradientPaint(0, 0, colorStart, 0, getHeight(), colorEnd);
        }

        g2d.setPaint(gp);
        g2d.fillRect(0, 0, getWidth(), getHeight());

        g2d.dispose();
    }
}
