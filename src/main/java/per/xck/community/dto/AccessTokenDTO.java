package per.xck.community.dto;

import lombok.Data;

// DataTransformingObject
@Data
public class AccessTokenDTO {
    private String client_id;
    private String  client_secret;
    private String code;
    private String redirect_uri;
    private String state;
}
