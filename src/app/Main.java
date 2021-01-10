package app;

import control.BlockPresenter;
import control.Command;
import control.DownCommand;
import control.LeftCommand;
import control.RightCommand;
import control.UpCommand;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.util.HashMap;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import model.Block;
import view.BlockDisplay;

public class Main extends JFrame {
    private static final int SIZE = 100;

    public static void main(String[] args) {
        new Main().execute();
    }
    private Block block;
    private BlockPanel blockDisplay;
    private HashMap<String, Command> commands;
    private BlockPresenter blockPresenter;
    
    
    public Main(){
        this.setTitle("Block shifter");
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setSize(700, 762);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.getContentPane().add(blockPanel());
        this.add(toolbar(), BorderLayout.SOUTH);
    }
    
    private void execute() {
        this.block = new Block(4,4);
        this.blockPresenter = new BlockPresenter(SIZE,block,blockDisplay);
        this.commands = createCommands();
        this.setVisible(true);
        
    }

    private JPanel blockPanel() {
        BlockPanel panel = new BlockPanel(SIZE, Block.MAX);
        this.blockDisplay = panel;
        return panel;
    }

    private HashMap<String, Command> createCommands() {
        HashMap<String, Command> commands = new HashMap<>();
        commands.put("L", new LeftCommand(block));
        commands.put("R", new RightCommand(block));
        commands.put("U", new UpCommand(block));
        commands.put("D", new DownCommand(block));
        return commands;
    }

    private Component toolbar() {
        JMenuBar bar = new JMenuBar();
        bar.setLayout(new FlowLayout(FlowLayout.CENTER));
        bar.add(button("L"));
        bar.add(button("R"));
        bar.add(button("U"));
        bar.add(button("D"));
        return bar;
    }

    private JButton button(String name) {
        JButton button = new JButton(name);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                commands.get(name).execute();
            }
        });
        return button;
    }
    
}
