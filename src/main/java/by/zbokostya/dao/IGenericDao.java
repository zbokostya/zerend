package by.zbokostya.dao;

import org.jooq.DSLContext;
import org.jooq.SortField;
import org.jooq.Condition;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


public interface IGenericDao<T, ID extends Serializable> {

    T insert(T entity);

    void insert(Collection<T> entities);

    T update(T entity);

    T update(T entiry, boolean ignoreNull);

    void update(Collection<T> entities);

    void update(Collection<T> entities, boolean ignoreNull);

    int deleteById(ID id);

    void deleteByIds(Collection<ID> ids);

    void delete(Condition...conditions);

    void deleteWithOptional(Stream<Optional<Condition>> conditions);

    void delete(Stream<Condition> conditions);

    T get(ID id);

    Optional<T> getOptional(ID id);

    List<T> get(Collection<ID> ids);

    List<T> fetch(Condition...conditions);

    int count(Condition...conditions);

    int countWithOptional(Stream<Optional<Condition>> conditions);

    int count(Stream<Condition> conditions);

    List<T> fetchWithOptional(Stream<Optional<Condition>> conditions, SortField<?>...sorts);

    List<T> fetch(Stream<Condition> conditions, SortField<?>...sorts);

//    PageResult<T> fetch(PageResult<T> page, Condition...conditions);
//
//    PageResult<T> fetch(PageResult<T> page, SortField<?> sort);
//
//    PageResult<T> fetch(PageResult<T> page, Stream<Condition> conditions, SortField<?>...sorts);
//
//    PageResult<T> fetchWithOptional(PageResult<T> page, Stream<Optional<Condition>> conditions, SortField<?>...sorts);
//
//    PageResult<T> fetch(PageResult<T> page, Executor<SelectLimitStep<?>> ec, RecordMapper<Record, T> mapper);

    Optional<T> fetchOne(Condition...conditions);

    Optional<T> fetchOne(Stream<Condition> conditions);

    Optional<T> fetchOneWithOptional(Stream<Optional<Condition>> conditions);

    <O> O execute(Executor<O> cb);

    @FunctionalInterface
    interface Executor<E> {
        E execute(DSLContext context);
    }


}

