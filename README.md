# azure-vm
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/azure-vm)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/azure-vm)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/azure-vm)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/azure-vm)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/azure-vm)

Web app that administers a VM on Azure. Deployed here: [azure-vm](https://azure-vm-dot-trevorism-gcloud.appspot.com)

Current Version: 0.3.0

Setup a VM like this: [VM Setup from Scratch](https://docs.google.com/document/d/1ZjcUJjTaKrr328Hi5XIYNKO_TA86z8jfZrcr8JEOfrE)

##How to build
`gradle clean build`

Add a `secrets.properties` to src\main\resources

```
clientId=<servicePrincipal application id>
tenantId=<AD tenant ID>
clientSecret=<servicePrincipal secret key>
subscriptionId=<Azure Subscription id>
vmId=<ID of the VM to administer>  #  /subscriptions/<Azure Subscription id>/resourceGroups/VMS/providers/Microsoft.Compute/virtualMachines/<VM Name>
```


