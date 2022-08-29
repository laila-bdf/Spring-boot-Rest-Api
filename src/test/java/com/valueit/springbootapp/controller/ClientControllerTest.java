package com.valueit.springbootapp.controller;

import com.valueit.springbootapp.dto.ClientDTO;
import com.valueit.springbootapp.exception.EntityNotFoundException;
import com.valueit.springbootapp.model.Client;
import com.valueit.springbootapp.repository.ClientRepository;
import com.valueit.springbootapp.services.ClientService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@AutoConfigureJsonTesters
@WebMvcTest(ClientController.class)
class ClientControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    ClientService clientService;
    @MockBean
    private ClientRepository clientrepo;
    // This object will be initialized thanks to @AutoConfigureJsonTesters
    @Autowired
    private JacksonTester<ClientDTO> jsonClientDTO;

    @Test
    public void testGetClientById_OK() throws Exception {

        // given
        ClientDTO client = new ClientDTO();
        client.setNom("");
        client.setPrenom("");
        given(clientService.findById(2L)).willReturn(client);

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/api/v1/clients/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(response.getContentAsString()).isEqualTo(
                jsonClientDTO.write(client).getJson()
        );
    }

    @Test
    public void testGetClientById_KO() throws Exception {
        // given
        given(clientService.findById(2L))
                .willThrow(new EntityNotFoundException("client not found"));

        // when
        MockHttpServletResponse response = mvc.perform(
                        get("/api/v1/clients/2")
                                .accept(MediaType.APPLICATION_JSON))
                .andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(response.getContentAsString()).isNotEmpty();
    }

    @Test
    public void testSave_OK() throws Exception {

        ClientDTO client = new ClientDTO();
        client.setNom("nom");
        client.setPrenom("laila");
        // when
        MockHttpServletResponse response = mvc.perform(
                post("/api/v1/clients/").contentType(MediaType.APPLICATION_JSON).content(
                        jsonClientDTO.write(client).getJson()
                )).andReturn().getResponse();

        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }






    @Test
    public void testDelete_Ok() throws Exception {
        // execute
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/2")).andReturn().getResponse();

        // verify that service method was called once
        verify(clientService).deleteClientById(2L);

        //assert
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());


    }

    @Test
    public void testDelete_KO() throws Exception {
        given(clientService.deleteClientById(2L))
                .willThrow(new EntityNotFoundException("client not found"));
        // execute
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.delete("/api/v1/clients/2"))
                .andReturn().getResponse();

        // verify that service method was called once
        verify(clientService).deleteClientById(2L);

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
    }

    @Test
    public void testUpdateClient_OK() throws Exception  {

        ClientDTO client = new ClientDTO();
        client.setNom("nom");
        client.setPrenom("ayoub");
        given(clientService.update(client, 2L)).willReturn(client);

        // execute
        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.put("/api/v1/clients/2").contentType(MediaType.APPLICATION_JSON).content(
                jsonClientDTO.write(client).getJson()
        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.ACCEPTED.value());

        assertThat(response.getContentAsString()).isEqualTo(
                jsonClientDTO.write(client).getJson()
        );

    }

    @Test
    public void testUpdateClient_KO() throws Exception {
        ClientDTO client = new ClientDTO();
        client.setNom("nom");
        client.setPrenom("ayoub");
        given(clientService.update(client, 2L)).willThrow(new EntityNotFoundException("Client n'existe pas"));

        MockHttpServletResponse response = mvc.perform(MockMvcRequestBuilders.put("/api/v1/clients/2").contentType(MediaType.APPLICATION_JSON).content(
                jsonClientDTO.write(client).getJson()
        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());

    }

    private List<ClientDTO> buildClients() {
        ClientDTO client1 = new ClientDTO();
        client1.setNom("nom");
        client1.setPrenom("ayoub");
        ClientDTO client2 = new ClientDTO();
        client2.setNom("nom");
        client2.setPrenom("safae");
        List<ClientDTO> cltList = Arrays.asList(client1, client2);
        return cltList;
    }

    private List<Client> buildClient() {
        Client client1 = new Client();
        client1.setNom("nom1");
        client1.setPrenom("ayoub");
        Client client2 = new Client();
        client2.setNom("nom2");
        client2.setPrenom("safae");
        List<Client> cltList = Arrays.asList(client1, client2);
        return cltList;
    }
}