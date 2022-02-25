package emlakburada.dto.response;

import lombok.Data;

import java.util.UUID;

@Data
public class UserResponse {

    private Integer userId;
    private String isim;
    private String email;
    private String fotograf;
    private String biyografi;
}
