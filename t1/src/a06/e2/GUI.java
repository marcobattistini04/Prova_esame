package a06.e2;

import javax.swing.*;
import java.util.*;
import java.awt.*;

public class GUI extends JFrame {
    
    private final Logic logic;
    private final Map<Pair<Integer, Integer>, JButton> positions;
    
    public GUI(int size) {
        this.logic = new LogicImpl(size);
        this.positions = new HashMap<>();
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(100*size, 100*size);
        
        JPanel main = new JPanel(new BorderLayout());
        JPanel panel = new JPanel(new GridLayout(size,size));
        this.getContentPane().add(main);
        main.add(BorderLayout.CENTER, panel);
        JButton fire = new JButton("Fire");
        main.add(BorderLayout.SOUTH, fire);
        fire.addActionListener( e -> {
                logic.hit();
                if (!logic.isOver()) {
                    this.updateView();
                } else {
                    fire.setEnabled(false);
                }
            }
        );
        for (int i=0; i<size; i++){
            for (int j=0; j<size; j++){
                final JButton jb = new JButton(String.valueOf(Math.random() > 0.5 ? 1 : 2));
                this.positions.put(new Pair<Integer,Integer>(i, j), jb);
                panel.add(jb);
            }
        }
        this.setVisible(true);
    }
    
    private void updateView() {
        logic.newPositions();
    }
}
