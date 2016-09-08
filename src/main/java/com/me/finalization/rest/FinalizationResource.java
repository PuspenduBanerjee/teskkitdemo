package com.me.finalization.rest;

import io.swagger.annotations.Api;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Puspendu on 8/31/2016.
 */
@Path("/service")
@Api
    public interface FinalizationResource {

        @GET
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        DummyResponse getInfo(@QueryParam(value = "whoami") String whoami);


    }
