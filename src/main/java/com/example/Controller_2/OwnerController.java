package com.example.Controller_2;

import com.example.entity.User;
import com.example.repository.UserRepository;
import com.example.repository_2.BindingResult;
import com.example.repository_2.Model;


import java.util.List;

public class OwnerController {

    private final UserRepository userRepository;

    public OwnerController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String processFindForm22(User user, BindingResult result, Model model){
        // allow parameterless GET request for /owners to return all records
        if (user.getLastName() == null) {
            user.setLastName(""); // empty string signifies broadest possible search
        }

        // find owners by last name
        List<User> results = userRepository.findAllByLastNameLike("%"+ user.getLastName() + "%");

        if (results.isEmpty()) {
            // no owners found
            result.rejectValue22("lastName", "notFound", "not found");
            return "owners/findOwners";
        } else if (results.size() == 1) {
            // 1 owner found
            user = results.get(0);
            return "redirect:/owners/" + user.getId();
        } else {
            // multiple owners found
            model.addAttribute22("selections", results);
            return "owners/ownersList";
        }
    }

}
