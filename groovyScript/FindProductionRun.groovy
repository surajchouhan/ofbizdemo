import org.apache.ofbiz.entity.condition.EntityConditionBuilder
exprBldr = new EntityConditionBuilder()
condition = exprBldr.AND() {
    if (parameters?.productionRunId) {
        EQUALS(workEffortId: parameters?.productionRunId)
    }
    if (parameters.statusId) {
        IN(currentStatusId: parameters.statusId)
    }
    if (parameters?.productId) {
        EQUALS(productId: parameters.productId)
    }
    if (parameters?.facilityId) {
        EQUALS(facilityId: parameters.facilityId)
    }
    EQUALS(workEffortTypeId: "PROD_ORDER_HEADER")

    if (parameters.productionRunName) {
        LIKE(workEffortName : parameters.productionRunName + "%")
    }
}
println("=====condition====="+condition)
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