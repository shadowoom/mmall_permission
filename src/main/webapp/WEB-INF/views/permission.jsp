<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>权限管理</title>
    <jsp:include page="/common/backend_common.jsp"/>
    <jsp:include page="/common/page.jsp"/>
</head>
<body class="no-skin" youdao="bind" style="background: white">
<input id="gritter-light" checked="" type="checkbox" class="ace ace-switch ace-switch-5"/>

<div class="page-header">
    <h1>
        权限模块管理
        <small>
            <i class="ace-icon fa fa-angle-double-right"></i>
            维护权限模块和权限点关系
        </small>
    </h1>
</div>
<div class="main-content-inner">
    <div class="col-sm-3">
        <div class="table-header">
            权限模块列表&nbsp;&nbsp;
            <a class="green" href="#">
                <i class="ace-icon fa fa-plus-circle orange bigger-130 permissionModule-add"></i>
            </a>
        </div>
        <div id="permissionModuleList">
        </div>
    </div>
    <div class="col-sm-9">
        <div class="col-xs-12">
            <div class="table-header">
                权限点列表&nbsp;&nbsp;
                <a class="green" href="#">
                    <i class="ace-icon fa fa-plus-circle orange bigger-130 permission-add"></i>
                </a>
            </div>
            <div>
                <div id="dynamic-table_wrapper" class="dataTables_wrapper form-inline no-footer">
                    <div class="row">
                        <div class="col-xs-6">
                            <div class="dataTables_length" id="dynamic-table_length"><label>
                                展示
                                <select id="pageSize" name="dynamic-table_length" aria-controls="dynamic-table" class="form-control input-sm">
                                    <option value="10">10</option>
                                    <option value="25">25</option>
                                    <option value="50">50</option>
                                    <option value="100">100</option>
                                </select> 条记录 </label>
                            </div>
                        </div>
                    </div>
                    <table id="dynamic-table" class="table table-striped table-bordered table-hover dataTable no-footer" role="grid"
                           aria-describedby="dynamic-table_info" style="font-size:14px">
                        <thead>
                        <tr role="row">
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限名称
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                权限模块
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                类型
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                URL
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                状态
                            </th>
                            <th tabindex="0" aria-controls="dynamic-table" rowspan="1" colspan="1">
                                顺序
                            </th>
                            <th class="sorting_disabled" rowspan="1" colspan="1" aria-label=""></th>
                        </tr>
                        </thead>
                        <tbody id="permissionList"></tbody>
                    </table>
                    <div class="row" id="permissionPage">
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<div id="dialog-permissionModule-form" style="display: none;">
    <form id="permissionModuleForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">上级模块</label></td>
                <td>
                    <select id="parentId" name="parentId" data-placeholder="选择模块" style="width: 200px;"></select>
                    <input type="hidden" name="id" id="permissionModuleId"/>
                </td>
            </tr>
            <tr>
                <td><label for="permissionModuleName">名称</label></td>
                <td><input type="text" name="permissionModuleName" id="permissionModuleName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="permissionModuleSeq">顺序</label></td>
                <td><input type="text" name="permissionModuleSeq" id="permissionModuleSeq" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="permissionModuleStatus">状态</label></td>
                <td>
                    <select id="permissionModuleStatus" name="permissionModuleStatus" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                        <option value="2">删除</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="permissionModuleRemark">备注</label></td>
                <td><textarea name="remark" id="permissionModuleRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>
<div id="dialog-permission-form" style="display: none;">
    <form id="permissionForm">
        <table class="table table-striped table-bordered table-hover dataTable no-footer" role="grid">
            <tr>
                <td style="width: 80px;"><label for="parentId">所属权限模块</label></td>
                <td>
                    <select id="permissionModuleSelectId" name="permissionModuleId" data-placeholder="选择权限模块" style="width: 200px;"></select>
                </td>
            </tr>
            <tr>
                <td><label for="permissionName">名称</label></td>
                <input type="hidden" name="id" id="permissionId"/>
                <td><input type="text" name="permissionName" id="permissionName" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="permissionType">类型</label></td>
                <td>
                    <select id="permissionType" name="permissionType" data-placeholder="类型" style="width: 150px;">
                        <option value="1">菜单</option>
                        <option value="2">按钮</option>
                        <option value="3">其他</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="permissionUrl">URL</label></td>
                <td><input type="text" name="permissionUrl" id="permissionUrl" value="1" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="permissionStatus">状态</label></td>
                <td>
                    <select id="permissionStatus" name="permissionStatus" data-placeholder="选择状态" style="width: 150px;">
                        <option value="1">有效</option>
                        <option value="0">无效</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="permissionSeq">顺序</label></td>
                <td><input type="text" name="permissionSeq" id="permissionSeq" value="" class="text ui-widget-content ui-corner-all"></td>
            </tr>
            <tr>
                <td><label for="permissionRemark">备注</label></td>
                <td><textarea name="remark" id="permissionRemark" class="text ui-widget-content ui-corner-all" rows="3" cols="25"></textarea></td>
            </tr>
        </table>
    </form>
</div>

<script id="permissionModuleListTemplate" type="x-tmpl-mustache">
<ol class="dd-list ">
    {{#permissionModuleList}}
        <li class="dd-item dd2-item permissionModule-name {{displayClass}}" id="permissionModule_{{id}}" href="javascript:void(0)" data-id="{{id}}">
            <div class="dd2-content" style="cursor:pointer;">
            {{permissionModuleName}}
            &nbsp;
            <a class="green {{#showDownAngle}}{{/showDownAngle}}" href="#" data-id="{{id}}" >
                <i class="ace-icon fa fa-angle-double-down bigger-120 sub-permissionModule"></i>
            </a>
            <span style="float:right;">
                <a class="green permissionModule-edit" href="#" data-id="{{id}}" >
                    <i class="ace-icon fa fa-pencil bigger-100"></i>
                </a>
                &nbsp;
                <a class="red permissionModule-delete" href="#" data-id="{{id}}" data-name="{{permissionModuleName}}">
                    <i class="ace-icon fa fa-trash-o bigger-100"></i>
                </a>
            </span>
            </div>
        </li>
    {{/permissionModuleList}}
</ol>
</script>

<script id="permissionListTemplate" type="x-tmpl-mustache">
{{#permissionList}}
<tr role="row" class="permission-name odd" data-id="{{id}}"><!--even -->
    <td><a href="#" class="permission-edit" data-id="{{id}}">{{permissionName}}</a></td>
    <td>{{showPermissionModuleName}}</td>
    <td>{{showPermissionType}}</td>
    <td>{{permissionUrl}}</td>
    <td>{{#bold}}{{showPermissionStatus}}{{/bold}}</td>
    <td>{{permissionSeq}}</td>
    <td>
        <div class="hidden-sm hidden-xs action-buttons">
            <a class="green permission-edit" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-pencil bigger-100"></i>
            </a>
            <a class="red permission-role" href="#" data-id="{{id}}">
                <i class="ace-icon fa fa-flag bigger-100"></i>
            </a>
        </div>
    </td>
</tr>
{{/permissionList}}
</script>

<script type="application/javascript">
    $(function () {
        var permissionModuleList; // 存储树形权限模块列表
        var permissionModuleMap = {}; // 存储map格式的权限模块信息
        var permissionMap = {}; // 存储map格式的权限信息
        var optionStr = "";
        var lastClickPermissionModuleId = -1;

        var permissionModuleListTemplate = $('#permissionModuleListTemplate').html();
        Mustache.parse(permissionModuleListTemplate);
        var permissionListTemplate = $('#permissionListTemplate').html();
        Mustache.parse(permissionListTemplate);

        loadPermissionModuleTree(); // load permission module tree

        function loadPermissionModuleTree() {
            $.ajax({
                url: "/sys/permissionModule/tree.json",
                success : function (result) {
                    if (result.ret) {
                        permissionModuleList = result.data;
                        var rendered = Mustache.render(permissionModuleListTemplate, {permissionModuleList: result.data});
                        $("#permissionModuleList").html(rendered);
                        recursiveRenderDept(result.data);
                        bindDeptClick();
                    } else {
                        showMessage("加载权限模块列表", result.msg, false);
                    }
                }
            })
        }
    })

</script>

</body>
</html>
