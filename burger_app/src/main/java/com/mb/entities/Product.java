package com.mb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mb.enums.CategoryEnum;
import com.mb.enums.TypeEnum;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.annotations.DynamicUpdate;

@Entity
@Table(name = "products")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@DynamicUpdate
@ToString
public class Product {
  //	{ "id": 1, "name": "Paneer Burger", "price": 149, "category": "BURGER", "type": "VEG" },

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 25)
  private String name;

  @Column(nullable = false)
  private Double price;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false)
  private CategoryEnum category;

  @Column(nullable = false)
  @Enumerated(EnumType.STRING)
  private TypeEnum type;

  @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<OrdersProduct> orders = new ArrayList<OrdersProduct>();
}
