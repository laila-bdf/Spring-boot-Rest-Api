package com.valueit.springbootapp.services;

import com.valueit.springbootapp.dto.ClientDTO;
import com.valueit.springbootapp.exception.EntityNotFoundException;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Date;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClientServiceTest {

    @Autowired
    private ClientService clientService;



    @AfterEach
    public  void cleanUpEach(){
        clientService.deleteAll();
    }


    @Test
    public void testSaveClient_Pass()
    {
        ClientDTO expecetedClient = new  ClientDTO();
        expecetedClient.setNom("nomTest");
        expecetedClient.setPrenom("prenom");
        expecetedClient.setDateNaiss(new Date(0));

        ClientDTO savedClient = clientService.save(expecetedClient);

        assertNotNull(savedClient);
        assertNotNull(savedClient.getId());
        assertEquals(expecetedClient.getNom(), savedClient.getNom());
        assertEquals(expecetedClient.getPrenom(), savedClient.getPrenom());
        assertEquals(expecetedClient.getDateNaiss(), savedClient.getDateNaiss());
    }

    @Test
    public void testSaveClient_Fail() {

    }

    @Test
    public void testUpdateClient_Pass() throws Exception
    {
        ClientDTO expecetedClient = new  ClientDTO();
        expecetedClient.setNom("nomTest");
        expecetedClient.setPrenom("prenom");
        expecetedClient.setDateNaiss(new Date(0));

        ClientDTO savedClient = clientService.save(expecetedClient);

        ClientDTO clientUpdated = savedClient;
        clientUpdated.setNom("nom updated");

        ClientDTO cl_updated = clientService.update(clientUpdated, clientUpdated.getId());

        assertNotNull(cl_updated);
        assertEquals(clientUpdated.getId(), cl_updated.getId());
        assertEquals(clientUpdated.getNom(), cl_updated.getNom());
        assertEquals(clientUpdated.getPrenom(), cl_updated.getPrenom());
        assertEquals(clientUpdated.getDateNaiss(), cl_updated.getDateNaiss());
    }

    @Test
    public void testUpdateClient_Fail() throws Exception
    {
        ClientDTO client = new  ClientDTO();
        client.setNom("hane");
        client.setPrenom("hanae");
        client.setDateNaiss(new Date(0));

        assertThrows(EntityNotFoundException.class, ()-> {clientService.update(client, 0L);});
    }



    @Test
    public void testDeleteClientById_Pass() throws Exception {
        ClientDTO expecetedClient = new  ClientDTO();
        expecetedClient.setNom("bdf");
        expecetedClient.setPrenom("safae");
        expecetedClient.setDateNaiss(new Date(0));

        ClientDTO savedClient = clientService.save(expecetedClient);
        clientService.deleteClientById(savedClient.getId());

        assertThrows(EntityNotFoundException.class, ()-> clientService.findById(savedClient.getId()));


    }
    @Test
    public void testDeleteClientById_Fail() {
        assertThrows(EntityNotFoundException.class, ()-> clientService.deleteClientById(0L));

    }


    private List<ClientDTO> buildClients() {
        ClientDTO client1 = new ClientDTO();
        client1.setNom("bouadif");
        client1.setPrenom("ayoub");
        ClientDTO client2 = new ClientDTO();
        client2.setNom("bouadif");
        client2.setPrenom("safae");
        List<ClientDTO> cltList = Arrays.asList(client1, client2);
        return cltList;
    }

}