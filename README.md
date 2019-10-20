# azure-vm
![Jenkins](https://img.shields.io/jenkins/build/http/trevorism-build.eastus.cloudapp.azure.com/azure-vm)
![Jenkins Coverage](https://img.shields.io/jenkins/coverage/jacoco/http/trevorism-build.eastus.cloudapp.azure.com/azure-vm)
![GitHub last commit](https://img.shields.io/github/last-commit/trevorism/azure-vm)
![GitHub language count](https://img.shields.io/github/languages/count/trevorism/azure-vm)
![GitHub top language](https://img.shields.io/github/languages/top/trevorism/azure-vm)

Web app that turns on a VM on Azure. 

Setup a VM like this: [VM Setup from Scratch](https://github.com/trevorism/azure-vm/blob/master/SetupSteps.txt)

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


