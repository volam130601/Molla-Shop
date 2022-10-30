package com.molla.entity;

import lombok.*;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@EntityListeners(AuditingEntityListener.class) //Enable annotation @CreateDate
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String shortDescription;
    @Column(nullable = false, columnDefinition = "TEXT")
    private String fullDescription;
    @Column(nullable = false)
    private double price;
    @Column(nullable = false)
    private int quantity;
    @Column(nullable = false)
    private double discount;

    private String mainImage;
    private String extraImage1;
    private String extraImage2;
    private String extraImage3;

    private boolean enabled;
    private boolean inStock;
    @CreatedDate
    private Date createAt;
    private Date updateAt;

    @Transient
    private Long brandId;
    @Transient
    private Long categoryId;
    @ManyToOne
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Brand brand;
    @ManyToOne
    @JoinColumn(name = "category_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Category category;
    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<ProductReview> productReviews;
    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<OrderItem> orderItems;
    @OneToMany(mappedBy = "product")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private List<CartItem> cartItems;
    @ManyToOne
    @JoinColumn(name = "user_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private User user;

    @Transient
    public String getMainImagePath() {
        if (id == null || mainImage == null) return null;
        return "/product-images/" + id + "/" + mainImage;
    }

}
