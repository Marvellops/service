package ro.unibuc.hello.dto;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;

public class UserDTOTest {
    UserDTO userDTO = new UserDTO("641b7a7db0792b40cb8f3c96", "mary", "Jhonson");

    @Test
    public void test_userId(){
        Assertions.assertSame("641b7a7db0792b40cb8f3c96", userDTO.getId());
    }

    @Test
    public void test_userName(){
        Assertions.assertSame("mary", userDTO.getUserName());
    }
}