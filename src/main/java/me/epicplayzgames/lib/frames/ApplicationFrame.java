package me.epicplayzgames.lib.frames;

import me.epicplayzgames.lib.panels.MarqueePanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class ApplicationFrame implements KeyListener {

    private static final JFrame applicationFrame = new JFrame("Marquee Scroller");
    private static final MarqueePanel marqueePanel = new MarqueePanel();
    private static final JTextArea jTextArea = new JTextArea();
    private static final JButton selectButton = new JButton("Select File");
    private static final JFileChooser jFileChooser = new JFileChooser();
    private static final List<String> listOfStrings = new ArrayList<>();
    private static Font womicLanguageRegular;
    private static int returnVal;
    private static File file;
    private static BufferedReader bufferedReader;
    private static String line;
    private static String[] array;

    public void startFrame() {

        try {

            applicationFrame.setSize(720,120);
            applicationFrame.setLocationRelativeTo(null);
            applicationFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            jTextArea.setEditable(false);
            jTextArea.addKeyListener(this);

            marqueePanel.setBounds(0,0,720,120);
            marqueePanel.setScrollWhenFocused(false);

            selectButton.setPreferredSize(new Dimension(150,40));
            selectButton.addActionListener(ev -> {

                returnVal = jFileChooser.showOpenDialog(applicationFrame);

                if (returnVal == JFileChooser.APPROVE_OPTION) {

                    file = jFileChooser.getSelectedFile();

                    try {

                        bufferedReader = new BufferedReader(new FileReader(file));
                        line = bufferedReader.readLine();

                        while (line != null) {

                            listOfStrings.add(line);
                            line = bufferedReader.readLine();

                        }

                        bufferedReader.close();

                        array = listOfStrings.toArray(new String[0]);

                        for (String str: array) {

                            womicLanguageRegular = Font.createFont(Font.TRUETYPE_FONT, new File("src/main/java/me/epicplayzgames/lib/fonts/WomicLanguage-Regular.ttf")).deriveFont(25f);
                            GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment.getLocalGraphicsEnvironment();
                            graphicsEnvironment.registerFont(womicLanguageRegular);

                            jTextArea.setText(str);
                            jTextArea.setFont(womicLanguageRegular);

                        }

                    } catch(Exception e) {

                        JOptionPane.showMessageDialog(applicationFrame, "Error: " + e);

                    }

                }

            });

            jTextArea.setBackground(Color.LIGHT_GRAY);

            marqueePanel.add(jTextArea);

            applicationFrame.getContentPane().add(marqueePanel);
            applicationFrame.add(selectButton, BorderLayout.PAGE_END);
            applicationFrame.setVisible(true);


        } catch (Exception e) {

            JOptionPane.showMessageDialog(applicationFrame, "Error: " + e);

        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {

        if(e.getKeyCode() == 123) {

            marqueePanel.pauseScrolling();

        } else if (e.getKeyCode() == 122) {

            marqueePanel.resumeScrolling();

        }

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

}
