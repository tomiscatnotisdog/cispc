<!DOCTYPE html>
<html>
<head>
	<title>营销机会管理</title>
	<#include "../common.ftl">
</head>
<body class="childrenBody">

<form class="layui-form" >
	<blockquote class="layui-elem-quote quoteBox">


				<form class="layui-form">
					<div class="layui-inline">
						<div class="layui-input-inline">
							<input type="text" name="student"
								   class="layui-input
							searchVal" placeholder="学号" />
						</div>
						<div class="layui-input-inline">
							<input type="text" name="studentname" class="layui-input
							searchVal" placeholder="姓名" />
						</div>


						<div class="layui-input-inline">
							<input type="text" name="devresult" class="layui-input
							searchVal" placeholder="请假状态" />
						</div>


						<a class="layui-btn search_btn" data-type="reload"><i
									class="layui-icon">&#xe615;</i> 搜索</a>
					</div>
				</form>


	</blockquote>
	<table id="saleChanceList" class="layui-table"  lay-filter="saleChances"></table>



		<script type="text/html" id="toolbarDemo">


				<div class="layui-btn-container">

						<a class="layui-btn layui-btn-normal addNews_btn" lay-event="add">
							<i class="layui-icon">&#xe605;</i>
							添加学生假条
						</a>


						<a class="layui-btn layui-btn-normal delNews_btn" lay-event="del">
							<i class="layui-icon">&#xe600;</i>
							删除学生假条
						</a>

				</div>

		</script>



	<!--操作-->
	<script id="saleChanceListBar" type="text/html">
		<a class="layui-btn layui-btn-xs" id="edit" lay-event="edit">编辑</a>

				<a class="layui-btn layui-btn-xs layui-btn-danger" lay-event="del">删除</a>

	</script>

</form>
<script type="text/javascript">
	layui.use(['table','layer'],function(){
		var layer = parent.layer === undefined ? layui.layer : top.layer,
				$ = layui.jquery,
				table = layui.table;
		//营销机会列表展示
		var  tableIns = table.render({
			elem: '#saleChanceList',
			url : ctx+'/xuesheng/lisst',
			cellMinWidth : 95,
			page : true,
			height : "full-125",
			limits : [10,15,20,25],
			limit : 10,
			toolbar: "#toolbarDemo",
			id : "saleChanceTable",
			cols : [[
				{type: "checkbox", fixed:"center"},
				{field: "id", title:'ID',fixed:"true"},
				{field: 'studentname', title: '姓名',align:"center"},
				{field: 'student', title: '学号',  align:'center'},
				{field: 'department', title: '系部', align:'center'},
				{field: 'specialized', title: '专业', align:'center'},
				{field: 'clasnam', title: '班级',  align:'center'},
				{field: 'approver', title: '审核人', align:'center'},
				{field: 'reason', title: '请假理由', align:'center'},
				{field: 'devresult', title: '状况', align:'center' },


				{title: '操作', templet:'#saleChanceListBar',fixed:"right",align:"center", minWidth:150}
			]]
		});


		// 多条件搜索
		$(".search_btn").on("click", function () {
			table.reload("saleChanceTable", {
				page: {
					curr: 1
				},
				where: {
					student: $("input[name='student']").val(),
					studentname: $("input[name='studentname']").val(),
					devresult: $("input[name='devresult']").val()
				}
			})
		});


		// 头工具栏事件
		table.on('toolbar(saleChances)',function (obj) {
			var checkStatus = table.checkStatus(obj.config.id)
			switch (obj.event) {
				case "add":
					openAddOrUpdateSaleChanceDialog();
					break;
				case "del":
					delSaleChance(checkStatus.data);
					break;
			}
		});

		//行工具条事件
		table.on('tool(saleChances)', function (obj) { //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
			var data = obj.data; //获得当前行数据
			var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
			var tr = obj.tr; //获得当前行 tr 的 DOM 对象（如果有的话）

			if (layEvent === 'edit') { //编辑
				openAddOrUpdateSaleChanceDialog(data.id);
			} else if (layEvent === 'del') { //删除
				layer.confirm('真的删除行么', function (index) {

					layer.close(index);
					//向服务端发送删除指令
					$.ajax({
						type: "post",
						url: ctx + "/xuesheng/delete",
						data: {"id": data.id}, // 传递的参数是数组 ids=1&ids=2&ids=3
						success: function (result) {
							// 判断删除结果
							if (result.code == 200) {
								// 提示成功
								layer.msg("删除成功！", {icon: 6});
								// 刷新表格
								tableIns.reload();
							} else {
								// 提示失败
								layer.msg(result.msg, {icon: 5});
							}
						}
					});
				});
			}
		});



		/**
		 * 打开添加或更新对话框
		 */
		function openAddOrUpdateSaleChanceDialog(id) {
			var title="<h1>请假系统</h1>";
			var url=ctx+"/xuesheng/studentxuesheng";

			// 判断id是否为空；如果为空，则为添加操作，否则是修改操作
			if (id != null && id != '') {
				title = "<h3>请假</h3>";
				url= url+"?id=" + id; // 传递主键，查询数据使用
			}
			layui.layer.open({
				title:title,
				type:2,
				area:["700px","500px"],
				maxmin:true,
				content:url
			})
		}




		//删除 多条件删除
		function delSaleChance(datas) {
			// 判断是否选择了要删除的记录
			if (datas.length == 0) {
				layer.msg("请选择要删除的班级", {icon: 5});
				return;
			}

			//删除
			layer.confirm("你确定删除吗", {
				btn: ["确定", "取消"]

			}, function (index) {
				//关闭
				layer.close(index)
				//收集数据
				var id = [];
				for (var x in datas) {
					id.push(datas[x].id);
				}
				// 发送ajax请求，执行删除用户
				$.ajax({
					type: "post",
					url: ctx + "/xuesheng/delete",
					data: {"id": id.toString()}, // 传递的参数是数组 ids=1&ids=2&ids=3
					success: function (result) {
						// 判断删除结果
						if (result.code == 200) {
							// 提示成功
							layer.msg("删除成功！", {icon: 6});
							// 刷新表格
							tableIns.reload();
						} else {
							// 提示失败
							layer.msg(result.msg, {icon: 5});
						}
					}
				});
			});
		};

	});

</script>

</body>
</html>