package com.companyname.ofbizdemo.events;

import org.apache.ofbiz.base.util.Debug;
import org.apache.ofbiz.base.util.UtilDateTime;
import org.apache.ofbiz.base.util.UtilValidate;
import org.apache.ofbiz.entity.GenericValue;
import org.apache.ofbiz.service.GenericServiceException;
import org.apache.ofbiz.service.LocalDispatcher;
import org.apache.ofbiz.service.ServiceUtil;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

    public class ProductionRunEvents {
        public static final String module = OfbizDemoEvents.class.getName();

        public static String createProductionRunJavaEvent(HttpServletRequest request, HttpServletResponse response) {
            LocalDispatcher dispatcher = (LocalDispatcher) request.getAttribute("dispatcher");
            GenericValue userLogin = (GenericValue) request.getSession().getAttribute("userLogin");

            String productId = request.getParameter("productId");
            String pRQuantity = request.getParameter("quantity");
            String startDateStr = request.getParameter("startDate");
            String facilityId = request.getParameter("facilityId");
            String routingId = request.getParameter("routingId");
            String productionRunName = request.getParameter("productionRunName");
            String description = request.getParameter("description");
            String createDependentProductionRun = request.getParameter("createDependentProductionRun");

            System.out.println("==============================================="+productId+"+============================================");
            System.out.println("==============================================="+pRQuantity+"============================================");

            List<String> errorMessages = new ArrayList<String>();
            if (UtilValidate.isEmpty(productId) ){
                String errMsg = "Product id is required fields, please enter the product id";
                errorMessages.add(errMsg);

            }
            if (UtilValidate.isEmpty(pRQuantity) ) {
                String errMsg = "Quantity is required fields, please enter the quantity field.";
                errorMessages.add(errMsg);
            }
            Timestamp startDate = UtilDateTime.nowTimestamp();
            if (UtilValidate.isNotEmpty(startDateStr) ) {
                startDate = Timestamp.valueOf(startDateStr);
            }
            if (UtilValidate.isNotEmpty(errorMessages)) {
                request.setAttribute("_ERROR_MESSAGE_LIST_", errorMessages);
                return "error";
            }

            Map<String, Object> createProductionRunMap = new HashMap<>();
            createProductionRunMap.put("userLogin", userLogin);
            if (UtilValidate.isNotEmpty(startDate)) {
                createProductionRunMap.put("startDate", startDate);
            }
            if (UtilValidate.isNotEmpty(productId)) {
                createProductionRunMap.put("productId", productId);
            }
            if (UtilValidate.isNotEmpty(pRQuantity)) {
                createProductionRunMap.put("quantity", pRQuantity);
            }
            if (UtilValidate.isNotEmpty(facilityId)) {
                createProductionRunMap.put("facilityId", facilityId);
            }
            if (UtilValidate.isNotEmpty(routingId)) {
                createProductionRunMap.put("routingId", routingId);
            }
            if (UtilValidate.isNotEmpty(productionRunName)) {
                createProductionRunMap.put("workEffortName", productionRunName);
            }
            if (UtilValidate.isNotEmpty(description)) {
                createProductionRunMap.put("description", description);
            }
            if (UtilValidate.isNotEmpty(createDependentProductionRun)) {
                createProductionRunMap.put("createDependentProductionRun", createDependentProductionRun);
            }

            String productionRunId = null;
            try {
                Debug.logInfo("=======Creating Production Run demo record in event using service createProductionRunDemoEvent=========", module);
                Debug.log("======createProductionRunMap==="+createProductionRunMap);
                Map<String, Object> serviceResult = dispatcher.runSync("createProductionRunDemo", createProductionRunMap);
                if (ServiceUtil.isError(serviceResult)) {
                    request.setAttribute("_ERROR_MESSAGE_", ServiceUtil.getErrorMessage(serviceResult));
                    return "error";
                }
                productionRunId = (String) serviceResult.get("productionRunId");

            } catch (GenericServiceException e) {
                String errMsg = "Unable to create new records in WorkEffort entity: " + e.toString();
                request.setAttribute("_ERROR_MESSAGE_", errMsg);
                return "error";
            }
             request.setAttribute( "productionRunId", productionRunId);
            return "success";
        }
    }


