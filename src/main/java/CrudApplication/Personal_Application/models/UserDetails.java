package CrudApplication.Personal_Application.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Document(collection = "users_details")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserDetails {
    @Id
    private String id;
    private String name;
    private String address;
    private Integer age;
    private String email;
    private Integer phoneNumber;

    private Gender gender;
    public enum Gender {
        MALE,
        FEMALE

    }
}
