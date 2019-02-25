package repository;

import com.querydsl.core.types.Predicate;

import java.io.Serializable;
import java.util.List;

public interface Repository<Entity, Id extends Serializable> {

    Entity findById(Id id);

    Entity insertOrUpdate(Entity entity);

    void delete(Entity entity);

    void deleteById(Id id);

    List<Entity> findAll(Predicate expr);

    Long count();

    Long count(Predicate expr);
}