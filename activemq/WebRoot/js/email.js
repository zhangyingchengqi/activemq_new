	function sendInfo(){
		$.ajax({
			type:"POST",
			data:$("#emailInfo").serialize(),
			dataType:"JSON",
			url:"sendEmail.action",
			success:function(data){
				if(data.code==200){
					//发送邮件成功
					alert("发送成功");
				}else if(data.code==500){
					//发送邮件失败
					alert(data.msg);
				}
				
			}
		});
	}
	//添加收信人
	function addReceiver(){
		$("#receiverTr").after('<tr id="receiverTr"><td><label for="receiver">收信人:</label></td><td><input onfocus="emailFlag(this)" onkeyup="emailTip(this)" onblur="hideEmailTip(this)" class="receiver" name="receiver" type="text"></td><td><span class="delete" onclick="deleteTr(this)">x</span></td></tr>');
	}
	//删除收信人
	function deleteTr(obj){
		$(obj).parent("td").parent("tr").remove();
	}
	//邮件提示显示
	function emailTip(obj){
		var revise=$(obj).height() +10;//获取组件的高度,微调
		var value=$(obj).val();
		$("#emailTip_163").text(value+"@163.com");
		$("#emailTip_qq").text(value+"@qq.com");
		$("#emailTip").css("display","block");
		$("#emailTip").css({ "left": $(obj).offset().left, "top": $(obj).offset().top+ revise})
	}
	//隐藏提示块
	function hideEmailTip(obj){
		$("#emailTip").css("display","none");
	}
	//选择邮件
	function selectEmail(obj){
		var emailInput=$("#emailInfo input[select='true']");
	    $(emailInput).val( $(obj).text() );
	}
	//标记现在被选中的Input
	function emailFlag(obj){
		$("#emailInfo input").removeAttr("select");
		$(obj).attr("select","true");
	}