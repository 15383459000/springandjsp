import java.util.ArrayList;
import java.util.HashMap;

/**
 * @Author ycSong
 * @create 2020/1/10 9:49
 */
public class MyList<T> {

    private static final Integer DEFAULT_INITITAL_CAPACITY = 10;

    /**
     * 数组最大容量
     */
    private int capacity = DEFAULT_INITITAL_CAPACITY;

    /**
     *数据存储
     */
    private T[] storage = (T[]) new  Object[DEFAULT_INITITAL_CAPACITY];

    /**
     * 动态数组大小
     */
    private int size = 0;

    /**
     * 获取数组大小
     * @return
     */
    public int getSize() {
        return size;
    }

    /**
     * 添加数据
     * 当数据放不下
     * @param value
     */
    public void add(T value) {
        if (size == capacity) {
            /**
             * 创建新数组
             */
            int newCaoacity = capacity + capacity * 2;
            this.capacity = newCaoacity;
            T[] newStorage = (T[]) new  Object[newCaoacity];
            for (int i =0;i<size;i++){
                newStorage[i] = storage[i];
            }
            //新数组替换
            this.storage = newStorage;

        }
        this.storage [size] = value;
        size++;
    }

    /**
     * 获取
     * @param index
     * @return
     */
    public Object get (Integer index){
        //检查是否越界
        if (index>size-1) {
            throwArrayIndexOutOfBoundsException(index);
        }
        return storage[index];
    }

    /**
     * 移除
     * @param index
     */
    public void remove (Integer index) {
        //检查是否越界
        if (index>size-1) {
            throwArrayIndexOutOfBoundsException(index);
        }
        storage[index] = null;
    }

    /**
     * 抛出越界异常
     * @param index
     */
    private void throwArrayIndexOutOfBoundsException(Integer index){

        throw  new  ArrayIndexOutOfBoundsException("index "+index+" out of bound");
    }

    public static void main(String[] args) {

        MyList<Integer> myList = new MyList<>();
        myList.add(1);
        ArrayList<Integer> list = new ArrayList<>();
        list.add(0);
        list.add(1);
        list.add(2);
        list.remove(0);
        System.out.println(list.get(0));

    }

}