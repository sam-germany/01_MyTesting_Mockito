package com.example;

import com.example.Controller_2.OwnerController;
import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository_2.BindingResult;
import com.example.repository_2.Model;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.anyList;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class Testing_3_video_128_129 {

    @Mock
    UserRepository userRepository;

    @Mock
    Model model;

    @Mock
    BindingResult bindingResult;

    @InjectMocks
    OwnerController ownerController;

    @Captor
    ArgumentCaptor<String> stringArgumentCaptor22;

/*
    @Test
    void processFindFromWildcardString() {
        //given
        User user = new User(1, "aaa", "bbb");
        List<User> userList = new ArrayList<>();

        final ArgumentCaptor<String> captor = ArgumentCaptor.forClass(String.class);
        given(userRepository.findAllByLastNameLike(captor.capture())).willReturn(userList);

        //when
        String viewName = ownerController.processFindForm22(user, bindingResult, null);

        //then
  //      assertThat("%bbb%").isEqualToIgnoringCase(viewName);
        assertThat("%bbb%").isEqualToIgnoringCase(viewName);
    }
*/

    @BeforeEach
    void setUp() {
           given(userRepository.findAllByLastNameLike(stringArgumentCaptor22.capture()))
             .willAnswer( invoication -> {

                    List<User> owners = new ArrayList<>();
                    String name = invoication.getArgument(0);

                    if( name.equals("%bbb%")) {
                        owners.add(new User(1, "aaa", "bbb"));
                        return  owners;
                    }else if(name.equals("%DontFindMe%")) {
                        return  owners;
                    }else if(name.equals("%FindMe%")){
                        owners.add(new User(1, "aaa", "bbb"));
                        owners.add(new User(2, "aaa", "bbb"));
                        return  owners;
                    }
                    throw new RuntimeException("Invalid Argument");
                });
    }

    @Test
    void processFindFormWildcardFound() {
        //given
        User user = new User(1, "aaa", "FindMe");
        InOrder inOrder = inOrder(userRepository, model);

        //when
        String viewName = ownerController.processFindForm22(user, bindingResult, model);

        //then
        assertThat("%FindMe%").isEqualToIgnoringCase(stringArgumentCaptor22.getValue());
        assertThat("owners/ownersList" ).isEqualToIgnoringCase(viewName);

        //inorder asserts
        inOrder.verify(userRepository).findAllByLastNameLike(anyString());
        inOrder.verify(model, times(1)).addAttribute22(anyString(), anyList());
        verifyNoMoreInteractions(model);
    }

    @Test
    void processFindFromAnnotationString() {
        //given
        User user = new User(1, "aaa", "bbb");

        //when
        String viewName = ownerController.processFindForm22(user, bindingResult, null);

        //then
        assertThat("%bbb%").isEqualToIgnoringCase(stringArgumentCaptor22.getValue());
        assertThat("redirect:/owners/1" ).isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

    @Test
    void processFindForwardWildcardNotFound() {
        //given
        User user = new User(1, "aaa", "DontFindMe");

        //when
        String viewName = ownerController.processFindForm22(user, bindingResult, null);

        //then
        assertThat("%DontFindMe%").isEqualToIgnoringCase(stringArgumentCaptor22.getValue());
        assertThat("owners/findOwners" ).isEqualToIgnoringCase(viewName);
        verifyZeroInteractions(model);
    }

}
