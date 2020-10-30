package com.trevorism.gcloud.azure

import com.microsoft.azure.AzureEnvironment
import com.microsoft.azure.credentials.ApplicationTokenCredentials
import com.microsoft.azure.management.Azure
import com.microsoft.azure.management.compute.VirtualMachine
import com.trevorism.gcloud.model.AppCredentials

import java.util.logging.Logger

/**
 * @author tbrooks
 */
class AzureVmService implements VmService {

    private static final Logger log = Logger.getLogger(AzureVmService.class.name)
    private static final def DO_NOT_MODIFY_APP_SECRETS = ["azure-vm"]
    
    private final Properties properties
    private VirtualMachine vm
    private String jenkinsRoot

    AzureVmService() {
        properties = new Properties()
        properties.load(AzureVmService.class.getClassLoader().getResourceAsStream("secrets.properties") as InputStream)
        vm = getVirtualMachine()
        jenkinsRoot = properties.getProperty("jenkinsRoot")
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

    @Override
    boolean createProjectSecrets(AppCredentials appCredentials) {
        if(!isValid(appCredentials)){
            return false
        }

        def commandLines = [
                "mkdir ${jenkinsRoot}/${appCredentials.appName}".toString(),
                "rm ${jenkinsRoot}/${appCredentials.appName}/secrets.properties".toString(),
                "echo \"clientId=${appCredentials.clientId}\" >> ${jenkinsRoot}/${appCredentials.appName}/secrets.properties".toString(),
                "echo \"clientSecret=${appCredentials.clientSecret}\" >> ${jenkinsRoot}/${appCredentials.appName}/secrets.properties".toString(),
                "chown -R jenkins:jenkins ${jenkinsRoot}/${appCredentials.appName}".toString()
        ]

        def result = vm.runShellScript(commandLines, [])
        return result
    }

    @Override
    boolean updateProjectSecrets(AppCredentials appCredentials) {
        if(!isValid(appCredentials)){
            return false
        }

        def commandLines = [
                "sed -i -e 's/clientId=.*/clientId=${appCredentials.clientId}/g' ${jenkinsRoot}/${appCredentials.appName}/secrets.properties".toString(),
                "sed -i -e 's/clientSecret=.*/clientSecret=${appCredentials.clientSecret}/g' ${jenkinsRoot}/${appCredentials.appName}/secrets.properties".toString()
        ]

        def result = vm.runShellScript(commandLines, [])
        return result
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

    static boolean isValid(AppCredentials appCredentials) {
        if(!appCredentials || !appCredentials.appName || !appCredentials.clientId || !appCredentials.clientSecret || DO_NOT_MODIFY_APP_SECRETS.contains(appCredentials.appName))
            return false
        return true
    }
}
