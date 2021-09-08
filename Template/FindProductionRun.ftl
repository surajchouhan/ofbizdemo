<div class="screenlet">
    <div class="screenlet-title-bar">
       <h3>Find option</h3>
    </div>
    <div class="screenlet-body">
        <form id="FindProductionRunEvent" method="post" action="<@ofbizUrl>FindProductionRunFtl</@ofbizUrl>">
            <table width="100%">
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
                    <td width="20%">
                        <label for="statusId">${uiLabelMap.DemoOfbizStatus}</label>
                    </td>
                    <td width="2%">&nbsp;</td>
                    <td width="78%">
                    <#list statuses as statusItem>
                        <input type="checkbox" name="statusId" value="${statusItem.statusId!}"/>${statusItem.description!}
                    </#list>
                    </td>
                </tr>
                <tr>
                    <td width="20%">
                        <label class="control-label" for="productId">${uiLabelMap.DemoOfbizProductId}</label>
                    </td>
                    <td width="2%">&nbsp;</td>
                     <td width="78%"><@htmlTemplate.lookupField name="productId" id="productId" formName="FindProductionRunFtl" fieldFormName="LookupProduct"/></td>
                </tr>

                <tr>
                    <td width="20%">
                        <label for="productionRunName">${uiLabelMap.DemoOfbizProductionRunName}</label>
                    </td>
                    <td>&nbsp;</td>
                    <td>
                         <input type="text" name="productionRunName" id="productionRunName" value="${parameters.productionRunName!}"/>
                    </td>
                </tr>
                <tr width="20%">
                    <td>
                       <label  class="control-label"  for="facilityId">${uiLabelMap.DemoOfbizFacilityId}</label>
                     </td>
                     <td width="2%">&nbsp;</td>
                     <td width="78%">
                        <select name="facilityId" id="facilityId">
                            <#list facilities as facility>
                               <option value="${facility.facilityId!}">${facility.facilityName!}</option>
                            </#list>
                        </select>
                    </td>
               </tr>
                <tr>
                    <td height="2%">

                    </td>
                    <td>&nbsp;</td>
                    <td height="2%">
                        <input type="submit" value="Find"/>
                    </td>
                </tr>
            </table>
        </form>
   </div>
</div>
