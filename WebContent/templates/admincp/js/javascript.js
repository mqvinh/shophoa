//get img
function get_hinh(img, part) {
	if (img.files != null) {
		var filesname = img.files[0];
		var reader = new FileReader();
		reader.addEventListener("load", function() {
			$('.img').attr({
				'src' : reader.result
			});
		}, false);

		if (filesname) {
			reader.readAsDataURL(filesname);
		}
	} else {
		$('.img').attr({
			'src' : part
		});
	}

}
function get_hinh1(img, part) {
	if (img.files != null) {
		var filesname = img.files[0];
		var reader = new FileReader();
		reader.addEventListener("load", function() {
			$('.img').attr({
				'src' : reader.result
			});
		}, false);

		if (filesname) {
			reader.readAsDataURL(filesname);
		}
	} else {
		$('.img').attr({
			'src' : part
		});
	}

}

function delanh1(part) {
	$('.img').attr({
		'src' : part
	});
	document.getElementById("fileToUpload").value="";
}
//validate
//Hoa
$(document).ready(function() {
	$(".form_product").validate({
		ignore: [], 
		rules: {
			name: {
				required: true,
				chart_special: true
			},
			id_species: {
				required: true,
				min: 1
			},
			id_type: {
				required: true,
				min: 1
			},
			preview: {
				required: true,
			},
			price: {
				required: true,
				digits: true
			},
			number: {
				required: true,
				digits: true
			},
			detail:{ 
				required: function() 
				{
					CKEDITOR.instances.detail.updateElement(); 
				}, 
				minlength:1,
			}
	
		},
		messages: {
			name: {
				required: "Vui lòng nhập tên hoa",
			},
			id_species: {
				required: "Vui lòng chọn kiểu hoa",
				min: "Hãy chọn kiểu hoa"
			},
			id_type: {
				required: "Vui lòng chọn loại hoa",
				min: "Hãy chọn loại hoa"
			},
			preview: {
				required: "Vui lòng nhập mô tả",
			},
			price: {
				required: "Vui lòng nhập giá",
				digits: "Giá phải là số nguyên dương"
			},
			number: {
				required: "Vui lòng nhập số lượng",
				digits: "Số lượng phải là số nguyên dương"
			},
			detail:{ 
				required:"Vui lòng nhập vào khung này", 
				minlength:"Bạn không được để trống khung này",
			} 
		}
	});

});	

//species
$(document).ready(function() {
	$(".form_species").validate({
		rules: {
			name: {
				required: true,
				chart_special: true,
				checkSpecies: true
			}
		},
		messages: {
			name: {
				required: "Vui lòng nhập kiểu hoa",
			}
		}
	});
});	
//type
$(document).ready(function() {
	$(".form_type").validate({
		rules: {
			name: {
				required: true,
				chart_special: true,
				checkType: true
			}
		},
		messages: {
			name: {
				required: "Vui lòng nhập kiểu hoa",
			}
		}
	});
});	

//user
$(document).ready(function() {
	$(".form_user_add").validate({
		rules: {
			username: {
				required: true,
				chart_special: true,
				chart_space: true,
				checkUser: true
			},
			password:{
				required: true,
				minlength: 6,
			},
			rpassword:{
				equalTo: "#password",
			},
			role:{
				min: 1
			},
			fullname:{
				required: true,
			},
			address:{
				required: true,
			},
			email:{
				checkEmail: true,
				required: true,
				email: true,
			},
			phone:{
				required: true,
				digits: true,
			}
			
		},
		messages: {
			username: {
				required: "Vui lòng nhập username",
			},
			password:{
				required: "Hãy nhập password",
				minlength: "Tối thiểu 6 ký tự"
			},
			rpassword:{
				equalTo: "Phải trùng với password ở trên",
			},
			role:{
				min: "Hãy chọn quyền"
			},
			fullname:{
				required: "Hãy nhập fullname"
			},
			address:{
				required: "Hãy nhập địa chỉ"
			},
			email:{
				required: "Hãy nhập email",
				email: "Hãy nhập đúng email",
			},
			phone:{
				required: "Hãy nhập số điện thoại",
				digits: "Hãy nhập đúng số điện thoại",
			}
		}
	});
});	
$(document).ready(function() {
	$(".form_user_edit").validate({
		rules: {
			username: {
				required: true,
				chart_special: true,
				chart_space: true,
				checkUser: true
			},
			password:{
				minlength: 6,
			},
			rpassword:{
				equalTo: "#password",
			},
			role:{
				min: 1
			},
			fullname:{
				required: true,
			},
			address:{
				required: true,
			},
			email:{
				checkEmail: true,
				required: true,
				email: true,
			},
			phone:{
				required: true,
				digits: true,
			}
			
		},
		messages: {
			username: {
				required: "Vui lòng nhập username",
			},
			password:{
				minlength: "Tối thiểu 6 ký tự"
			},
			rpassword:{
				equalTo: "Phải trùng với password ở trên",
			},
			role:{
				min: "Hãy chọn quyền"
			},
			fullname:{
				required: "Hãy nhập fullname"
			},
			address:{
				required: "Hãy nhập địa chỉ"
			},
			email:{
				required: "Hãy nhập email",
				email: "Hãy nhập đúng email",
			},
			phone:{
				required: "Hãy nhập số điện thoại",
				digits: "Hãy nhập đúng số điện thoại",
			}
		}
	});
});	
//role
$(document).ready(function() {
	$(".form_role").validate({
		rules: {
			name: {
				required: true,
				chart_special: true,
				chart_space: true,
				checkRole: true
			}
		},
		messages: {
			name: {
				required: "Vui lòng nhập tên cấp bậc",
			}
		}
	});
});	
//contact
$(document).ready(function() {
	$(".form_contact").validate({
		rules: {
			fullname: {
				required: true,
			},
			preview:{
				required: true,
				minlength: 6,
			},
			email:{
				required: true,
				email: true,
			},
			phone:{
				required: true,
				digits: true,
			}
			
		},
		messages: {
			username: {
				required: "Vui lòng nhập fullname",
			},
			preview:{
				required: "Hãy nhập nội dung",
				minlength: "Tối thiểu 6 ký tự"
			},
			email:{
				required: "Hãy nhập email",
				email: "Hãy nhập đúng email",
			},
			phone:{
				required: "Hãy nhập số điện thoại",
				digits: "Hãy nhập đúng số điện thoại",
			}
		}
	});
});	
//user
$(document).ready(function() {
	$(".form_dangky").validate({
		rules: {
			username: {
				required: true,
				minlength: 6,
				chart_special: true,
				checkUser: true,
				chart_space: true
			},
			password:{
				required: true,
				minlength: 6,
			},
			rpassword:{
				equalTo: "#password",
			},
			fullname:{
				required: true,
			},
			address:{
				required: true,
			},
			email:{
				checkEmail: true,
				required: true,
				email: true,
			},
			phone:{
				required: true,
				digits: true,
			}
			
		},
		messages: {
			username: {
				required: "Vui lòng nhập username",
				minlength: "tên đăng nhập có ít nhất 6 ký tự",
			},
			password:{
				required: "Hãy nhập password",
				minlength: "Tối thiểu 6 ký tự"
			},
			rpassword:{
				equalTo: "Phải trùng với password ở trên",
			},
			fullname:{
				required: "Hãy nhập fullname",
			},
			address:{
				required: "Hãy nhập địa chỉ",
			},
			email:{
				required: "Hãy nhập email",
				email: "Hãy nhập đúng email",
			},
			phone:{
				required: "Hãy nhập số điện thoại",
				digits: "Hãy nhập đúng số điện thoại",
			}
		}
	});
});	
