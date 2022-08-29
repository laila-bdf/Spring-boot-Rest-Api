package com.valueit.springbootapp.repository;

import com.valueit.springbootapp.model.Client;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Repository
public interface ClientRepository extends JpaRepository<Client,Long> , JpaSpecificationExecutor<Client> {
    Page<Client> findClientByNomIgnoreCaseAndAndPrenomIgnoreCase(String nom, String prenom , Pageable page);
}
