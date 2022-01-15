package pgdp.maze;

import static pgdp.maze.Direction.*;

public final class MazeSolver {

    // Man soll von außen keine Objekte der Klasse erzeugen können. Alle Methoden
    // sind static. Daher ist der Konstruktor private
    private MazeSolver() {

    }

    public static Path solveMaze(Maze maze) {
        if (maze == null) {
            return null;
        }

        // Aufruf der Methode solveMazeFrom mit dem Eingang als Position
        return solveMazeFrom(maze, maze.getEntrance());
    }

    public static Path solveMazeFrom(Maze maze, Position position) {
        if (maze == null || position == null) {
            return null;
        }

        Path path = new Path();

        // Rekursive Funktion, die das richtige Path findet
        if (findExit(maze, position, path)) {
            return path;
        }

        return null;
    }

    private static boolean findExit(Maze maze, Position position, Path path) {
        // Check, ob die Position gültig ist
        if (!maze.isEmptyTile(position)) {
            return false;
        }

        // markieren der Position
        maze.mark(position);

        // Ziel der Rekursion
        if (position.equals(maze.getExit())) {
            return true;
        }

        // Überprüfen der direkten Nachbarn der Position in allen Richtungen
        // rekursiv und beim richtigen Path addieren der Direktion am Anfang des Paths.
        if (findExit(maze, position.getPositionOneTile(LEFT), path)) {
            path.prepend(LEFT);
            return true;
        } else if (findExit(maze, position.getPositionOneTile(RIGHT), path)) {
            path.prepend(RIGHT);
            return true;
        } else if (findExit(maze, position.getPositionOneTile(UP), path)) {
            path.prepend(UP);
            return true;
        } else if (findExit(maze, position.getPositionOneTile(DOWN), path)) {
            path.prepend(DOWN);
            return true;
        }

        return false;
    }

    // Zum Testen
    public static void main(String[] args) {
        Maze maze = MazeParser.parseFromFile("Maze.txt");
        if (maze == null) {
            return;
        }

        System.out.println(maze);
        Path path = solveMaze(maze);
        System.out.println(path);
        System.out.println();
        System.out.println(maze.toString(path));
    }

}
