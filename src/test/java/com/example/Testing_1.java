package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.assertj.core.api.AssertionsForClassTypes;
import org.assertj.core.api.AssertionsForInterfaceTypes;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.mockito.BDDMockito.*;

@ExtendWith(MockitoExtension.class)
class Testing_1 {

	@Mock
	UserRepository userRepository;

	@InjectMocks
	UserService userService;

	@Test
	void findAll33() {

		//given
		User user22 = new User();
		Set<User> user33 = new HashSet<>();
		user33.add(user22);
		given(userRepository.findAll()).willReturn(user33);

		//when
		Iterable<User> foundVisits = userService.findAllUser();

		//then
		then(userRepository).should().findAll();
		AssertionsForInterfaceTypes.assertThat(foundVisits).hasSize(1);
	}

	@Test
	void findById33() {
		//given
		User  user = new User();
		given(userRepository.findById(anyInt())).willReturn(Optional.of(user));

		//when
		User foundVisit = userService.findUserById(1);

		//then
		then(userRepository).should().findById(anyInt());
		AssertionsForClassTypes.assertThat(foundVisit).isNotNull();
	}

	@Test
	void save33() {
		//given
		User user = new User();
		given(userRepository.save(any(User.class))).willReturn(user);

		//when
		User savedVisit  = userService.createUser(new User());

		//then
		then(userRepository).should().save(any(User.class));
		AssertionsForClassTypes.assertThat(savedVisit).isNotNull();
	}

	@Test
	void delete33() {
		//given
		User user = new User();

		//when
		userService.delete(user);

		//then
		then(userRepository).should().delete(any(User.class));
	}

	@Test
	void deleteById33() {
		// given  --none

		//when
		userService.deleteUserById(1);

		//then
		then(userRepository).should().deleteById(anyInt());

	}
}
