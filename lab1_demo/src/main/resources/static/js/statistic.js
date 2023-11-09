/**
 * 
 */
$(document).ready(
		function() {
			var title = {
				text : '月度销量榜'
			};
			var subtitle = {
				text : '2017年度总结'
			};
			var xAxis = {
				categories : [ '一月', '二月', '三月', '四月', '五月', '六月', '七月', '八月',
						'九月', '十月', '十一月', '十二月' ]
			};
			var yAxis = {
				title : {
					text : '销量 (百本)'
				},
				plotLines : [ {
					value : 0,
					width : 1,
					color : '#808080'
				} ]
			};

			var tooltip = {
				valueSuffix : '百本'
			}

			var legend = {
				layout : 'vertical',
				align : 'right',
				verticalAlign : 'middle',
				borderWidth : 0
			};

			var series = [
					{
						name : '小说类',
						data : [ 7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5,
								23.3, 18.3, 13.9, 9.6 ]
					},
					{
						name : '科普类',
						data : [ -0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1,
								20.1, 14.1, 8.6, 2.5 ]
					},
					{
						name : '儿童教育类',
						data : [ -0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9,
								14.3, 9.0, 3.9, 1.0 ]
					},
					{
						name : '工具书类',
						data : [ 3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6,
								14.2, 10.3, 6.6, 4.8 ]
					} ];

			var json = {};

			json.title = title;
			json.subtitle = subtitle;
			json.xAxis = xAxis;
			json.yAxis = yAxis;
			json.tooltip = tooltip;
			json.legend = legend;
			json.series = series;

			$('#container1').highcharts(json);
		});

$(document)
		.ready(
				function() {
					var chart = {
						type : 'column'
					};
					var title = {
						text : '2017年度总结'
					};
					var subtitle = {
						text : '2017年度各类图书销售情况'
					};
					var xAxis = {
						categories : [ 'Jan', 'Feb', 'Mar', 'Apr', 'May',
								'Jun', 'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec' ],
						crosshair : true
					};
					var yAxis = {
						min : 0,
						title : {
							text : '销售量 (百本)'
						}
					};
					var tooltip = {
						headerFormat : '<span style="font-size:10px">{point.key}</span><table>',
						pointFormat : '<tr><td style="color:{series.color};padding:0">{series.name}: </td>'
								+ '<td style="padding:0"><b>{point.y:.1f} 百本</b></td></tr>',
						footerFormat : '</table>',
						shared : true,
						useHTML : true
					};
					var plotOptions = {
						column : {
							pointPadding : 0.2,
							borderWidth : 0
						}
					};
					var credits = {
						enabled : false
					};

					var series = [
							{
								name : '小说类',
								data : [ 49.9, 71.5, 106.4, 129.2, 144.0,
										176.0, 135.6, 148.5, 216.4, 194.1,
										95.6, 54.4 ]
							},
							{
								name : '科普类',
								data : [ 83.6, 78.8, 98.5, 93.4, 106.0, 84.5,
										105.0, 104.3, 91.2, 83.5, 106.6, 92.3 ]
							},
							{
								name : '儿童教育类',
								data : [ 48.9, 38.8, 39.3, 41.4, 47.0, 48.3,
										59.0, 59.6, 52.4, 65.2, 59.3, 51.2 ]
							},
							{
								name : '工具书类',
								data : [ 42.4, 33.2, 34.5, 39.7, 52.6, 75.5,
										57.4, 60.4, 47.6, 39.1, 46.8, 51.1 ]
							} ];

					var json = {};
					json.chart = chart;
					json.title = title;
					json.subtitle = subtitle;
					json.tooltip = tooltip;
					json.xAxis = xAxis;
					json.yAxis = yAxis;
					json.series = series;
					json.plotOptions = plotOptions;
					json.credits = credits;
					$('#container2').highcharts(json);

				});