<form >
       <div class="right">
                   <!--上传按钮-->
                 <a href="javascript:;">
                        <!-- 图片承载容器 -->
                        <label id="container" for="upload" style="display: inline-block; width: 132px; height: 74px;">
                                 <img  th:if="${id} == null" th:src="@{/javaex/pc/images/default.png}" width="100%" height="100%" />
                                 <img th:if="${id} != null"  th:src="@{${articleInfo.cover}}" width="100%" height="100%" />
                            </label>
                        <input type="file" class="hide" id="upload" accept="image/gif, image/jpeg, image/jpg, image/png" />
                     </a>
                 <input type="hidden" id="cover" name="cover" value="" />
            </div>
</form>   

javaex.upload({
        type : "image",
        url : "upload", // 请求路径
        id : "upload", // <input type="file" />的id
        param : "file", // 参数名称，SSM中与MultipartFile的参数名保持一致
        dataType : "url", // 返回的数据类型：base64 或 url
        callback : function(rtn) {
            // 后台返回的数据
            if (rtn.code == "000000") {
                var imgUrl = rtn.data.imgUrl;
                $("#container img").attr("src", imgUrl);
                $("#cover").val(imgUrl);
            } else {
                javaex.optTip({
                    content : rtn.msg,
                    type : "error"
                });
            }
        }
    });    