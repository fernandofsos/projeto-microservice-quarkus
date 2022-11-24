package org.acme.controller;


import org.acme.dto.ProductDto;
import org.acme.service.ProductService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/api/products")
public class ProductController {

    @Inject
    ProductService productService;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<ProductDto> findAllProducts(){
         return  productService.getAllProduct();
    }

    @POST
    @Transactional
    public Response saveProduct(ProductDto productDto){
        try {
            productService.createNewProduct(productDto);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @PUT
    @Path("/{id}")
    @Transactional
    public Response changeProduct(@PathParam("id") long id, ProductDto productDto){
        try {
            productService.changeProduct(id,productDto);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }

    @DELETE
    @Path("/{id}")
    @Transactional
    public Response removeProduct(@PathParam("id") long id){
        try {
            productService.deleteProduct(id);
            return Response.ok().build();
        }catch (Exception e){
            e.printStackTrace();
            return Response.serverError().build();
        }
    }


}
