package com.example;

import com.example.entity.Address;
import com.example.entity.Employee;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class Testing_4_Spy {

    Employee e1;
    Employee e2;

    @Spy
    Address address = new Address();          //with @Spy we create a dummy object of address, it will not write anything in DB

    @BeforeEach
    public void setup() {
        e1 = new Employee(1, "John");
        e1.setAddress(address);

        e2 = new Employee(2, "Martin");
        e2.setAddress(address);

        when(address.getAddressDetails()).thenReturn("88_HighStreet");    //here we are just calling the  "address.getAddressDetails()"
                                                                        // after the method is successfully called then we are putting
    }                                                                   // "88_HighStreet"  <- this process is called "Stubbing"

    @Test
    public void testEmployeeDetails() {

        String e1Details = e1.getEmployeeDetails();
        assertEquals("1_John_88_HighStreet", e1Details);

        String e2Details = e2.getEmployeeDetails();
        assertEquals("2_Martin_88_HighStreet", e2Details);
    }



}
