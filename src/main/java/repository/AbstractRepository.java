package repository;

import com.querydsl.core.types.EntityPath;
import com.querydsl.core.types.Expression;
import com.querydsl.jpa.HQLTemplates;
import com.querydsl.jpa.impl.JPADeleteClause;
import com.querydsl.jpa.impl.JPAQuery;
import model.Identifiable;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

public abstract class AbstractRepository<T extends Identifiable> implements Repository<T, Long> {

    @Inject
    private Provider<EntityManager> em;

    protected JPAQuery<T> selectFrom(EntityPath<T> entity) {
        return select(entity).from(entity);
    }

    protected JPAQuery<T> select(Expression<T> select) {
        return new JPAQuery<Void>(em.get(), HQLTemplates.DEFAULT).select(select);
    }

    protected JPADeleteClause delete(EntityPath<T> entity) {
        return new JPADeleteClause(em.get(), entity, HQLTemplates.DEFAULT);
    }

    protected void detach(Object entity) {
        em.get().detach(entity);
    }

    protected <E extends Identifiable> E find(Class<E> type, Long id) {
        return em.get().find(type, id);
    }

    protected void persist(Object entity) {
        em.get().persist(entity);
    }

    protected <E extends Identifiable> E merge(E entity) {
        return em.get().merge(entity);
    }

    protected <E extends Identifiable> E persistOrMerge(E entity) {
        if (entity.getId() != null) {
            return merge(entity);
        }
        persist(entity);
        return entity;
    }

    protected void remove(Object entity) {
        em.get().remove(em.get().contains(entity) ? entity : em.get().merge(entity));
    }
}
