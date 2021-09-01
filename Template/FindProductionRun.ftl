<div class="screenlet">
    <div class="screenlet-title-bar">
       <h3>Find option</h3>
    </div>
    <div class="screenlet-body">
        <form id="FindProductionRunEvent" method="post" action="<@ofbizUrl>FindProductionRunFtl</@ofbizUrl>">
            <table>
                <tr>
                    <td>
                        <label class="control-label" for="productionRunId">${uiLabelMap.DemoOfbizProductionRunId}</label>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                        <input type="text" name="productionRunId" id="productionRunId" value="${parameters.productionRunId!}"/>
                    </td>
                </tr>
                <tr>
                    <td>
                        <label for="statusId">${uiLabelMap.DemoOfbizStatus}</label>
                    </td>
                    <td>&nbsp;</td>
                    <#list statuses as statusItem>
                      <td align="center">
                        <input type="checkbox" name="statusId" value="${statusItem.statusId!}"/>${statusItem.description!}
                      </td>
                    </#list>
                </tr>
                <tr>
                    <td>
                        <label for="productId">${uiLabelMap.DemoOfbizProductId}</label>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                       <input type="text" name="productId" id="productId"/>
                     </td>
                </tr>
                <tr>
                    <td>
                        <label for="productionRunName">${uiLabelMap.DemoOfbizProductionRunName}</label>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                         <input type="text" name="productionRunName" id="productionRunName"/>
                    </td>
                </tr>
                <tr>
                    <td width="20%">
                    <label  class="control-label"  for="facilityId">${uiLabelMap.DemoOfbizFacilityId}</label>
                    <select name="facilityId" id="facilityId">
                    <#list facilities as facility>
                     <option value="${facility.facilityId!}">${facility.facilityName!}</option>
                     </#list>
                     </select>

                    </td>
               </tr>
                <tr>
                    <td>

                    </td>
                    <td>&nbsp;</td>
                    <td>
                        <input type="submit" value="Find"/>
                    </td>
                </tr>
            </table>
        </form>
   </div>
</div>
