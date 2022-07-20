package it.giuseppeimpesi.itemmerger.data;

import lombok.Getter;

import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

@Getter
public abstract class AbstractHandler<E>  {

    private final Map<Object, E> cache = new ConcurrentHashMap<>();

    public E get(Object object) {
        return cache.get(object);
    }

    public void add(Object object, E element) {
        cache.put(object, element);
    }

    public void remove(Object object) {
        cache.remove(object);
    }

    public void clear() {
        cache.clear();
    }

    public Optional<E> getOptionalType(Object object) {
        return Optional.ofNullable(cache.get(object));
    }
}
