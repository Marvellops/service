package ro.unibuc.hello.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.data.UserRepository;
import ro.unibuc.hello.dto.RegisterUserDTO;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.exception.EntityNotFoundException;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {
    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService = new UserService();

    @Test
    public void test_register_user_with_data() {
        // Arrange
        String firstName = "miruna";
        String lastName = "avram";
        String userName = "mirunavr";
        String password = "1234";

        // Act
        String response = userService.registerUser(firstName, lastName, userName, password);

        // Assert
//        Assertions.assertEquals("miruna", registerUserDTO.getFirstName());
//        Assertions.assertEquals("avram", registerUserDTO.getLastName());
//        Assertions.assertEquals("mirunavr", registerUserDTO.getUserName());
//        Assertions.assertEquals("1234", registerUserDTO.getPassword());
        Assertions.assertEquals("Saved successfully", response);
    }

    @Test
    public void test_register_user_with_data_null() {
        // Arrange

        // Act
        String response = userService.registerUser(null, null, null,null);

        // Assert
        Assertions.assertEquals("Empty data", response);
    }

    @Test
    void test_getUserById_throwsEntityNotFoundException_whenInformationNull() {
        // Arrange
        String id = "642488b9818d7e1";

        UserEntity user = userRepository.findById(id).orElse(null);

        try {
            // Act
            UserDTO userDTO = userService.getUserById(id);
        } catch (Exception ex) {
            // Assert
            Assertions.assertEquals(ex.getClass(), EntityNotFoundException.class);
            Assertions.assertEquals(ex.getMessage(), "Entity: 642488b9818d7e1 was not found");
        }
    }
}

