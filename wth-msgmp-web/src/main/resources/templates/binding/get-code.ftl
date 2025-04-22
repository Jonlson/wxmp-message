<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <meta http-equiv="X-UA-Compatible" content="IE=edge">
  <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0;">
  <link rel="stylesheet" href="${springUrl}css/style.css">
  <script type="text/javascript" src="${springUrl}js/jquery.min.js" charset="utf-8"></script>
  <title>绑定手机号</title>
</head>
<body>
<form>
  <section>
    <p class="h1-title">请输入验证码</p>
    <p class="sub-title" id="phone"></p>
  </section>
  <input id="code" class="input" type="text" placeholder="请输入验证码" />

  <button class="tip" type="button" id="tip"></button>
  <input id = "openId" style="display: none;">
  <button class="btn get-code" type="button" id="btn">绑定</button>
</form>

<section class="confirm" id="confirm">
  <section class="content">
    <p class="unbind-tip" id="msg"></p>
    <section class="bottom">
      <button class="btn ok" id="ok">确定</button>
    </section>
  </section>
</section>

<script>
  window.onload = function () {
    document.getElementById('openId').value = '${req.openId}';
    var phoneEle = document.getElementById('phone')
    var codeEle = document.getElementById('code')
    var code = ''
    var tipEle = document.getElementById('tip')
    var reg = /\d+/g;
    var search = window.location.search
    var result = search.match(reg)
    var phone = result ? result.shift() : ''
    var btn = document.getElementById('btn')

    // 获取验证码倒计时数字
    var num = 60;
    var intervalId = setInterval(tipChange, 1000)

    phoneTip()
    // 页面初始化后禁用按钮
    disabledBtn(btn, true)

    codeEle.addEventListener('input', function (e) {
      var value = e.target.value
      if (value) {
        disabledBtn(btn, false)
      } else {
        disabledBtn(btn, true)
      }
      code = value
    }, false)

    document.getElementById('ok').addEventListener('click', function () {
      document.getElementById('confirm').style.display = 'none'
    }, false)

    btn.addEventListener('click', function () {
      $.ajax({
        type: 'post',
        url: '${springUrl}wx/check',
        data: {phone: phone, code: document.getElementById('code').value},
        cache: false,
        dataType: "json",
        success: function (result) {
          if(result.code == 0){
            window.location.href = '${springUrl}wx/has-bind?phone='+phone+'&openId='+document.getElementById('openId').value
          }else{
            var confirm = document.getElementById('confirm')
            confirm.style.display = 'block'
            document.getElementById('msg').innerText = result.msg;
          }
        }
      });
    }, false)
    tipEle.addEventListener('click', function () {
      window.location.href = '${springUrl}wx/get-code?phone='+phone+'&openId='+document.getElementById('openId').value
    }, false)

    function phoneTip () {
      phoneEle.innerText = '验证码已发送至' + phone
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

    function tipChange () {
      if (num > 1) {
        num -= 1
        tipEle.innerText = num + '秒后重新获取'
        if (Array.prototype.slice.call(tipEle.classList).indexOf('active') !== -1) {
          tipEle.classList.remove('active')
        }
      } else {
        clearInterval(intervalId)
        tipEle.innerText = '重新获取'
        if (Array.prototype.slice.call(tipEle.classList).indexOf('active') === -1) {
          tipEle.classList.add('active')
        }
      }
    }

  }
</script>
</body>
</html>