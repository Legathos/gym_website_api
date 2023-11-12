package dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserIntakeDto {
    private Long id;
    private Long user_id;
    private Long calories;
    private Long carbs;
    private Long fats;
    private Long protein;
    private Date date;
}
