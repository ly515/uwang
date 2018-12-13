package com.uwang.x28.snake;
import java.util.LinkedList;

/**
 * 
 * @author QuinnNorris
 * 
 *         �ߵ�ʵ����
 */
public class Snake {

    private Direction snakeDir;
    // ��ǰ��ͷ����ķ���

    private Direction moveDir;
    // moveDir�Ǵ�Board���ж�ȡ���ķ���
    // moveDir����run������һ�������У�ͨ�����̶�ȡ�ģ���ͷ��Ҫ�ı�ķ���

    // ��ε��߼��ǣ������ȴ�Board�ļ��̼�������ȡ�����Ҫ�ı���ߵķ��򣬵������ǲ�ֱ�Ӱ��ߵķ������óɻ�ȡ�ķ���
    // ��Ϊ��������run������һ�������ж�ΰ��²�ͬ�ķ���������ܻᵼ��һЩBUG�������ȼ�¼�������Ҫ�ı�ɡ��ķ���
    // Ȼ�����ƶ���ʱ�򣬻�ȡ�����Ҫ�ı�ķ���moveDir�������ڵķ���snakeDir�������жϺ��ٴ���

    private Food food;
    // ��ǰ������Ϸ�е�ʳ��������߳���һ��ʳ�����ˢ��

    private LinkedList<SnakePos> snakeBody;
    // �ߵ����壬��һ����SnakePos��Ԫ����
    // ���ݽṹ��������Ϊ������ʴ����٣�����ɾ��������

    public static final int Row = Configure.ROW;
    public static final int Column = Configure.COL;
    // ��Configure�ļ��ж�ȡ����Ϸ����

    public Snake() {
        snakeBody = new LinkedList<SnakePos>();
        reset();
        // ��ʼ����
    }

    public Direction getSnakeDir() {
        return snakeDir;
    }

    public void setSnakeDir(Direction snakeDir) {
        this.snakeDir = snakeDir;
    }

    public LinkedList<SnakePos> getSnakeBody() {
        return snakeBody;
    }

    public Food getFood() {
        return food;
    }

    public void setFood(Food food) {
        this.food = food;
    }

    public void setMoveDir(Direction dir) {
        this.moveDir = dir;
    }

    /**
     * �˷���������ʼ���ߣ����߱��һ����ֱ3�񳤶ȣ��������ϵ����λ������
     */
    public void reset() {
        snakeBody.clear();
        // �������
        SnakePos beginPos = null;
        // ����һ����ͷ������
        setMoveDir(null);
        // �����̼����ķ�������Ϊnull
        do {
            beginPos = this.RandomPos();
            // ���÷������������ͷλ��
        } while (beginPos.row + 3 > Row);
        // �����ͷ��������û�Ӵ����ױߣ���������ǿ��Ա����ܵ�

        snakeBody.add(beginPos);
        snakeBody.add(new SnakePos(beginPos.row + 1, beginPos.col));
        snakeBody.add(new SnakePos(beginPos.row + 2, beginPos.col));
        // �������ߣ�������ͷ����ӵ�SnakeBody������
        setSnakeDir(Direction.UP);
        // ���÷���Ϊ����
    }

    /**
     * ����һ�������壨SnakePos�ࣩ��������������У����䷵��
     * 
     * @return ���б������һ�����������
     */
    private SnakePos RandomPos() {

        int randomRow = (int) (Math.random() * Row);
        int randomCol = (int) (Math.random() * Column);

        return new SnakePos(randomRow, randomCol);
    }

    /**
     * �����ߵ��ƶ�
     */
    public void snakeMove() {

        int addRow = snakeBody.getFirst().row;
        int addCol = snakeBody.getFirst().col;
        // ��Ҫ��ӵ�����ͷ�ض���ԭ��ͷ����ĳ�������һ��
        // �Ƚ�����ͷ����������Ϊԭ��ͷ������

        Direction up = Direction.UP;
        Direction down = Direction.DOWN;
        Direction left = Direction.LEFT;
        Direction right = Direction.RIGHT;
        // ����Directionö������ĸ����ã�Ϊ����д�����֣��ٺ٣�

        if ((moveDir != null)
                && !((snakeDir == up && moveDir == down)
                        || (snakeDir == down && moveDir == up)
                        || (snakeDir == left && moveDir == right) || (snakeDir == right && moveDir == left)))
            snakeDir = moveDir;
        // ��������������ͽ����̼�����moveDir��������Ϊ���µ���ͷ����

        switch (snakeDir) {
        case UP:
            addRow--;
            break;
        case DOWN:
            addRow++;
            break;
        case LEFT:
            addCol--;
            break;
        case RIGHT:
            addCol++;
            break;
        }
        // ����������ͷ����ȷ���µ���ͷ���ĸ������ɣ��޸�����ͷ����������

        SnakePos addPos = new SnakePos(addRow, addCol);
        // ��������������꣬����һ�������壨SnakePos������

        if (!isFood(addPos))
            snakeBody.removeLast();
        // �������ʳ���ȥ��snakeBody���������һ���ڵ�
        else
            setFood(new Food().getSnake(snakeBody));
        // ��ʳ�����������һ��ʳ�����ȥ�����һ���ڵ�

        if (isCollision(addPos))
            reset();
        // �����ײ�ˣ�������������
        else
            snakeBody.addFirst(addPos);
        // û��ײ�ͽ��ղ����õ�����ͷ�Ž�������
        // ע�⣬��ʹ��ʳ��Ҳ��ִ����һ�仰����Ϊ����ʳ�ﲻ������ײ
    }

    /**
     * �ж�һ�����ǲ���ʳ��
     * 
     * @param addPos
     *            Ҫ�жϵĸ���
     * @return ����true��ʾ��ʳ��
     */
    private boolean isFood(SnakePos addPos) {
        if (food.row == addPos.row && food.col == addPos.col)
            return true;
        // �����������������������е�food����������һ���ͱ�ʾ��ʳ��
        return false;
    }

    /**
     * ��ײ��⣬�������ǽ�ڻ������������Ϊ��ײ
     * 
     * @param addPos
     *            Ҫ�ж��Ƿ�Ϊǽ�ڣ��������壩�ĸ���
     * @return �ᷢ����ײ����true
     */
    private boolean isCollision(SnakePos addPos) {
        if (addPos.row < 0 || addPos.row > Row - 1 || addPos.col < 0
                || addPos.col > Column - 1)
            return true;
        // �����ǽ�ڷ���true
        for (SnakePos sp : snakeBody)
            if ((sp.row == addPos.row) && (sp.col == addPos.col))
                return true;
        // ����������巵��true
        return false;
    }

}