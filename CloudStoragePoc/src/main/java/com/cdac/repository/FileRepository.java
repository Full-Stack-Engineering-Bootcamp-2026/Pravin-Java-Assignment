

package com.cdac.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cdac.entities.File;



@Repository
public interface FileRepository extends  JpaRepository<File,Long>  {

    
}