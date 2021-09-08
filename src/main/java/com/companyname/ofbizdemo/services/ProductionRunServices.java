package com.companyname.ofbizdemo.services;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilMisc;
import org.apache.ofbiz.base.util.UtilProperties;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.Delegator;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRun;
import org.apache.ofbiz.service.*;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.Locale;
import java.util.Map;


public class ProductionRunServices {

    private static final String MODULE = org.apache.ofbiz.manufacturing.jobshopmgt.ProductionRunServices.class.getName();
    private static final String RESOURCE = "OfbizDemoUiLabels";
    public static Map<String, Object> createProductionRun(DispatchContext ctx, Map<String, ? extends Object> context) {
        Map<String, Object> result = ServiceUtil.returnSuccess();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        BigDecimal quantity = (BigDecimal) context.get("quantity");
        String productionRunId = null;
        GenericValue userLogin = (GenericValue) context.get("userLogin");

        try {
            Map<String, Object> createProductionRunCtx = ctx.makeValidContext("createProductionRun", ModelService.IN_PARAM, context);
            createProductionRunCtx.put("pRQuantity", quantity);
            createProductionRunCtx.put("userLogin", userLogin);
            Debug.log("======createProductionRunCtx====="+createProductionRunCtx);

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


    public static Map<String, Object> updateProductionRun(DispatchContext ctx, Map<String, ? extends Object> context) {
        Delegator delegator = ctx.getDelegator();
        LocalDispatcher dispatcher = ctx.getDispatcher();
        Locale locale = (Locale) context.get("locale");
        GenericValue userLogin = (GenericValue) context.get("userLogin");
        String productionRunId = (String) context.get("productionRunId");

        if (UtilValidate.isNotEmpty(productionRunId)) {
            ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher);
            if (productionRun.exist()) {

                if (!"PRUN_CREATED".equals(productionRun.getGenericValue().getString("currentStatusId"))
                        && !"PRUN_SCHEDULED".equals(productionRun.getGenericValue().getString("currentStatusId"))) {
                    return ServiceUtil.returnError(UtilProperties.getMessage(RESOURCE, "OfbizDemoProductionRunPrinted", locale));
                }

                BigDecimal quantity = (BigDecimal) context.get("quantity");
                if (quantity != null && quantity.compareTo(productionRun.getQuantity()) != 0) {
                    productionRun.setQuantity(quantity);
                }

                Timestamp estimatedStartDate = (Timestamp) context.get("startDate");
                if (estimatedStartDate != null && !estimatedStartDate.equals(productionRun.getEstimatedStartDate())) {
                    productionRun.setEstimatedStartDate(estimatedStartDate);
                }

                String workEffortName = (String) context.get("productionRunName");
                if (workEffortName != null) {
                    productionRun.setProductionRunName(workEffortName);
                }

                String description = (String) context.get("description");
                if (description != null) {
                    productionRun.setDescription(description);
                }

                String facilityId = (String) context.get("facilityId");
                if (facilityId != null) {
                    productionRun.getGenericValue().set("facilityId", facilityId);
                }

                boolean updateEstimatedOrderDates = productionRun.isUpdateCompletionDate();
                if (productionRun.store()) {
                    if (updateEstimatedOrderDates && "PRUN_SCHEDULED".equals(productionRun.getGenericValue().getString("currentStatusId"))) {
                        try {
                            Map<String, Object> result = dispatcher.runSync("setEstimatedDeliveryDates",
                                    UtilMisc.toMap("userLogin", userLogin));
                            if (ServiceUtil.isError(result)) {
                                return ServiceUtil.returnError(ServiceUtil.getErrorMessage(result));
                            }
                        } catch (GenericServiceException e) {
                            Debug.logError(e, "Problem calling the setEstimatedDeliveryDates service", MODULE);
                            return ServiceUtil.returnError(UtilProperties.getMessage(RESOURCE, "OfbizDemoProductionRunNotUpdated", locale));
                        }
                    }
                    return ServiceUtil.returnSuccess();
                } else {
                    Debug.logError("productionRun.store() fail for productionRunId =" + productionRunId, MODULE);
                    return ServiceUtil.returnError(UtilProperties.getMessage(RESOURCE, "OfbizDemoProductionRunNotUpdated", locale));
                }
            }
            Debug.logError("no productionRun for productionRunId =" + productionRunId, MODULE);
            return ServiceUtil.returnError(UtilProperties.getMessage(RESOURCE, "OfbizDemoProductionRunNotUpdated", locale));
        }
        Debug.logError("service updateProductionRun call with productionRunId empty", MODULE);
        return ServiceUtil.returnError(UtilProperties.getMessage(RESOURCE, "OfbizDemoProductionRunNotUpdated", locale));
    }
    }
