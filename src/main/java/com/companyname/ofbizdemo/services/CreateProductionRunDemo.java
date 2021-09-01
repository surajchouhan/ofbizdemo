package com.companyname.ofbizdemo.services;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRunServices;
import org.apache.ofbiz.service.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CreateProductionRunDemo {

    private static final String MODULE = ProductionRunServices.class.getName();

    public static Map<String, Object> createProductionRun(DispatchContext ctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        String productId = (String) context.get("productId");
        Timestamp startDate = (Timestamp) context.get("startDate");
        BigDecimal quantity = (BigDecimal) context.get("quantity");
        String facilityId = (String) context.get("facilityId");
        // Optional input fields
        String routingId = (String) context.get("routingId");
        String workEffortName = (String) context.get("workEffortName");
        String description = (String) context.get("description");
        String productionRunId = null;
        GenericValue userLogin = (GenericValue) context.get("userLogin");

//        Map<String, Object> serviceContext = new HashMap<>();
//        serviceContext.put("productId", productId); // the product that we want to manufacture
//        serviceContext.put("startDate", startDate); // the quantity that we want to manufacture
//        serviceContext.put("pRQuantity", quantity);
//        serviceContext.put("facilityId", facilityId);
//        serviceContext.put("routingId", routingId);
//        serviceContext.put("workEffortName", workEffortName);
//        serviceContext.put("description", description);
//        serviceContext.put("userLogin", userLogin);

        try {
            Map<String, Object> createProductionRunCtx = ctx.makeValidContext("createProductionRun", ModelService.IN_PARAM, context);
            createProductionRunCtx.put("pRQuantity", quantity);
            createProductionRunCtx.put("userLogin", userLogin);
            Debug.log("====createProductionRunCtx====="+createProductionRunCtx);
//            Debug.log("====serviceContext====="+serviceContext);

            Map<String, Object> serviceResult = dispatcher.runSync("createProductionRun", createProductionRunCtx);
            if (ServiceUtil.isError(serviceResult)) {
                return ServiceUtil.returnError(ServiceUtil.getErrorMessage(serviceResult));
            }
            productionRunId = (String) serviceResult.get("productionRunId");
        } catch (GenericServiceException e) {
            Debug.logError(e, "Problem calling the createWorkEffort service", MODULE);
            return ServiceUtil.returnError(e.getMessage());
        }
        result.put("productionRunId", productionRunId);
        return result;
    }
}
