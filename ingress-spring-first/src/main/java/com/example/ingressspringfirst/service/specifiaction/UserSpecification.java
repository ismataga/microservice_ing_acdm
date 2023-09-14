package com.example.ingressspringfirst.service.specifiaction;

import static com.example.ingressspringfirst.model.constants.CriteriaConstants.AGE;
import static com.example.ingressspringfirst.model.constants.CriteriaConstants.BIRTH_PLACE;
import static com.example.ingressspringfirst.utils.PredicateUtil.applyLikePattern;


import com.example.ingressspringfirst.entity.UserEntity;
import com.example.ingressspringfirst.model.criteria.UserCriteria;
import com.example.ingressspringfirst.utils.PredicateUtil;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import lombok.AllArgsConstructor;



import org.springframework.data.jpa.domain.Specification;


@AllArgsConstructor
public class UserSpecification implements Specification<UserEntity> {

    private UserCriteria userCriteria;
    @Override
    public Predicate toPredicate(Root<UserEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        var predicates = PredicateUtil.builder()
                .anyNullSafety(userCriteria.getBirthPlace(), birthPlace ->  cb.like(root.get(BIRTH_PLACE), applyLikePattern(birthPlace)))
                .anyNullSafety(userCriteria.getAgeFrom(),ageFrom -> cb.greaterThanOrEqualTo(root.get(AGE), ageFrom))
                .anyNullSafety(userCriteria.getAgeTo(),ageTo -> cb.greaterThanOrEqualTo(root.get(AGE), ageTo))
                .build();

        return cb.and(predicates);
    }
}
