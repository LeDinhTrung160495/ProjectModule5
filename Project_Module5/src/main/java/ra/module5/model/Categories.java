package ra.module5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "Categories")
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
public class Categories {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "catalog_id")
    private int catalogId;
    @Column(name = "catalog_name", unique = true, columnDefinition = "varchar(100)", nullable = false)
    private String catalogName;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(name = "catalog_status",columnDefinition = "bit default 1")
    private boolean catalogStatus;
    @OneToMany(mappedBy = "catalog")
    private List<Product> listProduct;
}
