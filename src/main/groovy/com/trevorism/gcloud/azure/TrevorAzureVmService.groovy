package com.trevorism.gcloud.azure

import com.microsoft.azure.AzureEnvironment
import com.microsoft.azure.credentials.ApplicationTokenCredentials
import com.microsoft.azure.management.Azure
import com.microsoft.azure.management.compute.VirtualMachine

/**
 * @author tbrooks
 */
class TrevorAzureVmService implements VmService{

    private final Properties properties
    private final VirtualMachine vm


    TrevorAzureVmService(){
        properties = new Properties()
        properties.load(TrevorAzureVmService.class.getClassLoader().getResourceAsStream("secrets.properties"))
        vm = getVirtualMachine()
    }

    @Override
    void stopVm() {
        vm.powerOff()
    }

    @Override
    void startVm() {
        vm.start()
    }

    @Override
    void restartVm() {
        vm.restart()
    }

    @Override
    String getVmStatus() {
        return vm.powerState().toString()
    }

    private VirtualMachine getVirtualMachine() {
        ApplicationTokenCredentials credentials = new ApplicationTokenCredentials(
                properties.getProperty("clientId"),
                properties.getProperty("tenantId"),
                properties.getProperty("clientSecret"),
                AzureEnvironment.AZURE)
        Azure azure = Azure.authenticate(credentials).withSubscription(properties.getProperty("subscriptionId"))
        VirtualMachine vm = azure.virtualMachines().getById(properties.getProperty("vmId"))
        return vm
    }
}
