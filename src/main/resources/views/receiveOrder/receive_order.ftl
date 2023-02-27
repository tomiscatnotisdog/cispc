<!DOCTYPE html>
<html>
	<head>
		<title>我接收的</title>
		<#include "../common.ftl">
	</head>
	<body class="childrenBody">
		<form class="layui-form" >
			<blockquote class="layui-elem-quote quoteBox">
				<form class="layui-form">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" name="receiveMan"
								   class="layui-input
							searchVal" placeholder="接单人" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="createManLocation" class="layui-input
							searchVal" placeholder="目的地" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="receiveManPhone" class="layui-input
							searchVal" placeholder="接单人联系电话" />
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




			<table id="receiveOrderList" class="layui-table"  lay-filter="receiveOrder">
			</table>



			<script type="text/html" id="toolbarDemo">
				<div class="layui-btn-container">
					<#--	<#if permissions?seq_contains("101002")>-->
					<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
						<i class="layui-icon">&#xe608;</i>
						发起跑腿
					</a>
					<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
						<i class="layui-icon">&#xe608;</i>
						撤销跑腿订单
					</a>

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
				<a href="javascript:;"  class="layui-btn layui-btn-warm layui-btn-xs"  lay-event="update">更新</a>
				<#--				{{# } else { }}-->
				<a href="javascript:;" class="layui-btn layui-btn-normal layui-btn-xs"  lay-event="delete">撤销</a>
				<#--				{{# } }}-->
			</script>
		</form>
	<script type="text/javascript" src="${ctx}/js/receiveOrder/receive.order.js"></script>
	</body>
</html>