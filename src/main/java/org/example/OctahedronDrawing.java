package org.example;

import javax.swing.*;
import java.awt.*;

/**
 * A simple Swing application that draws an octahedron
 * and allows resizing and rotating it via buttons.
 */
public class OctahedronDrawing extends JFrame {
    private final Octahedron octahedron;

    /**
     * Constructor initializes the JFrame and UI controls.
     */
    public OctahedronDrawing() {
        // Create octahedron with initial scale
        octahedron = new Octahedron(50);

        // Set up window
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Octahedron Drawing");

        // Panel for control buttons
        JPanel controlPanel = new JPanel();
        JButton resizeButton = new JButton("Resize");
        JButton rotateButton = new JButton("Rotate");
        JTextField resizeScale = new JTextField("2", 10); // Input for scaling

        // Resize button logic
        resizeButton.addActionListener(e -> {
            octahedron.resize(Double.parseDouble(resizeScale.getText()));
            repaint(); // Redraw after resizing
        });

        // Rotate button logic
        rotateButton.addActionListener(e -> {
            octahedron.rotate();
            repaint(); // Redraw after rotation
        });

        // Add components to control panel
        controlPanel.add(rotateButton);
        controlPanel.add(resizeButton);
        controlPanel.add(resizeScale);

        // Add panel to bottom of frame
        add(controlPanel, BorderLayout.SOUTH);

        // Make window visible
        setVisible(true);
    }

    /**
     * Paints the current state of the octahedron on the frame.
     *
     * @param g Graphics context used for drawing
     */
    @Override
    public void paint(Graphics g) {
        super.paint(g);

        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.BLACK);

        int[][] edges = octahedron.getEdges();

        // Draw each edge of the octahedron
        for (int[] edge : edges) {
            int x1 = (int) octahedron.getVertex(edge[0])[0];
            int y1 = (int) octahedron.getVertex(edge[0])[1];
            int x2 = (int) octahedron.getVertex(edge[1])[0];
            int y2 = (int) octahedron.getVertex(edge[1])[1];

            // Convert coordinates to screen center
            g2d.drawLine(
                    x1 + getWidth() / 2, getHeight() / 2 - y1,
                    x2 + getWidth() / 2, getHeight() / 2 - y2
            );
        }
    }

    /**
     * Main method to launch the Swing UI.
     *
     * @param args Command-line arguments (not used)
     */
    public static void main(String[] args) {
        // Run the application in the Event Dispatch Thread
        SwingUtilities.invokeLater(OctahedronDrawing::new);
    }
}
