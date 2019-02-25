import lombok.var;
import model.HelpdeskUserEntity;
import model.QHelpdeskUserEntity;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import repository.UserRepository;

import javax.inject.Inject;
import java.util.UUID;

import static org.junit.Assert.*;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class UserRepositoryTest extends AbstractPersistenceTest {
    @Inject
    private UserRepository repository;

    @Test
    public void test1_count_all() {
        Long expected = 3L;
        Long actual = repository.count();
        assertEquals(expected, actual);
    }

    @Test
    public void test2_insert_update() {
        var name = "Zonald Zrump";
        var newName = "Donald Duck";
        var user = repository.insertOrUpdate(HelpdeskUserEntity.builder()
                .active((byte) 0)
                .name(name)
                .shoeSize(45)
                .uuid(UUID.randomUUID().toString())
                .build());

        assertEquals(name, user.getName());
        assertNotNull(repository.findAll(QHelpdeskUserEntity.helpdeskUserEntity.name.eq(name)));

        user.setName(newName);
        var updatedUser = repository.insertOrUpdate(user);
        assertNotNull(updatedUser);
        assertNotNull(repository.findAll(QHelpdeskUserEntity.helpdeskUserEntity.name.eq(newName)));
    }

    @Test
    public void test3_delete() {
        var name = "Donald Duck";
        var user = repository.findByName(name);
        assertNotNull(user);

        repository.delete(user);

        var deletedUser = repository.findByName(name);
        assertNull(deletedUser);
    }
}