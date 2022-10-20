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
    private long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String description;
    @Column(nullable = false)
    private Double price;
    @Column(nullable = false)
    private Double discount;
    @Column(nullable = false)
    private Integer quantity;

    @Column(length = 100)
    private String sku;

    @CreatedDate
    private Date createAt;
    private Date updateAt;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id")
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    private Brand brand;

    @ManyToOne(fetch = FetchType.LAZY)
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
    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @EqualsAndHashCode.Exclude
    @ToString.Exclude
    @JoinTable(name = "product_image",
            joinColumns = @JoinColumn(name = "product_id"),
            inverseJoinColumns = @JoinColumn(name = "p_image_id"))
    private List<ProductImage> productImages;
}
