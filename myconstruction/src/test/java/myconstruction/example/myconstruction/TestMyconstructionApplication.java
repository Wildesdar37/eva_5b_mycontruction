package myconstruction.example.myconstruction;

import org.springframework.boot.SpringApplication;

public class TestMyconstructionApplication {

	public static void main(String[] args) {
		SpringApplication.from(MyconstructionApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
