package ro.unibuc.hello.dto;

import ro.unibuc.hello.data.UserEntity;

public class UserDTO {
    private String id;
    private String firstName;
    private String userName;

    public UserDTO(){

    }
    public UserDTO(String id, String userName, String firstName)
    {
        this.id = id;
        this.userName = userName;
        this.firstName = firstName;

    }

    public String getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }


    @Override
    public String toString() {
            return "UserDTO{" +
                "userName='" + userName + '\'' +
                ", firstName='" + firstName + '\'' +
                '}';
    }
}
