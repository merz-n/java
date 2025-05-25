import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSafeList<T>{
    private final List<T> list = new ArrayList<>();
    private final Lock lock = new ReentrantLock();

    public void add(T element){
        try {
            lock.lock();
            list.add(element);
        } finally {
            lock.unlock();
        }
    }

    public T get(int index){
        if (index>=0 && index< list.size()) {
            try {
                lock.lock();
                return list.get(index);
            } finally {
                lock.unlock();
            }
        }throw new IndexOutOfBoundsException(index);
    }
    public void remove(int index){
        if (index>=0 && index< list.size()) {
            try {
                lock.lock();
                list.remove(index);
            } finally {
                lock.unlock();
            }
        }else throw new IndexOutOfBoundsException(index);
    }

    public int size() {
        try {
            lock.lock();
            return list.size();
        } finally {
            lock.unlock();
        }
    }
}
