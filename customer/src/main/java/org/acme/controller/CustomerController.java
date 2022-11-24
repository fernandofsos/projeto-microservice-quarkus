package org.acme.controller;


import org.acme.dto.CustomerDTO;
import org.acme.service.CustomerService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/customer")
public class CustomerController {

    @Inject
    CustomerService customerService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<CustomerDTO> findAllCustomers() {

        return customerService.findAllCustomer();

    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCustomer(CustomerDTO customerDTO) {
        try {
            customerService.createNewCustomer(customerDTO);
            return Response.ok().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @PUT
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCustomer(@PathParam("id") Long id, CustomerDTO customerDTO) {
        try {
            customerService.changeCustomer(id,customerDTO);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


    @DELETE
    @Path("/{id}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    public Response saveCustomer(@PathParam("id") Long id) {
        try {
            customerService.deleteCustomer(id);
            return Response.accepted().build();
        } catch (Exception e) {
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


}
