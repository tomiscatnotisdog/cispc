<!DOCTYPE html>
<html>
<head>
	<title>客户开发计划管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">
	<div class="layui-col-md12">
		<div class="layui-card">
			<div class="layui-card-body">
				<form class="layui-form" >
					<input name="id" type="hidden" value="${(checkinfo.id)!}"/>
					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">创建人</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="createMan" id="createMan"  value="${(checkinfo.createMan)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">最大人数</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="maxNumbers" id="maxNumbers" value="${(checkinfo.maxNumbers)!}" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">活动地点</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="place"  id="place" lay-verify="required"  value="${(checkinfo.place)!}" readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">活动类型</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   lay-verify="phone" name="type" value="${(checkinfo.type)!}" id="phone" readonly="readonly">
							</div>
						</div>
					</div>

					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">创建日期</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="createDate" value="${(checkinfo.createDate?string('yyyy-MM-dd HH:mm:ss'))!}" id="createDate"  readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">是否完成</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="isComplate" value="${(checkinfo.isComplate)!}"  readonly="readonly">
							</div>
						</div>
					</div>


					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">创建日期</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="createDate"  value="${(checkinfo.createDate?string('yyyy-MM-dd HH:mm:ss'))!}" id="createDate"  readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">结束日期</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="complateDate" id="complateDate" value="${(checkinfo.complateDate?string('yyyy-MM-dd HH:mm:ss'))!}"  readonly="readonly">
							</div>
						</div>
					</div>


					<div class="layui-form-item layui-row">
						<div class="layui-col-xs6">
							<label class="layui-form-label">创建人手机</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="createManPhone" value="${(checkinfo.createManPhone)!}" id="createManPhone"  readonly="readonly">
							</div>
						</div>
						<div class="layui-col-xs6">
							<label class="layui-form-label">活动名称</label>
							<div class="layui-input-block">
								<input type="text" class="layui-input"
									   name="activityName" value="${(checkinfo.activityName)!}"  readonly="readonly">
							</div>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>

	<div class="layui-col-md12">
		<#--<h2 align="center">参加人以及信息</h2>-->
		<table id="joininfoList" class="layui-table"  lay-filter="joininfo"></table>
	</div>


		<script type="text/html" id="toolbarDemo">
			<div class="layui-btn-container">

				<#--<p> class="layui-p layui-btn-normal " >

					参加人 以及信息
				</p>--><#--
				<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
					<i class="layui-icon">&#xe608;</i>
					参加人 以及信息
				</a>
				<a class="layui-btn layui-btn-normal addNews_btn" lay-event="success">
					<i class="layui-icon">&#xe608;</i>
					开发成功
				</a>
				<a class="layui-btn layui-btn-normal addNews_btn" lay-event="failed">
					<i class="layui-icon">&#xe608;</i>
					开发失败
				</a>-->

			</div>
		</script>

		<!--操作-->
	<#--	<script id="cusDevPlanListBar" type="text/html">
			<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>
			<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>
		</script>-->


	<script type="text/javascript" src="${ctx}/js/createupdate/data.js"></script>
</body>
</html>