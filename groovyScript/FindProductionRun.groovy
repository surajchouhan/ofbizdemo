import org.apache.ofbiz.entity.condition.EntityConditionBuilder


exprBldr = new EntityConditionBuilder()
condition = exprBldr.AND() {
    if (parameters?.productionRunId) {
        EQUALS(workEffortId: parameters?.productionRunId)
    }
    if (parameters.currentStatusId) {
        IN(currentStatusId: parameters.currentStatusId)
    }
    if (parameters?.productId) {
        EQUALS(productId: parameters.productId)
    }
    EQUALS(workEffortTypeId: "PROD_ORDER_HEADER")

  //  if (parameters.productionRunTypeId){
    //    IN(workEffortTypeId:parameters.productionRunTypeId)
   // }
//    if(parameters.startDate){
//        IN(actualStartDate:parameters.startDate)
//    }

    if (parameters.productionRunName) {
        EQUALS(workEffortName:parameters.productionRunName)
    }

}

workEfforts = from("WorkEffortAndGoods").where(condition).queryList()
productionRunList = []
workEfforts.each { workEffort ->
    workEffortMap = [:]
    workEffortMap.putAll(workEffort)
    product = from("Product").where(productId: workEffort.productId).queryOne()
    workEffortMap.productName = product?.productName
    facility = from("Facility").where(facilityId: workEffort?.facilityId).queryOne()
    workEffortMap.facilityName = facility?.facilityName
    productionRunList.add(workEffortMap)
}
context.productionRunList = productionRunList
/*
if(productionRunId) {
    supplierInfo = [:]
    supplierInfo.partyId = partyId
    ProductionRun=from("WorkEffort").where("workEffortId", productionRunId, "workEffortTypeId","PRODUCTION_RUN" ).filterByDate().queryFirst()
    productRunList - [];

    productRunList = from("")
    context.productRunList = productRunList

    productionRunId = parameters.productionRunId
    if (productionRunId) {
        ProductionRun productionRun = new ProductionRun(productionRunId, delegator, dispatcher)
        if (productionRun.exist()) {
            productionRunId = productionRun.getGenericValue().workEffortId
            context.productionRunId = productionRunId
            context.productionRun = productionRun.getGenericValue()
            productionRunData = [:]
            productionRunData.productionRunId = productionRunId
            productionRunData.productId = productionRun.getProductProduced().productId
            productionRunData.currentStatusId = productionRun.getGenericValue().currentStatusId
            productionRunData.facilityId = productionRun.getGenericValue().facilityId
            productionRunData.workEffortName = productionRun.getProductionRunName()
            productionRunData.description = productionRun.getDescription()
            productionRunData.quantity = productionRun.getQuantity()
            productionRunData.estimatedStartDate = productionRun.getEstimatedStartDate()
            productionRunData.estimatedCompletionDate = productionRun.getEstimatedCompletionDate()

            context.productionRunData = productionRunData

        }
    }
*/