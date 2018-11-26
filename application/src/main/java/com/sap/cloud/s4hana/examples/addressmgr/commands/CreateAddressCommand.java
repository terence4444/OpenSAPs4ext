package com.sap.cloud.s4hana.examples.addressmgr.commands;

import com.netflix.hystrix.HystrixCommand;
import com.sap.cloud.sdk.frameworks.hystrix.HystrixUtil;
import com.sap.cloud.sdk.s4hana.connectivity.ErpCommand;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartner;
import org.slf4j.Logger;

import com.sap.cloud.sdk.cloudplatform.logging.CloudLoggerFactory;
import com.sap.cloud.sdk.s4hana.datamodel.odata.namespaces.businesspartner.BusinessPartnerAddress;
import com.sap.cloud.sdk.s4hana.datamodel.odata.services.BusinessPartnerService;

public class CreateAddressCommand extends ErpCommand<BusinessPartnerAddress> {
    private static final Logger logger = CloudLoggerFactory.getLogger(CreateAddressCommand.class);

    private final BusinessPartnerService service;
    private final BusinessPartnerAddress addressToCreate;

    public CreateAddressCommand(final BusinessPartnerService service, final BusinessPartnerAddress addressToCreate) {
        super(HystrixUtil.getDefaultErpCommandSetter(
                CreateAddressCommand.class,
                HystrixUtil.getDefaultErpCommandProperties()
                .withExecutionTimeoutInMilliseconds(10000)
        ));
        this.service = service;
        this.addressToCreate = addressToCreate;
    }

    @Override
    public BusinessPartnerAddress run() throws Exception {
        return service.createBusinessPartnerAddress(addressToCreate).execute();
    }
}
