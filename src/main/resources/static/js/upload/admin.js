var P2 = window.P2 || {};
function ajaxFileUpload(url, file_id, callback,appData) {
	jQuery.ajaxFileUpload({
		url : url,
		data:appData,
		secureuri : false, // 是否需要安全协议，一般设置为false
		fileElementId : file_id, // 文件上传域的ID
		dataType : 'json', // 返回值类型 一般设置为json
		success : callback,
		error : function(data, status, e) {
			alert(e);
		}
	});
}

P2.controller = {
	_gid : null,
	_gname : null,
	_url : path + "/rest/model",
	showPanel : function(id, name) {
		this._gid = id;
		this._gname = name;
		$('#scenePanel').removeClass("hide");
		$('#scenePanel input').attr("placeholder", name);
		$('#scenePanel').modal({
			backdrop : false
		});
	},
	deleteScene : function(id) {
		P2.remote.get(this._url + "/deleteScene/" + id, function(back) {
			if (back.state) {
				window.location.reload();
			} else {
				alert(back.content);
			}
		});
	},
	uploadScene : function(callback,appData) {
		ajaxFileUpload(this._url + "/uploadSceneFile", "file", callback,appData);
	},
	confirm : function(id, callback) {
		P2.remote.get(this._url + "/updateScene/" + id, callback);
	},
	updateScene : function() {
		ajaxFileUpload(this._url + "/updateScene?id=" + this._gid + "&name="
				+ this._gname, "scenefile", function(back) {
			if (back.state) {
			}
		});
	},
	updateThumbnail : function() {
		ajaxFileUpload(this._url + "/uploadThumbnail?id=" + this._gid
				+ "&name=" + this._gname, "thumbnailfile", function(back) {
			if (back.state) {
			}
		});
	},
	updateConfig : function() {
		ajaxFileUpload(this._url + "/uploadConfig/" + this._gid, "configfile",
				function(back) {
					if (back.state) {
					}
				});
	},
	exportInfo : function(id) {
		window.open(this._url + "/exportEditorInfo/" + id);
	}
};
P2.remote = {
	get : function(url, callback) {
		$.ajax({
			type : 'GET',
			async : callback ? true : false,
			url : url,
			contentType : 'application/json',
			success : callback ? callback : function(back) {
				ret = back;
			},
			dataType : "json",
			error : function(req, status, ex) {
			},
			timeout : 60000
		});
	}
};

var shiyong = function(id) {
	P2.remote.get(path + "/rest/model/useScene?modelid=" + id, function(back) {
		if (back.state) {
			window.location.href = path + "/show.html?id=" + id;
		} else {
			alert(back.content);
		}
	});
};
