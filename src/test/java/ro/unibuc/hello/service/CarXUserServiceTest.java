package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.unibuc.hello.data.CarXUserRepository;
import ro.unibuc.hello.dto.BuyCarDTO;

@ExtendWith(SpringExtension.class)
public class CarXUserServiceTest {
    @Mock
    CarXUserRepository carXUserRepository;

    @InjectMocks
    CarXUserService carXUserService = new CarXUserService();

    @Test
    public void test_buyCar_with_data() {
        // Arrange
        BuyCarDTO buyCarDTO= new BuyCarDTO("642488b9818d7e17c7fb9b98", "642488b9818d7e17c7fb9b93");

        // Act
        String response = carXUserService.buyCar(buyCarDTO);

        // Assert
        Assertions.assertEquals("Acquisition was completed successfully", response);
    }

    @Test
    public void test_buyCar_with_user_null() {
        // Arrange
        BuyCarDTO buyCarDTO= new BuyCarDTO(null, "642488b9818d7e17c7fb9b93");

        // Act
        String response = carXUserService.buyCar(buyCarDTO);

        // Assert
        Assertions.assertEquals("Acquisition failed", response);
    }

    @Test
    public void test_buyCar_with_car_null() {
        // Arrange
        BuyCarDTO buyCarDTO= new BuyCarDTO("642488b9818d7e17c7fb9b98", null);

        // Act
        String response = carXUserService.buyCar(buyCarDTO);

        // Assert
        Assertions.assertEquals("Acquisition failed", response);
    }

    @Test
    public void test_buyCar_with_null() {
        // Arrange
        BuyCarDTO buyCarDTO= new BuyCarDTO(null, null);

        // Act
        String response = carXUserService.buyCar(buyCarDTO);

        // Assert
        Assertions.assertEquals("Acquisition failed", response);
    }
}
