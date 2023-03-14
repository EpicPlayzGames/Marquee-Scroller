package me.epicplayzgames.lib.frames;

import me.epicplayzgames.lib.panels.MarqueePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;

public class ApplicationFrame implements KeyListener {

    private static final JFrame applicationFrame = new JFrame("Marquee Scroller");
    private static final MarqueePanel marqueePanel = new MarqueePanel();
    private static final JTextArea jTextArea= new JTextArea(10,10);
    private static final JButton selectButton = new JButton("Select File");
    private static final JFileChooser jFileChooser = new JFileChooser();

    public void StartFrame() {

        applicationFrame.setSize(720,120);
        applicationFrame.setLocationRelativeTo(null);
        applicationFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        jTextArea.setEditable(false);

        selectButton.setPreferredSize(new Dimension(150,40));
        selectButton.addActionListener(ev -> {
            int returnVal = jFileChooser.showOpenDialog(applicationFrame);
            if(returnVal == JFileChooser.APPROVE_OPTION) {

                File file = jFileChooser.getSelectedFile();

                try {

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file)));
                    jTextArea.read(bufferedReader, "");

                } catch (Exception e) {

                    e.printStackTrace();

                }

            }

        });

        marqueePanel.setBounds(0,0,720,120);
        marqueePanel.setScrollWhenFocused(false);

        applicationFrame.add(selectButton, BorderLayout.PAGE_END);

        jTextArea.addKeyListener(this);

        marqueePanel.add(jTextArea);
        applicationFrame.add(marqueePanel);
        applicationFrame.setVisible(true);
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode()==123){
            marqueePanel.pauseScrolling();

        } else if(e.getKeyCode() == 122) {
            marqueePanel.resumeScrolling();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
