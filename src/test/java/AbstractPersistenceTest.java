import org.junit.runner.RunWith;

import javax.inject.Inject;
import javax.inject.Provider;
import javax.persistence.EntityManager;

@RunWith(TestRunner.class)
public abstract class AbstractPersistenceTest {
    @Inject
    private Provider<EntityManager> em;
}