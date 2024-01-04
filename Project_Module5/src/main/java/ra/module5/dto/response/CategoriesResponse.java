package ra.module5.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CategoriesResponse {
    private int catalogId;
    private String catalogName;
    private String description;
    private boolean catalogStatus;
}
