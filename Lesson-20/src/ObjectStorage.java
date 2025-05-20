public interface ObjectStorage<T>{
    void put(String namespace, String name, T object);
    T get(String namespace, String name) throws ObjectNotFoundException;
}
