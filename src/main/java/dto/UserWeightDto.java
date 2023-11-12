package dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserWeightDto {
    private Long id;
    private Long user_id;
    private Long weight;
    private Date date;
}
