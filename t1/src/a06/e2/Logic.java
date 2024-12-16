package a06.e2;

import java.util.List;

public interface Logic {
    public void hit();
    public List<Pair<Integer, Integer>> newPositions();
    public boolean isOver();


}
