package com.example.ingressspringfirst.repository;

import com.example.ingressspringfirst.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<UserEntity, Long> , JpaSpecificationExecutor<UserEntity> {

    //NATIVE QUERY
    @Query(nativeQuery = true, value = "SELECT count(*) FROM users WHERE birth_place =: birthPlace")
    Integer findCountByBirthPlace(@Param("birthPlace") String birthPlace);


    //JPQL
    @Query(value = "SELECT birthPlace FROM UserEntity WHERE id =: id")
    Integer findBirthPlaceById(Long id);
}
