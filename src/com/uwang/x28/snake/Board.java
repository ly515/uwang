package com.uwang.x28.snake;
import java.awt.EventQueue;
import java.awt.KeyEventPostProcessor;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;

/**
 * 
 * @author QuinnNorris
 * 
 * 
 */
public class Board {

    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub

        // ����һ���̣߳����е�Swing����������¼������߳̽������ã��߳̽�������Ͱ�������ת�Ƶ��û��ӿ������
        EventQueue.invokeLater(new Runnable() {
            // �����ڲ��࣬��һ��Runnable�ӿڵ�ʵ����ʵ����run����
            public void run() {

                JFrame frame = new BoardFrame();
                // ���������Լ������SimpleFrame������Ա��ڵ��ù���������
                
                //���˿�ܵı�������Ϊָ�����ַ�����
                frame.setTitle("Retro Snake");
                // ���ñ��� ,�����û��ڴ˿�����������رա�ʱĬ��ִ�еĲ����� 
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//���崰��x������
                // ѡ���û��رտ�ܵ�ʱ����еĲ��� ������Щʱ����Ҫ���������أ�����ֱ���˳���Ҫ�õ��������
                frame.setVisible(true);//�����ڿɼ���
                // �����ڿɼ����������Ա��û��ڵ�һ�ο�������֮ǰ�����ܹ��������������
            }
        });
    }

}

class BoardFrame extends JFrame {

    private Snake snk;
    // �����ǻ�ͼ�Ĺ������򴴽�һ���߶�������

    public static final int INTERVAL = Configure.INTERVAL;

    // ��Ҫ�õ���˯�߼�����������ߵ��ƶ��ٶ�
    // ��Configure�ļ��ж�ȡ����Ϸʱ����

    public BoardFrame() {

        snk = new Snake();

        snk.setFood(new Food().getSnake(snk.getSnakeBody()));
        // ����һ��ʳ����󣬵���getSnake�����жϸ�ʳ�����ɵ㲻���ߵ�������
        // getSnake�ķ���������Food����������ֱ�ӵ���

        final KeyboardFocusManager manager = KeyboardFocusManager
                .getCurrentKeyboardFocusManager();
        // ����һ�����̼��������
        // ��Ϊ����Ҫ�����濪���̣߳��߳���ֻ�ܻ��final���εľֲ�������������������ǲ��ɱ��

        new Thread(new Runnable() {
            // �����߳��������ػ���
            // ֮���Բ��ö��̣߳���Ϊ���ô�����������Ҫ�ı��˫��̰���߸�����

            public void run() {

                while (true) {
                    BoardComponent bc = new BoardComponent();
                    bc.setSnake(snk);
                    add(bc);
                    // ����JComponent��ʵ���������洴�����߶�����

                    MyKeyEventPostProcessor mke = new MyKeyEventPostProcessor();
                    mke.setSnk(snk);
                    manager.addKeyEventPostProcessor(mke);
                    // �����������̵�ʵ����ͬ�����߶�����

                    try {
                        Thread.sleep(INTERVAL);
                        // ���˶�֮����Ҫ�������sleep�����ﵽͣ�ٵ�Ч��
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                    snk.snakeMove();
                    // �����ƶ�����

                    pack();
                    // ����Ĭ�ϴ�С�Ĵ���
                }
            }
        }).start();

    }

}

class MyKeyEventPostProcessor implements KeyEventPostProcessor {

    private Snake snk;

    public boolean postProcessKeyEvent(KeyEvent e) {

        Direction dir = null;
        // ����һ��Directionö��������
        switch (e.getKeyCode()) {
        case KeyEvent.VK_UP:
            dir = Direction.UP;
            break;
        case KeyEvent.VK_DOWN:
            dir = Direction.DOWN;
            break;
        case KeyEvent.VK_LEFT:
            dir = Direction.LEFT;
            break;
        case KeyEvent.VK_RIGHT:
            dir = Direction.RIGHT;
            break;
        }
        // ���ݲ�ͬ�ķ����������ȡ��ֵ�����dir��

        if (dir != null)
            snk.setMoveDir(dir);
        // �����ȡ����ֵ�����������ĸ��������һ������ô��dir��ŵ�Snake���moveDir������
        return true;
    }

    public void setSnk(Snake snk) {
        this.snk = snk;
    }

}

class BoardComponent extends JComponent {

    public static final int Width = Configure.WIDTH;
    public static final int Height = Configure.HEIGTH;
    public static final int TileWidth = Configure.TILE_WIDTH;
    public static final int TileHeight = Configure.TILE_HEIGHT;
    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
    private static final int XOffset = (Width - Column * TileWidth) / 2;
    private static final int YOffset = (Height - Row * TileHeight) / 2;
    // ��Configure�ļ��ж�ȡ����Ϸ����

    private Snake snk;

    public void setSnake(Snake snk) {
        this.snk = snk;
    }

    /**
     * ���Ǹ����������������ӡ
     * 
     * @param g
     */
    public void paintComponent(Graphics g) {
        drawDecoration(g);
        drawFill(g);
    }

    /**
     * ����ʵ�ĵ��������Լ�ʳ��
     * 
     * @param g
     */
    public void drawFill(Graphics g) {

        g.setColor(Color.GREEN);

        for (SnakePos sp : snk.getSnakeBody())
            g.fillRect(sp.col * TileWidth + XOffset, sp.row * TileHeight
                    + YOffset, TileWidth, TileHeight);
        // �����ߵ����壬��ÿһ���߶���ɫ
        Food fd = snk.getFood();

        g.setColor(Color.BLUE);

        // ����ǰ��ʳ����ɫ
        g.fillRect(fd.col * TileWidth + XOffset, fd.row * TileHeight + YOffset,
                TileWidth, TileHeight);
    }

    /**
     * ������Ϸ�ı߽��ɫ��
     * 
     * @param g
     */
    public void drawDecoration(Graphics g) {
        g.setColor(Color.RED);
        g.drawRect(XOffset, YOffset, Column * TileWidth, Row * TileHeight);
    }

    /**
     * ���Ǹ����������������ʾ������������Ĵ�С
     * 
     * @return �����������������Ӧ���ж��
     */
    public Dimension getPreferredSize() {
        return new Dimension(Width, Height);
        // ����һ��Dimension���󣬱�ʾ�������Ĵ�С
    }
}