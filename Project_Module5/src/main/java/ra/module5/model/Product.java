package ra.module5.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "Product")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id")
    private int productId;
    @Column(name="stock_keeping_unit",unique = true)
    private String stockKeepingUnit;
    @Column(name = "product_name",unique = true,nullable = false)
    private String productName;
    @Column(name = "description",columnDefinition = "TEXT")
    private String description;
    @Column(precision = 10,scale = 2)
    private BigDecimal unitPrice;
    @Column(name = "stockQuantity")
    private int stockQuantity;
    @Column(name = "product_image",columnDefinition = "varchar(255)")
    private String image;
    @Column(name = "created_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date created;
    @Column(name = "updated_at")
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private Date updated;
    @ManyToOne
    @JoinColumn(name = "catalog_id",referencedColumnName = "catalog_id")
    private Categories catalog;
    // Sử dụng UUID để tạo chuỗi ngẫu nhiên và gán vào thuộc tính SKU
    public static Product createProductWithRandomSku() {
        String randomSku = UUID.randomUUID().toString();
        return Product.builder().stockKeepingUnit(randomSku).build();
    }
}
