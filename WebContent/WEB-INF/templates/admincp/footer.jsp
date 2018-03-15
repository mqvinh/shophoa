<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/templates/taglib.jsp"%>
<script type="text/javascript">
var count11=1;
function getCount() {
	if(count==1){
		return false;
	}else{
	$.ajax({
		url : '${pageContext.request.contextPath}/admincp/getCount',
		type: 'POST', 
		dataType: 'html', 
		data: {},
		success: function(data) {
			$('#count').html(data);
		}
	});}
}
setInterval(function(){
	getCount();
	count11=count11+1;
}, 1000);
</script>
<div class="copyrights">
	 <p>© 2016 Shoppy. All Rights Reserved | Design by  <a href="http://w3layouts.com/" target="_blank">W3layouts</a> </p>
</div>	