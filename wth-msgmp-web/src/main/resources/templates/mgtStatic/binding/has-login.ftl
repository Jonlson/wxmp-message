<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="${springUrl}css/Style.css">
    <script type="text/javascript" src="${springUrl}js/jquery.min.js" charset="utf-8"></script>
    <title>登录</title>
</head>

<body>
<section class="page-box">
    <div class="title-text" style="margin-bottom: 0;">商户管理后台</div>
    <div class="hint">即将登录商户管理后台，请确认是本人操作</div>
    <input id="sceneId" style="display: none;">
    <input id="phone" style="display: none;">
    <button class="btn login" type="button" id="login-btn">确认登录</button>
</section>
<script>
    window.onload = function () {
        document.getElementById('sceneId').value = '${WechatMpUser.sceneId}'
        document.getElementById('phone').value = '${WechatMpUser.phone}'
        var btn = document.getElementById('login-btn')
        // 登录按钮添加点击事件
        btn.addEventListener('click', function () {
            $.ajax({
                type: 'post',
                url: '${springUrl}_wx/wx_login',
                data: {
                    sceneId: document.getElementById('sceneId').value,
                    phone: document.getElementById('phone').value
                },
                cache: false,
                dataType: "json",
                success: function (result) {
                    if (result.code === 0) {
                        window.location.href = '${springUrl}_wx/login_success'
                    }
                }
            });
        }, false)
    }
</script>
</body>

</html>