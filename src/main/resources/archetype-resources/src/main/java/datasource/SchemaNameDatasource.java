package ${package}.datasource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@EnableJpaRepositories(
		basePackages = "${package}.repository.schema_name",
		entityManagerFactoryRef = "schema_nameEntityManager",
		transactionManagerRef = "schema_nameTransactionManager"
)
public class SchemaNameDatasource {

    //TODO: rename class name
    //TODO: replace schema_name and Schema_name. Take care of case sensivity!

	@Autowired
	private Environment env;

	@Bean(destroyMethod = "") //other wise the key will be removed from JNDI tree!
	public DataSource getSchema_nameDataSource() {
		DataSource dataSource = null;
		try {
			dataSource = (DataSource) new InitialContext().lookup(env.getProperty("datasource.jndi.schema_name"));
		} catch (NamingException e) {
			e.printStackTrace();
			dataSource = DataSourceBuilder.create()
					.url("jdbc:oracle:thin:@ora-zv42:42100:ANLEIT")
					.username("test") //we use the weblogic/jboss datasource
					.password("test") //we use the weblogic/jboss datasource
					.build();
		}
		return dataSource;
	}

	@Bean("anleiaoEntityManager")
	public LocalContainerEntityManagerFactoryBean anleiaoEntityManager() {
		LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
		em.setDataSource(this.getSchema_nameDataSource()); //Set the DataSource for the entity manager

		//TODO: Register packages that contains entities that should be managed by this entity manager
		em.setPackagesToScan(new String[] {
				"${package}.entity.schema_name"
		});

		HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
		em.setJpaVendorAdapter(vendorAdapter);
		HashMap<String, Object> properties = new HashMap<>();
		properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
		properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
		properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
		properties.put("hibernate.format_sql", env.getProperty("hibernate.format_sql"));
		properties.put("hibernate.use_sql_comments", env.getProperty("hibernate.use_sql_comments"));
		em.setJpaPropertyMap(properties);

		return em;
	}

	@Bean("anleiaoTransactionManager")
	public PlatformTransactionManager anleiaoTransactionManager() {
		JpaTransactionManager transactionManager = new JpaTransactionManager();
		transactionManager.setEntityManagerFactory(this.anleiaoEntityManager().getObject());
		return transactionManager;
	}

}
