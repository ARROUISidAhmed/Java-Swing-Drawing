/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.ups.m2ihm.drawingtools;

import fr.ups.m2ihm.drawingtools.ui.DrawingToolApplication;

/**
 *
 * @author David
 */
public final class Main {

    /**
     * The drawing tool application to be launched.
     */
    private static DrawingToolApplication drawingToolApplication;

    static {
        drawingToolApplication = new DrawingToolApplication();
    }

    /**
     * Private constructor because Main is a utility class.
     */
    private Main() {

    }

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> {

            drawingToolApplication.setVisible(true);
        });
    }

}
