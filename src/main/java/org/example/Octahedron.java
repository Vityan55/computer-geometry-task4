package org.example;

/**
 * Represents a 3D octahedron with operations for resizing and rotating.
 * The octahedron is defined by its vertices and the edges connecting them.
 */
public class Octahedron {
    private final double[][] vertex;
    private final int[][] edges;

    /**
     * Constructs an octahedron centered at the origin with the given size.
     *
     * @param size Half the length of the edges; determines the size of the octahedron.
     */
    public Octahedron(double size) {
        // Define the 6 vertices of the octahedron in 3D space
        vertex = new double[][]{
                {0, size, 0},     // top
                {size, 0, 0},     // right
                {0, -size, 0},    // bottom
                {-size, 0, 0},    // left
                {0, 0, size},     // front
                {0, 0, -size}     // back
        };

        // Define the 12 edges as pairs of vertex indices
        edges = new int[][]{
                {0, 1}, {1, 2}, {2, 3}, {3, 0}, // bottom square
                {0, 4}, {1, 4}, {2, 4}, {3, 4}, // front pyramid edges
                {0, 5}, {1, 5}, {2, 5}, {3, 5}  // back pyramid edges
        };
    }

    /**
     * Resizes the octahedron by scaling all its vertices by a specified factor.
     *
     * @param newSize Scale factor by which to resize the octahedron.
     */
    public void resize(double newSize) {
        // Scale each coordinate of each vertex (only x and y)
        for (int i = 0; i < vertex.length; i++) {
            for (int j = 0; j < vertex[i].length - 1; j++) {
                vertex[i][j] *= newSize;
            }
        }
    }

    /**
     * Rotates the octahedron 10 degrees around the Y-axis.
     */
    public void rotate() {
        double angle = Math.toRadians(10); // Convert degrees to radians

        // Rotation matrix for Y-axis
        double[][] rotationMatrix = {
                {Math.cos(angle), 0, Math.sin(angle)},
                {0, 1, 0},
                {-Math.sin(angle), 0, Math.cos(angle)}
        };

        // Multiply each vertex by the rotation matrix
        for (int i = 0; i < vertex.length; i++) {
            double[] result = new double[3]; // Store new vertex coordinates
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    result[j] += rotationMatrix[j][k] * vertex[i][k];
                }
            }
            vertex[i] = result; // Update the vertex with rotated coordinates
        }
    }

    /**
     * Returns the coordinates of the vertex at the specified index.
     *
     * @param index Index of the vertex (0–5)
     * @return 3D coordinates of the vertex
     */
    public double[] getVertex(int index) {
        return vertex[index];
    }

    /**
     * Returns the list of edges represented by pairs of vertex indices.
     *
     * @return 2D array of vertex index pairs forming edges
     */
    public int[][] getEdges() {
        return edges;
    }
}
