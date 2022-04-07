package com.restarant.backend.service.utils;

import com.restarant.backend.entity.Account;
import com.restarant.backend.entity.Customer;
import com.restarant.backend.security.jwt.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

@Component
public class JwtServiceUtils {

    @Autowired
    private JwtUtils jwtUtils;

    public Account getAccountByToken(HttpServletRequest request) {
//        String token = request.getHeader("token");
//        if(!jwtUtils.validateJwtToken(token)){
//            return null;
//        }
//        String username = jwtUtils.getUserNameFromJwtToken(token);
        return null;
    }

    public Customer getCustomerByToken(HttpServletRequest request){
        Customer customer = new Customer();
        customer.setId(1L);
        return customer;
    }
}
