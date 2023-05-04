package ro.unibuc.hello.controller;

import io.micrometer.core.annotation.Counted;
import io.micrometer.core.annotation.Timed;
import io.micrometer.core.instrument.MeterRegistry;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
;
import ro.unibuc.hello.data.UserEntity;
import ro.unibuc.hello.dto.CarsDTO;
import ro.unibuc.hello.dto.RegisterUserDTO;
import ro.unibuc.hello.dto.UserDTO;
import ro.unibuc.hello.service.UserService;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    MeterRegistry metricsRegistry;

    @GetMapping("/user/getAll")
    @ResponseBody
    @Timed(value = "hello.user.getAll.time", description = "Time taken to return all users")
    @Counted(value = "hello.user.getAll.count", description = "Times all users were returned")
    public List<UserEntity> getUsers(){
        List<UserEntity> users = userService.getUsers();
        return users;
    }

    @GetMapping("/user/get")
    @ResponseBody
    public UserDTO getUserById(@RequestParam(name="id") String id) {
        UserDTO userDTO = userService.getUserById(id);
        return userDTO;
    }

    @PostMapping("/user/register")
    @ResponseBody
    public String registerUser(@RequestParam(name="firstName") String firstName,
                                        @RequestParam(name="lastName") String lastName,
                                        @RequestParam(name="userName") String userName,
                                        @RequestParam(name="password") String password) {
        return userService.registerUser(firstName, lastName, userName, password);
    }

    @PutMapping("/user/changeUsername")
    @ResponseBody
    public void changeUsername(@RequestParam(name="id") String id,
                             @RequestParam(name="userName") String userName) {

        userService.changeUsername(id, userName);
    }

    @DeleteMapping("/user/delete")
    @ResponseBody
    public void deleteUser(@RequestParam(name="id") String id) {
        userService.deleteUserById(id);
    }
}
