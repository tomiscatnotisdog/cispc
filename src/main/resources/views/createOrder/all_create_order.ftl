<!DOCTYPE html>
<html>
	<head>
		<title>我发起的</title>
		<#include "../common.ftl">
	</head>
	<body class="childrenBody">
		<form class="layui-form" >
			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" name="createMan"
								   class="layui-input
							searchVal" placeholder="发起人" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="createManLocation" class="layui-input
							searchVal" placeholder="目的地" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="createManPhone" class="layui-input
							searchVal" placeholder="手机号" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="stUserNumber" class="layui-input
							searchVal" placeholder="学工号" />
						</div>
						<div class="layui-input-inline">
							<select name="isComplete"  id="isComplete"  >
								<option value="" >请选择订单状态</option>
								<option value="0">已撤销</option>
								<option value="1" >已发起</option>
								<option value="2" >接单中</option>
								<option value="3" >完成</option>
							</select>
						</div>
						<a class="layui-btn search_btn" data-type="reload">
							<i class="layui-icon">&#xe615;</i> 搜索
						</a>
					</div>
				</form>
			</blockquote>

			<table id="allOrderList" class="layui-table"  lay-filter="allOrder">
			</table>



			<script type="text/html" id="toolbarDemo">
				<div class="layui-btn-container">
					<#--	<#if permissions?seq_contains("101002")>-->
					<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
						<i class="layui-icon">&#xe608;</i>
						发起跑腿
					</a>
<#--					<a class="layui-btn layui-btn-normal addNews_btn" lay-event="receive">-->
<#--						<i class="layui-icon">&#xe608;</i>-->
<#--						批量接单-->
<#--					</a>-->

				</div>
			</script>
					<!--
                      行工具栏
                          详情:机会数据已开发结束,点击详情展示计划项相关数据
                          开发:机会数据处于开发中,点击开发添加计划项数据
                          此时链接内容显示由开发结果值控制
                    -->
			<script id="op" type="text/html" >
<#--				{{# if (d.devResult=== 0 || d.devResult==1) { }}-->
					<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="receive">接单</a>
<#--				{{# } else { }}-->
<#--					<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="info">撤销</a>-->
<#--				{{# } }}-->
			</script>
		
		</form>
	<script type="text/javascript" src="${ctx}/js/createOrder/all.create.order.js"></script>
	</body>
</html>