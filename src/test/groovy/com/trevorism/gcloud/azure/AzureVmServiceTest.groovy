package com.trevorism.gcloud.azure

import com.microsoft.azure.management.compute.VirtualMachine
import org.junit.Before
import org.junit.Test

class AzureVmServiceTest {

    VmService service = new AzureVmService()

    @Before
    void setup(){
        service.vm = {} as VirtualMachine
    }

    @Test
    void testStopVm() {
        service.stopVm()
        assert service.getVmStatus()
    }

    @Test
    void testStartVm() {
        service.startVm()
        assert service.getVmStatus()
    }

    @Test
    void testRestartVm() {
        service.restartVm()
        assert service.getVmStatus()
    }

    @Test
    void testGetVmStatus() {
        assert service.getVmStatus()
    }
}
