package com.meru.userService.dao;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Root;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import com.meru.userService.entity.User;
import com.meru.userService.repository.UserRepository;

@Component
public class UserDAOImpl implements UserDAO{
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private EntityManagerFactory entityManagerFactory;

	@Override
	public User signUp(User User) {
		return userRepository.save(User);
	}

	@Override
	public User updateUser(Long id, User user) {
		Optional<User> optionalUser   		= userRepository.findById(id);
		User           updatedUser  		= null;
		if (!optionalUser.isEmpty()) {
			updatedUser = userRepository.save(user);
		}
		return updatedUser;
	}

	@Override
	public User getUserDetails(Long id) {
		EntityManager 		    entityManager      = entityManagerFactory.createEntityManager();
		CriteriaBuilder         criteriaBuilder    = entityManager.getCriteriaBuilder();
		CriteriaQuery<User> 	query              = criteriaBuilder.createQuery(User.class);
		Root<User>          	userRoot       	   = query.from(User.class);
		query.distinct(true);
		userRoot.join("addressList",JoinType.LEFT);
		query.select(userRoot);
		 
		ParameterExpression<Long> userId = criteriaBuilder.parameter(Long.class);
		query.where(criteriaBuilder.equal(userRoot.get("id"), userId));
		 
		TypedQuery<User> typedQuery = entityManager.createQuery(query);
		typedQuery.setParameter(userId, id);
		List<User> userList 		= typedQuery.getResultList();
		if(!CollectionUtils.isEmpty(userList)) {
			return userList.get(0);
		}
		return null;
	}

}
