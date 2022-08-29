package com.valueit.springbootapp.validator;

import com.valueit.springbootapp.dto.ClientDTO;
import com.valueit.springbootapp.dto.CommandeDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class ClientValidator {

    public static List<String> validate(ClientDTO clientDTO){
        List<String> errors = new ArrayList<>();
        if(clientDTO== null ){
            errors.add("Veuillez renseigner les informations de client");
        }
        if( !StringUtils. hasLength(clientDTO.getNom())){
            errors.add("Veuillez renseigner le nom de client");
        }
        return  errors;
    }
}
