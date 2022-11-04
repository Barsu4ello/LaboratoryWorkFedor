package com.cvetkov.fedor.laboratoryWork;

import com.cvetkov.fedor.laboratoryWork.initialiser.Postgres;
import org.junit.jupiter.api.BeforeAll;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.transaction.annotation.Transactional;

@ActiveProfiles("test")
@SpringBootTest
@ContextConfiguration(initializers = {
		Postgres.Initializer.class
})
@Transactional
public abstract class LaboratoryWorkApplicationTests {

	@BeforeAll
	static void init() {
		Postgres.container.start();
	}

}
