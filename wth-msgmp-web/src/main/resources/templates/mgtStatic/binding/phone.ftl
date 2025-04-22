<!DOCTYPE html>
<html lang="en">

<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${springUrl}css/Tyle.css">
  <script type="text/javascript" src="${springUrl}js/jquery.min.js" charset="utf-8"></script>
  <title>绑定手机号</title>
</head>

<body>
  <form class="form">
    <section class="title">
      <p class="h1-title">欢迎登录24H智能售货公众号</p>
      <span class="sub-title">为保证确定是本人操作，请进行手机验证码校验</span>
    </section>
    <section class="code-box">
      <input id="phone" class="input" type="number" placeholder="请输入手机号码" />
      <p class="error-info" id="errorInfo"></p>
    </section>
    <section class="code-box">
      <input id="code" class="input" type="number" placeholder="请输入验证码" style="margin-top: 1.67rem" />
      <button class="get-code" type="button" id="btn">获取验证码</button>
    </section>
    <input id="openId" style="display: none;">
    <button class="btn binding" type="button" id="binding-btn">绑 定</button>
  </form>
  <script>
    window.onload = function () {
      document.getElementById('openId').value = '${WechatMpUser.openId}'
      var phoneEle = document.getElementById('phone')
      var phone = phoneEle.value
      var reg = /^1[2-9]\d{9}$/
      var errorInfo = document.getElementById('errorInfo')
      var btn = document.getElementById('btn')
      var bindBtn = document.getElementById('binding-btn')
      // 页面初始化后禁用按钮
      phoneChange(phone, btn)

      phoneEle.addEventListener('input', function (e) {
        var value = e.target.value
        phone = value
        phoneChange(value, btn)
      }, false)
      btn.addEventListener('click', function () {
        window.location.href = '${springUrl}_wx/get-code?phone=' + phone + '&openId=' + document.getElementById('openId').value
      }, false)

      bindBtn.addEventListener('click', function () {
      $.ajax({
        type: 'post',
        url: '${springUrl}_wx/check',
        data: {phone: phone, code: document.getElementById('code').value},
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code == 0){
            window.location.href = '${springUrl}_wx/has-bind?phone='+phone+'&openId='+document.getElementById('openId').value
          }else{
            var confirm = document.getElementById('confirm')
            confirm.style.display = 'block'
            document.getElementById('msg').innerText = result.msg;
          }
        }
      });
    }, false)

      function phoneChange(phone, btn) {
        if (reg.test(phone)) {
          setErrorInfo('')
          disabledBtn(btn, false)
        } else {
          setErrorInfo('手机号格式不正确')
          disabledBtn(btn, true)
        }
      }
      function setErrorInfo(msg) {
        errorInfo.innerText = msg
      }
      function disabledBtn(btn, flag) {
        var classList = Array.prototype.slice.call(btn.classList)
        if (flag) {
          btn.setAttribute('disabled', 'disabled')
          if (classList.indexOf('active') !== -1) {
            btn.classList.remove('active')
          }
        } else {
          btn.removeAttribute('disabled')
          if (classList.indexOf('active') === -1) {
            btn.classList.add('active')
          }
        }
      }

    }
  </script>
</body>

</html>