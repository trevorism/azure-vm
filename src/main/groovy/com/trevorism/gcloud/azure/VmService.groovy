package com.trevorism.gcloud.azure

/**
 * @author tbrooks
 */
interface VmService {
    void stopVm()
    void startVm()
    void restartVm()
    String getVmStatus()
}
