package repository;

import java.util.List;

public interface GenericFileRepository<T> {
    List<T> loadAll();
    void saveAll(List<T> items);
}
