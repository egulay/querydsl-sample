package conf;

import com.mysql.cj.jdbc.MysqlDataSource;
import lombok.var;
import javax.sql.DataSource;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Paths;
import java.util.Objects;
import java.util.Properties;

public class Persistence {

  public static Properties getProperties() throws IOException, URISyntaxException {
    Properties properties = new Properties();
    properties.put("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
    properties.put("hibernate.id.new_generator_mappings", false);
    properties.put("hibernate.connection.datasource", getMysqlDataSource());
    return properties;
  }

  private static DataSource getMysqlDataSource() throws IOException, URISyntaxException {
    var propPath = Paths
        .get(Objects.requireNonNull(
            Objects.requireNonNull(
                Persistence.class.getClassLoader()
                    .getResource("db.properties")).toURI()));

    var dbProperties = new Properties();
    dbProperties.load(new FileInputStream(propPath.toFile()));

    MysqlDataSource mysqlDataSource = new MysqlDataSource();
    mysqlDataSource.setURL(dbProperties.getProperty("jdbc.url"));
    mysqlDataSource.setUser(dbProperties.getProperty("jdbc.user"));
    mysqlDataSource.setPassword(dbProperties.getProperty("jdbc.password"));
    return mysqlDataSource;
  }
}
