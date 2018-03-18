package com.trevorism.gcloud.webapi.controller

import com.trevorism.gcloud.azure.TrevorAzureVmService
import com.trevorism.gcloud.azure.VmService
import com.trevorism.secure.Secure
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.Consumes
import javax.ws.rs.DELETE
import javax.ws.rs.GET
import javax.ws.rs.POST
import javax.ws.rs.PUT
import javax.ws.rs.Path
import javax.ws.rs.PathParam
import javax.ws.rs.Produces
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Api("Vm Operations")
@Path("/vm")
class VmController {

    private VmService service = new TrevorAzureVmService()

    @ApiOperation(value = "Start the trevor VM **Secure")
    @POST
    @Secure
    void startVm(){
        service.startVm()
    }

    @ApiOperation(value = "Stop the trevor VM **Secure")
    @DELETE
    @Secure
    void stopVm(){
        service.stopVm()
    }

    @ApiOperation(value = "Restart the trevor VM **Secure")
    @PUT
    @Secure
    void restartVm(){
        service.restartVm()
    }

    @ApiOperation(value = "Get the trevor VM status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getStatus(){
        service.getVmStatus()
    }
}
