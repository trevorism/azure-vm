package com.trevorism.gcloud.azure

import com.microsoft.azure.management.compute.InstanceViewStatus
import com.microsoft.azure.management.compute.RunCommandResult
import com.microsoft.azure.management.compute.VirtualMachine
import com.trevorism.gcloud.model.AppCredentials
import org.junit.Before
import org.junit.Test

class AzureVmServiceTest {

    VmService service = new AzureVmService()

    @Before
    void setup() {
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

    @Test
    void testCommand() {
        service.vm = [runShellScript: {arr1, arr2 -> ({} as RunCommandResult)}] as VirtualMachine
        assert service.createProjectSecrets(new AppCredentials(appName: "one", clientId: "two", clientSecret: "three"))
        assert service.updateProjectSecrets(new AppCredentials(appName: "one", clientId: "three", clientSecret: "four"))

    }
}
