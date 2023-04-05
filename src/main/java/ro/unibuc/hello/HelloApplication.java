package ro.unibuc.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import ro.unibuc.hello.data.CarRepository;
import ro.unibuc.hello.data.InformationEntity;
import ro.unibuc.hello.data.InformationRepository;
import ro.unibuc.hello.data.*;

import javax.annotation.PostConstruct;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses = InformationRepository.class)
public class HelloApplication {

	@Autowired
	private InformationRepository informationRepository;

	@Autowired
	private CarRepository carRepository;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private CarXUserRepository carXUserRepository;

	public static void main(String[] args) {
		SpringApplication.run(HelloApplication.class, args);
	}

	@PostConstruct
	public void runAfterObjectCreated() {
		informationRepository.deleteAll();
		informationRepository.save(new InformationEntity("Overview",
				"This is an example of using a data storage engine running separately from our applications server"));

		carRepository.deleteAll();
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b90","BMW", "Sedan", 2022, "Euro6",30000));
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b91","Volvo", "SUV", 2022, "Euro6",50000));
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b92","Renault", "Sedan", 2022, "Euro6",18000));
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b93","Mercedes", "Sedan", 2022, "Euro6",90000));
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b94","Dacia", "SUV", 2022, "Euro6",15000));
		carRepository.save(new CarEntity( "642488b9818d7e17c7fb9b95","Dacia", "Sedan", 2022, "Euro6",10000));

		userRepository.deleteAll();
		userRepository.save(new UserEntity("642488b9818d7e17c7fb9b98","Mary", "Johnson", "mary", "admin"));
		userRepository.save(new UserEntity("642488b9818d7e17c7fb9b99","Anne", "Johnson", "anny", "admin"));
		userRepository.save(new UserEntity("642488b9818d7e17c7fb9c00","Jon", "Mark","jonny", "admin"));
		userRepository.save(new UserEntity("642488b9818d7e17c7fb9c01","Danny", "Moore", "dan","admin"));

		carXUserRepository.deleteAll();
	}

}
