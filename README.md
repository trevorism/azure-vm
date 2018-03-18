# azure-vm 
Web app that turns on a VM on Azure

Add a secrets.properties to `src\main\resources`

```
clientId=<servicePrincipal application id>
tenantId=<AD tenant ID>
clientSecret=<servicePrincipal secret key>
subscriptionId=<Azure Subscription id>
vmId=<ID of the VM to administer>
```