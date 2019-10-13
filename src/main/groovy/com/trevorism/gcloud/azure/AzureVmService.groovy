package com.trevorism.gcloud.azure

import com.microsoft.azure.AzureEnvironment
import com.microsoft.azure.credentials.ApplicationTokenCredentials
import com.microsoft.azure.management.Azure
import com.microsoft.azure.management.compute.VirtualMachine

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class AzureVmService implements VmService{

    private static final Logger log = Logger.getLogger(AzureVmService.class.name)

    private final Properties properties
    private VirtualMachine vm

    AzureVmService(){
        properties = new Properties()
        properties.load(AzureVmService.class.getClassLoader().getResourceAsStream("secrets.properties") as InputStream)
        vm = getVirtualMachine()
    }

    @Override
    void stopVm() {
        vm.powerOff()
        log.info("Stopped the Azure VM")
    }

    @Override
    void startVm() {
        vm.start()
        log.info("Started the Azure VM")
    }

    @Override
    void restartVm() {
        vm.restart()
        log.info("Restarted the Azure VM")
    }

    @Override
    String getVmStatus() {
        String powerState = vm.powerState().toString()
        log.fine("Power state: ${powerState}")
        return powerState
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
