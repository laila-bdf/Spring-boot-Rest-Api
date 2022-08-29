package com.valueit.springbootapp.validator;

import com.valueit.springbootapp.dto.CommandeDTO;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class CommandeValidator {
    public static List<String> validate(CommandeDTO commandeDTO){
    List<String> errors = new ArrayList<>();
    if(commandeDTO== null ){
        errors.add("Veuillez renseigner le prix total de la commande");
    }
    return  errors;
    }
}
