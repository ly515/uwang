package com.uwang.x28.snake;
/**
 * 
 * @author QuinnNorris
 * 
 *         ������ �����߿������ɱ�ʾ�ߵ�һ��������ࣩ
 */
public class SnakePos {

    public int col;
    public int row;
    // һ���������λ������
    // ����Ϊpublic�������

    /**
     * ���й���������ʾ��һ����������Ϸ��������������
     * 
     * @param row
     *            ���ڵ���
     * @param col
     *            ���ڵ���
     */
    SnakePos(int row, int col) {
        this.col = col;
        this.row = row;
    }

    /**
     * ����һ���޲εĹ�����������Ϊ�˵��ã�����Ϊ��ΪFood��������
     */
    SnakePos() {
        col = 0;
        row = 0;
    }
}
