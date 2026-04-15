package com.mb.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Order {
  //
  //	 order should include:
  //		○​ User name
  //		○​ Email
  //		○​ Ordered items (with quantity)
  //		○​ Actual bill amount
  //		○​ Optimize
  //	Eachd bill amount
  //		○​ Applied combos
  //		○​ Timestamp
  //		●​ Fetch stored orders from

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 25)
  private String userName;

  @Column(nullable = false, unique = true, length = 25)
  private String email;

  @Column
  private Double actuallBil;

  @Column
  private Double optimizedBill;

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<OrderCombo> combo = new ArrayList<OrderCombo>();

  @OneToMany(mappedBy = "order", fetch = FetchType.LAZY)
  @JsonIgnore
  private List<OrdersProduct> products = new ArrayList<OrdersProduct>();

  @CreationTimestamp
  private LocalDateTime createdAt;
}
