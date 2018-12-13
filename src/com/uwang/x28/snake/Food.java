package com.uwang.x28.snake;
import java.util.LinkedList;

/**
 * 
 * @author QuinnNorris
 * 
 *         ʳ����
 */
public class Food extends SnakePos {

    public int row;
    public int col;
    // ��ʾʳ�����ڵ�����

    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
    // ��Configure�ļ��ж�ȡ����Ϸ����

    Food() {
        randomPos();
        // ������������������б���
    }

    /**
     * ��ȡ�ߵ�snakeBody������ʳ���������ص�
     * 
     * @param snakeBody
     *            ��ʾ�����������
     * @return ���������ʵ�����Ķ�����
     */
    public Food getSnake(LinkedList<SnakePos> snakeBody) {
        while (checkSame(snakeBody))
            randomPos();
        // �������ʳ���λ�ú��������ص������������ʳ���λ��
        return this;
        // �������������Ϊ����ʵ��ʱ��������
    }

    /**
     * ����������������Ƿ���һ���뵱ǰʳ��������ͬ
     * 
     * @param snakeBody
     *            ��ʾ�����������
     * @return ������ظ�����true
     */
    private boolean checkSame(LinkedList<SnakePos> snakeBody) {
        for (SnakePos sp : snakeBody)
            if (sp.row == this.row && sp.col == this.col)
                return true;
        // ѭ�������Ƿ����ظ�
        return false;
    }

    /**
     * ����ö�����к��б���
     */
    private void randomPos() {
        this.row = (int) (Math.random() * Row);
        this.col = (int) (Math.random() * Column);
    }
}
