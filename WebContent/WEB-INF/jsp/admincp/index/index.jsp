<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>

<div class="inner-block">
	<!--market updates updates-->
	<div class="market-updates">
		<div class="col-md-4 market-update-gd">
			<div class="market-update-block clr-block-1">
				<div class="col-md-8 market-update-left">
					<h3>${numUser}</h3>
					<h4>Số người dùng</h4>
					<p>Admin, mod, khách hàng</p>
				</div>
				<div class="col-md-4 market-update-right">
					<i class="fa fa-file-text-o"> </i>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="col-md-4 market-update-gd">
			<div class="market-update-block clr-block-2">
				<div class="col-md-8 market-update-left">
					<h3 id="count">0</h3>
					<h4>Người dung đang đăng nhập</h4>
				</div>
				<div class="col-md-4 market-update-right">
					<i class="fa fa-eye"> </i>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="col-md-4 market-update-gd">
			<div class="market-update-block clr-block-3">
				<div class="col-md-8 market-update-left">
					<h3>${sumContact}</h3>
					<h4>Liên hệ</h4>
					<p>Liên hệ của khách hàng</p>
				</div>
				<div class="col-md-4 market-update-right">
					<i class="fa fa-envelope-o"> </i>
				</div>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!--market updates end here-->
	<!--Thống kê đơn hàng-->
	<div class="chit-chat-layer1">
		<div class="col-md-12 chit-chat-layer1-left">
			<div class="work-progres" style="">
				<div class="chit-chat-heading">
					<h2 style="text-align: center;">Doanh thu</h2>
				</div>
				<div id="thongbao"></div>
				<hr width="100%" size="50px" align="center" color="green">
				<div class="form-group">
					<span style="font-size: 20px">Doanh thu từ:</span> <input
						id="date1"
						style="width: 145px; border-radius: 7px; border-style: hidden;"
						type="date" class="" id="name" name="name"> ---> <input
						id="date2"
						style="width: 145px; border-radius: 7px; border-style: hidden;"
						type="date" class="" id="name" name="name"> <a
						onclick="doanhthu()" href="javascript:void(0)"
						class="btn btn-default">Tra kết quả</a>
				</div>
				<div class="clearfix"></div>
				<div class="table-responsive" id="DThu2">
					<table class="table table-hover">
						<thead>
							<tr>
								<th>#</th>
								<th>Số liệu</th>
							</tr>
						</thead>
						<tbody id="DThu1">
							<tr>
								<td>1</td>
								<td>Tổng doanh thu: ${decimalformat.change(TDT)} VNĐ</td>
							</tr>
						</tbody>
					</table>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<script type="text/javascript">
		function doanhthu() {
			var adate1 = $('#date1').val();
			var adate2 = $('#date2').val();
			if (adate1 == "" || adate2 == "") {
				$('#thongbao')
						.html(
								'<div class="alert alert-danger alert-dismissable"'
						+'style="text-align: center;">'
										+ '<button aria-hidden="true" data-dismiss="alert" class="close"'
						+'	type="button">×</button>'
										+ 'Hãy điền đầy đủ thời gian để xem!!!'
										+ '</div>');
				return false;
			}
			if (adate1 > adate2) {
				$('#thongbao')
						.html(
								'<div class="alert alert-danger alert-dismissable"'
						+'style="text-align: center;">'
										+ '<button aria-hidden="true" data-dismiss="alert" class="close"'
						+'	type="button">×</button>'
										+ 'Thời gian đầu phải trước thòi gian sau!!!'
										+ '</div>');
				return false;
			}
			$
					.ajax({
						url : '${pageContext.request.contextPath}/admincp/thongKEx',
						type : 'POST',
						dataType : 'html',
						data : {
							date1 : adate1,
							date2 : adate2
						},
						beforeSend : function() {
							$('#DThu2')
									.html(
											'<img src="${defines.SHOPHOA_TEMPLATE_URL}/themes/images/loading.gif" alt="" />');
						},
						success : function(data) {
							$('#DThu2').html(data);
						}
					});
		}
	</script>
	<!--Thống kê nhóm người dùng-->
	<div class="chit-chat-layer1">
		<div class="col-md-12 chit-chat-layer1-left">
			<div class="work-progres" style="">
				<div class="chit-chat-heading">
					<h2 style="text-align: center;">Doanh thu nhóm người dùng</h2>
				</div>
				<hr width="100%" size="50px" align="center" color="green">
				<table class="table table-hover">
					<thead>
						<tr>
							<th>#</th>
							<th>Khách hàng</th>
							<th>Doanh thu</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td>#</td>
							<td>Tổng doanh thu</td>
							<td>${decimalformat.change(TDT)} VNĐ</td>
						</tr>
						<c:forEach items="${lItemRole}" var="ItemRole">
							<c:set value="0" var="test"></c:set>
							<c:forEach items="${lItemRoleNhom}" var="ItemRoleNhom">
								<c:if test="${ItemRoleNhom.name eq ItemRole.name}">
									<tr>
										<td>${ItemRoleNhom.role}</td>
										<td>${ItemRoleNhom.name}</td>
										<td>${decimalformat.change(ItemRoleNhom.doanhthu)} VNĐ</td>
									</tr>
									<c:set value="1" var="test"></c:set>
								</c:if>
							</c:forEach>
							<c:if test="${test==0}">
								<tr>
									<td>${ItemRole.role}</td>
									<td>${ItemRole.name}</td>
									<td>${ItemRole.doanhthu} VNĐ</td>
								</tr>
							</c:if>
						</c:forEach>
					</tbody>
				</table>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!--main page chit chating end here-->
	<!--Thống kê sản phẩm-->
	<div class="chit-chat-layer1">
		<div class="col-md-12 chit-chat-layer1-left">
			<div class="work-progres" style="">
				<div class="chit-chat-heading">
					<h2 style="text-align: center;">Sản phẩm</h2>
				</div>
				<hr width="100%" size="50px" align="center" color="green">
				<div class="clearfix"></div>
				<div class="prograc-blocks" id="DThu2">
					<!--Progress bars-->
					<div class="home-progres-main">
						<h3>Top 4 loại hoa bán chạy nhất</h3>
					</div>
					<div class='bar_group'>
						<div class='bar_group__bar thin' label='Tổng lần mua'
							show_values='true' tooltip='true' value='${sumBuy}'></div>
						<c:set value="0" var="ttop"></c:set>
						<c:forEach items="${lItemBuy}" var="ItemBuy">
							<div class='bar_group__bar thin' label='${ItemBuy.name}'
								show_values='true' tooltip='true' value='${ItemBuy.buy}'></div>
							<c:set value="${ttop+ItemBuy.buy}" var="ttop"></c:set>
						</c:forEach>
						<div class='bar_group__bar thin' label='Các hoa khác'
							show_values='true' tooltip='true' value='${sumBuy-ttop}'></div>
					</div>
					<script src="${defines.ADMIN_TEMPLATE_URL}/js/bars.js"></script>
				</div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!--main page chit chating end here-->
	<!--climate start here-->
	<div class="climate">
		<div class="col-md-4 climate-grids">
			<div class="climate-grid1">
				<div class="climate-gd1-top">
					<div class="col-md-6 climate-gd1top-left">
						<h4>Aprill 6-wed</h4>
						<h3>
							12:30<span class="timein-pms">PM</span>
						</h3>
						<p>Humidity:</p>
						<p>Sunset:</p>
						<p>Sunrise:</p>
					</div>
					<div class="col-md-6 climate-gd1top-right">
						<span class="clime-icon">
							<figure class="icons">
								<canvas id="partly-cloudy-day" width="64" height="64">
								</canvas>
							</figure> <script>
								var icons = new Skycons({
									"color" : "#fff"
								}), list = [ "clear-night",
										"partly-cloudy-day",
										"partly-cloudy-night", "cloudy",
										"rain", "sleet", "snow", "wind", "fog" ], i;

								for (i = list.length; i--;)
									icons.set(list[i], list[i]);

								icons.play();
							</script>
						</span>
						<p>88%</p>
						<p>5:40PM</p>
						<p>6:30AM</p>
					</div>
					<div class="clearfix"></div>
				</div>
				<div class="climate-gd1-bottom">
					<div class="col-md-4 cloudy1">
						<h4>Hongkong</h4>
						<figure class="icons">
							<canvas id="sleet" width="58" height="58">
							</canvas>
						</figure>
						<script>
							var icons = new Skycons({
								"color" : "#fff"
							}), list = [ "clear-night", "clear-day",
									"partly-cloudy-night", "cloudy", "rain",
									"sleet", "snow", "wind", "fog" ], i;

							for (i = list.length; i--;)
								icons.set(list[i], list[i]);

							icons.play();
						</script>
						<h3>10c</h3>
					</div>
					<div class="col-md-4 cloudy1">
						<h4>UK</h4>
						<figure class="icons">
							<canvas id="cloudy" width="58" height="58"></canvas>
						</figure>
						<script>
							var icons = new Skycons({
								"color" : "#fff"
							}), list = [ "clear-night", "cloudy",
									"partly-cloudy-night", "cloudy", "rain",
									"sleet", "snow", "wind", "fog" ], i;

							for (i = list.length; i--;)
								icons.set(list[i], list[i]);

							icons.play();
						</script>
						<h3>6c</h3>
					</div>
					<div class="col-md-4 cloudy1">
						<h4>USA</h4>
						<figure class="icons">
							<canvas id="snow" width="58" height="58">
							</canvas>
						</figure>
						<script>
							var icons = new Skycons({
								"color" : "#fff"
							}), list = [ "clear-night", "clear-day",
									"partly-cloudy-night", "cloudy", "rain",
									"sleet", "snow", "wind", "fog" ], i;

							for (i = list.length; i--;)
								icons.set(list[i], list[i]);

							icons.play();
						</script>
						<h3>10c</h3>
					</div>
					<div class="clearfix"></div>
				</div>
			</div>
		</div>


		<div class="clearfix"></div>
	</div>
	<!--climate end here-->
</div>