    package com.mb.specification;

    import org.springframework.data.jpa.domain.Specification;

    import com.mb.entities.Product;
    import com.mb.enums.CategoryEnum;
    import com.mb.enums.TypeEnum;

    public class ProductSpecs {

        public static Specification<Product> ofType( TypeEnum type){

 if (type == null) return (root, query, cb) -> cb.conjunction(); 
            return (root, query, cb) -> cb.equal(root.get("type"), type) ;
        }
        
        public static Specification<Product> ofCategory( CategoryEnum category){
  if (category == null) return (root, query, cb) -> cb.conjunction(); 
            return (root, query, cb) -> cb.equal(root.get("category"), category) ;
        }
        
    }