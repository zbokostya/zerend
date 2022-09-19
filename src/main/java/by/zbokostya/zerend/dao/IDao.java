package by.zbokostya.zerend.dao;

public interface IDao<T, ID> {
    T insert(T entity);
    T get(ID id);
    T update(T entity);
    int deleteById(ID id);
}
