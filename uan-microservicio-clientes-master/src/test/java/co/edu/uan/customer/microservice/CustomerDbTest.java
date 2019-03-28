package co.edu.uan.customer.microservice;

import static com.wix.mysql.distribution.Version.v5_7_19;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.InputStream;
import java.math.BigInteger;
import java.time.ZoneId;
import java.util.TimeZone;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.apache.commons.io.IOUtils;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import com.wix.mysql.EmbeddedMysql;
import com.wix.mysql.config.MysqldConfig;
import com.wix.mysql.config.SchemaConfig;

import org.junit.Test;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CustomerDbTest {
	@Resource
	private EntityManager entityManager;

	private static EmbeddedMysql embeddedMysql;

	@Value(value = "classpath:crearTablaCustomer.sql")
	private org.springframework.core.io.Resource userModel;

	@Before
	public void startMySqlForTesting() {
		try {
			MysqldConfig config = MysqldConfig.aMysqldConfig(v5_7_19).withPort(3307)
					.withTimeZone(TimeZone.getTimeZone(ZoneId.of("UTC"))).withUser("test", "test").build();

			SchemaConfig schemaConfig = SchemaConfig.aSchemaConfig("test_database").build();
			embeddedMysql = EmbeddedMysql.anEmbeddedMysql(config).addSchema(schemaConfig).start();
			InputStream inputStream = userModel.getInputStream();
			String ddl = IOUtils.toString(inputStream, "UTF-8");
		} catch (Exception e) {
			fail();
		}
	}

	@Test
	public void testDatabaseConnection() {
		Query query = entityManager.createNativeQuery("SELECT 1");
		assertEquals(BigInteger.valueOf(1L), query.getSingleResult());
	}
	
	@AfterClass
    public static void stopMySql() {
        if (null != embeddedMysql) {
            embeddedMysql.stop();
        }
    }
}
