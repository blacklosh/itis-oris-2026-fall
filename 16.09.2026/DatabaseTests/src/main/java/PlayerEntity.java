import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PlayerEntity {

    private Long id;

    private String name;

    private String position;

    private Integer height;

    private Integer weight;

    private Integer salary;

}
