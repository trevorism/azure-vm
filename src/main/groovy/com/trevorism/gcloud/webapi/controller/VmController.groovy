package com.trevorism.gcloud.webapi.controller

import com.trevorism.event.WorkCompleteEventProducer
import com.trevorism.event.model.WorkComplete
import com.trevorism.gcloud.azure.AzureVmService
import com.trevorism.gcloud.azure.VmService
import com.trevorism.gcloud.model.AppCredentials
import com.trevorism.secure.Roles
import com.trevorism.secure.Secure
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation

import javax.ws.rs.*
import javax.ws.rs.core.Context
import javax.ws.rs.core.HttpHeaders
import javax.ws.rs.core.MediaType

/**
 * @author tbrooks
 */
@Api("Vm Operations")
@Path("/vm")
class VmController {

    public static final String CORRELATION_HEADER = "X-Correlation-ID"
    private VmService service = new AzureVmService()
    private WorkCompleteEventProducer eventProducer = new WorkCompleteEventProducer()

    @ApiOperation(value = "Start the trevor VM **Secure")
    @POST
    @Secure(Roles.SYSTEM)
    void startVm(@Context HttpHeaders headers) {
        service.startVm()
        String correlationId = headers?.getHeaderString(CORRELATION_HEADER)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Stop the trevor VM **Secure")
    @DELETE
    @Secure(Roles.SYSTEM)
    void stopVm(@Context HttpHeaders headers) {
        service.stopVm()
        String correlationId = headers?.getHeaderString(CORRELATION_HEADER)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Restart the trevor VM **Secure")
    @PUT
    @Secure(Roles.SYSTEM)
    void restartVm(@Context HttpHeaders headers) {
        service.restartVm()
        String correlationId = headers?.getHeaderString(CORRELATION_HEADER)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Get the trevor VM status")
    @GET
    @Secure(Roles.SYSTEM)
    @Produces(MediaType.APPLICATION_JSON)
    String getStatus() {
        service.getVmStatus()
    }

    @ApiOperation(value = "Create clientId and secret file for the given project")
    @POST
    @Secure(Roles.SYSTEM)
    @Path("secret")
    boolean createSecrets(AppCredentials appCredentials){
        service.createProjectSecrets(appCredentials)
    }

    @ApiOperation(value = "Update clientId and secret file for the given project")
    @PUT
    @Secure(Roles.SYSTEM)
    @Path("secret")
    boolean updateSecrets(AppCredentials appCredentials){
        service.updateProjectSecrets(appCredentials)
    }
}
