package repository;

import com.google.inject.persist.Transactional;
import com.querydsl.core.types.Predicate;
import lombok.var;
import model.HelpdeskUserEntity;

import java.util.List;
import java.util.Objects;

import static model.QHelpdeskUserEntity.helpdeskUserEntity;

@Transactional
public class UserRepository extends AbstractRepository<HelpdeskUserEntity> {

    @Override
    public HelpdeskUserEntity findById(Long id) {
        return find(HelpdeskUserEntity.class, id);
    }

    public HelpdeskUserEntity findByName(String name) {
        return selectFrom(helpdeskUserEntity).where(helpdeskUserEntity.name.eq(name)).fetchOne();
    }

    @Override
    public HelpdeskUserEntity insertOrUpdate(HelpdeskUserEntity user) {
        return persistOrMerge(user);
    }

    @Override
    public void delete(HelpdeskUserEntity user) {
        remove(user);
    }

    @Override
    public void deleteById(Long id) {
        var entity = find(HelpdeskUserEntity.class, id);
        if (!Objects.isNull(entity)) {
            delete(entity);
        }
    }

    @Override
    public Long count() {
        return selectFrom(helpdeskUserEntity).fetchCount();
    }

    @Override
    public Long count(Predicate expr) {
        return selectFrom(helpdeskUserEntity).where(expr).fetchCount();
    }

    @Override
    public List<HelpdeskUserEntity> findAll(Predicate expr) {
        return selectFrom(helpdeskUserEntity).where(expr).fetch();
    }
}
