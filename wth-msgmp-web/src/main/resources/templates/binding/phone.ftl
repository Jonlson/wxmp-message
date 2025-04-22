<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0">
  <link rel="stylesheet" href="${springUrl}css/style.css">
  <title>绑定手机号</title>
</head>
<body>
  <form>
    <section>
      <p class="h1-title">欢迎使用24H智能售货</p>
      <span class="sub-title">用科技为您开启轻松生活</span>
    </section>
    <input id="phone" class="input" type="number" placeholder="请输入手机号码" />
    <input id = "openId" style="display: none;">
    <p class="error-info" id="errorInfo"></p>
    <button class="btn get-code" type="button" id="btn">获取验证码</button>
  </form>
  <script>
    window.onload = function () {
      document.getElementById('openId').value ='${WechatMpUser.openId}'
      var phoneEle = document.getElementById('phone')
      var phone = phoneEle.value
      var reg = /^1[2-9]\d{9}$/
      var errorInfo = document.getElementById('errorInfo')
      var btn = document.getElementById('btn')
      // 页面初始化后禁用按钮
      phoneChange(phone, btn)

      phoneEle.addEventListener('input', function (e) {
        var value = e.target.value
        phone = value
        phoneChange(value, btn)
      }, false)
      btn.addEventListener('click', function () {
          window.location.href = '${springUrl}wx/get-code?phone='+phone+'&openId='+document.getElementById('openId').value
      }, false)

      function phoneChange (phone, btn) {
        if (reg.test(phone)) {
          setErrorInfo('')
          disabledBtn(btn, false)
        } else {
          if(phone) {
            setErrorInfo('手机号格式不正确')
          }
          disabledBtn(btn, true)
        }
      }
      function setErrorInfo (msg) {
        errorInfo.innerText = msg
      }
      function disabledBtn (btn, flag) {
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