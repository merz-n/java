import java.util.ArrayList;
import java.util.*;

public class Box <T extends Fruit> {
    private List<T> fruits = new ArrayList<>();

    public void add(T fruit){
        fruits.add(fruit);
    }

    public float getWeight(){
        float weight = 0.0f;
        for(T fruit: fruits){
            weight+= fruit.getWeight();
        }
        return weight;
    }
    public boolean compare(Box<?> other){
         if (Math.abs(this.getWeight()-other.getWeight())<0.0001f){
             return true;
        }
         return false;
    }

    public void transfer(Box<T> other){
        if (this == other)return;
        other.fruits.addAll(this.fruits);
        this.fruits.clear();
    }
}
