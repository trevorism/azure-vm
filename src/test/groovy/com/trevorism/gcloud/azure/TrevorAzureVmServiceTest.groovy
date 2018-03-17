package com.trevorism.gcloud.azure

import org.junit.Test

/**
 * @author tbrooks
 */
class TrevorAzureVmServiceTest {

    @Test
    void testGetVmStatus() {
        TrevorAzureVmService service = new TrevorAzureVmService()
        assert service.getVmStatus()
    }
}
