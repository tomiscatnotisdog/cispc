<!DOCTYPE html>
<html>
<head>
	<title>客户开发计划管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">
		<form class="layui-form">
			<div class="layui-inline">
				<div class="layui-input-inline">
					<input type="text" name="activityName"
						   class="layui-input
					searchVal" placeholder="活动名称" />
				</div>
				<div class="layui-input-inline">
					<input type="text" name="type" class="layui-input
					searchVal" placeholder="活动类型" />
				</div>
				<#--<div class="layui-input-inline">
                    <select name="devResult"  id="devResult"  >
                        <option value="" >请选择</option>
                        <option value="0">未开发</option>
                        <option value="1" >开发中</option>
						<option value="2" >开发成功</option>
						<option value="3" >开发失败</option>
                    </select>
				</div>-->
				<a class="layui-btn search_btn" data-type="reload"><i
							class="layui-icon">&#xe615;</i> 搜索</a>
			</div>
		</form>
	</blockquote>
	<table id="saleChanceList" class="layui-table"  lay-filter="myfrend"></table>


	<script type="text/html" id="toolbarDemo">
	</script>


	<!--操作-->
	<script id="op" type="text/html" >

			<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="disable">取消</a>
			<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="update">修改</a>
			<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="check">查看</a>
		<#--	<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="dev">开发</a>
			<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="info">详情</a>-->

	</script>

</form>
<script type="text/javascript" src="${ctx}/js/myFrend/my.Frend.js"></script>

</body>
</html>