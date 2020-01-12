import java.util.*;

/**
 * 自己实现的HashMap
 * @author meteor
 */
public class SimpleHashMap<K, V> {

    /**
     * 静态内部类，键值对
     * @param <K>
     * @param <V>
     */
    public static class Pair<K, V> {

        /**
         * 键
         */
        private K key;

        /**
         * 值
         */
        private V value;

        public Pair(K key, V value) {
            this.key = key;
            this.value = value;
        }

        /**
         * 生成一个value为null的键值对对象
         * @param key
         * @param <K>
         * @param <V>
         * @return
         */
        private static <K, V> Pair<K, V> nullValue(K key) {
            return new Pair<>(key, null);
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }

            Pair<?, ?> pair = (Pair<?, ?>) o;

            return key.equals(pair.key);
        }

        @Override
        public int hashCode() {
            return key.hashCode();
        }

        @Override
        public String toString() {
            return "Pair{" +
                    "key=" + key +
                    ", value=" + value +
                    '}';
        }
    }

    /**
     * 已经插入的元素数量
     */
    private int size;

    /**
     * 存放数据链表的插槽
     */
    private LinkedList<Pair<K, V>>[] slots = new LinkedList[100];

    /**
     * 存放所有的key
     */
    private Set<K> keySet = new HashSet<>();

    /**
     * 插入元素
     * @param key
     * @param value
     */
    public void put(K key, V value) {
        int slotIndex = slotIndex(key);
        if (null == slots[slotIndex]) {
            slots[slotIndex] = new LinkedList<>();
        }

        LinkedList<Pair<K, V>> linkedList = slots[slotIndex];
        Pair<K, V> pair = new Pair<>(key, value);
        // 删除旧元素
        if (linkedList.contains(pair)) {
            linkedList.remove(pair);
            size--;
        }

        // 添加新元素
        linkedList.add(pair);
        keySet.add(key);
        size++;
    }

    /**
     * 获取元素
     * @param key
     * @return
     */
    public V get(K key) {

        // 获取key所在的插槽的数据链表
        LinkedList<Pair<K, V>> linkedList = getLinkedList(key);
        if (null == linkedList) {
            return null;
        }

        Iterator<Pair<K, V>> iterator = linkedList.iterator();
        // 查找元素
        while (iterator.hasNext()) {
            Pair<K, V> temp = iterator.next();

            // 查找成功
            if (temp.key.equals(key)) {
                return temp.getValue();
            }
        }

        // 查找失败
        return null;
    }

    /**
     * 删除元素
     * @param key
     */
    public void remove(K key) {
        LinkedList<Pair<K, V>> linkedList = getLinkedList(key);
        if (null == linkedList) {
            return;
        }

        // 删除数据
        linkedList.remove(Pair.nullValue(key));
        keySet.remove(key);
        size--;
    }

    /**
     * 判断是否存在key，外部调用
     * @param key
     * @return
     */
    public boolean containsKey(K key) {
        // 计算key所在的插槽的数据链表
        LinkedList<Pair<K, V>> linkedList = getLinkedList(key);
        if (null == linkedList) {
            return false;
        }
        return linkedList.contains(Pair.nullValue(key));
    }

    /**
     * 获取元素数量
     * @return
     */
    public int size() {
        return size;
    }

    /**
     * 获取所有的key
     * @return
     */
    public Set<K> keySet() {
        return keySet;
    }

    /**
     * 为map里面的键值对生成迭代器
     */
    public Iterable<Pair<K, V>> pairs() {
        return new Iterable() {
            @Override
            public Iterator iterator() {
                return new Iterator<Pair<K, V>>() {
                    private Iterator<K> keySetIterator = keySet.iterator();

                    @Override
                    public boolean hasNext() {
                        return keySetIterator.hasNext();
                    }

                    @Override
                    public Pair<K, V> next() {
                        K key = keySetIterator.next();
                        return new Pair<>(key, get(key));
                    }
                };
            }
        };
    }

    /**
     * 格式化输出map里面的所有内容
     * @return
     */
    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (int i = 0; i < slots.length; i++) {
            LinkedList<Pair<K, V>> linkedList = slots[i];
            if (null == linkedList) {
                continue;
            }

            linkedList.forEach(pair -> {
                builder.append(pair.key);
                builder.append(" : ");
                builder.append(pair.value);
                builder.append(", ");
            });
        }

        builder.delete(builder.length() - 2, builder.length());
        builder.append(" }");

        return builder.toString();
    }

    /**
     * 获取key所在插槽里面的数据链表
     * @param key
     * @return
     */
    private LinkedList<Pair<K, V>> getLinkedList(K key) {
        // 返回key所在的插槽
        return slots[slotIndex(key)];
    }

    /**
     * 计算key所在的插槽
     * @param key
     * @return
     */
    private int slotIndex(K key) {
        return Math.abs(key.hashCode() % slots.length);
    }

    /**
     * 测试SimpleMap
     * @param args
     */
    public static void main(String[] args) {
        SimpleHashMap<String, String> map = new SimpleHashMap<>();
        map.put("gender", "female");
        map.put("name", "meteor");
        map.put("name", "meteor1");
        map.put("description", "good");
        System.out.println("map里面的数据数量为：" + map.size());
        System.out.println("map里面的数据为：" + map);
        System.out.println("map里面所有的key为：" + map.keySet());System.out.println("map里面的数据为：" + map);
        System.out.println("map里面所有的key为：" + map.keySet());

        map.remove("name");
        System.out.println("\n删除一个数据后：\n");
        System.out.println("map里面的数据数量为：" + map.size());
        System.out.println("map里面的数据为：" + map);
        System.out.println("map里面所有的key为：" + map.keySet());System.out.println("map里面的数据为：" + map);
        System.out.println("map里面所有的key为：" + map.keySet());

        System.out.println("\n测试迭代器：\n");
        for (Pair<String, String> pair : map.pairs()) {
            System.out.println(pair.getKey() + ":" + pair.getValue());
        }
    }
}
