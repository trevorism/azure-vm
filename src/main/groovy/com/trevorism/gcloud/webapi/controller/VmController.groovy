package com.trevorism.gcloud.webapi.controller

import com.trevorism.event.WorkCompleteEventProducer
import com.trevorism.event.model.WorkComplete
import com.trevorism.gcloud.azure.AzureVmService
import com.trevorism.gcloud.azure.VmService
import com.trevorism.http.headers.HeadersHttpClient
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

    private VmService service = new AzureVmService()
    private WorkCompleteEventProducer eventProducer = new WorkCompleteEventProducer()

    @ApiOperation(value = "Start the trevor VM **Secure")
    @POST
    @Secure
    void startVm(@Context HttpHeaders headers) {
        service.startVm()
        String correlationId = headers?.getHeaderString(HeadersHttpClient.CORRELATION_ID_HEADER_KEY)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Stop the trevor VM **Secure")
    @DELETE
    @Secure
    void stopVm(@Context HttpHeaders headers) {
        service.stopVm()
        String correlationId = headers?.getHeaderString(HeadersHttpClient.CORRELATION_ID_HEADER_KEY)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Restart the trevor VM **Secure")
    @PUT
    @Secure
    void restartVm(@Context HttpHeaders headers) {
        service.restartVm()
        String correlationId = headers?.getHeaderString(HeadersHttpClient.CORRELATION_ID_HEADER_KEY)
        eventProducer.sendEvent(new WorkComplete("trevorism-gcloud", "azure-vm", correlationId))
    }

    @ApiOperation(value = "Get the trevor VM status")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    String getStatus() {
        service.getVmStatus()
    }
}
