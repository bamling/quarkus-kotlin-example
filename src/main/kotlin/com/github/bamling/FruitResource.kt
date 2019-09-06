package com.github.bamling

import javax.inject.Inject
import javax.ws.rs.Consumes
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.Context
import javax.ws.rs.core.MediaType
import javax.ws.rs.core.Response
import javax.ws.rs.core.UriInfo

@Path("/fruits")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
class FruitResource {

    @Inject
    lateinit var fruitService: FruitService

    @POST
    fun add(fruit: Fruit, @Context uriInfo: UriInfo): Response {
        val created = fruitService.add(fruit)
        return Response
                .created(uriInfo.absolutePathBuilder.path(created.id.toString()).build())
                .entity(created)
                .build()
    }

    @GET
    fun list(): List<Fruit> = fruitService.list()

    @GET
    @Path("/{id}")
    fun find(@PathParam("id") id: String): Response = fruitService.find(id)?.let { fruit ->
        Response.ok(fruit).build()
    } ?: Response.status(Response.Status.NOT_FOUND).build()

}