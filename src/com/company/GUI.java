package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import com.company.logic.Box;
import com.company.logic.Coord;
import com.company.logic.Game;
import com.company.logic.Ranges;

public class GUI extends JFrame{
    private final int IMAGE_SIZE = 50;
    int cols , rows , bombs , saved_cols = 0, saved_rows = 0, saved_bombs = 0;
    private Game game;
    public JPanel panelBoard;
    private JPanel panelHelp;
    private JPanel panelDialog;
    ImageIcon[] ic = new ImageIcon[3];
    JButton reset = new JButton("");
    JButton customs = new JButton("Settings");
    JButton bot = new JButton("BOT");
    private JFrame frame;
    JTextField t1, t2, t3;
    JLabel lb1, lb2, lb3;
    JButton b1;

    protected GUI(){ // конструктор
        initFrame();
    }

    public void initFrame(){
        frame = new JFrame();
        panelDialog = new JPanel();

        t1 = new JTextField();
        t2 = new JTextField();
        t3 = new JTextField();

        lb1 = new JLabel("Row");
        lb2 = new JLabel("Column");
        lb3 = new JLabel("mine");

        b1 = new JButton("OK");
        b1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    if(e.getSource() == b1) {
                        rows = Integer.parseInt(t1.getText());
                        cols = Integer.parseInt(t2.getText());
                        bombs = Integer.parseInt(t3.getText());
                        //Minesweeper ms=new Minesweeper();
                        saved_cols = cols;
                        saved_rows = rows;
                        saved_bombs = bombs;
                        game = new Game(saved_cols, saved_rows, saved_bombs);
                        dispose();
                        butIcons();
                        setImages();
                        initMenu();
                        initPanel();
                        initFrame1();
                        game.start();
                        panelBoard.repaint();

                    }
                } catch (Exception any) {
                    JOptionPane.showMessageDialog(null, "Wrong");
                    t1.setText("");
                    t2.setText("");
                    t3.setText("");
                }
            }
        });

        panelDialog.removeAll();
        panelDialog.setBorder(BorderFactory.createLoweredBevelBorder());
        panelDialog.setLayout(new GridLayout(0 , 2));

        panelDialog.add(lb1);
        panelDialog.add(t1);
        panelDialog.add(lb2);
        panelDialog.add(t2);
        panelDialog.add(lb3);
        panelDialog.add(t3);

        panelDialog.add(b1);

        frame.getContentPane().add(panelDialog, BorderLayout.CENTER);
        frame.setContentPane(panelDialog);
        frame.setSize(200,200);
        frame.setVisible(true);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setIconImage(gameIcons("icon"));
        frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private void initFrame1(){ // инициализация фрейма
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        setTitle("Minesweeper");
        setResizable(false);
        pack();
        setLocationRelativeTo(null);
        setIconImage(gameIcons("icon"));
    }

    private void initPanel() { // инициализация панели
        panelHelp = new JPanel();
        panelBoard = new JPanel(){
            @Override
            public void paintComponent(Graphics g) {
                super.paintComponent(g);
                for (Coord coord : Ranges.getAllCoords()) {
                    g.drawImage((Image) game.getBox(coord).image, coord.x * IMAGE_SIZE, coord.y * IMAGE_SIZE,null);
                }
            }
        };

        panelBoard.addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                int x = e.getX() / IMAGE_SIZE;
                int y = e.getY() / IMAGE_SIZE;
                Coord coord = new Coord(x, y);

                if (e.getButton() == MouseEvent.BUTTON1){
                    game.pressedLeftButton(coord);
                }

                if (e.getButton() == MouseEvent.BUTTON3){
                    game.pressedRightButton(coord);
                }

                panelBoard.repaint();
            }
        });

        customs.setPreferredSize(new Dimension(70, 30));
        customs.setBackground(Color.LIGHT_GRAY);
        customs.setFont(new Font("DigtalFont.TTF", Font.BOLD, 18));
        customs.setBorder(BorderFactory.createEtchedBorder());
        customs.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                initFrame();
            }
        });

        //Кнопка ресет_________________________________
        reset.setPreferredSize(new Dimension(30, 30));
        reset.setIcon(ic[1]);
        reset.setBackground(Color.GRAY);
        reset.setLayout(new GridLayout());
        reset.setBorder(BorderFactory.createEtchedBorder());
        reset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                game.start();
                panelBoard.repaint();
            }
        });

        //Кнопка бот_________________________________
        bot.setPreferredSize(new Dimension(70, 30));
        bot.setBackground(Color.LIGHT_GRAY);
        bot.setLayout(new GridLayout(0, 4));
        bot.setFont(new Font("DigtalFont.TTF", Font.BOLD, 18));
        bot.setBorder(BorderFactory.createEtchedBorder());

        //Верхняя панель_________________________________
        panelHelp.removeAll();
        panelHelp.setLayout(new BorderLayout());
        panelHelp.add(reset, BorderLayout.CENTER);
        panelHelp.add(bot, BorderLayout.EAST);
        panelHelp.add(customs, BorderLayout.WEST);
        panelHelp.setBorder(BorderFactory.createLoweredBevelBorder());

        //Основная панель_________________________________
        panelBoard.setLayout(new BorderLayout());
        panelBoard.setPreferredSize(new Dimension(Ranges.getSize().x * IMAGE_SIZE, Ranges.getSize().y * IMAGE_SIZE));
        //panelBoard.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10), BorderFactory.createLoweredBevelBorder()));

        panelHelp.repaint();
        panelBoard.repaint();


        getContentPane().add(panelHelp, BorderLayout.NORTH);
        getContentPane().add(panelBoard, BorderLayout.CENTER);
    }



    public void initMenu(){//Строчка меню

        JMenuBar bar = new JMenuBar();

        JMenu game = new JMenu("Game");
        final JMenuItem exit = new JMenuItem("Exit");
        final JMenu help = new JMenu("Help");
        final JMenuItem helpitem = new JMenuItem("Help");


        //Действие кнопки Exit в разделе Game______________________
        exit.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        //Действи кнопки Help в разделе Help_______________________
        helpitem.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null, "instruction");
            }
        });

        // Добавление графических элементов меню_____________
        setJMenuBar(bar);

        game.add(exit);
        help.add(helpitem);

        bar.add(game);
        bar.add(help);
    }


    private void setImages (){
        for (Box box : Box.values()) {
            box.image = gameIcons(box.name().toLowerCase());
        }

    }

    private Image gameIcons (String name){
        String filename = "/img/" + name + ".png";
        ImageIcon icon = new ImageIcon(getClass().getResource(filename));

        return icon.getImage();
    }

    private void butIcons(){//Картинки для кнопок
        ic[1] = new ImageIcon("resource/img/new game.gif");
        ic[2] = new ImageIcon("resource/img/crape.gif");

    }

}
