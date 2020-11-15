package com.nuon.goamall.repository;

import com.nuon.goamall.model.GridCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GridCategoryRepository extends JpaRepository<GridCategory, Long> {

}
