package com.me.finalization.rest;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by 258265 on 8/31/2016.
 */
    @Path("/")
    public interface FinalizationResource {

        @GET
        String getInfo();


    }
