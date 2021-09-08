import org.apache.ofbiz.service.ServiceUtil

def createProductionRun() {
    println("=======Creating Production Run demo record using groovy service createProductionRun=========")
    createProductionRunMap = [:]
    createProductionRunMap.put("userLogin",parameters.userLogin)
    createProductionRunMap.put("productId", parameters.productId)
    createProductionRunMap.put("startDate", parameters.startDate)
    createProductionRunMap.put("quantity", parameters.quantity)
    createProductionRunMap.put("facilityId", parameters.facilityId)
    createProductionRunMap.put("routingId", parameters.routingId)
    createProductionRunMap.put("workEffortName", parameters.workEffortName)
    createProductionRunMap.put("description", parameters.description)
    createProductionRunMap.put("createDependentProductionRun",parameters.createDependentProductionRun)
    String productionRunId = null
    serviceResult = dispatcher.runSync("createProductionRunDemo", createProductionRunMap)
    productionRunId = (String) serviceResult.get("productionRunId")
    result = ServiceUtil.returnSuccess()
    result.productionRunId = productionRunId
    return result
}