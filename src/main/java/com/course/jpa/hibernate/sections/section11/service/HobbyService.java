package com.course.jpa.hibernate.sections.section11.service;

import com.course.jpa.hibernate.entities.Hobby;
import com.course.jpa.hibernate.sections.section11.repository.HobbyRepo;
import jakarta.persistence.EntityManager;
import jakarta.persistence.TypedQuery;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class HobbyService {

    private final EntityManager entityManager;

    private final HobbyRepo hobbyRepo;

    public HobbyService(EntityManager entityManager, HobbyRepo hobbyRepo) {
        this.entityManager = entityManager;
        this.hobbyRepo = hobbyRepo;
    }

    @Transactional
    public String createHobby(Hobby hobby) {
        hobby.setHobbyId(hobbyRepo.generateId());
        entityManager.persist(hobby);
        return "New hobby created";
    }

    public List<Hobby> getAllHobbies() {

        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hobby> cq = cb.createQuery(Hobby.class);

        Root<Hobby> hobbyRoot = cq.from(Hobby.class);

        TypedQuery<Hobby> query = entityManager.createQuery
                (cq.select((hobbyRoot)));

        List<Hobby> result = query.getResultList();

        return result;
    }

    public Hobby getHobbyById(Long id) {

        // Create criteria builder to create a criteria query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hobby> cq = cb.createQuery(Hobby.class);

        // define root for tables which involved in the query
        Root<Hobby> hobbyRoot = cq.from(Hobby.class);

        // Define predicate etc using criteria builder
        Predicate isMatchedWithId = cb.equal(hobbyRoot.get("hobbyId"),id);

        // add predicate to the criteria query
        cq.where(isMatchedWithId);

        TypedQuery<Hobby> query = entityManager.createQuery
                (cq.select(hobbyRoot));

        // Using getResultStream() to avoid NoResultException
        return query.getResultStream().findFirst().orElse(null);


    }

    public Hobby getHobbyByIdAndName(Long id,String name) {

        // Create criteria builder to create a criteria query
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Hobby> cq = cb.createQuery(Hobby.class);

        // define root for tables which involved in the query
        Root<Hobby> hobbyRoot = cq.from(Hobby.class);

        // Define predicate etc using criteria builder
        Predicate isMatchedWithId = cb.equal(hobbyRoot.get("hobbyId"),id);
        Predicate isMatchedWithName = cb.equal(hobbyRoot.get("hobbyName"),name);

        // add predicate to the criteria query
        cq.where(isMatchedWithId, isMatchedWithName); // by default, it will put "and" between them
//        cq.where(cb.or(isMatchedWithId, isMatchedWithName)); // or use explicit condition using cb

        TypedQuery<Hobby> query = entityManager.createQuery
                (cq.select(hobbyRoot));

        // Using getResultStream() to avoid NoResultException
        return query.getResultStream().findFirst().orElse(null);


    }
}
