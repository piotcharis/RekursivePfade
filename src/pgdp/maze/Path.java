package pgdp.maze;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

// TODO: Implementiere diese Klasse, sodass sie eine Sequenz von Directions repräsentiert
public class Path {
    // TODO: Attribut(e)
    private ArrayList<Direction> path;


    // TODO: Soll einen leeren Pfad erzeugen
    public Path() {
        path = new ArrayList<Direction>();
    }

    // TODO: Soll ein HashSet<Position> mit allen Positionen zurückgeben, die man beim Ablaufen des Pfades 'this'
    //  besucht, wenn man bei der Position 'start' beginnt (ungeachtet irgendwelcher WALLs o.Ä.)
    public Set<Position> toPositionSet(Position start) {
        Set<Position> result = new HashSet<Position>();
        Position currentPosition = start;
        result.add(start);
        for (int i = 0; i < path.size(); i++) {
            result.add(currentPosition.getPositionOneTile(path.get(i)));
            currentPosition = currentPosition.getPositionOneTile(path.get(i));
        }
        return result;
    }

    // TODO: Soll die übergebene Richtung 'direction' vorne in die bisherige Sequenz einfügen
    public void prepend(Direction direction) {
        ArrayList<Direction> newPath = new ArrayList<Direction>();
        newPath.add(direction);
        newPath.addAll(path);
        this.path = newPath;
    }

    public void add(Direction direction) {
        path.add(direction);
    }

    public void remove(Direction direction) {
        path.remove(direction);
    }

    public Direction getLast() {
        return path.get(path.size() - 1);
    }

    public Direction getStep(int index) {
        return path.get(index);
    }

    public int getSize() {
        return path.size();
    }

    // TODO: Soll eine String-Repräsentation des Pfades 'this' zurückgeben, wie in der Aufgabenstellung beschrieben
    @Override
    public String toString() {
        if (path.size() == 0) {
            return "[]";
        }

        String result = "[";
        for (int i = 0; i < path.size() - 1; i++) {
            result += getStep(i) + ", ";
        }
        result += getStep(path.size() - 1) + "]";
        return result;
    }
}
