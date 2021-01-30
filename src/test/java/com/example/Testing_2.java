package com.example;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.*;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class Testing_2 {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserService userService;

    @Test
    void testDeleteByObject() {

        //given
        User user22 = new User();
        //when
        userService.delete(user22);
        //then
        then(userRepository).should().delete(any(User.class));
        //verify(specialtyRepository).delete(any(Speciality.class));
    }

    @Test
    void findByIdTest() {
        User user22 = new User();

        when(userRepository.findById(1)).thenReturn(Optional.of(user22));

        User foundUser = userService.findUserById(1);

        assertThat(foundUser).isNotNull();

        verify(userRepository).findById(anyInt());
    }

    @Test
    void findByBddTest() {
        User user22 = new User();

        //given
        given(userRepository.findById(1)).willReturn(Optional.of(user22));

        //when
        User foundUser = userService.findUserById(1);

        //then
        assertThat(foundUser).isNotNull();
        //   verify(specialtyRepository).findById(anyLong());
        //    then(specialtyRepository).should().findById(anyLong());
        then(userRepository).should(times(1)).findById(anyInt());
        then(userRepository).shouldHaveNoMoreInteractions();
    }

    @Test
    void deleteById() {
        //given     -- none    , means nothing is given

        //when
        userService.deleteUserById(1);
        userService.deleteUserById(1);

        //then
        then(userRepository).should(times(2)).deleteById(1);
        //    verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdALeast() {
        //given    -- none

        //when
        userService.deleteUserById(1);
        userService.deleteUserById(1);

        //then
        then(userRepository).should(atLeastOnce()).deleteById(1);
        //    verify(specialtyRepository, times(2)).deleteById(1l);
    }

    @Test
    void deleteByIdAtMost() {
        //given    -- none

        // when
        userService.deleteUserById(1);
        userService.deleteUserById(1);

        //then
        then(userRepository).should(atMost(5)).deleteById(1);
        //     verify(specialtyRepository, atMost(5)).deleteById(1l);
    }

    @Test
    void deleteByIdAtNever() {

        //when
        userService.deleteUserById(1);
        userService.deleteUserById(1);

        //then
        then(userRepository).should(atLeastOnce()).deleteById(1);
        //verify(specialRepository, atLeastOnce()).deleteById(1L);

        then(userRepository).should(never()).deleteById(5);
        //     verify(specialtyRepository, never()).deleteById(5l);
    }

    @Test
    void testDelete() {
        //when
        userService.delete(new User());

        //then
        then(userRepository).should().delete(any());
    }

    @Test
    void testDoThrow() {
        doThrow(new RuntimeException("boom")).when(userRepository).delete(any());

        assertThrows(RuntimeException.class, ()-> userRepository.delete(new User()));

        verify(userRepository).delete(any());
        then(userRepository).should().delete(any(User.class));
    }

    @Test
    void testFindByIDThrows() {
        // given
        given(userRepository.findById(1)).willThrow(new RuntimeException("boom"));

        //when
        assertThrows(RuntimeException.class, ()-> userService.findUserById(1));

        //then
        then(userRepository).should().findById(1);
    }

    @Test
    void testDeleteBDD() {
        willThrow(new RuntimeException("boom")).given(userRepository).delete(any());

        assertThrows(RuntimeException.class, ()-> userRepository.delete(new User()));

        then(userRepository).should().delete(any());
    }


    @Test
    void testSaveLambda() {
        //given
        User  user22 = new User();
        user22.setName("MATCH_ME");
        user22.setId(1);

        User savedUser = new User();
        savedUser.setId(1);

        //need mock to only return on match MATCH_ME string
        given(userRepository.save(argThat( x -> x.getName().equals("MATCH_ME"))))
        .willReturn(user22);

        System.out.println("----------------");
        //when
        User returnUser = userService.createUser(user22);

        System.out.println("------------2222222222----");
        //then
       //  assertNotNull(returnUser);

        assertThat(returnUser.getId()).isEqualTo(1);

    }

}
