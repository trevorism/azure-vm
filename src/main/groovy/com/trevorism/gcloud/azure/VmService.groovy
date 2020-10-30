package com.trevorism.gcloud.azure

import com.trevorism.gcloud.model.AppCredentials

/**
 * @author tbrooks
 */
interface VmService {
    void stopVm()
    void startVm()
    void restartVm()
    String getVmStatus()

    boolean createProjectSecrets(AppCredentials appCredentials)
    boolean updateProjectSecrets(AppCredentials appCredentials)
}
