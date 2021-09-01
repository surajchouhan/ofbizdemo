 <div class="screenlet">
 <div class="screenlet-title-bar">
    <h3>Result</h3>
 </div>
 <div class="screenlet-body">
     <table cellspacing=0 cellpadding=2 border=0 class="basic-table">
        <thead><tr>
          <th>${uiLabelMap.DemoOfbizProductionRunId}</th>
          <th>${uiLabelMap.DemoOfbizStatus}</th>
          <th>${uiLabelMap.DemoOfbizProductId}</th>
          <th>${uiLabelMap.DemoOfbizProductName}</th>
          <th>${uiLabelMap.DemoOfbizWorkEffortGoodStdTypeId}</th>
          <th>${uiLabelMap.DemoOfbizProductionRunName}</th>
          <th>${uiLabelMap.DemoOfbizFacilityId}</th>
          <th>${uiLabelMap.DemoOfbizFacilityName}</th>
          <th>${uiLabelMap.DemoOfbizProductionRunTypeId}</th>
          <th>${uiLabelMap.DemoOfbizStartDate}</th>

        </tr></thead>
         <#if productionRunList?has_content>
        <tbody>
                <#list productionRunList as productionRun>
                  <tr>
                     <td>${productionRun.workEffortId!}</td>
                     <td>${productionRun.currentStatusId!}</td>
                     <td>${productionRun.productId!}</td>
                     <td>${productionRun.productName!}</td>
                     <td>${productionRun.workEffortGoodStdTypeId!}</td>
                     <td>${productionRun.workEffortName!}</!td>
                     <td>${productionRun.facilityId!}</td>
                     <td>${productionRun.facilityName!}</td>
                     <td>${productionRun.workEffortTypeId!}</td>
                     <td>${productionRun.actualStartDate!}</td>

                   </tr>
                   </#list>
        </tbody>
          </#if>
       </table>

 </div>
 </div>
 </div>


