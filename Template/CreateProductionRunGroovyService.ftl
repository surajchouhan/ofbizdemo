<div class="screenlet">
    <div class="screenlet-title-bar">
       <h3>Create Production Run from Groovy Service</h3>
    </div>
       <div ="screenlet-body">
            <form id="CreateProductionRunGroovyService" name="CreateProductionRunGroovyService" method="post" action="<@ofbizUrl>createProductionRunGroovyService</@ofbizUrl>">
                <table width="100%">
                    <tr>
                       <td width="20%">
                          <label class="control-label" for="productId">${uiLabelMap.DemoOfbizProductId}</label>
                       </td>
                       <td width="2%">&nbsp;</td>
                        <td width="78%"><@htmlTemplate.lookupField name="productId" id="productId" formName="createProductionRunGroovyService" value="${parameters.productId!}" fieldFormName="LookupProduct"/></td>
                    </tr>
                    <tr>
                        <td width="20%">
                             <label class="control-label"for="quantity">${uiLabelMap.DemoOfbizQuantity}</label>
                        </td>
                        <td width="2%">&nbsp;</td>

                        <td width="78%">
                           <input type="text" name="quantity" id="quantity" value="${parameters.quantity!}"  class='required' maxLength="100"/>*
                       </td>
                    </tr>
                    <tr>
                        <td width="20%">
                            <label class="control-label"  for="startDate">${uiLabelMap.DemoOfbizStartDate}</label>
                        </td>
                         <td width="2%">&nbsp;</td>
                        <td width="78%">
                            <@htmlTemplate.renderDateTimeField name="startDate" event="" action="" className="" alert="" title="" value="${parameters.startDate!}" size="20" maxlength="50" id="startDate" dateType="timestamp" shortDateInput=false timeDropdownParamName="" defaultDateTimeString="" localizedIconTitle="" timeDropdown="" timeHourName="" classString="" hour1="" hour2="" timeMinutesName="" minutes="" isTwelveHour="" ampmName="" amSelected="" pmSelected="" compositeType="" formName=""/>
                        </td>
                    </tr>
                     <tr>
                         <td width="20%">
                             <label  class="control-label" for="facilityId">${uiLabelMap.DemoOfbizFacilityId}</label>
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
                       <td width="20%">
                            <label class="control-label"  for="routingId">${uiLabelMap.DemoOfbizRoutingId}</label>
                       </td>
                        <td width="2%">&nbsp;</td>
                         <td width="78%"><@htmlTemplate.lookupField name="routingId" id="routingId" formName="CreateProductionRunJavaService" fieldFormName="LookupRouting"/></td>
                    </tr>
                    <tr>
                       <td width="20%">
                            <label class="control-label"  for="workEffortName">${uiLabelMap.DemoOfbizProductionRunName}</label>
                       </td>
                       <td width="2%">&nbsp;</td>
                       <td width="78%">
                          <input type="text" name="workEffortName" id="workEffortName" value="${parameters.workEffortName!}"  class='required' maxLength="100"/>
                       </td>
                    </tr>
                    <tr>
                       <td width="20%">
                           <label class="control-label" for="description">${uiLabelMap.DemoOfbizDescription}</label>
                       </td>
                       <td width="2%">&nbsp;</td>
                       <td width="78%">
                            <input type="text" name="description" id="description" value="${parameters.description!}"/>
                       </td>
                    </tr>
                    <tr>
                        <td width="20%">
                           <label class="control-label" for="createDependentProductionRun">${uiLabelMap.DemoOfbizCreateDependentProductionRun}</label>
                        </td>
                         <td width="2%">&nbsp;</td>
                         <td width="78%">
                            <select name="createDependentProductionRun" id="createDependentProductionRun">
                                <option value="Yes">Yes</option>
                                <option value="No">No</option>
                        </td>
                    </tr>
                    <tr>
                        <td width="20%">
                        </td>
                        <td width="2%">&nbsp;</td>
                        <td width="78%">
                            <input type="submit" value="Submit"/>
                        </td>
                    </tr>

                </table>
            </form>
       </div>
</div>
