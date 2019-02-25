package bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Scopes;
import com.google.inject.persist.jpa.JpaPersistModule;
import conf.Persistence;
import repository.UserRepository;

import java.io.IOException;
import java.net.URISyntaxException;

public class ServiceModule extends AbstractModule {
    @Override
    protected void configure() {
        try {
            install(new JpaPersistModule("default").properties(Persistence.getProperties()));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
        bind(JpaInitializer.class).asEagerSingleton();
        bind(UserRepository.class).in(Scopes.SINGLETON);
    }
}
