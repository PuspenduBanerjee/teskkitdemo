package com.me.finalization.rest.resources;

import com.me.finalization.rest.model.DummyResponse;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * Created by Puspendu on 8/31/2016.
 */
@Api(value = "Service Root")
@Path("/service")
    public interface FinalizationResource {

        @GET
        @ApiOperation(value = "whoami")
        @Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
        DummyResponse getInfo(@QueryParam(value = "whoami") String whoami);


    }
