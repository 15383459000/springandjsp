

import com.xitianfo.exception.SysException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Objects;

/**
 * @Author ycSong
 * @create 2020/1/10 14:21
 */
public class MyHashMap<k,v> {

    //初始桶数
    private static final int DEFAULT_INITIAL_CAPACITY = 16;
    //创建链表数组
    private HashSet[] links = new HashSet[DEFAULT_INITIAL_CAPACITY];

    /**
     * 添加
     * @param key
     * @param value
     */
    public void put (k key,v value){
        //接收entry
        Entry<k,v> entry = new Entry<>(key,value);
        //计算hash值
        int hashValue =key.hashCode()%16;
        //第一次使用新建对象
        if (links[hashValue]==null){
            links[hashValue] = new HashSet<Entry>();
        }
        //添加至集合当中
        if (links[hashValue].contains(entry)){
            links[hashValue].remove(entry);
        }
        links[hashValue].add(entry);

    }

    /**
     * 移除数据
     * @param key
     */
    public void remove (k key){
        //计算hash值
        int hashValue = key.hashCode()%16;
        //检验是否有这个桶
        if (links[hashValue]==null){
            throw new SysException("没这个，瞎删");
        }else {
            //遍历这个桶，如果键则删除
            for (Object i: links[hashValue]){
                if (((Entry)i).key.equals(key)){
                    links[hashValue].remove(i);
                    return;
                }
            }
            throw new SysException("没这个");
        }
    }

    /**
     * 通过健获取值
     * @param key
     * @return
     */
    public v get(k key) {
        //计算hash值
        int hashValue = key.hashCode()%16;
        //检验是否有这个桶
        if (links[hashValue]==null){
            throw new SysException("没这个，瞎查");
        }else {
            //遍历这个桶，如果键相同返回值
            for (Object i: links[hashValue]){
                if (((Entry)i).key.equals(key)){
                    return (v) ((Entry) i).value;
                }
            }
            throw new SysException("没这个");
        }
    }


    /**
     * 键值对类
     * @param <k>
     * @param <v>
     */
    private static class Entry<k,v> {

        private k key;

        private v value;

        public Entry(k key, v value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public boolean equals(Object o) {

            //如果不能能转换，
            if (!(o instanceof Entry)){
                return false;
            }
            //如果两个key值相同返回true
            if (this.key.equals(((Entry) o).key)) {
                return true;
            }
            return false;
        }

        @Override
        public int hashCode() {
            //直接返回key值的hash值
            return key.hashCode();
        }
    }

    public static void main(String[] args) {

        try {
            MyHashMap<String,String> myHashMap =new MyHashMap<>();
            myHashMap.put("aaa","111");
            myHashMap.put("aaa","777");
            myHashMap.put("abc","55");
            myHashMap.put("abd","66");
            System.out.println(myHashMap.get("aaa"));
            myHashMap.remove("aaa");
            System.out.println("1");
        } catch (SysException e) {
            System.out.println(e.toString());
        }
    }
}
