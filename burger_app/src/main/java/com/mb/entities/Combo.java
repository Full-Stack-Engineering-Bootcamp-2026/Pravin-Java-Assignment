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
import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "combos")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Combo {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false, length = 25)
  private String comboName;

  //  @Column(nullable = false, unique = true, length = 50)
  //  private String comboCode;

  @Column(nullable = false)
  private Double actualPrice;

  @Column(nullable = false)
  private Double comboPrice;

  @OneToMany(fetch = FetchType.LAZY, mappedBy = "combo")
  @JsonIgnore
  private List<ProductsCombo> product = new ArrayList<ProductsCombo>();
}
