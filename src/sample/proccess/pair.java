package sample.proccess;



import java.io.Serializable;

public class pair<K,V> implements Serializable {


    private K key;
    public K getKey() { return key; }

    private V value;

    public V getValue() { return value; }
    public void setValue(V v)
    {
        this.value=v;
    }
    public void setkey(K k)
    {
        this.key=k;
    }
    public pair(K key,  V value) {
        this.key = key;
        this.value = value;
    }
}


